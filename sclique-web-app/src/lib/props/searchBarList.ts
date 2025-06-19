export interface SearchBarListProps {
  title: string;
  items: { id: string; title: string }[];
  onItemClick?: (item: { id: string; title: string }) => void;
  emptyText: string;
}

export interface SearchResultsProps {
  events: { id: string; title: string }[];
  organisations: { id: string; title: string }[];
  variant: "events" | "organisations" | "eventsOrganisations";
}
