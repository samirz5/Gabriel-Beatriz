import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Form} from "react-bootstrap";
import BackgroundBtn from "../images/6-chalk-banner-1.png";
import Background from "../images/background.jpg"
import {Link} from 'react-router-dom'
import ButtonGroup from "react-bootstrap/ButtonGroup";
import Button from "react-bootstrap/Button";

class CreateRoom extends Component {
    render() {
        return (
            <div className="Layout"
                 style={{backgroundImage: "url(" + Background + ")", backgroundSize: "cover", paddingTop: "5rem"}}>

                <header style={{paddingBottom: "3rem", fontSize: "55px"}}>
                    Nieuwe kamer
                </header>

                <Form style={{paddingBottom: "2rem"}}>
                    <Form.Group controlId="formBasicEmail">
                        <Form.Label>Kamer naam</Form.Label>
                        <Form.Control className="input" type="email"/>
                    </Form.Group>
                </Form>

                <Form style={{paddingBottom: "2rem"}}>
                    <table>
                        <tr>
                            <td>
                                <p>
                                    Level doolhof
                                </p>
                            </td>
                            <td>
                                <ButtonGroup aria-label="Basic example">
                                    <Button variant="outline-light">Makkelijk</Button>
                                    <Button variant="outline-light">Gemiddeld</Button>
                                    <Button variant="outline-light">Moeilijk</Button>
                                </ButtonGroup>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>
                                    Level rekenen
                                </p>
                            </td>
                            <td>
                                <ButtonGroup aria-label="Basic example">
                                    <Button variant="outline-light">Groep 3-4</Button>
                                    <Button variant="outline-light">Groep 5-6</Button>
                                    <Button variant="outline-light">Groep 7</Button>
                                    <Button variant="outline-light">Groep 8</Button>
                                </ButtonGroup>
                            </td>
                        </tr>
                    </table>


                    <div
                        style={{position: "fixed", right: "0", top: "0", paddingTop: "1rem", paddingRight: "1rem"}}>
                        <Link to="/lobby" className="btn btn-default">
                            <img src={BackgroundBtn} width="170" alt=""/>
                            <div className="txtCreateAccount">
                                Terug
                            </div>
                        </Link>
                    </div>

                    <div className="btnLogin">
                        <Link to="/room" className="btn btn-default">
                            <img src={BackgroundBtn} width="250" alt=""/>
                            <div className="txtLogin">
                                Maak kamer
                            </div>
                        </Link>
                    </div>
                </Form>
            </div>
        );
    }
}

export default CreateRoom;