import React, { useEffect } from "react";
import './App.css';
import RouterSetter from "./page/RouterSetter";
import { gapi } from 'gapi-script';

function App() {

  useEffect(() => {
    function start(){
      gapi.client.init({
        clientId:"928158375038-qbbbq09rc9sdo6bi3aa2pndgs6e1fld5.apps.googleusercontent.com",
        scope: ""
      })
    };

    gapi.load('client:auth2', start);
  });

  return (
    <div className="App">
      <RouterSetter />
    </div>
  );
}

export default App;
