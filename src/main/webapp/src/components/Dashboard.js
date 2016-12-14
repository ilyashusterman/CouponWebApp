
/**
 * Created by ilya on 12/12/2016.
 */
import React from 'react';
import Admin from './Admin';
import Company from './Company';
import Customer from './Customer';

class Dashboard extends React.Component {


    constructor() {
        super();
        this.state = {
            timesPressed: 0
        };
        this.onPressCounter=this.onPressCounter.bind(this);
    }
    onPressCounter(){
        this.setState({
            timesPressed: this.state.timesPressed+1
        });
    }
    renderUser(){
        let user = null;
        switch (this.props.clientType) {
            case 'admin': user = <Admin />; break;
            case 'company': user = <Company />; break;
            case 'customer': user = <Customer />; break;
            default : break;
        }
        return user;
    }

    render() {
        let user = this.renderUser();
        return (
            <div>
                <ul className="nav nav-pills" role="tablist">
                    <li role="presentation" className="active"><a onClick={this.onPressCounter}>Home <span className="badge">{this.state.timesPressed}</span></a></li>
                    <li role="presentation"><a href="#">Profile</a></li>
                    <li role="presentation"><a onClick={this.props.onLogoutClick}>Logout<span className="badge"></span></a></li>
                </ul>
                {user}
            </div>
        );
    }

}

export default Dashboard;