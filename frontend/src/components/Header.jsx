/*import { useNavigate } from "react-router-dom";
import { useCart } from "../context/CartContext";


export default function Header(){
    const navigate = useNavigate();
    const { state } = useCart();
    const totalItems = state.cartItems.reduce((sum, item) => sum + item.quantity, 0);

    return(
    <header className = "mx-auto my-2 flex items-center h-22 justify-between px-2 xl:w-5/6">
        <h2 className = "text-2x1 font-bold xl:text-3xl">ITEMHIVE STORE</h2>
        <div>
            {totalItems > 0 && (
 <span className = "flex items-center sticky top-7 w-6 ml-2 h-6 p-1 text-[20px] rounded-2xl bg-red-600 text-white">{totalItems}</span>
            )}
            
            <i className = "bx bx-cart cursor-pointer text-3xl transion-colors hover:text-amber-600 xl:text-4xl"
        onClick = {()=> navigate("/cart")}></i>
        </div>
    </header>
    )
}*/
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
}
