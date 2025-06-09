import { SearchResultsProps } from '@/lib/props/searchBarList';
import SearchBarList from './SearchBarList';

export default function SearchEventOrganisationModal({ events, organisations, variant }: SearchResultsProps) {
    return (
        <div className="flex flex-col p-4 gap-2 bg-[#FFFFFF] shadow-lg border border-[#F0F0F0] rounded-2xl">
            {(variant === "events" || variant === "eventsOrganisations") && (
                <SearchBarList
                    title="Events"
                    items={events}
                    onItemClick={(event) => console.log('Clicked event:', event)}
                    emptyText="No events found"
                />
            )}
            {(variant === "organisations" || variant === "eventsOrganisations") && (
                <SearchBarList
                    title="Organisations"
                    items={organisations}
                    onItemClick={(org) => console.log('Clicked organisation:', org)}
                    emptyText="No organisations found"
                />
            )}
        </div>
    );
}