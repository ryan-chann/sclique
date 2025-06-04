import Button from '@/components/Button';


function Header() {
    return (
        <header>
            <a>
                <Button variantStyle="justText" size="xlarge" text="Events" />
                <Button variantStyle="justText" size="xlarge" text="Organisations" />
            </a>
        </header>
    );
}

export default Header;