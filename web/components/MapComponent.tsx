"use client";

import { useEffect, useState } from "react";
import { MapContainer, TileLayer, Marker, Popup, useMap } from "react-leaflet";
import L from "leaflet";
import "leaflet/dist/leaflet.css";
import { Incident } from "@/types/incident";
import { formatTime } from "@/lib/utils";

// Fix for default marker icons in Leaflet with Next.js
const createCustomIcon = (color: string) => {
  return L.divIcon({
    className: "custom-leaflet-marker",
    html: `<div style="background-color: ${color}; width: 16px; height: 16px; border-radius: 50%; border: 2px solid white; box-shadow: 0 0 4px rgba(0,0,0,0.5);"></div>`,
    iconSize: [16, 16],
    iconAnchor: [8, 8],
  });
};

const icons = {
  CREATED: createCustomIcon("#ef4444"), // red-500
  ASSIGNED: createCustomIcon("#f97316"), // orange-500
  RESPONDING: createCustomIcon("#c2410c"), // orange-700
  RESOLVED: createCustomIcon("#22c55e"), // green-500
};

function MapUpdater({ selectedIncident }: { selectedIncident: Incident | null }) {
  const map = useMap();

  useEffect(() => {
    if (selectedIncident) {
      map.setView(
        [selectedIncident.location.lat, selectedIncident.location.lng],
        12,
        { animate: true }
      );
    } else {
      // reset to India view
      map.setView([22.5937, 78.9629], 5, { animate: true });
    }
  }, [selectedIncident, map]);


  return null;
}

interface MapComponentProps {
  incidents: Incident[];
  selectedIncident: Incident | null;
  onSelectIncident: (incident: Incident) => void;
}

export default function MapComponent({
  incidents,
  selectedIncident,
  onSelectIncident,
}: MapComponentProps) {
  // Center of India
  const center: [number, number] = [22.5937, 78.9629];
  const zoom = 5;

  return (
    <div className="w-full h-full bg-[#131313]">
      <MapContainer
        center={center}
        zoom={zoom}
        style={{ height: "100%", width: "100%", background: "#131313" }}
        zoomControl={false}
      >
        <TileLayer
          attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
          url="https://{s}.basemaps.cartocdn.com/dark_all/{z}/{x}/{y}{r}.png"
        />
        <MapUpdater selectedIncident={selectedIncident} />

        {incidents.filter((incident) =>
          typeof incident.location?.lat === "number" &&
          typeof incident.location?.lng === "number")
          .map((incident) => (
            <Marker
              key={incident.id}
              position={[incident.location.lat, incident.location.lng]}
              icon={selectedIncident?.id === incident.id ? createCustomIcon("#ffffff") // highlight (white)
                : icons[incident.status]
              }
              eventHandlers={{
                click: () => onSelectIncident(incident),
              }}
            >
              <Popup className="custom-popup">
                <div className="font-['Inter']">
                  <div className="font-bold text-sm mb-1">{incident.id}</div>
                  <div className="text-xs text-gray-500">{incident.status}</div>
                  <div className="text-xs text-gray-400 mt-1">{incident.createdAt && typeof incident.createdAt.toDate === "function"
                    ? formatTime(incident.createdAt)
                    : "N/A"}</div>
                </div>
              </Popup>
            </Marker>
          ))}
      </MapContainer>
    </div>
  );
}
