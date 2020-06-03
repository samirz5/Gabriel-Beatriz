import React, {Component} from "react";
import "../App.css";

class Stopwatch extends Component {
    state = {
        timerOn: false,
        timerStart: 0,
        timerTime: 0
    };

    startTimer = () => {
        this.setState({
            timerOn: true,
            timerTime: this.state.timerTime,
            timerStart: Date.now() - this.state.timerTime
        });
        this.timer = setInterval(() => {
            this.setState({
                timerTime: Date.now() - this.state.timerStart
            });
            this.props.onTimerChange(this.state.timerTime)
        }, 10);
    };

    componentDidMount() {
        this.startTimer()
    }

    // stopTimer = () => {
    //     this.setState({ timerOn: false });
    //     clearInterval(this.timer);
    // };
    //
    // resetTimer = () => {
    //     this.setState({
    //         timerStart: 0,
    //         timerTime: 0
    //     });
    // };

    render() {
        const {timerTime} = this.state;
        let centiseconds = ("0" + (Math.floor(timerTime / 100) % 100)).slice(-1);
        let seconds = ("0" + (Math.floor(timerTime / 1000) % 60)).slice(-2);
        let minutes = ("0" + (Math.floor(timerTime / 60000) % 60)).slice(-2);
        return (
            <div>
                <div>Tijd</div>
                <div>
                    {minutes} : {seconds} : {centiseconds}
                </div>
            </div>
        );
    }
}

export default Stopwatch;
