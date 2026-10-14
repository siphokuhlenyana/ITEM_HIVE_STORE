/*import {Routes, Route} from "react-router-dom";
import Header from "./components/Header";
import Home from "./pages/Home";
import Cart from "./pages/Cart"
function App(){

  return (
    <>
    <Header/>
    <Routes>
      <Route path = "/" element = {<Home />}></Route>
      <Route path = "/cart" element = {<Cart />}></Route>
    </Routes>
  
    </>
  )
}
export default App
*/

import { Routes, Route } from "react-router-dom";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Home from "./pages/Home";
import Cart from "./pages/Cart";
import PaymentSuccess from "./pages/PaymentSuccess";
import PaymentCancel from "./pages/PaymentCancel";

function App() {
  return (
    <div className="flex min-h-screen flex-col">
      <Header />

      <main className="flex-1">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/cart" element={<Cart />} />
            <Route path="/payment-success" element={<PaymentSuccess />} />
            <Route path="/payment-cancel" element={<PaymentCancel />} />
        </Routes>
      </main>

      <Footer />
    </div>
  );
}

export default App;