"use client";


import Button from "@/components/Button";
import { PaginationProps } from "@/lib/props/pagination";
import { ChevronLeft, ChevronRight } from "lucide-react";

export default function Pagination({ page, totalPages, onPageChange }: PaginationProps) {
  const handlePrev = () => {
    if (page > 0) {
      onPageChange(page - 1);
    }
  };

  const handleNext = () => {
    if (page + 1 < totalPages) {
      onPageChange(page + 1);
    }
  };

  return (
    <nav className="flex flex-row items-center">
      <Button
        variantStyle="justText"
        size="medium"
        icon={ChevronLeft}
        onClick={handlePrev}
        className="h-[48px] w-[46px]"
      />

      <div className="flex flex-row gap-2 py-2 px-14 rounded-full bg-[#FFFFFF] z-40 drop-shadow-md font-mulish font-semibold text-[22px] tracking-[0.014] leading-[1.64]">
        <span className="text-[#FB773C]">{String(page + 1).padStart(2, '0')}</span>
        <span>/</span>
        <span>{String(totalPages).padStart(2, '0')}</span>
      </div>

      <Button
        variantStyle="justText"
        size="medium"
        icon={ChevronRight}
        onClick={handleNext}
        className="h-[48px] w-[46px]"
      />
    </nav>
  );
}