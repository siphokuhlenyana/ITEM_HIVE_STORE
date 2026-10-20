import { useState } from "react";
import ProfileShell from "../components/ProfileShell";
import { load, save } from "../../utils/storage";

const brands = ["Visa", "Mastercard", "American Express"];

const inputClass = (hasError) =>
    `w-full rounded-lg border px-3 py-2 text-sm outline-none transition focus:border-amber-700 ${
        hasError ? "border-red-500" : "border-gray-300"
    }`;

const isExpired = (expiry) => {
    const [mm, yy] = expiry.split("/").map(Number);
    const end = new Date(2000 + yy, mm, 1); // first day after the expiry month
    return end <= new Date();
};

export default function PaymentMethods() {
    const email = load("itemhive_session", null)?.email;
    const key = `itemhive_cards:${email}`;

    const [cards, setCards] = useState(() => load(key, []));
    const [form, setForm] = useState({ brand: "Visa", last4: "", expiry: "" });
    const [errors, setErrors] = useState({});

    const update = (e) => {
        const { name, value } = e.target;
        setForm((f) => ({ ...f, [name]: value }));
        setErrors((er) => ({ ...er, [name]: "" }));
    };

    const handleAdd = (e) => {
        e.preventDefault();
        const er = {};
        if (!/^\d{4}$/.test(form.last4)) er.last4 = "Enter the last 4 digits.";
        if (!/^(0[1-9]|1[0-2])\/\d{2}$/.test(form.expiry)) er.expiry = "Use MM/YY.";
        else if (isExpired(form.expiry)) er.expiry = "This card has expired.";
        setErrors(er);
        if (Object.keys(er).length) return;

        const next = [...cards, { id: Date.now(), ...form }];
        setCards(next);
        save(key, next);
        setForm({ brand: "Visa", last4: "", expiry: "" });
    };

    const handleRemove = (id) => {
        const next = cards.filter((c) => c.id !== id);
        setCards(next);
        save(key, next);
    };

    return (
        <ProfileShell title="Saved payment methods">
            {cards.length === 0 ? (
                <p className="mb-6 text-sm text-gray-500">You haven't saved a card yet.</p>
            ) : (
                <ul className="mb-6 space-y-3">
                    {cards.map((c) => (
                        <li
                            key={c.id}
                            className="flex items-center justify-between rounded-lg border border-gray-200 px-4 py-3 text-sm"
                        >
                            <span>
                                <span className="font-medium text-gray-900">{c.brand}</span> ending in {c.last4}
                                <span className="block text-xs text-gray-500">Expires {c.expiry}</span>
                            </span>
                            <button
                                onClick={() => handleRemove(c.id)}
                                className="text-xs font-medium text-red-600 hover:underline"
                            >
                                Remove
                            </button>
                        </li>
                    ))}
                </ul>
            )}

            <form onSubmit={handleAdd} noValidate className="space-y-4 border-t border-gray-200 pt-6">
                <h2 className="font-semibold text-gray-900">Add a card</h2>

                <div>
                    <label htmlFor="brand" className="mb-1 block text-sm font-medium text-gray-700">Card type</label>
                    <select id="brand" name="brand" value={form.brand} onChange={update} className={inputClass(false)}>
                        {brands.map((b) => <option key={b}>{b}</option>)}
                    </select>
                </div>

                <div className="grid grid-cols-2 gap-4">
                    <div>
                        <label htmlFor="last4" className="mb-1 block text-sm font-medium text-gray-700">Last 4 digits</label>
                        <input
                            id="last4" name="last4" inputMode="numeric" maxLength={4} placeholder="4242"
                            value={form.last4} onChange={update} className={inputClass(errors.last4)}
                        />
                        {errors.last4 && <span className="mt-1 block text-xs text-red-600">{errors.last4}</span>}
                    </div>
                    <div>
                        <label htmlFor="expiry" className="mb-1 block text-sm font-medium text-gray-700">Expiry</label>
                        <input
                            id="expiry" name="expiry" placeholder="MM/YY" maxLength={5}
                            value={form.expiry} onChange={update} className={inputClass(errors.expiry)}
                        />
                        {errors.expiry && <span className="mt-1 block text-xs text-red-600">{errors.expiry}</span>}
                    </div>
                </div>

                <p className="text-xs text-gray-500">
                    Only the card type, last 4 digits and expiry are kept. Full card numbers are entered
                    on Stripe's secure checkout page, never here.
                </p>

                <button className="w-full rounded-lg bg-black p-3 text-white transition hover:bg-amber-700">
                    Save card
                </button>
            </form>
        </ProfileShell>
    );
}