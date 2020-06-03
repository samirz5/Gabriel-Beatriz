import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Background from "../images/background.jpg"
import {Link} from "react-router-dom";
import BackgroundBtn from "../images/6-chalk-banner-1.png";
import SockJS from "sockjs-client";
import * as Stomp from "@stomp/stompjs";
import Button from "react-bootstrap/Button";
import Check from "../images/check.png"

let stompClient = null;

class Room extends Component {

    componentDidMount() {
        this.connect();
    }

    constructor(props) {
        console.log('constructor');
        super(props);
        this.state = {
            players: [],
            begin: false
        };
    }

    getDalUser = () => {
        this.setState({
            dalUser: JSON.parse(sessionStorage.getItem("dalUserStorage"))
        });
        console.log(this.state.dalUser, 'DALUSER')
    };

    componentDidUpdate(prevProps, prevState, snapshot) {
        console.log('componentDidUpdate', this.state);
        if (prevState.dalUser !== this.state.dalUser) {
            console.log('dalUser update');
            let daluser = this.state.dalUser;
            let player = this.state.player;
            player.playerId = daluser.id;
            player.userName = daluser.userName;
            player.avatarUrl = daluser.avatarUrl;
            console.log(daluser, "DALUSER");
        }
    }

    /**
     * connect methode aangemaakt.
     * Websocket connectie word eerst gemaakt.
     * Client wordt vervolgens geregistreerd en toegevoegd in de lobby.
     * */
    connect = () => {
        let _this = this;
        const socket = new SockJS(this.props.url + '/game');
        stompClient = Stomp.Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            // Receiving message
            stompClient.subscribe('/topic/public', (messageOutput) => {
                _this.onMessageReceived(messageOutput);
            });
            const msg = _this.props.dalUser;
            _this.sendMessage('/app/connect', msg);
        });
    };

    onMessageReceived(msg) {
        const message = JSON.parse(msg.body);
        console.log(message, 'OBJ');
        if (message.begin === true) {
            sessionStorage.setItem('maze', JSON.stringify(message.players[0].maze));

            //sessionStorage.setItem('players', JSON.stringify(obj.playersLevel));
            window.location.replace('#/singleplayer');
        } else {
            this.setState({
                    players: message.players
                }
            );
        }
    }

    setReady = () => {
        const msg = this.props.dalUser;
        this.sendMessage('/app/setready', msg);
    };

    /**
     * Versturen van message naar de server.
     * @param endPoint, naar welk endpoint de message gestuurd moet worden.
     * @param msg, het te versturen message.
     */
    sendMessage = (endPoint, msg) => {
        stompClient.send(endPoint, {}, JSON.stringify(msg));
        console.log(JSON.stringify(msg), 'msg send');
    };

    render() {
        return (
            <div className="Layout"
                 style={{backgroundImage: "url(" + Background + ")", backgroundSize: "cover", paddingTop: "5rem"}}>

                <header style={{paddingBottom: "1.5rem", fontSize: "40px"}}>
                    Kamer 1
                </header>

                <table>
                    <tr>
                        <th style={{padding: "1rem"}}>Naam</th>
                        <th>Score</th>
                    </tr>
                    {this.state && this.state.players && this.createTable()}
                </table>

                <div className="btnLogin">
                    <Button className="btn btn-default" onClick={this.setReady}
                            style={{background: "transparent", border: "none"}}>
                        <img src={BackgroundBtn} width="250" alt=""/>
                        <div className="txtLogin">
                            Start!
                        </div>
                    </Button>
                </div>

                <div style={{position: "fixed", right: "0", top: "0", paddingTop: "1rem", paddingRight: "1rem"}}>
                    <Link to="/lobby" className="btn btn-default">
                        <img src={BackgroundBtn} width="170" alt=""/>
                        <div className="txtCreateAccount">
                            Terug
                        </div>
                    </Link>
                </div>
            </div>
        );
    }

    createTable = () => {
        let table = [];
        let playerList = this.state.players;
        // Outer loop to create parent
        for (let i = 0; i < playerList.length; i++) {
            let children = [];
            //Inner loop to create children
            children.push(<td>{playerList[i].userName}</td>);
            children.push(<td>{playerList[i].score}</td>);
            if (playerList[i].ready) {
                children.push(<td><img src={Check} width="40" alt=""/></td>);
            }
            //Create the parent and add the children
            table.push(<tr>{children}</tr>)
        }
        return table
    }
}

export default Room;