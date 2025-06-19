"use client";

import { useEffect, useState } from "react";
import { useParams } from "next/navigation";
import CountdownBar from "@/components/CountdownBar";
import EventSessionBar from "@/components/EventSessionBar";
import OrganiserCard from "@/components/OrganiserCard";
import PriceTable from "@/components/EventPriceTable";
import ScanToJoinCard from "@/components/ScanToJoinCard";
import EventDurationBar from "@/components/EventDurationBar";
import { MapPin } from "lucide-react";
import Image from "next/image";
import ReactMarkdown from "react-markdown";
import remarkGfm from "remark-gfm";
import {
  h1MarkdownStyles,
  h2MarkdownStyles,
  h3MarkdownStyles,
  h4MarkdownStyles,
  h5MarkdownStyles,
  pMarkdownStyles,
} from "@/lib/props/markdownComponentStyles";
import { cn } from "@/lib/utils";

type EventProfile = {
  id: string;
  name: string;
  venue: string;
  description: string;
  durationInMinutes: number;
  participationLink: string;
  organiser: {
    id: string;
    name: string;
    organiserImage: {
      mimeType: string;
      imageDataBase64: string;
    };
  };
  eventFees: {
    type: string;
    price: number;
  }[];
  eventSessions: {
    session: string;
  }[];
  eventImage: {
    mimeType: string;
    imageDataBase64: string;
  };
};

export default function Profile() {
  const { id } = useParams();
  const [eventData, setEventData] = useState<EventProfile | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (!id) return;

    fetch(`http://localhost:8080/api/v1/event/profile?eventId=${id}`)
      .then((res) => res.json())
      .then((data) => {
        setEventData(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Failed to fetch event profile:", err);
        setLoading(false);
      });
  }, [id]);

  if (loading) return <p>Loading event profile...</p>;
  if (!eventData) return <p>Event not found.</p>;

  const {
    name,
    venue,
    description,
    durationInMinutes,
    participationLink,
    organiser,
    eventFees,
    eventSessions,
    eventImage,
  } = eventData;

  const eventImageUrl = `data:${eventImage.mimeType};base64,${eventImage.imageDataBase64}`;
  const organiserImageUrl = `data:${organiser.organiserImage.mimeType};base64,${organiser.organiserImage.imageDataBase64}`;
  const sessionTimes = eventSessions.map((s) =>
    new Date(s.session).toLocaleString("en-MY", {
      dateStyle: "medium",
      timeStyle: "short",
    })
  );

  return (
    <main className="my-8">
      <section className="flex flex-row gap-[18px]">
        <div className="flex flex-col gap-2">
          <div className="max-w-[601px] w-[601px] h-[380px]">
            <Image
              src={eventImageUrl}
              alt={name}
              width={601}
              height={380}
              className="object-cover w-[601px] h-[380px]"
            />
          </div>

          <h1 className="text-[36px] font-mulish font-semibold tracking-[0.014] leading-[1.72] max-w-[601px]">
            {name}
          </h1>

          <hgroup className="mt-4">
            <h2 className="text-[30px] font-mulish font-medium tracking-[0.014] leading-[1.73]">
              Organiser
            </h2>
            <OrganiserCard id={organiser.id} name={organiser.name} imageSrc={organiserImageUrl} />
          </hgroup>
        </div>

        <div className="flex flex-col gap-6 max-w-[480px] grow-0 ml-10">
          <CountdownBar targetDate="2025-07-10T09:00:00" />
          <ScanToJoinCard url={participationLink} />
          <EventSessionBar dateTimes={sessionTimes} />

          <div className="flex flex-row items-center gap-4">
            <MapPin size={40} className="shrink-0" />
            <p className="text-[20px] w-[400px] text-wrap font-mulish font-medium tracking-[0.014] leading-[1.64]">
              {venue}
            </p>
          </div>

          <hgroup>
            <h2 className="text-[30px] font-mulish font-medium tracking-[0.014] leading-[1.73]">
              Fees
            </h2>
            <PriceTable
              data={eventFees.map((fee) => ({
                type: fee.type,
                price: `MYR ${fee.price}`,
              }))}
            />
          </hgroup>
        </div>
      </section>

      <hgroup className="flex flex-col mt-10">
        <h2 className="text-[30px] font-mulish font-medium tracking-[0.014] leading-[1.73]">
          About This Event
        </h2>

        <EventDurationBar durationMinutes={durationInMinutes} />

        <article
          className={cn(
            h1MarkdownStyles,
            h2MarkdownStyles,
            h3MarkdownStyles,
            h4MarkdownStyles,
            h5MarkdownStyles,
            pMarkdownStyles,
            "block mt-4 p-4 z-40 drop-shadow-sm bg-white rounded-md max-w-[1200px] w-[1200px]"
          )}
        >
          <ReactMarkdown remarkPlugins={[remarkGfm]}>
            {description}
          </ReactMarkdown>
        </article>
      </hgroup>
    </main>
  );
}
