export default () => {
  return {
    'Content-Type': 'application/json; charset=utf-8',
    'Authorization': `Bearer ${localStorage.auth ? JSON.parse(localStorage.auth).access_token : null}`
  }
}