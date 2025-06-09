import { cva } from "class-variance-authority";

export const searchBarFormVariants = cva(
    "flex items-center bg-white shadow-md px-4 py-2",
    {
        variants: {
            corner: {
                rounded: "rounded-2xl",
                none: "",
            },
        },
        defaultVariants: {
            corner: "rounded"
        }
    }
);