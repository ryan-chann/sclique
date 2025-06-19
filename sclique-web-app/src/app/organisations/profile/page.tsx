"use client"

import Image from "next/image";
import { OrganizationChart } from "primereact/organizationchart";
import React, { useMemo } from 'react';
import {buildTree} from "@/lib/utils"
import CommitteeMemberCardTreeNode from "@/components/CommitteeMemberCardTreeNode";
import ReactMarkdown from 'react-markdown';
import { cn } from "@/lib/utils"
import {h1MarkdownStyles, h2MarkdownStyles, h3MarkdownStyles, h4MarkdownStyles, h5MarkdownStyles, pMarkdownStyles} from "@/lib/props/markdownComponentStyles";
import remarkGfm from "remark-gfm";

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

const members = [
  {
    id: 1,
    name: "John Smith",
    position: "President",
    email: "john.smith@example.com",
    phone: "012-345-6789",
    image: "https://dummyimage.com/100x100/000/fff",
    managerId: null,
  },
  {
    id: 2,
    name: "Alice Tan",
    position: "Vice President",
    email: "alice.tan@example.com",
    phone: "012-111-2222",
    image: "https://dummyimage.com/100x100/111/fff",
    managerId: 1,
  },
  {
    id: 3,
    name: "Ben Lee",
    position: "Secretary",
    email: "ben.lee@example.com",
    phone: "012-333-4444",
    image: "https://dummyimage.com/100x100/222/fff",
    managerId: 1,
  },
  {
    id: 4,
    name: "Diana Wong",
    position: "Treasurer",
    email: "diana.wong@example.com",
    phone: "012-555-6666",
    image: "https://dummyimage.com/100x100/333/fff",
    managerId: 2,
  },
  {
    id: 5,
    name: "Ethan Lim",
    position: "Tech Lead",
    email: "ethan.lim@example.com",
    phone: "012-777-8888",
    image: "https://dummyimage.com/100x100/444/fff",
    managerId: 2,
  },
  {
    id: 6,
    name: "Fiona Chan",
    position: "Marketing Head",
    email: "fiona.chan@example.com",
    phone: "012-999-0000",
    image: "https://dummyimage.com/100x100/555/fff",
    managerId: 3,
  },
];

export default function Profile() {

const data = useMemo(() => buildTree(members), []);

  return (
    <main className="flex flex-col mb-5">
      <section className="relative w-full h-[300px]">
        <Image
          src="https://dummyimage.com/1080x300/000/fff"
          alt="Cover"
          layout="fill"
          objectFit="cover"
          className="w-full h-full object-cover"
        />
        <div className="absolute bottom-[-90px] left-1/2 transform -translate-x-1/2">
          <Image
            src="https://dummyimage.com/150x150/333/fff"
            alt="Profile"
            width={200}
            height={200}
            className="rounded-full border-4 border-white shadow-lg"
          />
        </div>
      </section>

      <div className="h-[100px]" />

      <h1 className="text-[36px] font-mulish font-semibold tracking-[0.014] leading-[1.72] max-w-[601px] text-center self-center">
        Sunway Tech Club
      </h1>

      <hgroup className="mt-6 px-4">
        <h2 className="text-[30px] font-mulish font-semibold tracking-[0.014] leading-[1.73]">About Sunway Tech Club</h2>
        <article className={cn(
          h1MarkdownStyles,
          h2MarkdownStyles,
          h3MarkdownStyles,
          h4MarkdownStyles,
          h5MarkdownStyles,
          pMarkdownStyles,
          "block mt-4 p-4 z-40 drop-shadow-sm bg-white rounded-md w-full")}>
          <ReactMarkdown remarkPlugins={[remarkGfm]}>
            {markdown}
          </ReactMarkdown>
        </article>
      </hgroup>

      <hgroup className="mt-10 px-4">
        <h2 className="text-[30px] font-mulish font-semibold tracking-[0.014] leading-[1.73] mb-2">Board of Director</h2>
        <div className="max-w-full overflow-auto border rounded-md p-4 shadow-inner" style={{ maxHeight: '600px' }}>
            <OrganizationChart
                value={data}
                nodeTemplate={CommitteeMemberCardTreeNode}
            />
        </div>
      </hgroup>
    </main>
  );
}
