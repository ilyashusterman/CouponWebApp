import React from 'react';
//import { browserHistory, Router, Route, Link, withRouter } from 'react-router';
import axios from 'axios';
import LoginForm from './src/components/LoginForm';
import Dashboard from './src/components/Dashboard';

class App extends React.Component{

  constructor() {
    super();
      this.state = {
        didLoggedIn: false,
          errorMessage: ''
      };
       this.handleLogin = this.handleLogin.bind(this);
    //  this.loginSuccess = this.loginSuccess.bind(this);
  }

    loginSuccess(){
        this.setState({didLoggedIn: true});
    }

    handleLogin(username, password, clientType) {
   //  const { username, password, clientType }= event;
        let self = this;
        console.log(username, password, clientType);
        axios.post('webapi/login', {
            username: username,
            password: password,
            clientType: clientType
        })
            .then(function (response) {
                console.log(response);
             //   this.loginSuccess();
                self.setState({didLoggedIn: true});
            })
            .catch(function (error) {
                console.log(error.response.data);
                self.setState({errorMessage: error.response.data});
            });
    }

        handleLogoutClick() {
        //logout post method from backend
            let self = this;
        axios.get('webapi/logout')
            .then(function (response) {
                console.log(response);
                self.setState({didLoggedIn: false});
            })
            .catch(function (error) {
                console.log(error.response.data);
            });
    }

  render() {
      const isLoggedIn = this.state.didLoggedIn;
      const errorMessage = this.state.errorMessage;

      let renderElement = null;
      if (!isLoggedIn) {
          renderElement = <LoginForm loginUser={this.handleLogin.bind(this)} />;
      } else {
          renderElement = <Dashboard onClick={this.handleLogoutClick}  />;
      }
      let renderError= null;
      if(errorMessage == ''){
          renderError=null;
      }
      else{
          renderError=<div className="alert alert-danger" >{errorMessage}
          <a href="http://localhost:8080/CouponWebAppPhase2/">Please try again</a>
          </div>
      }
    return (
      <div>
          {renderElement}
          {renderError}
      </div>
    );
  }

}


export default App;
