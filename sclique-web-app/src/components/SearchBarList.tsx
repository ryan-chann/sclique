import { SearchBarListProps } from '@/lib/props/searchBarList';

export default function SearchBarList({ title, items, onItemClick, emptyText }: SearchBarListProps) {
    return (
        <hgroup className="flex flex-col">
            <h2 className="font-mulish font-semibold text-[18px] tracking-[0.012] leading-[1.78]">{title}</h2>
            <ul className="font-nunitosans font-light text-[14px] tracking-[0.012] leading-[1.57]">
                {items.length > 0 ? (
                    items.map((item, idx) => (
                        <li
                            key={idx}
                            className="h-[38px] flex items-center rounded cursor-pointer hover:bg-black/10 list-none"
                            onClick={() => onItemClick?.(item)}
                        >
                            {item}
                        </li>
                    ))
                ) : (
                    <li className="font-nunitosans font-light text-[14px] tracking-[0.012] leading-[1.57] text-gray-500 list-none">
                        {emptyText}
                    </li>
                )}
            </ul>
        </hgroup>
    );
}