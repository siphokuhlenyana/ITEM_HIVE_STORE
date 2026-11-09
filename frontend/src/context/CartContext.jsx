import { createContext, useContext, useEffect, useReducer } from "react";
import { CartReducer, initialState } from "./CartReducer";
import { load, save } from "../../utils/storage";

const CartContext = createContext();

export function CartProvider({ children }) {
    // Start from the saved cart, so it survives the trip to Stripe's page and back.
    const [state, dispatch] = useReducer(CartReducer, initialState, (initial) => ({
        ...initial,
        cartItems: load("itemhive_cart", initial.cartItems),
    }));

    useEffect(() => {
        save("itemhive_cart", state.cartItems);
    }, [state.cartItems]);

    return (
        <CartContext.Provider value={{ state, dispatch }}>
            {children}
        </CartContext.Provider>
    );
}

export function useCart() {
    return useContext(CartContext);
}