import { useState, useEffect } from "react";
import { searchEvents, searchOrganisations } from "@/lib/apis/search";

export function useSearch(
  query: string,
  variant: "events" | "organisations" | "eventsOrganisations"
) {
  const [showModal, setShowModal] = useState(false);
  const [events, setEvents] = useState([]);
  const [organisations, setOrganisations] = useState([]);

useEffect(() => {
  if (!query.trim()) {
    setShowModal(false);
    setEvents([]);
    setOrganisations([]);
    return;
  }

  console.log("Search query:", query, "Variant:", variant);

  const delayDebounce = setTimeout(() => {
    setShowModal(true);

    async function fetchData() {
      try {
        if (variant === "events" || variant === "eventsOrganisations") {
          const eventResults = await searchEvents(query);
          console.log("Fetched events:", eventResults);
          setEvents(eventResults);
        }

        if (variant === "organisations" || variant === "eventsOrganisations") {
          const orgResults = await searchOrganisations(query);
          console.log("Fetched organisations:", orgResults);
          setOrganisations(orgResults);
        }
      } catch (error) {
        console.error("Error fetching search data:", error);
      }
    }

    fetchData();
  }, 300);

  return () => {
    console.log("Clearing debounce for:", query);
    clearTimeout(delayDebounce);
  };
}, [query, variant]);

  return { showModal, events, organisations };
}
