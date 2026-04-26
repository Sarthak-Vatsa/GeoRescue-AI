"use client";

import { useEffect, useState } from "react";
import { collection, onSnapshot, query } from "firebase/firestore";
import { db } from "@/lib/firebase";
import { Incident } from "@/types/incident";
import MapView from "@/components/MapView";
import IncidentList from "@/components/IncidentList";
import IncidentDetail from "@/components/IncidentDetail";
import { Activity } from "lucide-react";

export default function Dashboard() {
  const [incidents, setIncidents] = useState<Incident[]>([]);
  const [selectedIncident, setSelectedIncident] = useState<Incident | null>(null);
  const [filter, setFilter] = useState<"all" | "active">("active");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const q = query(collection(db, "incidents"));

    const unsubscribe = onSnapshot(
      q,
      (snapshot) => {
        const data: Incident[] = [];
        snapshot.forEach((doc) => {
          const raw = doc.data();
          if (!raw || !raw.location) return;
          data.push({
            id: doc.id,
            status: raw.status,
            location: raw.location,
            assignedResponderId: raw.assignedResponderId || null,
            createdAt: raw.createdAt,
            timeline: raw.timeline || {},
          } as Incident);
        });

        // Sort by created at desc (newest first)
        data.sort((a, b) => {
          const aTime = a.createdAt?.toDate?.().getTime?.() || 0;
          const bTime = b.createdAt?.toDate?.().getTime?.() || 0;
          return bTime - aTime;
        });

        setIncidents(data);
        setLoading(false);
        setError(null);

        // Update selected incident if it exists in new data
        setSelectedIncident(current => {
          if (!current) return null;
          return data.find(i => i.id === current.id) || null;
        });
      },
      (err) => {
        console.error("Firestore error:", err);
        setError("Failed to load incidents");
        setLoading(false);
      }
    );

    return () => unsubscribe();
  }, []);

  return (
    <div className="flex h-screen w-full overflow-hidden bg-black text-[#e5e2e1]">
      {/* Header spanning top */}
      <div className="absolute top-0 left-0 w-full h-14 bg-[#121212] border-b border-[#2C2C2C] flex items-center px-4 z-[1000]">
        <Activity className="text-red-500 mr-3" size={20} />
        <h1 className="font-['Space_Grotesk'] text-xl font-bold tracking-tight">GeoRescue AI</h1>
        <div className="ml-auto flex items-center space-x-4 text-xs font-mono text-gray-500">
          <span className="flex items-center">
            <div className="w-2 h-2 rounded-full bg-green-500 mr-2 animate-pulse"></div>
            LIVE FEED
          </span>
        </div>
      </div>

      {/* Main content below header */}
      <div className="flex w-full h-full pt-14">
        {/* Left/Center: Map */}
        <div className="flex-1 relative z-0">
          <MapView
            incidents={incidents}
            selectedIncident={selectedIncident}
            onSelectIncident={setSelectedIncident}
          />
        </div>

        {/* Right Panel: Incident List & Detail */}
        <div className="w-[400px] flex-shrink-0 flex flex-col bg-[#121212] z-10 border-l border-[#2C2C2C]">
          {selectedIncident ? (
            <div className="h-full">
              <IncidentDetail
                incident={selectedIncident}
                onClose={() => setSelectedIncident(null)}
              />
            </div>
          ) : (
            <IncidentList
              incidents={incidents}
              selectedIncident={selectedIncident}
              onSelectIncident={setSelectedIncident}
              filter={filter}
              onFilterChange={setFilter}
              loading={loading}
              error={error}
            />
          )}
        </div>
      </div>
    </div>
  );
}
