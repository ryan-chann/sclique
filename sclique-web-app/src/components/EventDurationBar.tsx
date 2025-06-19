"use client";

import { ClockFading } from "lucide-react";
import { EventDurationBarProps } from "@/lib/props/eventDurationBar";

export default function EventDurationBar({ durationMinutes }: EventDurationBarProps) {
  const hours = Math.floor(durationMinutes / 60);
  const minutes = durationMinutes % 60;

  const formatted = `${hours > 0 ? `${hours} hour${hours > 1 ? "s" : ""}` : ""}${
    hours && minutes ? " " : ""
  }${minutes > 0 ? `${minutes} minute${minutes > 1 ? "s" : ""}` : ""}`;

  return (
    <article className="flex flex-row gap-6 items-center">
      <div className="p-2">
        <ClockFading size={50} className="shrink-0" />
      </div>
      <p className="text-[22px] font-mulish font-regular tracking-[0.014] leading-[1.64]">
        Each event session lasts{" "}
        <span className="font-semibold">{formatted}</span>
      </p>
    </article>
  );
}
