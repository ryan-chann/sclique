import { ButtonProps } from "@/lib/props/button";
import { buttonVariants } from "@/lib/variants/button";

import { cn } from "@/lib/utils"

export default function Button({
    icon: Icon,
    text,
    size,
    className,
    variantStyle,
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
                    className={cn(text && "mr-2", "w-full h-full")}
                />
            )}
            {text && <span>{text}</span>}
        </button>

    )
}