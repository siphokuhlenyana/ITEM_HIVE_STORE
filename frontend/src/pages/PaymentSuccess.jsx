import { useEffect, useState } from "react";
import { useSearchParams, useNavigate } from "react-router-dom";
import { useCart } from "../context/CartContext";

export default function PaymentSuccess() {
    const [params] = useSearchParams();
    const sessionId = params.get("session_id");
    const { dispatch } = useCart();
    const navigate = useNavigate();
    const [status, setStatus] = useState("checking");

    useEffect(() => {
        if (!sessionId) { setStatus("failed"); return; }
        fetch(`${import.meta.env.VITE_API_URL}/api/payments/status/${sessionId}`)
            .then((res) => res.text())
            .then((text) => {
                if (text === "paid") {
                    setStatus("paid");
                    dispatch({ type: "CLEAR_CART" });
                } else {
                    setStatus("failed");
                }
            })
            .catch(() => setStatus("failed"));
    }, [sessionId, dispatch]);

    return (
        <div className="mx-auto flex min-h-[60vh] max-w-xl flex-col items-center justify-center px-4 text-center">
            {status === "checking" && <h1 className="text-2xl font-bold">Confirming your payment...</h1>}
            {status === "paid" && <h1 className="text-3xl font-bold text-green-600">Payment successful. Thank you!</h1>}
            {status === "failed" && <h1 className="text-2xl font-bold text-red-600">We couldn't confirm your payment.</h1>}
            <button onClick={() => navigate("/")} className="mt-6 rounded-lg bg-black px-6 py-3 text-white hover:bg-amber-700">
                Back to store
            </button>
        </div>
    );
}