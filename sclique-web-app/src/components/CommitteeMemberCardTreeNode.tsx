import Image from "next/image";
import { Mail, Phone } from "lucide-react";
import { TreeNode } from 'primereact/treenode';

const CommitteeMemberCardTreeNode = (node: TreeNode) => {
  const { name, position, email, phone, image } = node.data;

  return (
    <section className="p-organizationchart-node-content">
        <article className="flex flex-row gap-4 max-w-[400px]">
        <div className="flex-shrink-0 self-center">
            <Image
            src={image}
            alt={name}
            width={100}
            height={100}
            className="rounded-md object-cover"
            />
        </div>
        <hgroup>
            <h3 className="font-mulish text-[18px] font-semibold">{name}</h3>
            <p className="font-nunitosans text-[16px] font-light">{position}</p>
            <div className="mt-2 flex gap-2 text-[14px] font-light">
              <Mail className="w-4 h-4" />
              <span>{email}</span>
            </div>
            <div className="mt-2 flex gap-2 text-[14px] font-light">
              <Phone className="w-4 h-4" />
              <span>{phone}</span>
            </div>
        </hgroup>
        </article>
    </section>
  );
};

export default CommitteeMemberCardTreeNode;
