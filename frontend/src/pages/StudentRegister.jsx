import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { api } from "../../utils/api";

const fields = [
    { name: "firstName", label: "First name", type: "text", placeholder: "First name" },
    { name: "lastName", label: "Last name", type: "text", placeholder: "Last name" },
    { name: "studentNumber", label: "Student number", type: "text", placeholder: "240865537", full: true },
    { name: "email", label: "Student email", type: "email", placeholder: "240865537@mycput.ac.za", full: true },
    { name: "phone", label: "Phone number", type: "tel", placeholder: "0821234567", full: true },
    { name: "address", label: "Address", type: "text", placeholder: "Address", full: true },
    { name: "password", label: "Password", type: "password", placeholder: "At least 6 characters", full: true },
];

const inputClass = (hasError) =>
    `w-full rounded-lg border px-3 py-2 text-sm outline-none transition focus:border-amber-700 ${
        hasError ? "border-red-500" : "border-gray-300"
    }`;

export default function StudentRegister() {
    const navigate = useNavigate();
    const [form, setForm] = useState({
        firstName: "", lastName: "", studentNumber: "",
        email: "", phone: "", address: "", password: "",
    });
    const [errors, setErrors] = useState({});
    const [formError, setFormError] = useState("");
    const [submitting, setSubmitting] = useState(false);

    const update = (e) => {
        const { name, value } = e.target;
        setForm((f) => ({ ...f, [name]: value }));
        setErrors((er) => ({ ...er, [name]: "" }));
        setFormError("");
    };

    const validate = () => {
        const er = {};
        if (!form.firstName.trim()) er.firstName = "Required.";
        if (!form.lastName.trim()) er.lastName = "Required.";
        if (!/^\d{9}$/.test(form.studentNumber.trim())) er.studentNumber = "Enter your 9-digit student number.";
        if (!/^\S+@\S+\.\S+$/.test(form.email.trim())) er.email = "Enter a valid email address.";
        if (!/^\+?\d{9,12}$/.test(form.phone.trim())) er.phone = "Enter a valid phone number.";
        if (!form.address.trim()) er.address = "Required.";
        if (form.password.length < 6) er.password = "Password must be at least 6 characters.";
        return er;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const er = validate();
        setErrors(er);
        if (Object.keys(er).length) return;

        setSubmitting(true);
        const email = form.email.trim().toLowerCase();
        try {
            await api("/api/auth/register", {
                method: "POST",
                auth: false,
                body: {
                    firstName: form.firstName.trim(),
                    lastName: form.lastName.trim(),
                    studentNumber: form.studentNumber.trim(),
                    email,
                    phone: form.phone.trim(),
                    address: form.address.trim(),
                    password: form.password,
                },
            });
            navigate(`/verify-email?email=${encodeURIComponent(email)}`);
        } catch (err) {
            if (err.status === 409) setErrors({ email: err.message });
            else setFormError(err.message);
            setSubmitting(false);
        }
    };

    return (
        <div className="flex min-h-[70vh] items-center justify-center px-4 py-10">
            <form
                onSubmit={handleSubmit}
                noValidate
                className="w-full max-w-lg rounded-xl border border-gray-200 bg-white p-8 shadow-sm"
            >
                <h1 className="mb-6 text-center text-2xl font-bold text-gray-900">Student Registration</h1>

                {formError && (
                    <div role="alert" className="mb-4 rounded-lg bg-red-50 p-3 text-sm text-red-700">
                        {formError}
                    </div>
                )}

                <div className="grid gap-4 sm:grid-cols-2">
                    {fields.map((f) => (
                        <div key={f.name} className={f.full ? "sm:col-span-2" : ""}>
                            <label htmlFor={f.name} className="mb-1 block text-sm font-medium text-gray-700">
                                {f.label}
                            </label>
                            <input
                                id={f.name} name={f.name} type={f.type}
                                placeholder={f.placeholder}
                                className={inputClass(errors[f.name])}
                                value={form[f.name]} onChange={update}
                            />
                            {errors[f.name] && (
                                <span className="mt-1 block text-xs text-red-600">{errors[f.name]}</span>
                            )}
                        </div>
                    ))}
                </div>

                <button
                    disabled={submitting}
                    className="mt-6 w-full rounded-lg bg-black p-3 text-white transition hover:bg-amber-700 disabled:opacity-50"
                >
                    {submitting ? "Creating account…" : "Create Account"}
                </button>

                <p className="mt-4 text-center text-sm text-gray-500">
                    Already registered?{" "}
                    <Link to="/student-login" className="font-medium text-amber-700 hover:underline">
                        Log in
                    </Link>
                </p>
            </form>
        </div>
    );
}