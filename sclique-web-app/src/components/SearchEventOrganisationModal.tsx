import { SearchResultsProps } from '@/types/search';

export default function SearchEventOrganisationModal({ events, organisations }: SearchResultsProps) {
    return (
        <div className="flex flex-col max-w-[640px] mx-auto p-4 gap-2 bg-[#FFFFFF] shadow-lg border border-[#F0F0F0] rounded-2xl">
            <hgroup className="flex flex-col">
                <h2 className="font-[Muli] font-semibold text-[18px] tracking-[0.012] leading-[1.78]">Events</h2>
                <ul className="font-[Nunito_Sans] font-light text-[14px] tracking-[0.012] leading-[1.57]">
                    {events.length > 0 ? (
                        events.map((event, idx) => (
                            <li
                                key={idx}
                                className="h-[38px] flex items-center rounded cursor-pointer hover:bg-black/10 list-none"
                                onClick={() => console.log('Clicked event:', event)}
                            >
                                {event}
                            </li>
                        ))
                    ) : (
                        <li className="font-[Nunito_Sans] font-light text-[14px] tracking-[0.012] leading-[1.57] text-gray-500 list-none">No events found</li>
                    )}
                </ul>
            </hgroup>

            <hgroup className="flex flex-col">
                <h2 className="font-[Muli] font-semibold text-[18px] tracking-[0.012] leading-[1.78]">Organisations</h2>
                <ul className="font-[Nunito_Sans] font-light text-[14px] tracking-[0.012] leading-[1.57]">
                    {organisations.length > 0 ? (
                        organisations.map((org, idx) => (
                            <li
                                key={idx}
                                className="h-[38px] flex items-center rounded cursor-pointer hover:bg-black/10 list-none"
                                onClick={() => console.log('Clicked organisation:', org)}
                            >
                                {org}
                            </li>
                        ))
                    ) : (
                        <li className="font-[Nunito_Sans] font-light text-[14px] tracking-[0.012] leading-[1.57] text-gray-500 list-none">No organisations found</li>
                    )}
                </ul>
            </hgroup>
        </div>
    );
}