import { OrganisationListingProps } from "@/lib/props/organisationListing";
import { Page } from "@/lib/props/page";

export async function fetchOrganisations(
  query: string,
  page: number,
  pageSize: number
): Promise<Page<OrganisationListingProps>> {
  const res = await fetch(
  `http://localhost:8080/api/v1/organisation/search/listing?query=${encodeURIComponent(query)}&page=${page}&pageSize=${pageSize}`
  );

  if (!res.ok) {
    throw new Error("Failed to fetch organisations");
  }

  const data: Page<OrganisationListingProps> = await res.json();
  return data;
}
