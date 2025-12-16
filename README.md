# Allia Health Interview Project

## Author
Bruno Costa - Software Engineer
- LinkedIn: https://www.linkedin.com/in/brunocosta91/
- GitHub: https://github.com/brunocosta91

## Description
This project is a RESTful API developed for the Allia Health interview process.

## Technology Stack
- Java 21
- Spring Framework
- H2 Database
- Maven
- JUnit & Mockito
- MapStruct
- Lombok
- Swagger/OpenAPI

## How to Run the Project

### Prerequisites
- Java 21 installed
- Maven installed

### Steps to Run Locally
1. Clone the repository:
   ```sh
   git clone https://github.com/brunocosta91/allia-health-interview
   cd allia-health-interview
   ```
2. Build the project:
   ```sh
   mvn clean install
   ```
3. Run the application:
   ```sh
   mvn spring-boot:run
   ```
   
4. Access the API at: `http://localhost:8080/swagger-ui/index.html`

### Running Tests
To execute automated tests:
```sh
mvn test
```

## Request Examples
See the `request.http` file at the root of the project for API usage examples.

---
# Knowledge-based answers

## Scalability & Reliability

### How you'd scale to 100k patients & 10M refill orders/month
To scale the application to handle 100k patients and 10M refill orders per month, I would consider the following strategies:
1. **Database Optimization**: Use a robust database system (e.g., PostgreSQL, MySQL). Implement indexing, and query optimization to handle large datasets efficiently.
2. **Load Balancing**: Deploy the application on multiple servers behind a load balancer to distribute incoming traffic evenly and ensure high availability.
3. **Caching**: Implement caching strategies to store frequently accessed data and reduce database load.

### First 3 production-readiness changes

1. **Security**: Add authentication and authorization mechanisms to secure API endpoints and protect sensitive patient data.
2. **Monitoring & Logging**: Integrate monitoring tools and centralized logging to track application performance and diagnose issues in real-time.
3. **Backups**: Set up automated database backups.

### Metrics, logs, alerts to track
- **Metrics**: API response times, error rates (4xx and 5xx), database query performance, CPU and memory usage.
- **Logs**: Centralized logging of application events, errors, and user activities.
- **Alerts**: Set up alerts for high error rates, slow response times, and resource utilization thresholds.

### Detecting 5xx spikes & slow endpoints
- Use monitoring tools (Prometheus + Grafana) to visualize and alert on 5xx error rates and endpoint latencies.
- Set up automated alerts for error rate thresholds and latency percentiles.

### Security considerations for health data
- Implement strong authentication and authorization (e.g., OAuth2, JWT).
- Encrypt sensitive data both in transit (TLS) and at rest.
- Regularly update dependencies to patch security vulnerabilities.

## Cloud & DevOps Perspective

### How you'd containerize the service
To containerize the service, I would create a Dockerfile that defines the application environment, dependencies, and how to run the application. The Dockerfile would include:
1. Base image (e.g., openjdk:21-jdk).
2. Copying the application JAR file into the container.
3. Setting the entry point to run the application.

### A simple CI/CD pipeline
I would set up a CI/CD pipeline using GitHub Actions. The pipeline would include:
1. **Build Stage**: Compile the code and run unit tests.
2. **Test Stage**: Execute integration tests and code quality checks.
3. **Deploy Stage**: Deploy the application.

### Relevant Google Cloud services
Basically use the Google Cloud services to run the containerized application, store data, and monitor performance ( Google Kubernetes Engine, Cloud SQL, etc.).
