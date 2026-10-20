import { load, remove } from "./storage";

const BASE = import.meta.env.VITE_API_URL;

/**
 * Talks to the Spring Boot backend.
 * Adds the login token automatically. Throws an Error with .status and a readable message.
 */
export async function api(path, { method = "GET", body, auth = true } = {}) {
    const headers = { "Content-Type": "application/json" };
    const session = load("itemhive_session", null);
    if (auth && session?.token) headers.Authorization = `Bearer ${session.token}`;

    let res;
    try {
        res = await fetch(`${BASE}${path}`, {
            method,
            headers,
            body: body ? JSON.stringify(body) : undefined,
        });
    } catch {
        throw new Error("Cannot reach the server. Check that the backend is running.");
    }

    const text = await res.text();
    let data = null;
    try {
        data = text ? JSON.parse(text) : null;
    } catch {
        data = { message: text };
    }

    if (!res.ok) {
        // Token expired or invalid: clear the session so the user is sent to log in again.
        if (res.status === 401 && auth && session?.token) remove("itemhive_session");
        const err = new Error(data?.message || "Something went wrong. Please try again.");
        err.status = res.status;
        throw err;
    }
    return data;
}