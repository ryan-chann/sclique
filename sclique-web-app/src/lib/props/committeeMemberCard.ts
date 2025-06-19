import { OrganizationChartNodeData } from 'primereact/organizationchart';

export interface CommitteeMember extends OrganizationChartNodeData {
  id: number;
  name: string;
  position: string;
  email: string;
  phone: string;
  image: string;
  managerId: number | null;
}

export interface CommitteeMemberTreeNode {
  key: string;
  type: 'person';
  expanded: boolean;
  data: {
    name: string;
    position: string;
    email: string;
    phone: string;
    image: string;
  };
  children: CommitteeMember[];
}