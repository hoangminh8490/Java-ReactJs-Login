import React from "react";
import { BrowserRouter, Routes, Route, Navigate, useNavigate } from "react-router-dom";
import { Security, LoginCallback, SecureRoute } from "@okta/okta-react";
import { OktaAuth, toRelativeUrl } from "@okta/okta-auth-js";
import Login from "../Login";
import Register from "../Register";
import HomePage from "./HomePage";
import HomeOkta from "./okta/HomeOkta";
import { oktaConfig } from "../config/oktaConfig"
import Profile from "./okta/Profile";
import Locked from "./okta/Locked";

// const oktaAuth = new OktaAuth(oktaConfig);

const RouterSetter = (props) => {

  // const restoreOriginalUri = async (_oktaAuth, originalUri) => {
  //   props.history.replace(toRelativeUrl(originalUri || '/', window.location.origin));
  // };

  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/home" element={<HomePage />} />
          <Route path="/register" element={<Register />} />
          <Route path="*" element={<Navigate to="/login" replace />} />
        </Routes>
      </BrowserRouter>
      {/* <Security oktaAuth={oktaAuth} restoreOriginalUri={restoreOriginalUri}>
      <BrowserRouter>
        <Routes>
          <Route path="/okta-home" exact component={HomeOkta} />
          <Route path="/login/callback" exact component={LoginCallback} />
          <Route path="/locked" exact component={Locked} />
          <Route path="/profile" component={Profile} />
        </Routes>
        </BrowserRouter>
      </Security> */}
    </div>
  );
};

export default RouterSetter;
