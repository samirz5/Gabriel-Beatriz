import React, {Component} from "react";
import Background from "../images/background.jpg";
import HeartFull from "../images/fullHeart.png"
import {Link} from "react-router-dom";
import BackgroundBtn from "../images/6-chalk-banner-1.png";
import Stopwatch from "../scripts/Stopwatch";
import SockJS from "sockjs-client";
import * as Stomp from "@stomp/stompjs";
import {CanvasHandler} from '../scripts/CanvasHandler';
import {QuestionModal} from "../components/QuestionModal";

const dimensions = {
    height: 700,
    width: 1200
};

let stompClient = null;
let canvasHandler = null;

class Level extends Component {

    constructor(props) {
        super(props);
        this.state = {
            level: 1,
            maze: [],
            position: {
                x: 1,
                y: 10
            },
            direction: '',
            playersLevel: [],
            timer: 0,
            avatars: null,
            images: null,
            lives: 3,
            question: '',
            rightAnswer: '',
            show: false
        };
    }


    componentDidMount() {
        this.connect();
        canvasHandler = new CanvasHandler(
            document.getElementById('canvasMaze').getContext('2d'),
            document.getElementById('canvasAvatars').getContext('2d'),
            dimensions
        );
        window.addEventListener('keydown', this.handleKeyInput.bind(this));
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        //console.log(this.state.maze, 'STATE UPDATED');
        if (this.state.maze !== prevState.maze) {
            canvasHandler.drawMaze(this.state.maze);
        }
        canvasHandler.drawAvatar(
            this.state.maze,
            this.state.playersLevel,
            this.props.dalUser
        );
    }

    handleKeyInput = (e) => {
        const code = e.keyCode ? e.keyCode : e.which;
        if (code === 37 || code === 65) {
            this.setState({
                direction: 'LEFT'
            });
            sendDirectionMessage(this);
        } else if (code === 38 || code === 87) {
            this.setState({
                direction: 'UP'
            });
            sendDirectionMessage(this);
        } else if (code === 39 || code === 68) {
            this.setState({
                direction: 'RIGHT'
            });
            sendDirectionMessage(this);
        } else if (code === 40 || code === 83) {
            this.setState({
                direction: 'DOWN'
            });
            sendDirectionMessage(this);
        }

        console.log(this.props.dalUser, 'dalUser')

        function sendDirectionMessage(that) {
            const message = {
                playerId: that.props.dalUser.playerId,
                timer: that.state.timer,
                direction: that.state.direction,
                avatarUrl: that.props.dalUser.avatarUrl
            };
            that.sendMessage('/app/updateposition', message);
        }
    };

    connect = () => {
        let _this = this;
        const socket = new SockJS(this.props.url + '/game');
        stompClient = Stomp.Stomp.over(socket);

        stompClient.connect({}, function (frame) {

            stompClient.subscribe('/topic/level', (messageOutput) => {
                _this.handleMessage(messageOutput);
            });

            _this.sendMessage('/app/game/connect', {})  // todo
        });
    };

    tryAnswer = (playerAnswer) => {

        let correct = false;

        if (playerAnswer !== "" && parseInt(playerAnswer) === parseInt(this.state.rightAnswer)) {
            correct = true;

            this.setState({
                show: false
            });
        }

        if (!correct) {
            const lives = this.state.lives;
            const newLives = lives - 1;
            this.setState({
                lives: newLives
            });
        }
    }

    /**
     * hier moeten posities afgehandeld worden die van backend teruggestuurd worden.
     * @param message
     */
    handleMessage(message) {
        let content = (JSON.parse(message.body));
        let answer = "";

        console.log(content)

        if (content.players.length > 0){
            content.players.forEach(p => {
                if (p.playerId === this.props.dalUser.playerId) {
                    this.setState({
                        playersLevel: content.players,
                        maze: p.maze
                    });
                }
            });
            console.log('updated players and maze')
        }

            /**
             * Ontvang message met pop up vragen per persoon
             */
            if (content.fieldState === 'OBSTACLE' && content.id === this.props.dalUser.playerId)
                this.setState({
                    question: content.questionToUse,
                    rightAnswer: content.answer,
                    show: true
                });

            const returnMessage = {
                playerId: this.props.dalUser.playerId
            };
            this.sendMessage('/app/removeObstacle', returnMessage);

            let posYY = 0;
            let posXX = 0;
            for (let i = 0; i < this.state.playersLevel.length; i++) {
                if (this.state.playersLevel[i].playerId === this.props.dalUser.playerId) {
                    posXX = this.state.playersLevel[i].position[0];
                    posYY = this.state.playersLevel[i].position[1];
                }
            }
            this.setState({
                maze: canvasHandler.removeObstacle(this.state, dimensions, posXX, posYY)
            });


        /**
         * Message ontvangen wanneer iemand gefinished is
         */
        //todo: elk persoon krijgt te zien wie gefinished is en speler moe niet meer kunnen bewegen
        if (content.fieldState === 'FINISHED') {
            window.alert(this.props.dalUser.userName + " has finished");//todo moet van message gebruiker opgehaald worden niet de opgeslagen gebruiker
            console.log("Finished message")
        }


        if (content.fieldState === 'ALLFINISHED') {
            content.players.forEach(p => {
                if (p.playerId === this.props.dalUser.playerId) {
                    this.setState({
                        maze: p.maze
                    });
                }
            });
        }
    }


    sendMessage = (endPoint, msg) => {
        stompClient.send(endPoint, {}, JSON.stringify(msg));
    };

    onChange = (newTime) => {
        this.setState({timer: newTime});
    };

    showLives = () => {
        let hearts = [];

        for (let i = 0; i < this.state.lives; i++) {
            hearts.push(<img src={HeartFull} alt="Life" height="42" width="42"/>)
        }
        return hearts
    };

    render() {
        return (
            <div className="Layout"
                 style={{backgroundImage: "url(" + Background + ")", backgroundSize: "cover", paddingTop: "3rem"}}>

                <header style={{paddingBottom: "3rem", fontSize: "40px"}}>
                    Level {this.state.level}
                </header>

                <div className="Timers"
                     style={{position: "fixed", left: "0", top: "0", paddingTop: "2rem", paddingLeft: "2rem"}}>
                    <Stopwatch timer={this.state.timer} onTimerChange={this.onChange}/>
                </div>
                <div className="row">
                    <div className="grid">
                        <div className="canvasWrapper">
                            <canvas id="canvasMaze" width={dimensions.width} height={dimensions.height}/>
                        </div>
                        <div className="canvasWrapper">
                            <canvas id="canvasAvatars" width={dimensions.width} height={dimensions.height}/>
                        </div>
                    </div>
                    <div className="flex-column" style={{paddingLeft: "3rem"}}>
                        <div className="row" style={{paddingBottom: "1rem"}}>
                            Score: 0
                        </div>
                        <div className="row">
                            {this.showLives()}
                        </div>
                    </div>
                    {this.state.show ? (
                        <QuestionModal question={this.state.question}
                                       onTryAnswer={this.tryAnswer}/>) : null}
                </div>
                <div style={{position: "fixed", right: "0", top: "0", paddingTop: "1rem", paddingRight: "1rem"}}>
                    <Link to="/startGame" className="btn btn-default">
                        <img src={BackgroundBtn} width="170" alt=""/>
                        <div className="txtCreateAccount">
                            Geef op
                        </div>
                    </Link>
                </div>
            </div>
        );
    }
}

export default Level;