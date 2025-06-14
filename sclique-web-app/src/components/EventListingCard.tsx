import Image from "next/image";
import Button from "@/components/Button";

export default function EventListingCard() {
    return (
        <article className="flex flex-row gap-[42px]">
            <div className="w-[183px] h-[183px] z-40 drop-shadow-md">
                <Image
                    src="https://dummyimage.com/1080x1080/000/fff"
                    alt="dummyimage"
                    width="183"
                    height="183"
                />
            </div>

            <hgroup className="flex flex-col justify-between h-[183px]">
                <div>
                    <h2 className="text-[30px] font-[Muli] font-semibold tracking-[0.014] leading-[1.73]">
                        Viper Challenge King of Mountain 2025
                    </h2>

                    <p className="text-[22px] font-[Muli] font-semibold tracking-[0.014] leading-[1.64]">
                        [UTC+08] Sat, 2025 Feb 22. 7:00AM
                    </p>

                    <p className="text-[16px] font-[Nunito_Sans] font-light tracking-[0.012] leading-[1.5]">
                        Sunway University Foyer
                    </p>

                    <p className="text-[12px] font-[Nunito_Sans] font-extralight tracking-[0.012] leading-[1.5]">
                        From RM <span>122.84</span>
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