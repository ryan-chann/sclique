'use client';

import { useState } from 'react';
import { useRouter, useSearchParams } from 'next/navigation';
import { ListFilter } from 'lucide-react';

import SearchBar from '@/components/SearchBar';
import Button from '@/components/Button';
import Pagination from '@/components/Pagination';
import EventListingCard from '@/components/EventListingCard';

import { useSearch } from '@/lib/hooks/search';
import { useEventSearch } from '@/lib/hooks/search';

export default function EventsClient() {
  const router = useRouter();
  const searchParams = useSearchParams();

  const [searchBarQuery, setSearchBarQuery] = useState('');

  const listingQuery = searchParams.get('query') || '';
  const page = parseInt(searchParams.get('page') || '0', 10);
  const pageSize = 5;

  const { showModal, events } = useSearch(searchBarQuery, 'events');
  const { data, loading } = useEventSearch(listingQuery, page, pageSize);

  const handleSearchSubmit = () => {
    if (!searchBarQuery.trim()) return;

    router.push(`/events?query=${encodeURIComponent(searchBarQuery)}&page=0`);
    setSearchBarQuery('');
  };

  const handlePageChange = (newPage: number) => {
    router.push(`/events?query=${encodeURIComponent(listingQuery)}&page=${newPage}`);
  };

  return (
    <main className="mt-[14px]">
      <search className="mt-[14px] flex flex-row gap-[34px]">
        <SearchBar
          query={searchBarQuery}
          onQueryChange={setSearchBarQuery}
          onSearchSubmit={handleSearchSubmit}
          corner="none"
          placeholder="Search for Events"
          containerProps={{ className: 'p-2 w-[462px]' }}
          modalProps={{ className: 'w-[446px] mt-2' }}
          showModal={showModal}
          events={events}
          organisations={[]}
          variant="events"
        />

        <Button
          variantStyle="withoutFill"
          size="large"
          icon={ListFilter}
          text="Filter"
          className="h-[40px] self-center"
        />
      </search>

      {listingQuery && (
        <h1 className="mt-4 ml-2 font-mulish font-semibold text-[36px] tracking-[0.014] leading-[1.72]">
          Search For <span className="text-[#1677FF]">{listingQuery}</span>
        </h1>
      )}

      <section className="mt-6 ml-2 w-full flex ">
        <div className="grid grid-cols-1 gap-y-[64px] w-[1220px]">
          {loading ? (
            <p>Loading...</p>
          ) : data?.content?.length ? (
            <>
              {data.content.map((event, i) => (
                <EventListingCard
                  key={i}
                  id={event.id}
                  title={event.title}
                  venue={event.venue}
                  eventFees={event.eventFees}
                  eventSessions={event.eventSessions}
                  imageDataBase64={event.imageDataBase64}
                  mimeType={event.mimeType}
                />
              ))}

              <div className="mt-8 mb-10 flex justify-end w-[1220px] mx-auto">
                <Pagination
                  page={page}
                  totalPages={data?.totalPages || 1}
                  onPageChange={handlePageChange}
                />
              </div>
            </>
          ) : listingQuery ? (
            <p className="ml-2">No events found.</p>
          ) : null}
        </div>
      </section>
    </main>
  );
}
