import { useState } from "react";
import ProfileShell from "../components/ProfileShell";
import { load, save } from "../../utils/storage";

const languages = [
    { code: "en", label: "English" },
    { code: "af", label: "Afrikaans" },
    { code: "xh", label: "isiXhosa" },
    { code: "zu", label: "isiZulu" },
];

export default function Language() {
    const email = load("itemhive_session", null)?.email;
    const key = `itemhive_prefs:${email}`;

    const [language, setLanguage] = useState(() => load(key, {}).language || "en");
    const [saved, setSaved] = useState(false);

    const handleSave = () => {
        save(key, { ...load(key, {}), language });
        setSaved(true);
    };

    return (
        <ProfileShell title="Language preference">
            {saved && (
                <div role="status" className="mb-4 rounded-lg bg-green-50 p-3 text-sm text-green-700">
                    Language saved.
                </div>
            )}

            <fieldset className="space-y-3">
                <legend className="sr-only">Choose a language</legend>
                {languages.map((l) => (
                    <label
                        key={l.code}
                        className="flex cursor-pointer items-center gap-3 rounded-lg border border-gray-200 px-4 py-3 text-sm transition hover:border-amber-700"
                    >
                        <input
                            type="radio"
                            name="language"
                            value={l.code}
                            checked={language === l.code}
                            onChange={() => {
                                setLanguage(l.code);
                                setSaved(false);
                            }}
                            className="h-4 w-4 accent-black"
                        />
                        {l.label}
                    </label>
                ))}
            </fieldset>

            <button
                onClick={handleSave}
                className="mt-6 w-full rounded-lg bg-black p-3 text-white transition hover:bg-amber-700"
            >
                Save changes
            </button>
        </ProfileShell>
    );
}