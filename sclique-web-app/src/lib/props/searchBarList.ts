export type SearchBarListProps = {
  title: string;
  items: string[];
  onItemClick?: (item: string) => void;
  emptyText: string;
};

export type SearchResultsProps = {
  events: string[];
  organisations: string[];
  variant?: "events" | "organisations" | "eventsOrganisations";
};