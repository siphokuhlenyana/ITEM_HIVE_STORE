import { Navigate, useNavigate } from "react-router-dom";
import { load, remove } from "/utils/storage";

const options = ["Edit Profile", "Saved Payment Methods", "Notifications", "Language Preference"];

export default function Profile() {
    const navigate = useNavigate();
    const session = load("itemhive_session", null);

    if (!session) return <Navigate to="/student-login" replace />;

    const name = session.name || "Student";
    const initials = name
        .split(" ")
        .map((part) => part[0])
        .join("")
        .slice(0, 2)
        .toUpperCase();

    const handleLogout = () => {
        remove("itemhive_session");
        navigate("/student-login");
    };

    return (
        <div className="mx-auto flex max-w-md justify-center px-4 py-10">
            <div className="w-full rounded-xl border border-gray-200 bg-white p-8 text-center shadow-sm">
                <div className="mx-auto mb-4 flex h-20 w-20 items-center justify-center rounded-full bg-black text-2xl font-bold text-white">
                    {initials}
                </div>
                <h2 className="text-xl font-bold text-gray-900">{name}</h2>
                <p className="text-sm text-gray-500">{session.email}</p>
                <span className="mt-3 inline-block rounded-full bg-green-100 px-3 py-1 text-xs font-semibold text-green-700">
                    Verified Student
                </span>

                <div className="mt-6 space-y-3 text-left">
                    {options.map((o) => (
                        <div
                            key={o}
                            className="cursor-pointer rounded-lg border border-gray-200 px-4 py-3 text-sm font-medium text-gray-700 transition hover:border-amber-700 hover:text-amber-700"
                        >
                            {o}
                        </div>
                    ))}
                </div>

                <button
                    onClick={handleLogout}
                    className="mt-6 w-full rounded-lg bg-black p-3 text-white transition hover:bg-amber-700"
                >
                    Log out
                </button>
            </div>
        </div>
    );
}