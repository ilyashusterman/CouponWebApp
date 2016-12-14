
/**
 * Created by ilya on 12/12/2016.
 */
import React from 'react';
import Admin from './Admin';
import Company from './Company';
import Customer from './Customer';

class Dashboard extends React.Component {

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
                {user}
            </div>
        );
    }

}

export default Dashboard;