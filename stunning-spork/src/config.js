import pjson from './../package.json';

export const DATA_SERVER_URL = process.env.REACT_APP_DATA_SERVER_URL;
export const OAUTH_SERVER_URL = process.env.REACT_APP_OAUTH_SERVER_URL;
export const CLIENT_VERSION = pjson.version;
export const REACT_VERSION = pjson.dependencies.react;
