import React, {Component} from "react";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

export class QuestionModal extends Component {

    state = {
        question: this.props.question,
        answer: ''
    };

    constructor(props) {
        super(props);

        this.setAnswer = this.setAnswer.bind(this);
        this.ref = React.createRef();
    }

    componentDidMount() {
        this.ref.current.focus();
    }

    setAnswer(event) {
        const target = event.target;
        const value = target.value;
        this.setState({
            answer: value
        })
    }

    _handleKeyDown = (e) => {
        if (e.key === 'Enter') {
            this.props.onTryAnswer(this.state.answer)
        }
    };

    render() {
        const props = this.props;

        return (
            <Modal
                {...props}
                size="lg"
                aria-labelledby="contained-modal-title-vcenter"
                centered
                show={true}
                onKeyDown={this._handleKeyDown}
            >
                <Modal.Header closeButton>
                    <Modal.Title id="contained-modal-title-vcenter">
                        {this.state.question}
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                        <Form.Group>
                            <Form.Label>Antwoord</Form.Label>
                            <Form.Control type="text" placeholder="Antwoord" onChange={this.setAnswer} ref={this.ref} />
                        </Form.Group>

                        <Button className="btn btn-default" onClick={() => this.props.onTryAnswer(this.state.answer)}>
                            Ok!
                        </Button>
                </Modal.Body>
            </Modal>
        )
    }
}