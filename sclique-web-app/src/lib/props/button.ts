import { VariantProps } from "class-variance-authority";
import { LucideIcon } from "lucide-react";
import { buttonVariants } from "@/lib/variants/button";

export interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement>, VariantProps<typeof buttonVariants> {
  icon?: LucideIcon;
  text?: string;
  buttonRef?: React.Ref<HTMLButtonElement>;
}
