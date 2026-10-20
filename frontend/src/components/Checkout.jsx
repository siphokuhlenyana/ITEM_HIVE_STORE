
const handleCheckout = async () => {
  try {
    const response = await fetch(
      "http://localhost:8080/api/payments/checkout",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          buyerId: "B001",
          listingId: "L001",
          quantity: 2,
        }),
      }
    );

    const data = await response.json();

    if (!response.ok) {
      throw new Error(data.message || "Checkout failed");
    }

    if (data.url) {
      window.location.href = data.url;
    } else {
      throw new Error("The server did not return a checkout URL");
    }
  } catch (error) {
    console.error("Checkout error:", error);
    alert(error.message || "Unable to start checkout.");
  }
};
