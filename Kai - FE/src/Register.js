import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const Register = (props) => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const navigate = useNavigate();

    const handleRegister = async ()=> {
        const data = {};
        data.username = username;
        data.password = password;
        data.email = email;
    
        fetch("http://localhost:8081/register", {
          method: "POST",
          body: JSON.stringify(data),
          headers: {
            "Content-Type": "application/json",
          },
        })
          .then((res) => res.json() )
        //   .then((res) =>  setToken(res.token))
        .then((res) => console.log('res', res) )
          .catch((err) => console.log(err));
    }

    return (
        <div className="auth-form-container">
            <h2>Register</h2>
        <div className="register-form">
            <label htmlFor="name">Email</label>
            <input value={email}  onChange={(e) => setEmail(e.target.value)} id="name" placeholder="full Name" name="email"/>
            <label htmlFor="email">Full Name</label>
            <input value={username} onChange={(e) => setUsername(e.target.value)} type="text" placeholder="youremail@gmail.com" name="username" />
            <label htmlFor="password">Password</label>
            <input value={password} onChange={(e) => setPassword(e.target.value)} type="password" placeholder="********" name="password" />
            <button onClick={handleRegister}>Register</button>
        </div>
        <button className="link-btn" onClick={() => navigate("/login")}>Already have an account? Login here.</button>
    </div>
    );
};

export default Register;