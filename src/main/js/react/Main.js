import React from 'react'
import Cookies from 'js-cookie'

class Item extends React.Component {
    render() {
        return <h3>{this.props.item}</h3>
    }
}

class Main extends React.Component {

    constructor(props) {
        super(props)
        this.items = [];
    }

    logOut() {
        Cookies.remove('token');
        Cookies.remove("username");
        Cookies.remove("logged")
        Cookies.remove("admin");
        window.location.replace("/")
    }

    render() {
        return <div className={"logged_"+Cookies.get("admin")}>
                <h1>{Cookies.get("logged") == "1" ? "Vítejte zpátky, " + Cookies.get("username") + " !": "Prosím, přihlašte se." }</h1>
                {this.items.map((item, index) => (
                    <Item key={index} item={item} />
                ))}
                <button onClick={this.logOut}>Odhlásit</button>
            </div>
    }
}
export default Main;