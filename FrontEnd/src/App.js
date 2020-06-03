import React, {Component} from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Login from './pages/Login.js';
import {HashRouter, Route} from "react-router-dom";
import CreateAccount from "./pages/CreateAccount";
import Level from "./pages/Level";
import GameMode from "./pages/GameMode";
import Lobby from "./pages/Lobby"
import Room from "./pages/Room"
import CreateRoom from "./pages/CreateRoom"

const url = 'http://localhost:9000';

class App extends Component {

    state = {
        dalUser: null
    };

    constructor(props) {
        super(props);

        this.loginOrCreateUser = this.loginOrCreateUser.bind(this);
    }

    componentDidMount() {

    }

    render() {
        return (
            <HashRouter>
                <div className="App">
                    <Route path="/" exact
                           render={(props) => <Login {...props} onCreateUser={this.loginOrCreateUser}/>}/>
                    <Route path="/startGame" render={(props) => <GameMode {...props} dalUser={this.state.dalUser}/>}/>
                    <Route path="/createAccount"
                           render={(props) => <CreateAccount {...props} onCreateUser={this.loginOrCreateUser}/>}/>
                    <Route path="/singlePlayer" render={(props) => <Level {...props} dalUser={this.state.dalUser} url={url}/>}/>
                    <Route path="/lobby" render={(props) => <Lobby {...props} dalUser={this.state.dalUser}/>}/>
                    <Route path="/room" render={(props) => <Room {...props} dalUser={this.state.dalUser} url={url}/>}/>
                    <Route path="/createRoom"
                           render={(props) => <CreateRoom {...props} dalUser={this.state.dalUser}/>}/>
                </div>
            </HashRouter>
        );
    }

    loginOrCreateUser(loginOrCreate, username, password, teacherId, avatarUrl, firstName, lastName) {
        switch (loginOrCreate) {
            case "login":
                fetch(url + '/login?username=' + username + '&password=' + password)
                    .then(res => res.json())
                    .then((data) => {
                        this.setState(
                            {dalUser: data},
                            () => {
                                console.log('receivedUser:' + this.state.dalUser);
                                if (this.state.dalUser.playerId !== -1) {
                                    sessionStorage.setItem("dalUserStorage", JSON.stringify(this.state.dalUser));
                                    window.location.replace('#/startGame');
                                } else {
                                    window.alert("Je hebt nog geen account!");
                                }
                            });
                    }).catch(console.log)
                break;
            case "create":
                fetch(url + '/create?username=' + username + '&password=' + password + '&teacherId=' + teacherId + '&avatarUrl=' + avatarUrl + '&firstName=' + firstName + '&lastName=' + lastName)
                    .then(res => res.json())
                    .then((data) => {
                        this.setState(
                            {dalUser: data},
                            () => {
                                console.log('receivedUser:' + this.state.dalUser);
                                window.location.replace('#/startGame');
                            }
                        );
                    }).catch(console.log)
                break;
        }
    }
}

export default App;
