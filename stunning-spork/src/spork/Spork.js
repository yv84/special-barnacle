import React from 'react';
import {Jumbotron, Row, Col, Form, FormGroup, FormControl, Button} from 'react-bootstrap';
import headers from './../security/headers';
import 'whatwg-fetch';

class Spork extends React.Component {

  constructor() {
    super();
    

    this.state = {

      message: {
        message: ''
      },
      route: '',
      error: null

    }
  }

  componentDidMount() {

  }

  onSubmit = (event) => {
    event.preventDefault();
    console.log(event);
    const json = fetch(`http://localhost:8080/sbarnacle/fluffy-invention/api/kafka/addmessage`, {
        method: 'POST',
        headers: headers(),
        crossDomain: true,
        body: JSON.stringify(this.state.message)
    }).then((response) => {
        console.log(response);
        console.log(response.statusText);
        if (!response.ok) {
            return Promise.reject(new Error(
                'Response failed: ' + response.status + ' (' + response.statusText + ')'
            ));
        }
        return;
    }).catch(error => {
        console.error(`Fetch Error =\n`, error);
    });
    console.log(json);
    
    return json;
  }

  inputChangeHandler = (event) => {
    let {message} = this.state;
    const target = event.target;

    message[target.name] = target.value; //<1>

    this.setState({message});
  };

  render() {
      return (
        <Row>
          <Jumbotron>
            <h1>Welcome to the Site</h1>
          </Jumbotron>
          <Row>
            <Col sm={4} >
            <div>send status:</div>
              {this.state.error ? <p className="alert alert-danger">{this.state.error} </p> : null}

              <Form onSubmit={this.onSubmit}>
                <FormGroup>
                  <div>Submit message</div >
                  <FormControl type='text' name='message' placeholder='Message'
                              value={this.state.message.data}
                              onChange={this.inputChangeHandler}
                               />
                </FormGroup>
                <FormGroup>
                  <Button  type="submit">Submit</Button>
                </FormGroup>
              </Form>
            </Col>
          </Row>
        </Row>
      )
  }
}

export default Spork;
