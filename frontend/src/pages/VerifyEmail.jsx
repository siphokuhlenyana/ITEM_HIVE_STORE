import { useEffect, useState } from "react";
import { Link, useNavigate, useSearchParams } from "react-router-dom";
import { api } from "../../utils/api";

export default function VerifyEmail() {
    const [params] = useSearchParams();
    const email = params.get("email") || "";
    const navigate = useNavigate();

    const [code, setCode] = useState("");
    const [error, setError] = useState("");
    const [info, setInfo] = useState("");
    const [loading, setLoading] = useState(false);
    const [cooldown, setCooldown] = useState(30);

    useEffect(() => {
        if (cooldown <= 0) return undefined;
        const t = setTimeout(() => setCooldown((c) => c - 1), 1000);
        return () => clearTimeout(t);
    }, [cooldown]);

    if (!email) {
        return (
            <div className="mx-auto flex min-h-[60vh] max-w-md flex-col items-center justify-center px-4 text-center">
                <h1 className="mb-4 text-2xl font-bold text-gray-900">Nothing to verify</h1>
                <Link to="/student-register" className="font-medium text-amber-700 hover:underline">
                    Create an account
                </Link>
            </div>
        );
    }

    const handleVerify = async (e) => {
        e.preventDefault();
        if (!/^\d{6}$/.test(code)) {
            setError("Enter the 6-digit code from your email.");
            return;
        }
        setLoading(true);
        setError("");
        try {
            await api("/api/auth/verify", { method: "POST", auth: false, body: { email, code } });
            setInfo("Email verified. Redirecting to log in…");
            setTimeout(() => navigate("/student-login"), 1200);
        } catch (err) {
            setError(err.message);
            setLoading(false);
        }
    };

    const handleResend = async () => {
        setError("");
        setInfo("");
        try {
            await api("/api/auth/resend", { method: "POST", auth: false, body: { email } });
            setInfo("A new code is on its way.");
            setCooldown(30);
        } catch (err) {
            setError(err.message);
        }
    };

    return (
        <div className="flex min-h-[70vh] items-center justify-center px-4 py-10">
            <form
                onSubmit={handleVerify}
                noValidate
                className="w-full max-w-md rounded-xl border border-gray-200 bg-white p-8 shadow-sm"
            >
                <h1 className="text-center text-2xl font-bold text-gray-900">Verify your email</h1>
                <p className="mb-6 mt-1 text-center text-sm text-gray-500">
                    We sent a 6-digit code to <span className="font-medium text-gray-700">{email}</span>.
                    It expires in 15 minutes.
                </p>

                {error && (
                    <div role="alert" className="mb-4 rounded-lg bg-red-50 p-3 text-sm text-red-700">
                        {error}
                    </div>
                )}
                {info && (
                    <div role="status" className="mb-4 rounded-lg bg-green-50 p-3 text-sm text-green-700">
                        {info}
                    </div>
                )}

                <label htmlFor="code" className="mb-1 block text-sm font-medium text-gray-700">
                    Verification code
                </label>
                <input
                    id="code"
                    inputMode="numeric"
                    autoComplete="one-time-code"
                    maxLength={6}
                    placeholder="123456"
                    value={code}
                    onChange={(e) => {
                        setCode(e.target.value.replace(/\D/g, ""));
                        setError("");
                    }}
                    className="w-full rounded-lg border border-gray-300 px-3 py-2 text-center text-xl tracking-[0.5em] outline-none transition focus:border-amber-700"
                />

                <button
                    disabled={loading}
                    className="mt-5 w-full rounded-lg bg-black p-3 text-white transition hover:bg-amber-700 disabled:opacity-50"
                >
                    {loading ? "Verifying…" : "Verify email"}
                </button>

                <div className="mt-4 text-center text-sm text-gray-500">
                    Didn't get it?{" "}
                    <button
                        type="button"
                        onClick={handleResend}
                        disabled={cooldown > 0}
                        className="font-medium text-amber-700 hover:underline disabled:text-gray-400 disabled:no-underline"
                    >
                        {cooldown > 0 ? `Resend code in ${cooldown}s` : "Resend code"}
                    </button>
                </div>
            </form>
        </div>
    );
}