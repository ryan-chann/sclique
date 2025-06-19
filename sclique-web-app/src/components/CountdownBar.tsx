"use client";

import { useEffect, useState } from "react";
import { CountdownBarProps } from "@/lib/props/countdownBar";

export default function CountdownBar({
     targetDate
}: CountdownBarProps) {
  const calculateTimeLeft = () => {
    const difference = new Date(targetDate).getTime() - new Date().getTime();

    if (difference <= 0) {
      return { days: 0, hours: 0, minutes: 0, seconds: 0 };
    }

    return {
      days: Math.floor(difference / (1000 * 60 * 60 * 24)),
      hours: Math.floor((difference / (1000 * 60 * 60)) % 24),
      minutes: Math.floor((difference / (1000 * 60)) % 60),
      seconds: Math.floor((difference / 1000) % 60),
    };
  };

  const [timeLeft, setTimeLeft] = useState(calculateTimeLeft());

  useEffect(() => {
    const timer = setInterval(() => {
      setTimeLeft(calculateTimeLeft());
    }, 1000);

    return () => clearInterval(timer);
  }, [targetDate]);

  return (
    <section
      aria-label="Countdown Timer"
      className="flex justify-center gap-14 py-2"
    >
      {[
        { value: timeLeft.days, label: "Days" },
        { value: timeLeft.hours, label: "Hours" },
        { value: timeLeft.minutes, label: "Minutes" },
        { value: timeLeft.seconds, label: "Seconds" },
      ].map((item, index) => (
        <div key={index} className="text-center">
          <p className="text-[32px] font-semibold">{item.value.toString().padStart(2, "0")}</p>
          <p className="text-[18px] tracking-wide">{item.label}</p>
        </div>
      ))}
    </section>
  );
}
