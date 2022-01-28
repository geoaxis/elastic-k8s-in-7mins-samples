import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { init as initApm } from '@elastic/apm-rum'

ReactDOM.render(
    <App />,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();


const apm = initApm({

  // Set required service name (allowed characters: a-z, A-Z, 0-9, -, _, and space)
  serviceName: 'eccfe',

  // Set custom APM Server URL (default: http://localhost:8200)
  serverUrl: 'http://kubernetes.docker.internal/apm',

  // Set service version (required for sourcemap feature)
  serviceVersion: '1.0'
})
