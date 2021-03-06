import {OAUTH_SERVER_URL, DATA_SERVER_URL, OAUTH_LOGIN, OAUTH_PASSWORD} from './../config';
import {checkResponseStatus} from '../handlers/responseHandlers';
import headers from './../security/headers';
import 'whatwg-fetch';


//<1>
export default {
  logIn(auth) { //<2>
    localStorage.auth = JSON.stringify(auth);
  },

  logOut() { //<3>
    delete localStorage.auth;
  },

  refreshToken() {
    return fetch(
      `${OAUTH_SERVER_URL}/sbarnacle/oauth2/oauth/token`,
      { method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/x-www-form-urlencoded',
          'Authorization': 'Basic '+ btoa(OAUTH_LOGIN + ':' + OAUTH_PASSWORD),
        },
        body: 'grant_type=refresh_token&refresh_token=' + JSON.parse(localStorage.auth)['refresh_token']
      })
      .then((response) => {
          if (!response.ok) {
              return Promise.reject(new Error(
                  'Response failed: ' + response.status + ' (' + response.statusText + ')'
              ));
          }
          return response.json();
      })
      .then(checkResponseStatus)
      .then((a) => localStorage.auth = JSON.stringify(a))
      .catch(() => {
        this.logOut();
        throw new Error("Unable to refresh!")
      })
  },

  loggedIn() {  //<6>
    return localStorage.auth && fetch(
        `${DATA_SERVER_URL}/sbarnacle/fluffy-invention/api/ping/ping-auth`,
        {headers: headers()})
        .then(checkResponseStatus)
        .then(() => { return true })
        .catch(this.refreshToken)
        .catch(() => { return false });
  }
};