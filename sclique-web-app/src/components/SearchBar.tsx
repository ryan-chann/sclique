"use client";

import { useState } from 'react';
import { Search, X } from 'lucide-react';
import Button from '@/components/Button';

export default function SearchBar() {
    const [query, setQuery] = useState('');

    return (
        <div className="w-full max-w-xl mx-auto p-2">
            <form className="flex items-center rounded-2xl bg-white shadow-md px-4 py-2">
                <input
                    type="text"
                    placeholder="Placeholder"
                    value={query}
                    onChange={(e) => setQuery(e.target.value)}
                    className="flex-grow px-4 py-2 text-gray-700 focus:outline-none"
                />

                {query ? (
                    <button
                        type="button"
                        onClick={() => setQuery('')}
                        className="text-gray-700 hover:text-black mx-3 cursor-pointer"
                    >
                        <X size={20} />
                    </button>
                ) : (
                    <div className="w-[40px]" />
                )}

                <Button variantStyle="withFill" size="medium" icon={Search} />
            </form>
        </div>
    );
}