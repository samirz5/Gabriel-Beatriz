import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Form} from "react-bootstrap";
import BackgroundBtn from "../images/6-chalk-banner-1.png";
import Background from "../images/background.jpg"
import {Link} from "react-router-dom";

class GameMode extends Component {

    setSinglePlayer() {

        localStorage.setItem("singlePlayer",JSON.stringify(this.state.singlePlayer));
    }

    render() {
        return (
            <div className="Layout"
                 style={{backgroundImage: "url(" + Background + ")", backgroundSize: "cover", paddingTop: "13rem"}}>

                <Form>
                    Welkom {this.props.dalUser.userName}
                    <div className="btnLogin">
                        <Link to={"/room"} className="btn btn-default">
                            <img src={BackgroundBtn} width="250" alt=""/>
                            <div className="txtLogin">
                                Speel alleen!
                            </div>
                        </Link>
                    </div>

                    <div className="btnLogin">
                        <Link to={"/lobby"} className="btn btn-default">
                            <img src={BackgroundBtn} width="250" alt=""/>
                            <div className="txtLogin">
                                Speel met vriendjes!
                            </div>
                        </Link>
                    </div>

                    <div style={{position: "fixed", right: "0", top: "0", paddingTop: "1rem", paddingRight: "1rem"}}>
                        <Link to="/" className="btn btn-default">
                            <img src={BackgroundBtn} width="170" alt=""/>
                            <div className="txtCreateAccount">
                                Uitloggen
                            </div>
                        </Link>
                    </div>

                </Form>
            </div>
        );

    }
}

export default GameMode;