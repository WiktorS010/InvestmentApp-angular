# Investment Simulation Application

## Project Overview
This project provides a platform for users to simulate cryptocurrency investments. By utilizing the CoinMarketCap API, the application fetches real-time data such as coin prices, market capitalization, and symbols to enable realistic simulations. It is a CRUD-based application, allowing users to create, read, update, and delete investment records.

### Key Features
- **Cryptocurrency Data Integration**: Fetches live data from the CoinMarketCap API, including coin prices and market caps.
- **CRUD Functionality**: Users can create, read, update, and delete investment entries to track and manage their simulation portfolio.
- **Exception Handling**: Custom exception handling classes ensure robust and user-friendly error management.
- **Unit Testing**: The application includes unit tests written using Mockito to maintain high code quality and reliability.

## Tech Stack
- **Backend**: Spring Boot (Java 17)
- **Database**: MySQL
- **Build Tool**: Maven

### Dependencies
Below are the key dependencies used in this project:
- **Spring Boot Starter Data JPA**: For database interactions
- **Spring Boot Starter Web**: To build RESTful APIs
- **MySQL Connector**: For MySQL database connectivity
- **Lombok**: To reduce boilerplate code
- **Log4j**: For application logging
- **JUnit and Mockito**: For unit testing

## Project Structure
The project follows a standard Spring Boot structure:
```
src/main/java
  |- com.example.investmentapp
    |- controller
    |- service
    |- repository
    |- exception
    |- model

src/test/java
  |- com.example.investmentapp
    |- unit tests (Mockito)
```

## Installation and Setup
1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```bash
   cd InvestmentApp-backend
   ```
3. Set up the database:
   - Create a MySQL database.
   - Configure the database URL, username, and password in `application.properties`.
4. Build and run the project:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
5. The application will be available at `http://localhost:8080` or I recommend to check it by Postman.

## Testing
Run the unit tests with:
```bash
mvn test
```
