import { Incident } from "@/types/incident";
import { formatTime, cn } from "@/lib/utils";

interface IncidentCardProps {
  incident: Incident;
  isSelected: boolean;
  onClick: () => void;
}

const statusColors = {
  CREATED: "bg-red-500",
  ASSIGNED: "bg-orange-500",
  RESPONDING: "bg-orange-700",
  RESOLVED: "bg-green-500",
};

const statusBorderColors = {
  CREATED: "border-l-red-500",
  ASSIGNED: "border-l-orange-500",
  RESPONDING: "border-l-orange-700",
  RESOLVED: "border-l-green-500",
};

export default function IncidentCard({
  incident,
  isSelected,
  onClick,
}: IncidentCardProps) {
  return (
    <div
      onClick={onClick}
      className={cn(
        "cursor-pointer p-4 bg-[#1A1A1A] rounded text-[#e5e2e1] transition-colors border-l-4 border-y border-r border-y-transparent border-r-transparent hover:bg-[#2A2A2A]",
        statusBorderColors[incident.status],
        isSelected ? "bg-[#2A2A2A] border-y-[#2A2A2A] border-r-[#2A2A2A]" : ""
      )}
    >
      <div className="flex justify-between items-start mb-2">
        <span className="font-mono text-sm tracking-wider text-gray-400">
          ID: {incident.id.slice(0, 8)}...
        </span>
        <div
          className={cn(
            "px-2 py-0.5 rounded text-[11px] font-bold uppercase tracking-widest text-black",
            statusColors[incident.status]
          )}
        >
          {incident.status}
        </div>
      </div>

      <div className="grid grid-cols-2 gap-2 text-sm mt-3">
        <div>
          <p className="text-[11px] font-bold uppercase tracking-widest text-gray-500 mb-1">
            Location
          </p>
          <p className="font-mono">
            {typeof incident.location?.lat === "number" &&
              typeof incident.location?.lng === "number"
              ? `${incident.location.lat.toFixed(4)}, ${incident.location.lng.toFixed(4)}`
              : "Invalid location"}
          </p>
        </div>
        <div>
          <p className="text-[11px] font-bold uppercase tracking-widest text-gray-500 mb-1">
            Responder
          </p>
          <p className="truncate">
            {incident.assignedResponderId || "Unassigned"}
          </p>
        </div>
      </div>

      <div className="mt-3 pt-3 border-t border-[#2C2C2C] flex justify-between items-center text-xs text-gray-400">
        <span>Created:</span>
        <span className="font-mono">{formatTime(incident.createdAt)}</span>
      </div>
    </div>
  );
}
