import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Form} from "react-bootstrap";
import BackgroundBtn from "../images/6-chalk-banner-1.png";
import Background from "../images/background.jpg"
import {Link} from "react-router-dom";
import Button from "react-bootstrap/Button";
import AvatarBlue from "../images/Avatars/characterBlue.png"
import AvatarGreen from "../images/Avatars/characterGreen.png"
import AvatarOrange from "../images/Avatars/characterOrange.png"
import AvatarPink from "../images/Avatars/characterPink.png"
import AvatarRed from "../images/Avatars/characterRed.png"
import AvatarYellow from "../images/Avatars/characterYellow.png"

class CreateAccount extends Component {

    state = {
        username: '',
        password: '',
        teacherId: false,
        avatarUrl: 0,
        firstName: '',
        lastName: ''
    };

    constructor(props) {
        super(props);

        this.setUsername = this.setUsername.bind(this);
        this.setPassword = this.setPassword.bind(this);
        this.setTeacherId = this.setTeacherId.bind(this);
        this.setAvatarUrl = this.setAvatarUrl.bind(this);
        this.setFirstName = this.setFirstName.bind(this);
        this.setLastName = this.setLastName.bind(this);
    }

    setUsername(event) {
        const target = event.target;
        const value = target.value;
        this.setState({
            username: value
        })
    }

    setPassword(event) {
        const target = event.target;
        const value = target.value;
        this.setState({
            password: value
        })
    }

    setTeacherId() {
        this.setState({
            teacherId: !this.state.teacherId
        })
    }

    setAvatarUrl(avatar) {
        this.setState({
            avatarUrl: avatar
        })
    }

    setFirstName(event) {
        const target = event.target;
        const value = target.value;
        this.setState({
            firstName: value
        })
    }

    setLastName(event) {
        const target = event.target;
        const value = target.value;
        this.setState({
            lastName: value
        })
    }

    render() {
        return (
            <div className="Layout"
                 style={{backgroundImage: "url(" + Background + ")", backgroundSize: "cover", paddingTop: "2rem"}}>

                <div style={{paddingBottom: "1.3rem"}}>
                    <Button style={{background: "transparent", border: "none"}}
                            onClick={() => this.setAvatarUrl("0")}>
                        <img src={AvatarBlue} width="100" alt=""/>
                    </Button>
                    <Button style={{background: "transparent", border: "none"}}
                            onClick={() => this.setAvatarUrl("1")}>
                        <img src={AvatarGreen} width="100" alt=""/>
                    </Button>
                    <Button style={{background: "transparent", border: "none"}}
                            onClick={() => this.setAvatarUrl("2")}>
                        <img src={AvatarOrange} width="100" alt=""/>
                    </Button>
                    <Button style={{background: "transparent", border: "none"}}
                            onClick={() => this.setAvatarUrl("3")}>
                        <img src={AvatarPink} width="100" alt=""/>
                    </Button>
                    <Button style={{background: "transparent", border: "none"}}
                            onClick={() => this.setAvatarUrl("4")}>
                        <img src={AvatarYellow} width="100" alt=""/>
                    </Button>
                    <Button style={{background: "transparent", border: "none"}}
                            onClick={() => this.setAvatarUrl("5")}>
                        <img src={AvatarRed} width="100" alt=""/>
                    </Button>
                </div>

                <Form>
                    <Form.Group>
                        <Form.Label>Gebruikersnaam</Form.Label>
                        <Form.Control className="input" type="text" onChange={this.setUsername}
                                      value={this.state.username}/>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Wachtwoord</Form.Label>
                        <Form.Control className="input" type="password" onChange={this.setPassword}
                                      value={this.state.password}/>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Voornaam</Form.Label>
                        <Form.Control className="input" type="text" onChange={this.setFirstName}
                                      value={this.state.firstName}/>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Achternaam</Form.Label>
                        <Form.Control className="input" type="text" onChange={this.setLastName}
                                      value={this.state.lastName}/>
                    </Form.Group>


                </Form>
                <Form>

                    <div className="checkBoxDocent">
                        <Form.Check id="checkbox" style={{bottom: "0.3rem"}} onChange={this.setTeacherId}/>
                        <p style={{fontSize: 20}}>Docent</p>
                    </div>

                    <div className="btnLogin">
                        <Button
                            onClick={() => this.props.onCreateUser("create", this.state.username, this.state.password, this.state.teacherId, this.state.avatarUrl, this.state.firstName, this.state.lastName)}
                            className="btn btn-default"
                            style={{background: "transparent", border: "none"}}>
                            <img src={BackgroundBtn} width="250" alt=""/>
                            <div className="txtLogin">
                                Maak account
                            </div>
                        </Button>
                    </div>

                    <div style={{position: "fixed", right: "0", top: "0", paddingTop: "1rem", paddingRight: "1rem"}}>
                        <Link to="/" className="btn btn-default">
                            <img src={BackgroundBtn} width="170" alt=""/>
                            <div className="txtCreateAccount">
                                Terug
                            </div>
                        </Link>
                    </div>

                </Form>
            </div>
        );
    }
}

export default CreateAccount;