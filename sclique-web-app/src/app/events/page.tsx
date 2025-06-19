import { Suspense } from 'react';
import EventsClient from './EventsClient';

export default function EventsPage() {
  return (
    <Suspense fallback={<p>Loading...</p>}>
      <EventsClient />
    </Suspense>
  );
}
