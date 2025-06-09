import { VariantProps } from "class-variance-authority";
import {
  searchBarFormVariants
} from "@/lib/variants/searchBar";

type FormVariants = VariantProps<typeof searchBarFormVariants>;

export interface SearchBarProps extends FormVariants {
  corner?: FormVariants["corner"];
  placeholder?: string;
  containerRef?: React.Ref<HTMLDivElement>;
  containerProps?: React.HTMLAttributes<HTMLDivElement>;
  formRef?: React.Ref<HTMLFormElement>;
  formProps?: React.FormHTMLAttributes<HTMLFormElement>;
  modalProps?: React.HTMLAttributes<HTMLDivElement>;
  variant?: "events" | "organisations" | "eventsOrganisations";
}
