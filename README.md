# Email Verification REST API (Spring Boot)

A REST API built using **Spring Boot**, **Spring Data JPA**, and **JavaMailSender** that allows users to register and verify their email using a verification link.

This project demonstrates how modern applications verify user emails before allowing login.

---

## Features

* User Registration API
* Email Verification using token
* Token generation with UUID
* Email sending using Gmail SMTP
* Secure user verification flow
* MySQL database integration
* REST API tested using Postman

---

## Tech Stack

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* JavaMailSender
* Maven
* Postman

---

## Project Structure

src/main/java

```
controller
    UserController.java
service
    MailService.java
entity
    User.java
    VerificationToken.java
repository
    UserRepository.java
    TokenRepository.java
```

---

## API Endpoints

### Register User

POST /register

Request Body

```json
{
  "email": "user@gmail.com",
  "password": "123456"
}
```

Response

```
Verification email sent.
```

---

### Verify Email

GET /verify?token=your_token_here

Example

```
http://localhost:8080/verify?token=abc123xyz
```

Response

```
Email verified successfully
```

---

## Email Verification Flow

1. User registers using the `/register` API.
2. User data is saved with `verified = false`.
3. A verification token is generated using UUID.
4. The token is stored in the database.
5. A verification email with a link is sent to the user.
6. User clicks the link.
7. The `/verify` API validates the token.
8. The user's `verified` field is updated to `true`.

---

## Database Tables

### User Table

| Column   | Type    |
| -------- | ------- |
| id       | Long    |
| email    | String  |
| password | String  |
| verified | Boolean |

---

### Token Table

| Column  | Type   |
| ------- | ------ |
| id      | Long   |
| token   | String |
| user_id | Long   |

---

## Gmail SMTP Configuration

Add the following configuration in `application.properties`.

```
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password

spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

Note: Gmail requires **App Password** instead of your normal password.

---

## How to Run the Project

1. Clone the repository

```
git clone https://github.com/yourusername/email-verification-api.git
```

2. Open the project in IntelliJ / Eclipse

3. Configure MySQL database in `application.properties`

4. Run the Spring Boot application

```
mvn spring-boot:run
```

5. Test APIs using Postman

---

## Example Verification Link

```
http://localhost:8080/verify?token=abc123xyz
```

---

## Future Improvements

* Token expiration time
* Resend verification email
* Password encryption using BCrypt
* JWT Authentication
* HTML Email Template

---

## Author

Developed by **Sinoj K**

---

## License

This project is for learning and educational purposes.
