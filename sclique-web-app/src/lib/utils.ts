import { clsx, type ClassValue } from "clsx";
import { twMerge } from "tailwind-merge";
import {CommitteeMember} from "@/lib/props/committeeMemberCard";
import { OrganizationChartNodeData } from 'primereact/organizationchart';

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs));
}

export function buildTree(members: CommitteeMember[]): OrganizationChartNodeData[] {
  const idToNodeMap = new Map<number, OrganizationChartNodeData>();

  members.forEach((member) => {
    idToNodeMap.set(member.id, {
      expanded: true,
      data: {
        id: member.id,
        name: member.name,
        position: member.position,
        email: member.email,
        phone: member.phone,
        image: member.image,
      },
      children: [],
    } as unknown as OrganizationChartNodeData);
  });

  const rootNodes: OrganizationChartNodeData[] = [];

  members.forEach((member) => {
    const node = idToNodeMap.get(member.id)!;
    if (member.managerId === null) {
      rootNodes.push(node);
    } else {
      const managerNode = idToNodeMap.get(member.managerId);
      if (managerNode) {
        (managerNode.children as OrganizationChartNodeData[]).push(node);
      }
    }
  });

  return rootNodes;
}