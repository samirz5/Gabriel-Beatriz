import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Form} from "react-bootstrap";
import BackgroundBtn from "../images/6-chalk-banner-1.png";
import Background from "../images/Updatedbackground.jpg"
import {Link} from 'react-router-dom'
import Button from "react-bootstrap/Button";

class Login extends Component {

    state = {
        username: '',
        password: ''
    };

    constructor(props) {
        super(props);

        this.setUsername = this.setUsername.bind(this);
        this.setPassword = this.setPassword.bind(this);
    }

    setPassword(event) {
        const target = event.target;
        const value = target.value;

        this.setState({
                password: value
            }
        )
    }

    setUsername(event) {
        const target = event.target;
        const value = target.value;

        this.setState({
            username: value
        })
    }

    render() {
        return (
            <div className="Layout"
                 style={{backgroundImage: "url(" + Background + ")", backgroundSize: "cover", paddingTop: "13rem"}}>

                <header style={{paddingBottom: "1.5rem", fontSize: "55px"}}>
                    Gabriel and Beatriz
                </header>
                <Form>
                    <Form.Group>
                        <Form.Label>Gebruikersnaam</Form.Label>
                        <Form.Control className="input" type="text" id="username" value={this.state.username} onChange={this.setUsername}/>
                    </Form.Group>

                    <Form.Group>

                        <Form.Label>Wachtwoord</Form.Label>
                        <Form.Control className="input" type="password" value={this.state.password} onChange={this.setPassword}/>
                    </Form.Group>

                    <div className="btnLogin">
                        <Button onClick={() => this.props.onCreateUser("login", this.state.username, this.state.password)} className="btn btn-default"
                                style={{background: "transparent", border: "none"}}>
                            <img src={BackgroundBtn} width="250" alt=""/>
                            <div className="txtLogin">
                                Inloggen
                            </div>
                        </Button>
                    </div>
                </Form>

                <div style={{position: "fixed", right: "0", top: "0", paddingTop: "1rem", paddingRight: "1rem"}}>
                    <Link to="/createAccount" className="btn btn-default">
                        <img src={BackgroundBtn} width="250" alt=""/>
                        <div className="txtCreateAccount">
                            Maak account
                        </div>
                    </Link>
                </div>
            </div>

        );
    }
}

export default Login;
