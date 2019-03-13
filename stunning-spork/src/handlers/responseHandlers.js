import Auth from '../security/auth';

//<1>
export const checkResponseStatus = (response) => {
    if (response && response.status && response.status > 400) {
        let error = new Error(response);
        error.response = response;
        throw error;
    }
    if (response && response.token_type && response.token_type === "bearer") {
        return response;
    } else {
        let error = new Error(response);
        error.response = response;
        throw error;
    }
};

export const loginResponseHandler = (response, handler) => {
    Auth.logIn(response);

    if(handler) {
        handler.call();
    }
};