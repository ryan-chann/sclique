"use client";

import { useState } from "react";
import { useRouter, useSearchParams } from "next/navigation";
import { ListFilter } from "lucide-react";

import SearchBar from "@/components/SearchBar";
import Button from "@/components/Button";
import Pagination from "@/components/Pagination";
import OrganisationListingCard from "@/components/OrganisationListingCard";
import { useSearch } from "@/lib/hooks/search";
import { useOrganisationSearch } from "@/lib/hooks/search";

export default function Organisations() {
  const router = useRouter();
  const searchParams = useSearchParams();

  const [searchBarQuery, setSearchBarQuery] = useState("");

  const listingQuery = searchParams.get("query") || "";
  const page = parseInt(searchParams.get("page") || "0", 10);
  const pageSize = 10;

  const { showModal, setShowModal, organisations } = useSearch(searchBarQuery, "organisations");

  const { data, loading } = useOrganisationSearch(listingQuery, page, pageSize);

  const handleSearchSubmit = () => {
    if (!searchBarQuery.trim()) return;

    router.push(`/organisations?query=${encodeURIComponent(searchBarQuery)}&page=0`);

    setSearchBarQuery("");
    setShowModal(false);
  };

  const handlePageChange = (newPage: number) => {
    router.push(`/organisations?query=${encodeURIComponent(listingQuery)}&page=${newPage}`);
  };

  return (
    <main className="mt-[14px]">
      <search className="mt-[14px] flex flex-row gap-[34px]">
        <SearchBar
          query={searchBarQuery}
          onQueryChange={setSearchBarQuery}
          onSearchSubmit={handleSearchSubmit}
          corner="none"
          placeholder="Search Student Organisations"
          containerProps={{ className: "p-2 w-[462px]" }}
          modalProps={{ className: "w-[446px] mt-2" }}
          showModal={showModal}
          events={[]}
          organisations={organisations}
          variant="organisations"
        />

        <Button
          variantStyle="withoutFill"
          size="large"
          icon={ListFilter}
          text="Filter"
          className="h-[40px] self-center"
        />
      </search>

      <section className="w-full flex mt-6">
        <div className="grid grid-cols-2 gap-x-12 gap-y-12 w-[1220px]">
          {loading ? (
            <p>Loading...</p>
          ) : data?.content?.length ? (
            data.content.map((org, i) => (
              <OrganisationListingCard
                key={i}
                name={org.name}
                imageDataBase64={org.imageDataBase64}
                mimeType={org.mimeType}
              />
            ))
          ) : (
            <p>No organisations found.</p>
          )}

          <div className="col-span-2 flex justify-end mt-4">
            <Pagination
              page={page}
              totalPages={data?.totalPages || 1}
              onPageChange={handlePageChange}
            />
          </div>
        </div>
      </section>
    </main>
  );
}
