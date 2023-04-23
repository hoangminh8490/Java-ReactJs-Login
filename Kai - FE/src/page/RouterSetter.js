import React, { useContext} from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Login, { CheckLogin } from "../Login";
import Register from "../Register";
import HomePage from "./HomePage";

const RouterSetter = () => {
  // const isAuthenticated = true;
  // const isAuthenticated = useContext(CheckLogin)
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/home" element={<HomePage />} />
        <Route path="/register" element={<Register />} />
        {/* <Route
          path="/dashboard"
          element={
            isAuthenticated ? <HomePage /> : <Navigate to="/login" replace />
          }
        /> */}
        <Route path="*" element={<Navigate to="/login" replace />} />
      </Routes>
    </BrowserRouter>
  );
};

export default RouterSetter;
