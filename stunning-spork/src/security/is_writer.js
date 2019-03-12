export const is_writer = () => {
  return localStorage.auth ? JSON.parse(localStorage.auth).roles.indexOf("ROLE_WRITER") > -1 : null
}