import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { save } from "../../utils/storage";
import { api } from "../../utils/api";
import logo from "../assets/images/itemHive_logo.jpeg";

const inputClass = (hasError) =>
    `w-full rounded-lg border px-3 py-2 text-sm outline-none transition focus:border-amber-700 ${
        hasError ? "border-red-500" : "border-gray-300"
    }`;

export default function StudentLogin() {
    const navigate = useNavigate();
    const [form, setForm] = useState({ email: "", password: "" });
    const [errors, setErrors] = useState({});
    const [showPw, setShowPw] = useState(false);
    const [loading, setLoading] = useState(false);
    const [formError, setFormError] = useState("");

    const update = (e) => {
        const { name, value } = e.target;
        setForm((f) => ({ ...f, [name]: value }));
        setErrors((er) => ({ ...er, [name]: "" }));
        setFormError("");
    };

    const validate = () => {
        const er = {};
        if (!form.email.trim()) er.email = "Enter your student email.";
        else if (!/^\S+@\S+\.\S+$/.test(form.email.trim())) er.email = "Enter a valid email address.";
        if (!form.password) er.password = "Enter your password.";
        else if (form.password.length < 6) er.password = "Password must be at least 6 characters.";
        return er;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const er = validate();
        setErrors(er);
        if (Object.keys(er).length) return;

        setLoading(true);
        const email = form.email.trim();
        try {
            const data = await api("/api/auth/login", {
                method: "POST",
                auth: false,
                body: { email, password: form.password },
            });
            save("itemhive_session", {
                token: data.token,
                email: data.email,
                name: data.name,
                role: data.role,
            });
            navigate("/");
        } catch (err) {
            if (err.status === 403) {
                // Correct password but the email has not been verified yet.
                navigate(`/verify-email?email=${encodeURIComponent(email)}`);
                return;
            }
            setFormError(
                err.status === 401 ? "Incorrect email or password. Try again." : err.message
            );
            setLoading(false);
        }
    };

    return (
        <div className="flex min-h-[70vh] items-center justify-center px-4 py-10">
            <form
                onSubmit={handleSubmit}
                noValidate
                className="w-full max-w-md rounded-xl border border-gray-200 bg-white p-8 shadow-sm"
            >
                <img src={logo} alt="ItemHive Community" className="mx-auto mb-4 h-20 w-20 rounded-full object-cover" />
                <h1 className="text-center text-2xl font-bold text-gray-900">Welcome back</h1>
                <p className="mb-6 mt-1 text-center text-sm text-gray-500">
                    Log in with your student email to browse and buy.
                </p>

                {formError && (
                    <div role="alert" className="mb-4 rounded-lg bg-red-50 p-3 text-sm text-red-700">
                        {formError}
                    </div>
                )}

                <label htmlFor="email" className="mb-1 block text-sm font-medium text-gray-700">
                    Student email
                </label>
                <input
                    id="email" name="email" type="email" autoComplete="email"
                    className={inputClass(errors.email)}
                    placeholder="240865537@mycput.ac.za"
                    value={form.email} onChange={update}
                />
                {errors.email && <span className="mt-1 block text-xs text-red-600">{errors.email}</span>}

                <label htmlFor="password" className="mb-1 mt-4 block text-sm font-medium text-gray-700">
                    Password
                </label>
                <div className="relative">
                    <input
                        id="password" name="password" autoComplete="current-password"
                        type={showPw ? "text" : "password"}
                        className={`${inputClass(errors.password)} pr-16`}
                        placeholder="Your password"
                        value={form.password} onChange={update}
                    />
                    <button
                        type="button"
                        onClick={() => setShowPw((s) => !s)}
                        className="absolute right-3 top-1/2 -translate-y-1/2 text-xs font-medium text-gray-500 hover:text-amber-700"
                    >
                        {showPw ? "Hide" : "Show"}
                    </button>
                </div>
                {errors.password && <span className="mt-1 block text-xs text-red-600">{errors.password}</span>}

                <div className="mt-3 text-right">
                    <button
                        type="button"
                        onClick={() => alert("Password reset is coming soon.")}
                        className="text-sm text-gray-600 hover:text-amber-700"
                    >
                        Forgot password?
                    </button>
                </div>

                <button
                    disabled={loading}
                    className="mt-5 w-full rounded-lg bg-black p-3 text-white transition hover:bg-amber-700 disabled:opacity-50"
                >
                    {loading ? "Logging in…" : "Log in"}
                </button>

                <p className="mt-4 text-center text-sm text-gray-500">
                    New to ItemHive?{" "}
                    <Link to="/student-register" className="font-medium text-amber-700 hover:underline">
                        Create an account
                    </Link>
                </p>
            </form>
        </div>
    );
}