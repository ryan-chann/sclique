import Image from "next/image";
import Button from "@/components/Button";
import { EventListingProps } from "@/lib/props/eventListing";
import { format } from "date-fns";

export default function EventListingCard({
  title,
  venue,
  eventFees,
  eventSessions,
  imageDataBase64,
  mimeType,
}: EventListingProps) {
  const prices = eventFees.map(f => f.price);
  const minPrice = Math.min(...prices);
  const isFree = prices.includes(0.0);
  const priceDisplay = isFree ? "Free" : `From RM ${minPrice.toFixed(2)}`;

  const sortedSessions = [...eventSessions]
    .map(s => new Date(s.session))
    .sort((a, b) => a.getTime() - b.getTime());

  const mainSession = sortedSessions[0];
  const moreSessions = sortedSessions.length > 1 ? sortedSessions.length - 1 : 0;

  const formattedMainSession = mainSession
    ? `[UTC+08] ${format(mainSession, "EEE, yyyy MMM dd. h:mma")}`
    : "No Sessions";

  const sessionDisplay = moreSessions
    ? `${formattedMainSession} (+${moreSessions} more)`
    : formattedMainSession;

  const imageSrc =
    imageDataBase64 && mimeType
      ? `data:${mimeType};base64,${imageDataBase64}`
      : "https://dummyimage.com/1080x1080/000/fff"; // fallback image

  return (
    <article className="flex flex-row gap-[42px]">
      <div className="w-[183px] h-[183px] z-40 drop-shadow-md">
        <Image
          src={imageSrc}
          alt={title}
          width={183}
          height={183}
          unoptimized
          className="object-cover w-full h-full"
        />
      </div>

      <hgroup className="flex flex-col justify-between h-[183px]">
        <div>
          <h2 className="text-[30px] font-mulish font-semibold tracking-[0.014] leading-[1.73]">
            {title}
          </h2>

          <p className="text-[22px] font-mulish font-semibold tracking-[0.014] leading-[1.64]">
            {sessionDisplay}
          </p>

          <p className="text-[16px] font-nunitosans font-light tracking-[0.012] leading-[1.5]">
            {venue}
          </p>

          <p className="text-[12px] font-nunitosans font-extralight tracking-[0.012] leading-[1.5]">
            {priceDisplay}
          </p>
        </div>

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