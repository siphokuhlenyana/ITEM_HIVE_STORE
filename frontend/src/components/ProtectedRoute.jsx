import { Navigate } from "react-router-dom";
import { load } from "../../utils/storage";

export default function ProtectedRoute({ children }) {
    const session = load("itemhive_session", null);
    return session?.token ? children : <Navigate to="/student-login" replace />;
}