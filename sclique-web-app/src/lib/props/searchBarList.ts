export interface SearchBarListProps {
  title: string;
  items: string[];
  onItemClick?: (item: string) => void;
  emptyText: string;
};

export interface SearchResultsProps {
  events: string[];
  organisations: string[];
  variant?: "events" | "organisations" | "eventsOrganisations";
};