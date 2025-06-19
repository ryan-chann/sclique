import CountdownBar from "@/components/CountdownBar";
import EventSessionBar from "@/components/EventSessionBar";
import OrganiserCard from "@/components/OrganiserCard";
import PriceTable from "@/components/EventPriceTable";
import ScanToJoinCard from "@/components/ScanToJoinCard";
import EventDurationBar from "@/components/EventDurationBar";
import { MapPin } from 'lucide-react';
import Image from "next/image";
import ReactMarkdown from 'react-markdown';
import remarkGfm from "remark-gfm";
import {h1MarkdownStyles, h2MarkdownStyles, h3MarkdownStyles, h4MarkdownStyles, h5MarkdownStyles, pMarkdownStyles} from "@/lib/props/markdownComponentStyles";
import { cn } from "@/lib/utils"

export default function Profile() {
  const markdown = `## 🏃 About the Kangar City Duathlon 2025

Join us for the ultimate test of endurance, strength, and community spirit at the **Kangar City Duathlon 2025**! Whether you're a seasoned athlete or a first-timer, this event offers something for everyone.

---

### 📍 Event Highlights

- **Date:** 26 - 27 April 2025
- **Time:** 7:00 AM onwards
- **Location:** JC1, Sunway University
- **Organiser:** Sunway Fitness Club

---

### 🔥 Race Categories

1. **Men / Womens Open** - For individual athletes
2. **Team Relay** - Perfect for friends and colleagues
3. **Others** - Mixed and special categories

---

### 🎁 What’s Included

- Official race T-shirt
- Finisher medal
- Timing chip & bib
- Refreshments and support stations

---

### 📌 Notes

- Registration closes on **10 July 2025**
- Participants must be 18 years and older
- Bring your own bike (helmet required)

---

Get ready to challenge yourself and make unforgettable memories. **See you at the starting line!**`;

  return(
    <main className="my-8">
      <section className="flex flex-row gap-[18px]">
        <div className="flex flex-col gap-2">
          <div className="max-w-[601px] w-[601px] h-[380px]">
            <Image
              src="https://dummyimage.com/1080x1080/000/fff"
              alt="test"
              width={601}
              height={380}
              className="object-cover w-[601px] h-[380px]"
            />
          </div>

          <h1 className="text-[36px] font-mulish font-semibold tracking-[0.014] leading-[1.72] max-w-[601px]">KANGAR CITY DUATHLON 2025</h1>

          <hgroup className="mt-4">
            <h2 className="text-[30px] font-mulish font-medium tracking-[0.014] leading-[1.73]">Organiser</h2>
            <OrganiserCard name="Sunway Fitness Club" imageSrc="https://dummyimage.com/1080x1080/000/fff"/>
          </hgroup>
        </div>

        <div className="flex flex-col gap-6 max-w-[480px] grow-0 ml-10">
          <CountdownBar targetDate="2025-07-10T09:00:00"/>

          <ScanToJoinCard url="https://www.google.com"/>

          <EventSessionBar dateTimes={["26 Apr 2025, 7.00AM", "27 Apr 2025, 8.00AM"]} />

          <div className="flex flex-row items-center gap-4">
            <MapPin size={40} className="shrink-0"/>
            <p className="text-[20px] w-[400px] text-wrap font-mulish font-medium tracking-[0.014] leading-[1.64]">JC1, Sunway University</p>
          </div>

          <hgroup>
            <h2 className="text-[30px] font-mulish font-medium tracking-[0.014] leading-[1.73]">Fees</h2>

            <PriceTable
              data={[
                { type: "Men / Womens Open", price: "MYR 210" },
                { type: "Team Relay", price: "MYR 315" },
                { type: "Others", price: "MYR 400" },
              ]}
            />
          </hgroup>
        </div>
      </section>

      <hgroup className="flex flex-col mt-10">
        <h2 className="text-[30px] font-mulish font-medium tracking-[0.014] leading-[1.73]">About This Event</h2>

        <EventDurationBar durationMinutes={150} />

        <article className={cn(
          h1MarkdownStyles,
          h2MarkdownStyles,
          h3MarkdownStyles,
          h4MarkdownStyles,
          h5MarkdownStyles,
          pMarkdownStyles,
          "block mt-4 p-4 z-40 drop-shadow-sm bg-white rounded-md max-w-[1200px] w-[1200px]")}>
          <ReactMarkdown remarkPlugins={[remarkGfm]}>
            {markdown}
          </ReactMarkdown>
        </article>
      </hgroup>
    </main>
  );
}