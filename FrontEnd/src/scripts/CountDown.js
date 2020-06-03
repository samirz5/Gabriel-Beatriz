import React, { Component } from "react";
import "../App.css";
import Stopwatch from "./Stopwatch";

class Countdown extends Component {
    state = {
        timerOn: false,
        timerStart: 0,
        timerTime: 0
    };

    componentDidMount() {
        this.startTimer();
    }

    componentWillMount() {

    }

    startTimer = () => {
        this.setState({
            timerOn: true,
            timerTime: this.state.timerTime,
            timerStart: this.state.timerTime
        });
        const { timerTime, timerOn } = this.state;
        this.setState({ timerTime: timerTime + 4000 });
        this.timer = setInterval(() => {
            const newTime = this.state.timerTime - 10;
            if (newTime >= 0) {
                this.setState({
                    timerTime: newTime
                });
            } else {
                clearInterval(this.timer);
                this.setState({ timerOn: false });
            }
        }, 10);
    };

    render() {
        const { timerTime, timerStart, timerOn } = this.state;
        let seconds = ("" + (Math.floor((timerTime / 1000) % 60) % 60)).slice(-2);

        return (
            <div className="Countdown">
                <div className="Countdown-display">
                    <div className="Countdown-time">
                        {seconds}
                    </div>
                </div>

            </div>
        );
    }
}

export default Countdown;