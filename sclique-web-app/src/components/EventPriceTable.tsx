import { EventPriceTableProps } from "@/lib/props/eventPriceTable";

export default function PriceTable({
  data
}: EventPriceTableProps) {
  return (
    <div className="overflow-x-auto">
      <table className="min-w-full border-collapse text-left">
        <thead>
          <tr className="bg-white">
            <th className="py-3 px-6 font-bold text-black border-b">Type</th>
            <th className="py-3 px-6 font-bold text-black border-b">Price</th>
          </tr>
        </thead>
        <tbody className="text-[16px] text-black">
          {data.map((row, index) => (
            <tr key={index} className="border-b border-gray-300 bg-[#f5f5f5]">
              <td className="py-3 px-6">{row.type}</td>
              <td className="py-3 px-6">{row.price}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
