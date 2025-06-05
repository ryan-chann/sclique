import Button from '@/components/Button';


function Header() {
    return (
        <header>
            <a className="flex flex-row gap-2">
                <Button variantStyle="justText" size="xlarge" text="Events" />
                <Button variantStyle="justText" size="xlarge" text="Organisations" />
            </a>
        </header>
    );
}

export default Header;