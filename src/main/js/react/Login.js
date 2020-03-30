import React from 'react'
import Button from './Button';
import Form from './Form';

import Cookies from 'js-cookie'



class Login extends React.Component {

    constructor(props) {
        super(props);
        this.usernameComp = React.createRef();
        this.onClick = this.onClick.bind(this);
        this.passwordComp = React.createRef();
    }

    onClick() {
        if(this.usernameComp.current.state.text == "" || this.passwordComp.current.state.text == "") {
            alert("prosím, vyplňte jméno a heslo.");
            return;
        }
        var xhttp = new XMLHttpRequest();
        xhttp.open("GET", "/login/"+this.usernameComp.current.state.text+"/"+this.passwordComp.current.state.text, false);
        xhttp.send();
        var token = xhttp.response;
        console.log(xhttp.status);
        if(xhttp.status == 200) {
            Cookies.set('token', token);
            Cookies.set("username", this.usernameComp.current.state.text);
            Cookies.set("logged", "1")
            xhttp.open("GET", "/api/token/"+token+"/admin", false);
            xhttp.send();
            Cookies.set("admin", xhttp.response);
            window.location.replace("page");
        } else {
            Cookies.remove('token');
            Cookies.remove("username");
            Cookies.remove("logged");
            Cookies.remove("admin");
            console.log("invalid token!");
            alert("chyba: špatné jméno/heslo");
        }
    }

    render() {
        return <div className='form-container'><Form type='text' shadow='Jméno' ref={this.usernameComp}/><Form type='password' shadow='Heslo' ref={this.passwordComp}/><Button name='Přihlásit' onClick={this.onClick}/></div>
    }

}

export default Login;