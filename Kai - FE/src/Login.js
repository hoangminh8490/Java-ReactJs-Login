import React, { useState , createContext} from "react";
import { useNavigate } from "react-router-dom";
import { GoogleLogin } from "react-google-login";
// import { useOktaAuth } from "@okta/okta-react";

export const CheckLogin = createContext();

 const Login = () => {
  
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  // const { oktaAuth } = useOktaAuth();

  const handleLogin = async () => {
    const data = {};
    data.username = username;
    data.password = password;
    console.log("data", data);

    fetch("http://localhost:8080/authenticate", {
      method: "POST",
      body: JSON.stringify(data),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((res) => res.json())
      //   .then((res) =>  setToken(res.token))
      .then((res) => console.log("res", res))
      .catch((err) => console.log(err));
      
  };
    
  const onSuccessGoogle = async (response) => {
    // Gửi authCode đến trang backend của ứng dụng
    const data = {};
    data.token = response.tokenId;
  
      try {
        const response = await fetch('http://localhost:8080/oauth/google', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(data)
        });
  
        if (response.ok) {
          const data = await response.json();
          localStorage.setItem('token', data.token);
          navigate('/home');
          console.log('Login successful', data.token);
        } else {
          console.log('Login failed');
        }
      } catch (error) {
        console.error('Login error:', error);
      }
  };

  const onFailureGoogle = (response) => {
      console.log("Login Failed! res: ", response);
  }

  // const loginOkta = () => {
  //     oktaAuth.signInWithCredentials({ originaluri: "/profile" });
  // }

  return (
    <div className="auth-form-container">
      <h2>Login</h2>
      <div className="login-form">
        <label htmlFor="email">Email</label>
        <input
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          type="text"
          placeholder="youremail@gmail.com"
          name="username"
        />
        <label htmlFor="password">password</label>
        <input
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          type="password"
          placeholder="********"
          name="password"
        />
        <button onClick={handleLogin}>Log In</button>
      </div>
      <button className="link-btn" onClick={() => navigate("/register")}>
        Don't have an account? Register here.
      </button>
      <GoogleLogin
        clientId="928158375038-qbbbq09rc9sdo6bi3aa2pndgs6e1fld5.apps.googleusercontent.com"
        buttonText="Đăng nhập bằng Google"
        onSuccess={onSuccessGoogle}
        onFailure={onFailureGoogle}
        cookiePolicy={"single_host_origin"}
      />

       <button className="link-btn">
          Login Okta.
      </button> 
    </div>
  );
};

export default Login;
