import "./App.css";
import Header from "./Components/Header";
import Main from "./Components/Main";
import Cart from "./Components/Cart";
import { useState } from "react";
import axios from "axios";

const API_URL = "http://localhost:8080/order";

function App() {
  const [cartItems, setCartItems] = useState([]);
  const [orderInfo, setOrderInfo] = useState(null);

  const onAdd = (item) => {
    const exist = cartItems.find((x) => x.itemId === item.itemId);
    if (exist) {
      setCartItems(
        cartItems.map((x) =>
          x.itemId === item.itemId ? { ...exist, qty: exist.qty + 1 } : x
        )
      );
    } else {
      setCartItems([...cartItems, { ...item, qty: 1 }]);
    }
  };

  const onRemove = (item) => {
    const exist = cartItems.find((x) => x.itemId === item.itemId);
    if (exist.qty === 1) {
      setCartItems(cartItems.filter((x) => x.itemId !== item.itemId));
    } else {
      setCartItems(
        cartItems.map((x) =>
          x.itemId === item.itemId ? { ...exist, qty: exist.qty - 1 } : x
        )
      );
    }
  };

  const onCheckOut = (customerId) => {
    const itemObj = {};
    cartItems.forEach((item) => (itemObj[item.itemId] = item.qty));

    const post = { customerId: parseInt(customerId), items: itemObj };
    const postbody = JSON.stringify(post);

    axios({
      method: "post",
      url: API_URL,
      data: postbody,
      headers: { "Content-Type": "application/json; charset=utf-8" },
    }).then((response) => {
      setOrderInfo(response.data);
    }).catch((error) => {
      console.log("the error has occured: " + error);
    });
  };

  return (
    <div className="App">
      <Header />
      <div className="row">
        <Main onAdd={onAdd} />
        <Cart
          onAdd={onAdd}
          onRemove={onRemove}
          onCheckOut={onCheckOut}
          cartItems={cartItems}
          orderInfo={orderInfo}
        />
      </div>
    </div>
  );
}

export default App;
