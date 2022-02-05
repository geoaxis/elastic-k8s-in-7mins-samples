import React, { useState, useEffect }  from 'react';
import {Link} from "react-router-dom";
import { withTransaction } from '@elastic/apm-rum-react'

const Home = () => {
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [users, setUsers] = useState([]);
  useEffect(() => {
    fetch(window.__RUNTIME_CONFIG__.API_URL)
    .then(res => res.json())
    .then(
        (data) => {
          setIsLoaded(true);
          setUsers(data);
        },
        (error) => {
          setIsLoaded(true);
          setError(error);
        }
    )
  }, [])
  if (error) {
    return <div>Error: {error.message}</div>;
  } else if (!isLoaded) {
    return <div>Loading...</div>;
  } else {
    return (
        <div>
        <h1>Users with Stocks</h1>
        <ul>
          {users.map(user => (
              <li key={`${user.id}`}>
                <Link to={`user/${user.id}`}>{user.firstName} {user.lastName}</Link>
              </li>
          ))}
        </ul>
        </div>
    );
  }
}
export  default withTransaction('Home', 'component')(Home);