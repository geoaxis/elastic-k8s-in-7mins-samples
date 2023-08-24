import React from 'react';
import {
  BrowserRouter as Router,
  Route,
  Routes
} from "react-router-dom";



import User from "./user";
import Home from "./home";


const Webpages = () => {
  return(
      <Router basename="/eccfe">
        <Routes>
          <Route exact path="/" element= {<Home />} />
          <Route path = "/user/:userId" element = {<User/>} />
        </Routes>
      </Router>
  );
};
export default Webpages;
