import React, { useState, useEffect } from "react";
import { useOktaAuth } from "@okta/okta-react";

const Profile = () => {
  const { authState, oktaAuth } = useOktaAuth();
  const [userInfo, setUserInfo] = useState(null);
  const [messages, setMessages] = useState([]);

  useEffect(() => {
    if (!authState || !authState.isAuthenticated) {
      // When user isn't authenticated, forget any user info
      setUserInfo(null);
    } else {
      oktaAuth.getUser().then((info) => {
        setUserInfo(info);
      });
    }
  }, [authState, oktaAuth]); // Update if authState changes

  const callBackend = async () => {
    const response = await fetch("http://localhost:8083/cool-cars", {
      headers: {
        Authorization: `Bearer ${authState.accessToken.accessToken}`,
      },
    });

    if (!response.ok) {
      return Promise.reject();
    }
    const data = await response.json();
    setMessages(data.description);
  };

  console.log('token', authState.accessToken.accessToken)
  console.log('message', messages)

  if (!userInfo) {
    return (
      <div>
        <p>Fetching user profile...</p>
      </div>
    );
  }

  return (
    <div>
      <div>
        <p>
          Below is the information from your ID token which was obtained during
          the &nbsp;
          <a href="https://developer.okta.com/docs/guides/implement-auth-code-pkce">
            PKCE Flow
          </a>{" "}
          and is now stored in local storage.
        </p>
        <p>
          This route is protected with the <code>&lt;SecureRoute&gt;</code>{" "}
          component, which will ensure that this page cannot be accessed until
          you have authenticated.
        </p>
        <table>
          <thead>
            <tr>
              <th>Claim</th>
              <th>Value</th>
            </tr>
          </thead>
          <tbody>
            {Object.entries(userInfo).map((claimEntry) => {
              const claimName = claimEntry[0];
              const claimValue = claimEntry[1];
              const claimId = `claim-${claimName}`;
              return (
                <tr key={claimName}>
                  <td>{claimName}</td>
                  <td id={claimId}>{claimValue.toString()}</td>
                </tr>
              );
            })}
          </tbody>
        </table>
        <button onClick={callBackend}>Call api</button>
        {messages?<div>{messages}</div>:''}
      </div>
    </div>
  );
};

export default Profile;
