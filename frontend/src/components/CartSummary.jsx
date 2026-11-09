import { useState } from "react";
import { useCart } from "../context/CartContext";
import { api } from "../../utils/api";

export default function CartSummary() {
    const { state } = useCart();
    const { cartItems } = state;
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const totalItems = cartItems.reduce((sum, item) => sum + item.quantity, 0);
    const subTotal = cartItems.reduce((sum, item) => sum + Number(item.price) * item.quantity, 0);
    const discount = subTotal * 0.2;
    const deliveryFee = 15;
    const total = subTotal - discount + deliveryFee;

    const handleCheckout = async () => {
        setLoading(true);
        setError("");
        try {
            const data = await api("/api/payments/create-checkout-session", {
                method: "POST",
                body: { items: cartItems.map((i) => ({ productId: i.id, quantity: i.quantity })) },
            });
            window.location.href = data.url; // go to Stripe's hosted page
        } catch (e) {
            setError(
                e.status === 401 || e.status === 403
                    ? "Please log in to check out."
                    : e.message
            );
            setLoading(false);
        }
    };

    return (
        <div className="rounded-xl border border-gray-200 bg-white p-6 shadow-sm">
            <h1 className="mb-6 text-2xl font-bold text-gray-900">Order Summary</h1>
            <div className="space-y-4 text-sm text-gray-700">
                <div className="flex justify-between"><p>SubTotal</p><p className="font-medium">R{subTotal.toFixed(2)}</p></div>
                <div className="flex justify-between"><p>Total Items</p><p className="font-medium">{totalItems}</p></div>
                <div className="flex justify-between"><p>Discount (-20%)</p><p className="font-medium text-green-600">-R{discount.toFixed(2)}</p></div>
                <div className="flex justify-between"><p>Delivery Fee</p><p className="font-medium">R{deliveryFee.toFixed(2)}</p></div>
            </div>
            <div className="mt-4 flex justify-between border-t pt-4 text-lg font-bold">
                <p>Total</p><p>R{total.toFixed(2)}</p>
            </div>
            {error && <p className="mt-3 text-sm text-red-600">{error}</p>}
            <button
                onClick={handleCheckout}
                disabled={loading}
                className="mt-4 w-full rounded-lg bg-black p-3 text-white transition hover:bg-amber-700 disabled:opacity-50"
            >
                {loading ? "Redirecting..." : "Go to Checkout"}
            </button>
        </div>
    );
}