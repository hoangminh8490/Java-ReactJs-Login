const REACT_APP_CLIENT_ID = '0oa9bmsrg7jzbUFwp5d7';
const REACT_APP_OKTA_DOMAIN = 'dev-91267716.okta.com';
const REACT_APP_PORT = '3002'

export const oktaConfig = {
  clientId: `${REACT_APP_CLIENT_ID}`,
  issuer: `https://${REACT_APP_OKTA_DOMAIN}/oauth2/default`,
  redirectUri: `http://localhost:${REACT_APP_PORT}/login/callback`, // this makes it so redirects to login if not logged in for secure routes
  scopes: ["openid", "profile", "email"],
  pkce: true,
  disableHttpsCheck: true,
};
