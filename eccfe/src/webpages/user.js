import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";

const User = props => {
  const {userId} = useParams()
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [user, setUser] = useState([]);
  const [stocks, setStocks] = useState([]);

  const items = stocks.map((item) =>
      <li key={item.ticker}>
        {item.ticker}:{item.numberOfStocks} Stocks ×{item.stockPrice}$ ={item.totalValue}
      </li>
  );

  useEffect(() => {
    fetch(window.__RUNTIME_CONFIG__.API_URL + userId + "?stocks=true")
    .then(res => res.json())
    .then(
        (data) => {
          console.log(data);
          setIsLoaded(true);
          setUser(data);
          setStocks(data.stocks);
        },
        (error) => {
          setIsLoaded(true);
          setError(error);
        }
    )
  }, [])
  if (error) {
    return <div>Error: {error.message}</div>;
  }
  if (!isLoaded) {
    return <div>Loading...</div>;
  }

  if (user) {

    return (
        <div>
          <h1>{user.firstName} {user.lastName}</h1>
          <ul>
            {items}
          </ul>
        </div>
    );
  }
}
export default User;