import { useState, useEffect } from "react";

import { searchEvents, searchOrganisations } from "@/lib/apis/search";
import { fetchOrganisations } from "@/lib/apis/organisation";

import { Page } from "@/lib/props/page";
import { OrganisationListingProps } from "@/lib/props/organisationListing";


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

    const delayDebounce = setTimeout(() => {
      setShowModal(true);

      async function fetchData() {
        try {
          if (variant === "events" || variant === "eventsOrganisations") {
            const eventResults = await searchEvents(query);
            setEvents(eventResults);
          }

          if (variant === "organisations" || variant === "eventsOrganisations") {
            const orgResults = await searchOrganisations(query);
            setOrganisations(orgResults);
          }
        } catch (error) {
          console.error("Error fetching search data:", error);
        }
      }

      fetchData();
    }, 300);

    return () => {
      clearTimeout(delayDebounce);
    };
  }, [query, variant]);

  return {
    showModal,
    setShowModal,
    events,
    organisations,
  };
}

export function useOrganisationSearch(
  query: string,
  page: number,
  pageSize: number
) {
  const [data, setData] = useState<Page<OrganisationListingProps> | null>(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const load = async () => {
      try {
        setLoading(true);
        const result = await fetchOrganisations(query, page, pageSize);
        setData(result);
      } catch (err) {
        console.error("Failed to fetch organisations:", err);
        setData(null);
      } finally {
        setLoading(false);
      }
    };

    if (query.length > 0) {
      load();
    }
  }, [query, page, pageSize]);

  return { data, loading };
}
