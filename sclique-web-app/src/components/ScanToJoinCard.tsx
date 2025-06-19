import QRCode from "react-qr-code";
import { ScanToJoinCardProps } from "@/lib/props/scanToJoinCard";

export default function ScanToJoinCard({url}: ScanToJoinCardProps) {
    return (
        <div className="z-40 drop-shadow-md flex flex-row gap-9 bg-white w-[500px] py-2 px-4 rounded-sm">
            <QRCode value={url} size={107} />
            <h2 className="text-[30px] font-mulish font-medium tracking-[0.014] leading-[1.67] self-center">Scan To Join</h2>
        </div>
    );
}