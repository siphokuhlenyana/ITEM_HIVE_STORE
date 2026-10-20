import { useEffect, useState } from "react";
import { useSearchParams, useNavigate } from "react-router-dom";
import { useCart } from "../context/CartContext";
import { load, save } from "../../utils/storage";
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
            items: items.map((i) => ({
                id: i.id,
                name: i.name,
                price: Number(i.price),
                quantity: i.quantity,
            })),
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

                    // Save the order so it appears on the Orders page (once per payment).
                    if (summary) {
                        const session = load("itemhive_session", null);
                        const orders = load("itemhive_orders", []);
                        if (!orders.some((o) => o.id === sessionId)) {
                            save("itemhive_orders", [
                                {
                                    id: sessionId,
                                    email: session?.email || "guest",
                                    date: new Date().toISOString(),
                                    items: summary.items,
                                    total: summary.total,
                                    status: "Paid",
                                },
                                ...orders,
                            ]);
                        }
                    }

                    dispatch({ type: "CLEAR_CART" });
                } else {
                    setStatus("failed");
                }
            })
            .catch(() => !cancelled && setStatus("failed"));
        return () => { cancelled = true; };
    }, [sessionId, dispatch, summary]);

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
                { label: "View my orders", to: "/orders", variant: "green" },
                { label: "Continue shopping", to: "/", variant: "green" },
                { label: "Print receipt", onClick: () => window.print(), variant: "blue" },
            ]}
            redirectTo="/orders"
            seconds={15}
        />
    );
}