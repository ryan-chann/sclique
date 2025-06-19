import { EventListingProps } from "@/lib/props/eventListing";
import { PageProps } from "@/lib/props/page";
import { API_BASE_URL } from "@/lib/apis/connection";

export async function fetchEvents(
  query: string,
  page: number,
  pageSize: number
): Promise<PageProps<EventListingProps>> {
  const res = await fetch(
    `${API_BASE_URL}/api/v1/event/search/listing?query=${encodeURIComponent(query)}&page=${page}&pageSize=${pageSize}`
  );

  if (!res.ok) {
    throw new Error("Failed to fetch events");
  }

  const data: PageProps<EventListingProps> = await res.json();
  return data;
}
