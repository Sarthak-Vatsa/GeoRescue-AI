"use client";

import dynamic from "next/dynamic";
import { Incident } from "@/types/incident";

// Dynamically import the map component with SSR disabled
const MapComponent = dynamic(() => import("./MapComponent"), {
  ssr: false,
  loading: () => (
    <div className="w-full h-full flex items-center justify-center bg-[#131313] text-gray-500 font-mono text-sm">
      Loading map...
    </div>
  ),
});

interface MapViewProps {
  incidents: Incident[];
  selectedIncident: Incident | null;
  onSelectIncident: (incident: Incident) => void;
}

export default function MapView(props: MapViewProps) {
  return <MapComponent {...props} />;
}
