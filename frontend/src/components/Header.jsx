import { useNavigate, useLocation, NavLink } from "react-router-dom";
import { useCart } from "../context/CartContext";
import logo from "../assets/images/itemHive_logo.jpeg";

const links = [
    { to: "/", label: "Home" },
    { to: "/cart", label: "Cart" },
    { to: "/orders", label: "Orders" },
    { to: "/profile", label: "Profile" },
];

const authPages = ["/student-login", "/student-register", "/verify-email"];

export default function Header() {
    const navigate = useNavigate();
    const { pathname } = useLocation();
    const { state } = useCart();

    const isAuthPage = authPages.includes(pathname);

    const totalItems = state.cartItems.reduce((sum, item) => sum + item.quantity, 0);

    const linkClass = ({ isActive }) =>
        `relative text-sm font-medium transition hover:text-amber-700 ${
            isActive ? "text-amber-700" : "text-gray-700"
        }`;

    return (
        <header className="sticky top-0 z-50 border-b border-gray-200 bg-white shadow-sm">
            <div className="mx-auto flex h-20 max-w-7xl items-center justify-between px-4 xl:px-8">
                <div onClick={() => navigate("/")} className="flex cursor-pointer items-center gap-3">
                    <img src={logo} alt="ItemHive Logo" className="h-12 w-12 rounded-full object-cover" />
                    <h2 className="text-2xl font-bold text-gray-900 xl:text-3xl">ITEM HIVE</h2>
                </div>

                {!isAuthPage && (
                    <nav className="flex items-center gap-4 sm:gap-6">
                        {links.map((l) => (
                            <NavLink key={l.to} to={l.to} end={l.to === "/"} className={linkClass}>
                                {l.label}
                                {l.to === "/cart" && totalItems > 0 && (
                                    <span className="absolute -right-4 -top-3 flex h-5 min-w-5 items-center justify-center rounded-full bg-red-600 px-1 text-xs font-bold text-white">
                                        {totalItems}
                                    </span>
                                )}
                            </NavLink>
                        ))}
                    </nav>
                )}
            </div>
        </header>
    );
}