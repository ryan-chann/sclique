import Button from "@/components/Button";
import { ChevronLeft, ChevronRight } from "lucide-react";

export default function OrganisationListingCard() {
    return(
        <nav className="flex flex-row">
            <Button
                variantStyle="justText"
                size="medium"
                icon={ChevronLeft}
                className="h-[48px] w-[46px] self-center"
            />

            <div className="flex flex-row gap-2 py-2 px-14 rounded-full bg-[#FFFFFF] z-40 drop-shadow-md font-[Muli] font-semibold text-[22px] tracking-[0.014] leading-[1.64]">
                <span className="text-[#FB773C]">01</span>
                <span>/</span>
                <span>12</span>
            </div>

            <Button
                variantStyle="justText"
                size="medium"
                icon={ChevronRight}
                className="h-[48px] w-[46px] self-center"
            />
        </nav>
    );
}