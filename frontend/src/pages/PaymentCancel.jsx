import { useNavigate } from "react-router-dom";

export default function PaymentCancel() {
    const navigate = useNavigate();
    return (
        <div className="mx-auto flex min-h-[60vh] max-w-xl flex-col items-center justify-center px-4 text-center">
            <h1 className="mb-4 text-2xl font-bold">Payment cancelled</h1>
            <p className="mb-6 text-gray-600">Your cart is still saved.</p>
            <button onClick={() => navigate("/cart")} className="rounded-lg bg-black px-6 py-3 text-white hover:bg-amber-700">
                Back to cart
            </button>
        </div>
    );
}