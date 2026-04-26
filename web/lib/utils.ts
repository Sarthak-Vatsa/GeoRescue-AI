import { Timestamp } from "firebase/firestore";
import { clsx, type ClassValue } from "clsx";
import { twMerge } from "tailwind-merge";

export function formatTime(timestamp: Timestamp | undefined | null): string {
  if (!timestamp) return "--:--:-- UTC";
  
  const date = timestamp.toDate();
  
  const hours = date.getUTCHours().toString().padStart(2, "0");
  const minutes = date.getUTCMinutes().toString().padStart(2, "0");
  const seconds = date.getUTCSeconds().toString().padStart(2, "0");
  
  return `${hours}:${minutes}:${seconds} UTC`;
}

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs));
}
