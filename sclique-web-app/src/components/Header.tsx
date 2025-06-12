'use client';

import { usePathname } from 'next/navigation';
import Link from 'next/link';
import Button from '@/components/Button';

function Header() {
    const pathname = usePathname();

    const isEvents = pathname === '/events';
    const isOrganisations = pathname === '/organisations';

    const activeStyle = 'text-black hover:text-black cursor-default pointer-events-none';

    return (
        <header>
            <nav className="flex flex-row gap-2">
                <Link href="/events">
                    <Button
                        variantStyle="justText"
                        size="xlarge"
                        text="Events"
                        className={isEvents ? activeStyle : ""}
                    />
                </Link>

                <Link href="/organisations">
                    <Button
                        variantStyle="justText"
                        size="xlarge"
                        text="Organisations"
                        className={isOrganisations ? activeStyle : ""}
                    />
                </Link>
            </nav>
        </header>
    );
}

export default Header;
