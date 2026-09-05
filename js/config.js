// Your Spring Boot runs on port 8080.
// The adapter in api.js tries common controller paths and falls back to client-side filtering.
// If you share your Controller classes later, these can be made 100% exact.
const API_BASE = "http://localhost:8080";
const ENDPOINTS = {
  movies: ["/api/movies","/movies","/api/movie","/movie"],
  movieSearch: ["/api/movies/search","/movies/search","/api/movie/search","/movie/search"],
  cities: ["/api/cities","/cities"],
  theaters: ["/api/theaters","/theaters","/api/theater","/theater"],
  shows: ["/api/shows","/shows"],
  bookings: ["/api/bookings","/bookings"],
  users: ["/api/users","/users"],
  login: ["/api/users/login","/users/login","/api/login","/login"],
  register: ["/api/users/register","/users/register","/api/users/register","/register"]
};
