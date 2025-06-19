"use client"

import Image from "next/image";
import { OrganizationChart } from "primereact/organizationchart";
import React, { useMemo, useEffect, useState } from 'react';
import {buildTree} from "@/lib/utils"
import CommitteeMemberCardTreeNode from "@/components/CommitteeMemberCardTreeNode";
import ReactMarkdown from 'react-markdown';
import { cn } from "@/lib/utils"
import {h1MarkdownStyles, h2MarkdownStyles, h3MarkdownStyles, h4MarkdownStyles, h5MarkdownStyles, pMarkdownStyles} from "@/lib/props/markdownComponentStyles";
import remarkGfm from "remark-gfm";
import { useParams } from 'next/navigation'


type ImageDto = {
  mimeType: string;
  imageDataBase64: string;
};

type CommitteeMemberDto = {
  memberId: number;
  name: string;
  position: string;
  email: string;
  phoneNumber: string;
  managerId: number | null;
  avatar: ImageDto | null;
  image: ImageDto | null;
};

type OrganisationProfile = {
  organisationId: string;
  name: string;
  description: string;
  coverImage: ImageDto | null;
  profileImage: ImageDto | null;
  committeeMembers: CommitteeMemberDto[];
};

export default function OrganisationProfilePage() {
  const { id } = useParams();
  const [profile, setProfile] = useState<OrganisationProfile | null>(null);

useEffect(() => {
  const fetchProfile = async () => {
    if (typeof id !== "string") return;

    try {
      const res = await fetch(
        `http://localhost:8080/api/v1/organisation/profile?organisationId=${id}`,
        {
          headers: {
            Accept: "*/*",
          },
          cache: "no-store",
        }
      );

      if (!res.ok) {
        console.error("Failed to fetch profile", res.statusText);
        return;
      }

      const json = await res.json();

      if (json && json.organisationId) {
        setProfile(json);
      } else {
        console.warn("No valid organisation data", json);
      }
    } catch (err) {
      console.error("Fetch error:", err);
    }
  };

  fetchProfile();
}, [id]);

const treeData = useMemo(() => {
  if (!profile?.committeeMembers) return [];

  const mapped = profile.committeeMembers.map((m) => ({
    id: m.memberId,
    name: m.name,
    position: m.position,
    email: m.email,
    phone: m.phoneNumber,
    image: m.image
      ? `data:${m.image.mimeType};base64,${m.image.imageDataBase64}`
      : "https://dummyimage.com/160x160/000/fff",
    managerId: m.managerId,
  }));

  return buildTree(mapped);
}, [profile]);

  if (!profile) return <div className="p-10 text-center">Loading...</div>;

  const toImageSrc = (image?: ImageDto | null) =>
    image ? `data:${image.mimeType};base64,${image.imageDataBase64}` : "";

  return (
    <main className="flex flex-col mb-5">
      <section className="relative w-full h-[300px]">
        <Image
          src={toImageSrc(profile.coverImage) || "/fallback-cover.jpg"}
          alt="Cover"
          layout="fill"
          objectFit="cover"
          className="w-full h-full object-cover"
        />

        <div className="absolute bottom-[-90px] left-1/2 transform -translate-x-1/2">
          <Image
            src={toImageSrc(profile.profileImage) || "/fallback-profile.jpg"}
            alt="Profile"
            width={200}
            height={200}
            className="rounded-full border-4 border-white shadow-lg"
          />
        </div>
      </section>

      <div className="h-[100px]" />

      <h1 className="text-[36px] font-mulish font-semibold tracking-[0.014] leading-[1.72] max-w-[601px] text-center self-center">
        {profile.name}
      </h1>

      <hgroup className="mt-6 px-4">
        <h2 className="text-[30px] font-mulish font-semibold tracking-[0.014] leading-[1.73]">
          About {profile.name}
        </h2>
        <article
          className={cn(
            h1MarkdownStyles,
            h2MarkdownStyles,
            h3MarkdownStyles,
            h4MarkdownStyles,
            h5MarkdownStyles,
            pMarkdownStyles,
            "block mt-4 p-4 z-40 drop-shadow-sm bg-white rounded-md w-full"
          )}
        >
          <ReactMarkdown remarkPlugins={[remarkGfm]}>
            {profile.description || "*No description provided.*"}
          </ReactMarkdown>
        </article>
      </hgroup>

      <hgroup className="mt-10 px-4">
        <h2 className="text-[30px] font-mulish font-semibold tracking-[0.014] leading-[1.73] mb-2">
          Board of Director
        </h2>
        <div
          className="max-w-full overflow-auto border rounded-md p-4 shadow-inner"
          style={{ maxHeight: "600px" }}
        >
          <OrganizationChart value={treeData} nodeTemplate={CommitteeMemberCardTreeNode} />
        </div>
      </hgroup>
    </main>
  );
}
