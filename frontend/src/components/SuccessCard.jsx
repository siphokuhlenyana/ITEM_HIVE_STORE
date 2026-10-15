import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

/**
 * Reusable confirmation screen.
 * props: title, message, details[], actions[{label, to, variant, onClick}],
 *        redirectTo + seconds -> auto-redirect with countdown
 */
export default function SuccessCard({
                                        title,
                                        message,
                                        details = [],
                                        actions = [],
                                        redirectTo,
                                        seconds = 0,
                                    }) {
    const navigate = useNavigate();
    const [left, setLeft] = useState(seconds);

    useEffect(() => {
        if (!seconds || !redirectTo) return undefined;
        const timer = setInterval(() => setLeft((s) => s - 1), 1000);
        return () => clearInterval(timer);
    }, [seconds, redirectTo]);

    useEffect(() => {
        if (seconds && redirectTo && left <= 0) navigate(redirectTo);
    }, [left, seconds, redirectTo, navigate]);

    return (
        <div className="ih-center-page">
            <div className="ih-card ih-success" role="status">
                <div className="ih-tick" aria-hidden="true">✓</div>
                <h1 className="ih-success-title">{title}</h1>
                <p className="ih-muted">{message}</p>

                {details.length > 0 && (
                    <dl className="ih-details">
                        {details.map((d) => (
                            <div key={d.label}>
                                <dt>{d.label}</dt>
                                <dd>{d.value}</dd>
                            </div>
                        ))}
                    </dl>
                )}

                {actions.map((a) => (
                    <button
                        key={a.label}
                        className={`ih-btn ${a.variant === "blue" ? "ih-btn-blue" : "ih-btn-green"}`}
                        onClick={() => (a.onClick ? a.onClick() : navigate(a.to))}
                    >
                        {a.label}
                    </button>
                ))}

                {seconds > 0 && redirectTo && left > 0 && (
                    <p className="ih-small ih-muted">Redirecting in {left}s…</p>
                )}
            </div>
        </div>
    );
}