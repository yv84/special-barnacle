import React, {Component} from 'react';
import Garage from './Garage';
import Auth from './security/auth';
import Login from './Login';
import {OAUTH_SERVER_URL} from './config';
import {defaultErrorHandler} from './handlers/errorHandlers';
import {checkResponseStatus, loginResponseHandler} from './handlers/responseHandlers';


class App extends Component {

  //tag::state[]
  constructor() {
    super();

    this.state = {
      userDetails: {
        username: '',
        password: '',
        roles: ''
      },
      route: '',
      error: null
    }
  }

  reset = () => { //<1>
    this.setState({
      userDetails: {
        username: '',
        password: '',
        roles: ''
      },
      route: 'login',
      error: null
    });
  };
  //end::state[]

  //tag::lifecycle[]
  componentDidMount() {
    console.log('app mounting...');

    (async () => {
      if (await Auth.loggedIn()) {
        this.setState({route: 'garage'})
      } else {
        this.setState({route: 'login'});
      }
    })();
  }

  componentDidUpdate() {
    if (this.state.route !== 'login' && !Auth.loggedIn()) {
      this.setState({route: 'login'})
    }
  }
  //end::lifecycle[]

  //tag::inputChangeHandler[]
  inputChangeHandler = (event) => {
    let {userDetails} = this.state;
    const target = event.target;

    userDetails[target.name] = target.value; //<1>

    this.setState({userDetails});
  };
  //end::inputChangeHandler[]



  login = (e) => {
    console.log('login');
    e.preventDefault();
    fetch(`${OAUTH_SERVER_URL}/sbarnacle/oauth2/oauth/token`, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/x-www-form-urlencoded',
            'Authorization': 'Basic '+ btoa('auth-umbrella:auth-umbrella-pswd'),
        },
        body: 'grant_type=password&username=' + this.state.userDetails.username + '&password=' + this.state.userDetails.password
    }).then((response) => {
        if (!response.ok) {
            return Promise.reject(new Error(
                'Response failed: ' + response.status + ' (' + response.statusText + ')'
            ));
        }
        return response.json();
    })
    .then(checkResponseStatus) //<3>
    .then(response => loginResponseHandler(response, this.customLoginHandler)) //<4>
    .catch(error => defaultErrorHandler(error, this.customErrorHandler)); //<5>;
  };




  //tag::handler[]
  customLoginHandler = () => { //<1>
    this.setState({route: 'garage'});
  };

  customErrorHandler = (error) => { //<2>
    this.reset();
    this.setState({error: error.message});
  };
  //end::handler[]


  //tag::logout[]
  logoutHandler = () => {
    Auth.logOut();
    this.reset();
  };
  //end::logout[]


  //tag::routing[]
  contentForRoute() { //<1>
    const {error, userDetails, route} = this.state;

    const loginContent = <Login error={error} //<2>
                                userDetails={userDetails}
                                inputChangeHandler={this.inputChangeHandler}
                                onSubmit={this.login}/>;

    const garageContent = <Garage logoutHandler={this.logoutHandler}/>;

    switch (route) {
      case 'login':
        return loginContent;
      case 'garage':
        return garageContent;
      default:
        return <p>Loading...</p>;
    }
  };

  render() { //<3>
    const content = this.contentForRoute();

    return (
      <div>
        {content}
      </div>
    );
  };
  //end::routing[]
}

export default App;
