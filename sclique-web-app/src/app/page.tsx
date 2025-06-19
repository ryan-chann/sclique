"use client";
import SearchBar from "@/components/SearchBar";

import { useSearch } from "@/lib/hooks/search";
import { useState } from "react";


export default function Home() {
  const [query, setQuery] = useState("");
const { showModal, events, organisations } = useSearch(query, "eventsOrganisations");

  return (
    <main className="flex flex-col content-center mt-33 gap-10">
      <h1 className="text-center font-mulish text-[62px] font-bold tracking-[0.022] leading-[1.45]">
        Explore <br /> Sunway University
      </h1>
      <search>
        <SearchBar
          corner="rounded"
          containerProps={{ className: "p-2 mx-auto w-[656px]" }}
          modalProps={{ className: "w-[640px] mt-2" }}
          placeholder="Search for Events & Organisations"
          variant="eventsOrganisations"
          query={query}
          onQueryChange={setQuery}
          showModal={showModal}
          events={events}
          organisations={organisations}
        />
      </search>
    </main>
  );
}
