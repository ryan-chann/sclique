import SearchBar from '@/components/SearchBar';

export default function Home() {
  return (
    <main className="flex flex-col content-center mt-33 gap-10">
      <h1 className="text-center font-[Muli] text-[62px] font-bold tracking-[0.022] leading-[1.45]">Explore <br /> Sunway University</h1>
      <search>
        <SearchBar />
      </search>
    </main>
  );
}
