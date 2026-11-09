import { useEffect, useState } from "react";
import ProfileShell from "../components/ProfileShell";
import { api } from "../../utils/api";
import { load, save } from "../../utils/storage";

const inputClass = (hasError) =>
    `w-full rounded-lg border px-3 py-2 text-sm outline-none transition focus:border-amber-700 ${
        hasError ? "border-red-500" : "border-gray-300"
    }`;

const editable = [
    { name: "firstName", label: "First name", type: "text" },
    { name: "lastName", label: "Last name", type: "text" },
    { name: "phone", label: "Phone number", type: "tel" },
    { name: "address", label: "Address", type: "text" },
];

export default function EditProfile() {
    const [form, setForm] = useState(null);
    const [errors, setErrors] = useState({});
    const [message, setMessage] = useState({ type: "", text: "" });
    const [saving, setSaving] = useState(false);

    useEffect(() => {
        api("/api/users/me")
            .then(setForm)
            .catch((e) => setMessage({ type: "error", text: e.message }));
    }, []);

    const update = (e) => {
        const { name, value } = e.target;
        setForm((f) => ({ ...f, [name]: value }));
        setErrors((er) => ({ ...er, [name]: "" }));
        setMessage({ type: "", text: "" });
    };

    const validate = () => {
        const er = {};
        if (!form.firstName.trim()) er.firstName = "Required.";
        if (!form.lastName.trim()) er.lastName = "Required.";
        if (!/^\+?\d{9,12}$/.test(form.phone.trim())) er.phone = "Enter a valid phone number.";
        if (!form.address.trim()) er.address = "Required.";
        return er;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const er = validate();
        setErrors(er);
        if (Object.keys(er).length) return;

        setSaving(true);
        try {
            const updated = await api("/api/users/me", {
                method: "PUT",
                body: {
                    firstName: form.firstName.trim(),
                    lastName: form.lastName.trim(),
                    phone: form.phone.trim(),
                    address: form.address.trim(),
                },
            });
            setForm(updated);
            const session = load("itemhive_session", {});
            save("itemhive_session", { ...session, name: `${updated.firstName} ${updated.lastName}` });
            setMessage({ type: "success", text: "Profile saved." });
        } catch (err) {
            setMessage({ type: "error", text: err.message });
        } finally {
            setSaving(false);
        }
    };

    return (
        <ProfileShell title="Edit profile">
            {message.text && (
                <div
                    role={message.type === "error" ? "alert" : "status"}
                    className={`mb-4 rounded-lg p-3 text-sm ${
                        message.type === "error" ? "bg-red-50 text-red-700" : "bg-green-50 text-green-700"
                    }`}
                >
                    {message.text}
                </div>
            )}

            {!form ? (
                !message.text && <p className="text-sm text-gray-500">Loading your details…</p>
            ) : (
                <form onSubmit={handleSubmit} noValidate className="space-y-4">
                    {editable.map((f) => (
                        <div key={f.name}>
                            <label htmlFor={f.name} className="mb-1 block text-sm font-medium text-gray-700">
                                {f.label}
                            </label>
                            <input
                                id={f.name} name={f.name} type={f.type}
                                className={inputClass(errors[f.name])}
                                value={form[f.name] || ""} onChange={update}
                            />
                            {errors[f.name] && (
                                <span className="mt-1 block text-xs text-red-600">{errors[f.name]}</span>
                            )}
                        </div>
                    ))}

                    <div>
                        <label htmlFor="email" className="mb-1 block text-sm font-medium text-gray-700">
                            Student email
                        </label>
                        <input id="email" value={form.email} disabled className={`${inputClass(false)} bg-gray-50 text-gray-500`} />
                    </div>
                    <div>
                        <label htmlFor="studentNumber" className="mb-1 block text-sm font-medium text-gray-700">
                            Student number
                        </label>
                        <input id="studentNumber" value={form.studentNumber} disabled className={`${inputClass(false)} bg-gray-50 text-gray-500`} />
                    </div>

                    <button
                        disabled={saving}
                        className="w-full rounded-lg bg-black p-3 text-white transition hover:bg-amber-700 disabled:opacity-50"
                    >
                        {saving ? "Saving…" : "Save changes"}
                    </button>
                </form>
            )}
        </ProfileShell>
    );
}