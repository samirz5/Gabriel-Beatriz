import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Background from "../images/background.jpg"
import {Link} from "react-router-dom";
import BackgroundBtn from "../images/6-chalk-banner-1.png";

class Lobby extends Component {
    render() {
        return (
            <div className="Layout"
                 style={{backgroundImage: "url(" + Background + ")", backgroundSize: "cover", paddingTop: "5rem"}}>

                <header style={{paddingBottom: "1.5rem", fontSize: "40px"}}>
                    Lobby
                </header>

                <table>
                    <tbody>
                    <tr>
                        <th style={{padding: "1rem", paddingRight: "5rem"}}>
                            Kamers
                        </th>
                        <th>
                            Aantal spelers
                        </th>
                    </tr>
                    <tr>
                        <td style={{padding: "1rem", paddingRight: "5rem"}}>
                            Kamer 1
                        </td>
                        <td>
                            0/4 spelers
                        </td>
                        <td style={{paddingLeft: "5rem"}}>
                            <Link to="/room" className="btn btn-default" className="txtWhiteText">
                                Doe mee!
                            </Link>
                        </td>
                    </tr>
                    <tr>
                        <td style={{padding: "1rem", paddingRight: "5rem"}}>
                            Kamer 2
                        </td>
                        <td>
                            0/4 spelers
                        </td>
                        <td style={{paddingLeft: "5rem"}}>
                            <Link to="/room" className="btn btn-default" className="txtWhiteText">
                                Doe mee!
                            </Link>
                        </td>
                    </tr>
                    <tr>
                        <td style={{padding: "1rem", paddingRight: "5rem"}}>
                            Kamer 3
                        </td>
                        <td>
                            0/4 spelers
                        </td>
                        <td style={{paddingLeft: "5rem"}}>
                            <Link to="/room" className="btn btn-default" className="txtWhiteText">
                                Doe mee!
                            </Link>
                        </td>
                    </tr>
                    </tbody>
                </table>

                <div className="btnLogin">
                    <Link to="/createRoom" className="btn btn-default">
                        <img src={BackgroundBtn} width="250" alt=""/>
                        <div className="txtCreateAccount">
                            Nieuwe kamer
                        </div>
                    </Link>
                </div>

                <div style={{position: "fixed", right: "0", top: "0", paddingTop: "1rem", paddingRight: "1rem"}}>
                    <Link to="/startGame" className="btn btn-default">
                        <img src={BackgroundBtn} width="170" alt=""/>
                        <div className="txtCreateAccount">
                            Terug
                        </div>
                    </Link>
                </div>

            </div>


        );
    }
}

export default Lobby;