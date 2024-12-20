Meeting Room Booking API

This project provides a RESTful API to manage meeting room bookings, including viewing booking history, checking room availability, creating new bookings, and confirming seat reservations. The API is built using Spring Boot and includes various endpoints for handling booking-related operations.

Features

Retrieve Booking History: Fetch past and current bookings based on employee context.

Check Room Availability: Determine whether a room is available for booking.

Create New Booking: Allow employees to book meeting rooms.

Confirm Seat Reservations: Reserve a seat for a specific booking.

Prerequisites

To run this project, ensure you have the following installed:

Java 17 or higher

Maven

An IDE like IntelliJ IDEA or Eclipse

Build the Project:

mvn clean install

Run the Application:

mvn spring-boot:run


Endpoints

1. Get Booked Rooms

Endpoint: /workspot/room/bookings

Method: GET

Description: Fetches booking history for the logged-in employee or all booked rooms if no employee context is found.

Response:

200 OK: Returns booking history or a list of booked rooms.

2. Check Room Availability

Endpoint: /workspot/room/is-room-available

Method: GET

Description: Checks whether a meeting room is available for booking.

3. Create a New Booking

Endpoint: /workspot/room/create-booking

Method: POST

Description: Creates a new booking for a meeting room.

Request Body:

{
  "roomName": "Room 101",
  "date": "2024-12-20",
  "timeSlot": "10:00-11:00",
  "employeeId": "1234"
}

Response:

201 CREATED: Returns the details of the newly created booking.

4. Confirm Seat Reservation

Endpoint: /workspot/room/confirm-seat

Method: PUT

Description: Confirms a seat reservation for a specific booking.

Request Body:

{
  "bookingId": "5678",
  "seatNumber": "A5"
}

Response:

200 OK: Seat reservation confirmed.

Technologies Used

Spring Boot: For building the RESTful API.

Java 17: Programming language.

Maven: Dependency management.
