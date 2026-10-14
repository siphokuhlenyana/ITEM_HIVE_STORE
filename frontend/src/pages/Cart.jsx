import CartSummary from "../components/CartSummary";
import { useNavigate } from "react-router-dom";
import { useCart } from "../context/CartContext";
import CartItem from "../components/CartItem";


export default function Cart(){
    const navigate = useNavigate();
    const {state} = useCart();
    const {cartItems} = state;

    if (cartItems.length === 0){
        return(
            <div className="mx-auto flex min-h-[60vh] max-w-4xl flex-col items-center justify-center px-4 text-center">
                <h1 className="mb-4 text-3xl font-bold text-gray-800">Your Itemhive store Cart is empty</h1>
                <button onClick = {()=>navigate("/")} className="rounded-lg bg-black px-6 py-3 text-white transition hover:bg-amber-700">Go to home</button>
            </div>
        )
    }
    return(
    <div className="mx-auto max-w-7xl px-4 py-8 xl:px-8">
        <div className="mb-8">
            <div className="mb-2 flex items-center gap-2 text-sm text-gray-500">
                <h1 onClick = {()=> navigate("/")} className="cursor-pointer hover:text-amber-700">Home</h1>
                <h2 className="text-gray-700">Cart</h2>
            </div>
            <h1 className="text-3xl font-bold text-gray-900">Your cart</h1>
            
        </div>
        <div className="grid gap-8 lg:grid-cols-3">
            <div className="space-y-4 lg:col-span-2">
                {cartItems.map((item)=> (
                    <CartItem key = {item.id} item={item}/>
                ))}
            </div>
            <div>
                <CartSummary />
            </div>
        </div>
    </div>
    )
}