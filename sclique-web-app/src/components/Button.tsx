import { cva, VariantProps } from "class-variance-authority";
import { LucideIcon } from "lucide-react";

import { cn } from "@/lib/utils"


const buttonVariants = cva(
    "flex flex-row justify-center items-center disabled:opacity-56 font-[Nunito_Sans] font-bold tracking-[0.015] cursor-pointer",
    {
        variants: {
            variantStyle: {
                withoutFill: "border border-solid rounded-sm border-[#FB773C] text-[#FB773C] hover:text-[#D45928] hover:border-[#D45928]",
                withFill: "rounded-sm bg-[#FB773C] text-white hover:bg-[#D45928]",
                justText: "border-none text-[#0044B3] hover:text-[#595959]"

            },
            size: {
                small: "px-1 py-[2px] text-[12px] leading-[1.67]",
                medium: "px-2 py-1 text-[14px] leading-[1.57]",
                large: "px-4 py-2 text-[16px] leading-[1.5]",
                xlarge: "px-8 py-[12px] text-[26px] leading-[1.38]",
            },
        },
        defaultVariants: {
            size: "medium",
            variantStyle: "withoutFill",
        }
    }
);

export interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement>, VariantProps<typeof buttonVariants> {
    icon?: LucideIcon;
    text?: string;
    buttonRef?: React.Ref<HTMLButtonElement>;
}

function getIconSize(size?: ButtonProps["size"]): string {
    switch (size) {
        case "small":
            return "w-[14px] h-[14px]";
        case "medium":
            return "w-[16px] h-[16px]";
        case "large":
            return "w-[20px] h-[20px]";
        case "xlarge":
            return "w-[26px] h-[26px]";
        default:
            return "w-[16px] h-[16px]";
    }
}

export default function Button({
    icon: Icon,
    text,
    className,
    variantStyle,
    size,
    buttonRef,
    ...props
}: ButtonProps) {
    return (
        <button
            ref={buttonRef}
            className={cn(buttonVariants({ variantStyle, size }), className)}
            {...props}
        >
            {Icon && (
                <Icon
                    className={cn(text && "mr-2", getIconSize(size))}
                />
            )}
            {text && <span>{text}</span>}
        </button>

    )
}