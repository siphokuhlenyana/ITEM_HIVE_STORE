/*
import { useNavigate } from "react-router-dom";
import { useCart } from "../context/CartContext";

export default function Header() {
    const navigate = useNavigate();
    const { state } = useCart();

    const totalItems = state.cartItems.reduce(
        (sum, item) => sum + item.quantity,
        0
    );

    return (
        <header className="sticky top-0 z-50 border-b border-gray-200 bg-white shadow-sm">
            <div className="mx-auto flex h-20 max-w-7xl items-center justify-between px-4 xl:px-8">
                <h2
                    onClick={() => navigate("/")}
                    className="cursor-pointer text-2xl font-bold text-gray-900 xl:text-3xl"
                >
                    Itemhive Store
                </h2>

                <nav className="flex items-center gap-6">
                    <button
                        onClick={() => navigate("/")}
                        className="text-sm font-medium text-gray-700 transition hover:text-amber-700"
                    >
                        Home
                    </button>

                    <div
                        onClick={() => navigate("/cart")}
                        className="relative cursor-pointer"
                    >
                        <i className="bx bx-cart text-3xl text-gray-800 transition hover:text-amber-700 xl:text-4xl"></i>

                        {totalItems > 0 && (
                            <span className="absolute -right-2 -top-2 flex h-6 w-6 items-center justify-center rounded-full bg-red-600 text-xs font-bold text-white">
                                {totalItems}
                            </span>
                        )}
                    </div>
                </nav>
            </div>
        </header>
    );
}*/
import { useNavigate, useLocation, NavLink } from "react-router-dom";
import { useCart } from "../context/CartContext";
import logo from "../assets/images/itemHive_logo.jpeg";

const links = [
    { to: "/", label: "Home" },
    { to: "/cart", label: "Cart" },
    { to: "/events", label: "Orders" },
    { to: "/profile", label: "Profile" },
];

export default function Header() {
    const navigate = useNavigate();
    const { pathname } = useLocation();
    const { state } = useCart();

    const isAuthPage = pathname === "/student-login" || pathname === "/student-register";

    const totalItems = state.cartItems.reduce(
        (sum, item) => sum + item.quantity,
        0
    );

    const linkClass = ({ isActive }) =>
        `text-sm font-medium transition hover:text-amber-700 ${
            isActive ? "text-amber-700" : "text-gray-700"
        }`;

    return (
        <header className="sticky top-0 z-50 border-b border-gray-200 bg-white shadow-sm">
            <div className="mx-auto flex h-20 max-w-7xl items-center justify-between px-4 xl:px-8">
                <div
                    onClick={() => navigate("/home")}
                    className="flex cursor-pointer items-center gap-3"
                >
                    <img src={logo} alt="ItemHive Logo" className="h-12 w-12 rounded-full object-cover" />
                    <h2 className="text-2xl font-bold text-gray-900 xl:text-3xl">
                        ITEM HIVE
                    </h2>
                </div>

                {!isAuthPage && (
                    <nav className="flex items-center gap-4 sm:gap-6">
                        {links.map((l) => (
                            <NavLink key={l.to} to={l.to} className={linkClass}>
                                {l.label}
                            </NavLink>
                        ))}

                        <div
                            onClick={() => navigate("/cart")}
                            className="relative cursor-pointer"
                        >
                            <i className="bx bx-cart text-3xl text-gray-800 transition hover:text-amber-700 xl:text-4xl"></i>

                            {totalItems > 0 && (
                                <span className="absolute -right-2 -top-2 flex h-6 w-6 items-center justify-center rounded-full bg-red-600 text-xs font-bold text-white">
                                    {totalItems}
                                </span>
                            )}
                        </div>
                    </nav>
                )}
            </div>
        </header>
    );
}
