import {SERVER_URL} from './../config';
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
    debugger;
    return fetch(
      //`${SERVER_URL}/oauth/access_token`,
      `${SERVER_URL}/sbarnacle/oauth2/oauth/token`,
      { method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/x-www-form-urlencoded',
          'Authorization': 'Basic '+ btoa('auth-umbrella:auth-umbrella-pswd'),
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
      .catch(() => { throw new Error("Unable to refresh!")})
  },

  loggedIn() {  //<6>
    return localStorage.auth && fetch(
        //`${SERVER_URL}/parfume`,
        `${SERVER_URL}/sbarnacle/fluffy-invention/api/ping/ping-auth`,
        {headers: headers()})
        .then(checkResponseStatus)
        .then(() => { return true })
        .catch(this.refreshToken)
        .catch(() => { return false });
  }
};