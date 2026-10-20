import { useNavigate } from "react-router-dom";
import { load } from "../../utils/storage";

const money = (n) => `R${Number(n).toFixed(2)}`;

const formatDate = (iso) =>
    new Date(iso).toLocaleDateString("en-ZA", { day: "numeric", month: "long", year: "numeric" });

export default function Orders() {
    const navigate = useNavigate();
    const session = load("itemhive_session", null);

    const orders = load("itemhive_orders", [])
        .filter((o) => o.email === session?.email)
        .sort((a, b) => new Date(b.date) - new Date(a.date));

    if (orders.length === 0) {
        return (
            <div className="mx-auto flex min-h-[60vh] max-w-xl flex-col items-center justify-center px-4 text-center">
                <h1 className="mb-2 text-2xl font-bold text-gray-900">No orders yet</h1>
                <p className="mb-6 text-gray-600">Orders you pay for will show up here.</p>
                <button
                    onClick={() => navigate("/")}
                    className="rounded-lg bg-black px-6 py-3 text-white transition hover:bg-amber-700"
                >
                    Start shopping
                </button>
            </div>
        );
    }

    return (
        <div className="mx-auto max-w-3xl px-4 py-8">
            <h1 className="mb-6 text-3xl font-bold text-gray-900">Your orders</h1>

            <div className="space-y-4">
                {orders.map((order) => {
                    const subtotal = order.items.reduce((sum, i) => sum + i.price * i.quantity, 0);
                    const discount = subtotal * 0.2;
                    const delivery = order.total - (subtotal - discount);

                    return (
                        <article
                            key={order.id}
                            className="rounded-xl border border-gray-200 bg-white p-5 shadow-sm"
                        >
                            <header className="flex flex-wrap items-start justify-between gap-2">
                                <div>
                                    <h2 className="font-semibold text-gray-900">
                                        Order {order.id.slice(-8).toUpperCase()}
                                    </h2>
                                    <p className="text-sm text-gray-500">{formatDate(order.date)}</p>
                                </div>
                                <span className="rounded-full bg-green-100 px-3 py-1 text-xs font-semibold text-green-700">
                                    {order.status}
                                </span>
                            </header>

                            <ul className="mt-4 divide-y divide-gray-100 text-sm">
                                {order.items.map((item) => (
                                    <li key={item.id} className="flex justify-between py-2">
                                        <span className="text-gray-700">
                                            {item.name} <span className="text-gray-500">x {item.quantity}</span>
                                        </span>
                                        <span className="font-medium">{money(item.price * item.quantity)}</span>
                                    </li>
                                ))}
                            </ul>

                            <dl className="mt-3 space-y-1 border-t border-gray-200 pt-3 text-sm text-gray-600">
                                <div className="flex justify-between">
                                    <dt>Subtotal</dt>
                                    <dd>{money(subtotal)}</dd>
                                </div>
                                <div className="flex justify-between">
                                    <dt>Discount (-20%)</dt>
                                    <dd className="text-green-600">-{money(discount)}</dd>
                                </div>
                                <div className="flex justify-between">
                                    <dt>Delivery fee</dt>
                                    <dd>{money(delivery)}</dd>
                                </div>
                                <div className="flex justify-between border-t border-gray-200 pt-2 text-base font-bold text-gray-900">
                                    <dt>Total paid</dt>
                                    <dd>{money(order.total)}</dd>
                                </div>
                            </dl>
                        </article>
                    );
                })}
            </div>
        </div>
    );
}