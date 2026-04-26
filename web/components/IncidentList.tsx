"use client";

import { Incident } from "@/types/incident";
import IncidentCard from "./IncidentCard";

interface IncidentListProps {
  incidents: Incident[];
  selectedIncident: Incident | null;
  onSelectIncident: (incident: Incident) => void;
  filter: "all" | "active";
  onFilterChange: (filter: "all" | "active") => void;
  loading: boolean;
  error: string | null;
}

export default function IncidentList({
  incidents,
  selectedIncident,
  onSelectIncident,
  filter,
  onFilterChange,
  loading,
  error,
}: IncidentListProps) {
  const filteredIncidents =
    filter === "active"
      ? incidents.filter((i) => i.status !== "RESOLVED")
      : incidents;

  return (
    <div className="flex flex-col h-full overflow-hidden bg-[#121212] border-r border-[#2C2C2C]">
      <div className="p-4 border-b border-[#2C2C2C] flex justify-between items-center bg-[#131313]">
        <h2 className="font-['Space_Grotesk'] text-xl font-semibold text-[#e5e2e1]">
          Incidents
        </h2>
        <div className="flex bg-[#1A1A1A] p-1 rounded">
          <button
            onClick={() => onFilterChange("all")}
            className={`px-3 py-1 text-xs font-bold uppercase tracking-wider rounded transition-colors ${
              filter === "all"
                ? "bg-[#393939] text-white"
                : "text-gray-500 hover:text-white"
            }`}
          >
            All
          </button>
          <button
            onClick={() => onFilterChange("active")}
            className={`px-3 py-1 text-xs font-bold uppercase tracking-wider rounded transition-colors ${
              filter === "active"
                ? "bg-[#393939] text-white"
                : "text-gray-500 hover:text-white"
            }`}
          >
            Active
          </button>
        </div>
      </div>

      <div className="flex-1 overflow-y-auto p-4 space-y-3">
        {loading && (
          <div className="text-center text-gray-500 py-8">Fetching incidents...</div>
        )}
        
        {!loading && error && (
          <div className="text-center text-red-500 py-8">{error}</div>
        )}
        
        {!loading && !error && filteredIncidents.length === 0 && (
          <div className="text-center text-gray-500 py-8">No active incidents</div>
        )}
        
        {!loading && !error && filteredIncidents.map((incident) => (
          <IncidentCard
            key={incident.id}
            incident={incident}
            isSelected={selectedIncident?.id === incident.id}
            onClick={() => onSelectIncident(incident)}
          />
        ))}
      </div>
    </div>
  );
}
