import { cva } from "class-variance-authority";

const iconSizes = {
  small: "w-[14px] h-[14px]",
  medium: "w-[16px] h-[16px]",
  large: "w-[20px] h-[20px]",
  xlarge: "w-[26px] h-[26px]",
  default: "w-[16px] h-[16px]",
};
type IconSizeKey = keyof typeof iconSizes;

export const buttonVariants = cva(
  "flex flex-row justify-center items-center disabled:opacity-56 font-[Nunito_Sans] font-bold tracking-[0.015] cursor-pointer",
  {
    variants: {
      variantStyle: {
        withoutFill:
          "border border-solid rounded-sm border-[#FB773C] text-[#FB773C] hover:text-[#D45928] hover:border-[#D45928]",
        withFill: "rounded-sm bg-[#FB773C] text-white hover:bg-[#D45928]",
        justText: "border-none text-[#0044B3] hover:text-[#595959]",
      },
      size: {
        small: "px-1 py-[2px] text-[12px] leading-[1.67] h-6",
        medium: "px-2 py-1 text-[14px] leading-[1.57] h-[30px]",
        large: "px-4 py-2 text-[16px] leading-[1.5] h-10",
        xlarge: "px-8 py-[12px] text-[26px] leading-[1.38] h-15",
      },
    },
    defaultVariants: {
      size: "medium",
      variantStyle: "withoutFill",
    },
  }
);

export function getIconSize(size: IconSizeKey | null | undefined): string {
  return iconSizes[size ?? "default"];
}
