import React from "react";
import { useState, useEffect } from "react";
import axios from "axios";

const API_URL = 'http://localhost:8080/items';

export default function Main(props) {
  const [itemList, setItemList] = useState([]);
  const {onAdd} = props;

  useEffect(() => {
    axios.get(API_URL).then((res) => {
        setItemList(res.data);
    });
  }, []);

  if (!itemList) return null;

  return (
    <main className="block col-2">
      <table className="table table-striped">
        <thead>
          <tr>
            <td>Item ID</td>
            <td>Item Name</td>
            <td>Item Price</td>
          </tr>
        </thead>
        <tbody>
          {itemList.map((item) => (
            <tr key={item.itemId}>
              <td>{item.itemId}</td>
              <td>{item.itemName}</td>
              <td>{item.itemPrice}</td>
              <td><button onClick={() => onAdd(item)}>Add To Cart</button></td>
            </tr>
          ))}
        </tbody>
      </table>
    </main>
  );
}
