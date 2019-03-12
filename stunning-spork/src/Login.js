import React from 'react';
import {Jumbotron, Row, Col, Form, FormGroup, FormControl, Button} from 'react-bootstrap';

const Login = ({userDetails, error, inputChangeHandler, onSubmit}) => {

  return (
    <Row>
      <Jumbotron>
        <h1>Welcome to the Garage</h1>
      </Jumbotron>
      <Row>
        <Col sm={4} >

          {error ? <p className="alert alert-danger">{error} </p> : null}

          <Form onSubmit={onSubmit}>
            <FormGroup>
              <div>Login</div >
              <FormControl type='text' name='username' placeholder='Username'
                           value={userDetails.username}
                           onChange={inputChangeHandler}/>
              <FormControl type='password' name='password' placeholder='Password'
                           value={userDetails.password}
                           onChange={inputChangeHandler}/>
            </FormGroup>
            <FormGroup>
              <Button  type="submit">Login</Button>
            </FormGroup>
          </Form>
        </Col>
      </Row>
    </Row>
  );
};

export default Login;