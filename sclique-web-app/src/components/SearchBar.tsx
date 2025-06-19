"use client";

import { Search, X } from "lucide-react";
import React from "react";

import Button from "@/components/Button";
import { cn } from "@/lib/utils";
import { searchBarFormVariants } from "@/lib/variants/searchBar";
import { SearchBarProps } from "@/lib/props/searchBar";
import SearchEventOrganisationModal from "./SearchEventOrganisationModal";

type StructuredItem = { id: string; title: string };

export default function SearchBar({
  corner,
  containerRef,
  formRef,
  containerProps,
  formProps,
  modalProps,
  placeholder,
  variant = "eventsOrganisations",
  query,
  onQueryChange,
  showModal,
  events,
  organisations,
  onSearchSubmit,
}: SearchBarProps & {
  query: string;
  onQueryChange: (q: string) => void;
  showModal: boolean;
  events: StructuredItem[];
  organisations: StructuredItem[];
}) {
  return (
    <div
      ref={containerRef}
      className={cn("relative", containerProps?.className)}
      {...containerProps}
    >
      <form
        ref={formRef}
        onSubmit={(e) => {
          e.preventDefault();
          onSearchSubmit?.();
        }}
        className={cn(searchBarFormVariants({ corner }), formProps?.className)}
        {...formProps}
      >
        <input
          type="text"
          placeholder={placeholder}
          value={query}
          onChange={(e) => onQueryChange(e.target.value)}
          className="flex-grow px-4 py-2 text-gray-700 focus:outline-none"
        />

        {query ? (
          <button
            type="button"
            onClick={() => onQueryChange("")}
            className="text-gray-700 hover:text-black mx-3 cursor-pointer"
          >
            <X size={20} />
          </button>
        ) : (
          <div className="w-[40px]" />
        )}

        <Button variantStyle="withFill" size="medium" icon={Search} />
      </form>

      {showModal && (
        <div className={cn("absolute z-50", modalProps?.className)}>
          <SearchEventOrganisationModal
            events={events}
            organisations={organisations}
            variant={variant}
          />
        </div>
      )}
    </div>
  );
}
