import { useState } from "react";
import ProfileShell from "../components/ProfileShell";
import { load, save } from "../../utils/storage";

const defaults = { orderUpdates: true, promotions: false, newArrivals: false };

const items = [
    { name: "orderUpdates", label: "Order updates", hint: "Payment confirmations and delivery progress." },
    { name: "promotions", label: "Deals and discounts", hint: "Offers for students." },
    { name: "newArrivals", label: "New arrivals", hint: "When new products are added to the store." },
];

export default function Notifications() {
    const email = load("itemhive_session", null)?.email;
    const key = `itemhive_prefs:${email}`;

    const [prefs, setPrefs] = useState(() => ({ ...defaults, ...(load(key, {}).notifications || {}) }));
    const [saved, setSaved] = useState(false);

    const toggle = (name) => {
        setPrefs((p) => ({ ...p, [name]: !p[name] }));
        setSaved(false);
    };

    const handleSave = () => {
        save(key, { ...load(key, {}), notifications: prefs });
        setSaved(true);
    };

    return (
        <ProfileShell title="Notifications">
            {saved && (
                <div role="status" className="mb-4 rounded-lg bg-green-50 p-3 text-sm text-green-700">
                    Notification settings saved.
                </div>
            )}

            <ul className="space-y-4">
                {items.map((i) => (
                    <li key={i.name}>
                        <label className="flex cursor-pointer items-start gap-3">
                            <input
                                type="checkbox"
                                checked={prefs[i.name]}
                                onChange={() => toggle(i.name)}
                                className="mt-1 h-4 w-4 accent-black"
                            />
                            <span>
                                <span className="block text-sm font-medium text-gray-900">{i.label}</span>
                                <span className="block text-xs text-gray-500">{i.hint}</span>
                            </span>
                        </label>
                    </li>
                ))}
            </ul>

            <button
                onClick={handleSave}
                className="mt-6 w-full rounded-lg bg-black p-3 text-white transition hover:bg-amber-700"
            >
                Save changes
            </button>
        </ProfileShell>
    );
}