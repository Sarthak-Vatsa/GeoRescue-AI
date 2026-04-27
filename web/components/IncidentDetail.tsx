"use client";

import { Incident, IncidentStatus } from "@/types/incident";
import { formatTime, cn } from "@/lib/utils";
import { X } from "lucide-react";

interface IncidentDetailProps {
  incident: Incident | null;
  onClose: () => void;
}

const LIFECYCLE: IncidentStatus[] = ["CREATED", "ASSIGNED", "RESPONDING", "RESOLVED"];

const statusColors = {
  CREATED: "text-red-500 border-red-500",
  ASSIGNED: "text-orange-500 border-orange-500",
  RESPONDING: "text-orange-700 border-orange-700",
  RESOLVED: "text-green-500 border-green-500",
};

const statusBgColors = {
  CREATED: "bg-red-500",
  ASSIGNED: "bg-orange-500",
  RESPONDING: "bg-orange-700",
  RESOLVED: "bg-green-500",
};

export default function IncidentDetail({ incident, onClose }: IncidentDetailProps) {
  if (!incident) return null;

  const currentStatusIndex = LIFECYCLE.indexOf(incident.status);

  const getTimestampForStatus = (status: IncidentStatus) => {
    switch (status) {
      case "CREATED": return incident.createdAt;
      case "ASSIGNED": return incident.timeline.assignedAt;
      case "RESPONDING": return incident.timeline.respondedAt;
      case "RESOLVED": return incident.timeline.resolvedAt;
    }
  };

  return (
    <div className="flex flex-col h-full overflow-hidden bg-[#121212]">
      <div className="p-4 border-b border-[#2C2C2C] flex justify-between items-center bg-[#131313]">
        <h2 className="font-['Space_Grotesk'] text-xl font-semibold text-[#e5e2e1]">
          Details
        </h2>
        <button 
          onClick={onClose}
          className="p-1 text-gray-500 hover:text-white transition-colors"
        >
          <X size={20} />
        </button>
      </div>

      <div className="flex-1 overflow-y-auto p-6 text-[#e5e2e1]">
        <div className="mb-8">
          <div className="flex items-center space-x-3 mb-2">
            <span className={cn("px-2 py-0.5 rounded text-[11px] font-bold uppercase tracking-widest text-black", statusBgColors[incident.status])}>
              {incident.status}
            </span>
            <span className="font-mono text-sm tracking-wider text-gray-400">
              {incident.id}
            </span>
          </div>
        </div>

        <h3 className="text-[11px] font-bold uppercase tracking-widest text-gray-500 mb-4">Lifecycle</h3>
        <div className="space-y-0 relative before:absolute before:inset-0 before:ml-2 before:-translate-x-px md:before:mx-auto md:before:translate-x-0 before:h-full before:w-0.5 before:bg-gradient-to-b before:from-transparent before:via-[#2C2C2C] before:to-transparent">
          {LIFECYCLE.map((status, index) => {
            const isCompleted = index <= currentStatusIndex;
            const isCurrent = index === currentStatusIndex;
            const timestamp = getTimestampForStatus(status);
            
            return (
              <div key={status} className="relative flex items-center justify-between md:justify-normal md:odd:flex-row-reverse group is-active pb-6">
                <div className={cn(
                  "flex items-center justify-center w-5 h-5 rounded-full border-2 bg-[#121212] z-10",
                  isCompleted ? statusColors[status] : "border-[#2C2C2C] text-transparent"
                )}>
                  {isCompleted && <div className={cn("w-2 h-2 rounded-full", statusBgColors[status])} />}
                </div>
                
                <div className={cn(
                  "w-[calc(100%-2rem)] md:w-[calc(50%-2rem)] p-3 rounded",
                  isCurrent ? "bg-[#1A1A1A] border border-[#2C2C2C]" : ""
                )}>
                  <div className="flex justify-between items-center">
                    <h4 className={cn(
                      "text-sm font-bold uppercase tracking-widest",
                      isCompleted ? "text-gray-200" : "text-gray-600"
                    )}>
                      {status}
                    </h4>
                  </div>
                  {timestamp && (
                    <div className="font-mono text-xs text-gray-400 mt-1">
                      {formatTime(timestamp)}
                    </div>
                  )}
                </div>
              </div>
            );
          })}
        </div>
        
        <div className="mt-8 pt-6 border-t border-[#2C2C2C]">
           <h3 className="text-[11px] font-bold uppercase tracking-widest text-gray-500 mb-4">Metadata</h3>
           <div className="space-y-4">
             <div>
               <p className="text-xs text-gray-500 mb-1">Coordinates</p>
               <p className="font-mono text-sm">{incident.location.lat.toFixed(6)}, {incident.location.lng.toFixed(6)}</p>
             </div>
             <div>
               <p className="text-xs text-gray-500 mb-1">Assigned Responder</p>
               <p className="text-sm">{incident.assignedResponderId || "None"}</p>
             </div>
           </div>
        </div>
      </div>
    </div>
  );
}
