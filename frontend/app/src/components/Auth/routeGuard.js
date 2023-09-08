// routeGuard.js
export const isAuthenticated = () => {
    const token = localStorage.getItem("token"); // Retrieve the token from localStorage
    console.log('checking token ' + token)
    return !!token; // Return true if the token exists, indicating the user is authenticated
  };