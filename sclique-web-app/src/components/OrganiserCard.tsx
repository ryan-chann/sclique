"use client";

import Image from "next/image";
import { useRouter } from "next/navigation";
import { OrganiserProfileCardProps } from "@/lib/props/organiserProfileCard";
import Button from "./Button";

type Props = OrganiserProfileCardProps & {
  id: string;
};

export default function OrganiserProfileCard({
  id,
  name,
  imageSrc
}: Props) {
  const router = useRouter();

  const handleClick = () => {
    router.push(`/organisations/${id}`);
  };

  return (
    <article className="flex items-center gap-6 bg-white p-4 rounded-md shadow-sm w-full">
      <div className="w-[100px] h-[100px] rounded-full overflow-hidden flex-shrink-0">
        <Image
          src={imageSrc}
          alt={`${name} Logo`}
          width={100}
          height={100}
          className="object-cover w-full h-full"
        />
      </div>

      <Button
        onClick={handleClick}
        variantStyle="justText"
        size="medium"
        text={name}
        className="h-full w-full text-[24px] justify-start text-left"
      />
    </article>
  );
}
