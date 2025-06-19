import { Suspense } from 'react';
import OrganisationsClient from './OrganisationsClient';

export default function OrganisationsPage() {
  return (
    <Suspense fallback={<p>Loading organisations...</p>}>
      <OrganisationsClient />
    </Suspense>
  );
}
