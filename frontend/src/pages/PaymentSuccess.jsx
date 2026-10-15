/*import { useEffect, useState } from "react";
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
}*/
import { useEffect, useState } from "react";
import { useSearchParams, useNavigate } from "react-router-dom";
import { useCart } from "../context/CartContext";
import SuccessCard from "../components/SuccessCard";

export default function PaymentSuccess() {
    const [params] = useSearchParams();
    const sessionId = params.get("session_id");
    const { state, dispatch } = useCart();
    const navigate = useNavigate();
    const [status, setStatus] = useState("checking");

// Take a snapshot of the cart before it is cleared (same rules as CartSummary)
    const [summary] = useState(() => {
        const items = state.cartItems;
        if (items.length === 0) return null;
        const subTotal = items.reduce((sum, i) => sum + Number(i.price) * i.quantity, 0);
        return {
            itemCount: items.reduce((sum, i) => sum + i.quantity, 0),
            total: subTotal * 0.8 + 15,
        };
    });

    useEffect(() => {
        if (!sessionId) {
            setStatus("failed");
            return;
        }
        let cancelled = false;
        fetch(`${import.meta.env.VITE_API_URL}/api/payments/status/${sessionId}`)
            .then((res) => (res.ok ? res.text() : "error"))
            .then((text) => {
                if (cancelled) return;
                if (text === "paid") {
                    setStatus("paid");
                    dispatch({ type: "CLEAR_CART" });
                } else {
                    setStatus("failed");
                }
            })
            .catch(() => !cancelled && setStatus("failed"));
        return () => { cancelled = true; };
    }, [sessionId, dispatch]);

    if (status === "checking") {
        return (
            <div className="ih-center-page">
                <div className="ih-card">
                    <h1 className="ih-success-title">Confirming your payment...</h1>
                    <p className="ih-muted">Please don't close this page.</p>
                </div>
            </div>
        );
    }

    if (status === "failed") {
        return (
            <div className="ih-center-page">
                <div className="ih-card" role="alert">
                    <div className="ih-cross" aria-hidden="true">✕</div>
                    <h1 className="ih-success-title">We couldn't confirm your payment</h1>
                    <p className="ih-muted">
                        You have not been charged unless Stripe confirms it. Your cart is still saved.
                    </p>
                    <button className="ih-btn ih-btn-green" onClick={() => navigate("/cart")}>
                        Back to cart
                    </button>
                </div>
            </div>
        );
    }

    const details = [
        { label: "Order reference", value: sessionId.slice(-8).toUpperCase() },
        { label: "Payment status", value: "Paid" },
    ];
    if (summary) {
        details.push({ label: "Items", value: summary.itemCount });
        details.push({ label: "Amount paid", value: `R${summary.total.toFixed(2)}` });
    }

    return (
        <SuccessCard
            title="Payment successful"
            message="Thank you for shopping at Itemhive Store. Your order has been received."
            details={details}
            actions={[
                { label: "Continue shopping", to: "/", variant: "green" },
                { label: "Print receipt", onClick: () => window.print(), variant: "blue" },
            ]}
            redirectTo="/"
            seconds={15}
        />
    );
}
