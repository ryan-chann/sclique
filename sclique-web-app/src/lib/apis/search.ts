import { API_BASE_URL } from "./connection";

export async function searchEvents(query: string) {
  const res = await fetch(
    `${API_BASE_URL}/api/v1/event/search/titles?page=0&pageSize=5&query=${query}`
  );
  const data = await res.json();
  return data?.content || [];
}

export async function searchOrganisations(query: string) {
  const res = await fetch(
    `${API_BASE_URL}/api/v1/organisation/search/names?page=0&pageSize=5&query=${query}`
  );
  const data = await res.json();
  return data?.content || [];
}