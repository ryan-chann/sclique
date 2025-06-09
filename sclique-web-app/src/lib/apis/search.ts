export async function searchEvents(query: string) {
  const res = await fetch(
    `http://localhost:8080/api/v1/event/search/titles?page=0&pageSize=5&query=${query}`
  );
  const data = await res.json();
  return data?.content || [];
}

export async function searchOrganisations(query: string) {
  const res = await fetch(
    `http://localhost:8080/api/v1/organisation/search/names?page=0&pageSize=5&query=${query}`
  );
  const data = await res.json();
  return data?.content || [];
}