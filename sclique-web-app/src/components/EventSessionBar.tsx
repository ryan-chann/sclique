import { CalendarDays } from "lucide-react";
import { EventSessionBarProps } from "@/lib/props/eventSessionBar";

export default function EventSessionBar({ dateTimes }: EventSessionBarProps) {
  return (
    <div className="flex flex-row items-center gap-4 max-w-[600px]">
      <CalendarDays size={40} className="shrink-0" />
      <ul className="text-[20px] font-mulish font-medium tracking-[0.014em] leading-[1.64] list-none">
        {dateTimes.map((dateTime, index) => (
          <li key={index}>{dateTime}</li>
        ))}
      </ul>
    </div>
  );
}
