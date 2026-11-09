import { Link } from "react-router-dom";

export default function ProfileShell({ title, children }) {
    return (
        <div className="mx-auto flex max-w-md justify-center px-4 py-10">
            <div className="w-full rounded-xl border border-gray-200 bg-white p-8 shadow-sm">
                <Link to="/profile" className="text-sm text-gray-500 hover:text-amber-700">
                    Back to profile
                </Link>
                <h1 className="mb-6 mt-3 text-2xl font-bold text-gray-900">{title}</h1>
                {children}
            </div>
        </div>
    );
}