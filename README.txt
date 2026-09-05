BOOKMYSHOW UI V2

This version is designed around the backend/entities you shared in chat:
User, City, Movie, Theater, Screen, Seat, Show, Booking.
Movie fields: title, description, genre, language, durationMinutes, rating, releaseDate, posterUrl.
Movie search is specifically supported by name. The UI first tries common search endpoints and, if they are not available, fetches all movies and filters by title in the browser.

1) Copy index.html, css/, js/ and pages/ into your UI folder.
2) Start Spring Boot on http://localhost:8080.
3) Run the UI with VS Code Live Server (recommended).
4) CORS must allow the UI origin.
5) If your controllers use different mappings, send controller code; then the endpoint adapter can be made exact.
6) Backend bug reminder from your shared code:
   BookingService: totalPrice should be seats.size() * show.getTicketPrice()
   UserService login: invalid password condition should use !equals(...)
