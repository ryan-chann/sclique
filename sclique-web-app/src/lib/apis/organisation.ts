import { OrganisationListingProps } from "@/lib/props/organisationListing";
import { PageProps } from "@/lib/props/page";
import { API_BASE_URL } from "./connection";

export async function fetchOrganisations(
  query: string,
  page: number,
  pageSize: number
): Promise<PageProps<OrganisationListingProps>> {
  const res = await fetch(
  `${API_BASE_URL}/api/v1/organisation/search/listing?query=${encodeURIComponent(query)}&page=${page}&pageSize=${pageSize}`
  );

  if (!res.ok) {
    throw new Error("Failed to fetch organisations");
  }

  const data: PageProps<OrganisationListingProps> = await res.json();
  return data;
}
