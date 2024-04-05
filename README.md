# LogikAbsensi

## Introduction
LogikAbsensi is an application designed to streamline the process of user authentication and attendance recording. It provides a user-friendly interface for logging in, displaying user information, and recording attendance with time-in and time-out functionalities.

## Application Flow

### 1. Login
- Users are required to enter their username and password.
- The application verifies the entered credentials against the database for authentication.
- Upon successful login, users are granted access to the main panel.

### 2. Main Panel
- The main panel displays the username and ID of the logged-in user.
- It offers seamless navigation to the attendance (absensi) panel.

### 3. Absensi Panel
- The absensi panel allows users to record their attendance with time-in and time-out options.
- Time-in Functionality:
  - Users can clock in by clicking the time-in button.
  - The application records the time-in data into the database, associating it with the user's ID.
- Time-out Functionality:
  - Users can clock out by clicking the time-out button.
  - The application updates the time-out data in the database for the same day as the corresponding time-in.
- Reminder:
  - Time-in is only available between 05:00 and 12:00, while time-out is permitted between 12:01 and 22:00.
- Daily Limitation:
  - Users can perform time-in and time-out actions only once per day.

## License
LogikAbsensi is licensed under the [MIT License](https://opensource.org/licenses/MIT).
