import React from "react";
import { useState, useEffect } from "react";
import Modal from "react-modal";

const customStyles = {
  content: {
    top: "50%",
    left: "50%",
    right: "auto",
    bottom: "auto",
    marginRight: "-50%",
    transform: "translate(-50%, -50%)",
  },
};

export default function Cart(props) {
  const { cartItems, onAdd, onRemove, onCheckOut, orderInfo } = props;
  const [customerId, setCustomerId] = useState(null);
  const [customerName, setCustomerName] = useState(null);
  const [pricePerItem, setPricePerItem] = useState([]);
  const [totalTax, setTotalTax] = useState(null);
  const [totalCombinedPrice, setTotalCombinedPrice] = useState(null);
  let subtitle;
  const [modalIsOpen, setIsOpen] = React.useState(false);

  useEffect(() => {
    if (orderInfo !== null) {
      setCustomerName(orderInfo.customerName);
      setPricePerItem(orderInfo.pricePerItem);
      setTotalTax(orderInfo.totalTax.toFixed(2));
      setTotalCombinedPrice(orderInfo.totalCombinedPrice.toFixed(2));
    }
  }, [props.orderInfo]);

  function openModal() {
    setIsOpen(true);
  }

  function afterOpenModal() {
    subtitle.style.color = "#f00";
  }

  function closeModal() {
    setIsOpen(false);
    window.location.reload();
  }

  return (
    <>
      <aside className="block col-1">
        <h2>Cart Items</h2>
        <div>{cartItems.length === 0 && <div>Cart Is Empty</div>}</div>
        {cartItems.map((item) => (
          <div key={item.itemId} className="row">
            <div className="col-2">{item.itemName}</div>
            <div className="col-2">
              <button onClick={() => onAdd(item)} className="add">
                +
              </button>
              <button onClick={() => onRemove(item)} className="remove">
                -
              </button>
            </div>
            <div className="col-2 text-right">
              {item.qty} x ${item.itemPrice.toFixed(2)}
            </div>
          </div>
        ))}
        {cartItems.length !== 0 && (
          <>
            <hr></hr>
            <div className="row">
              <h3 className="inline center">Customer ID: </h3>
              <input
                type="number"
                className="inline"
                onInput={(e) => setCustomerId(e.target.value)}
              ></input>
              <button
                onClick={() => {
                  onCheckOut(customerId);
                  openModal();
                }}
              >
                Checkout
              </button>
            </div>
          </>
        )}
      </aside>
      <Modal
        isOpen={modalIsOpen}
        onAfterOpen={afterOpenModal}
        onRequestClose={closeModal}
        style={customStyles}
        contentLabel="Modal"
        ariaHideApp={false}
      >
        <div>
          <h1>Order Information</h1>
        </div>
        <div>
          <h3>Customer name: <div className="text-right ">{customerName}</div></h3>
          <h3>
            Total price per item:{" "}
            {pricePerItem.map((x) => (
              <div className="text-right">${x}</div>
            ))}
          </h3>
          <h3>Total tax: <div className="text-right ">${totalTax}</div></h3>
          <h3>Total price: <div className="text-right ">${totalCombinedPrice}</div></h3>
        </div>
        <button onClick={closeModal}>close</button>
      </Modal>
    </>
  );
}
