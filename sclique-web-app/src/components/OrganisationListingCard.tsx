import Button from "@/components/Button";
import Image from "next/image";
import { OrganisationListingProps } from "@/lib/props/organisationListing";

export default function OrganisationListingCard({ name, imageDataBase64, mimeType }: OrganisationListingProps) {
  const imageSrc =
    imageDataBase64 && mimeType
      ? `data:${mimeType};base64,${imageDataBase64}`
      : null;

  return (
    <article className="z-40 drop-shadow-md flex flex-row max-w-[601px] w-[601px] h-[160px] bg-[#FFFFFF] border border-[#BFBFBF] rounded-sm overflow-hidden">
      <div className="w-[160px] h-[160px] bg-[#F0F0F0] flex items-center justify-center">
        {imageSrc ? (
          <Image
            src={imageSrc}
            alt={`${name} Logo`}
            width={160}
            height={160}
            unoptimized
            className="object-cover w-[160px] h-[160px]"
          />
        ) : null}
      </div>
      <hgroup className="flex flex-col justify-between py-2 pl-4 pr-2 h-full">
        <h2 className="text-[22px] font-mulish font-semibold tracking-[0.014] leading-[1.64]">
          {name}
        </h2>
        <Button
          variantStyle="withoutFill"
          size="medium"
          text="View"
          className="h-[30px] w-[80px]"
        />
      </hgroup>
    </article>
  );
}
