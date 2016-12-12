import React from 'react';

class LoginForm extends React.Component {

    constructor() {
        super();
        this.state = {
            password: '',
            username:'',
            clientType: ''
        };
    }

    render() {
        return (
            <div className="login">

                <header className="login-header"><span className="text">LOGIN</span><span className="loader"></span></header>
            <form className="login-form" onSubmit={this._handleSubmit.bind(this)}>

                <input className="login-input" type="text" placeholder="Username" ref={c => this.username = c} onChange={this._getCharacterCount.bind(this)} />
                <p>{this.state.username.length} letters</p>

                <input  className="login-input" type="password" placeholder="Password" ref={c => this.password = c} onChange={this._getCharacterCount.bind(this)}/>
                <p>{this.state.password.length} letters</p>

                    <input className="login-input" type="text" placeholder="clientType " ref={c => this.clientType = c} onChange={this._getCharacterCount.bind(this)}/>
                    <p>{this.state.clientType.length} letters</p>


                <button className="login-btn" type="submit">
                  Login Now!
                </button>

            </form>
            </div>
        );
    }

    _getCharacterCount(e) {
        this.setState({
            password: this.password.value,
            username: this.username.value,
            clientType: this.clientType.value
        });
    }

    _handleSubmit(event) {
        event.preventDefault();

        if (!this.username.value || !this.password.value) {
            alert('Please enter your username and password.');
            return;
        }

        this.props.loginUser(this.username.value, this.password.value, this.clientType.value);

        this.props
        this.username.value = '';
        this.password.value = '';
        this.clientType.value = '';
        this.setState({ password: '', username: '', clientType: '' });
    }
}

export default LoginForm;
