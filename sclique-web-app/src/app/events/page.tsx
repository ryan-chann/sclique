"use client";

import SearchBar from "@/components/SearchBar";
import Button from "@/components/Button";
import { useSearch } from "@/lib/hooks/search";
import { ListFilter } from "lucide-react";
import { useState } from "react";

export default function Events() {
  const [query, setQuery] = useState("");
  const { showModal, events } = useSearch(query, "events");

  return (
    <main className="mt-[14px]">
        <search className="mt-[14px] flex flex-row gap-[34px]">
            <SearchBar
                query={query}
                onQueryChange={setQuery}
                corner="none"
                placeholder="Search for Events"
                containerProps={{ className: "p-2 w-[462px]" }}
                modalProps={{ className: "w-[446px] mt-2"}}
                showModal={showModal}
                events={events}
                organisations={[]}
                variant="events"
            />

            <Button
                variantStyle="withoutFill"
                size="large"
                icon={ListFilter}
                text="Filter"
                className="h-[40px] self-center"
            />
        </search>

        <h1 className="mt-4 ml-2 font-[Muli] font-semibold text-[36px] tracking-[0.014] leading-[1.72]">Search For <span className="text-[#1677FF]">Halloween</span></h1>

        <section>

        </section>
    </main>
  );
}
