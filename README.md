# Job Portal Web Application

A modern job portal built with Java Spring Boot, featuring a RESTful API, SQL database integration, and JavaScript frontend for seamless user experience.

## ✨ Features

- **RESTful API** - Built with Spring Boot for robust backend services
- **Database Integration** - SQL database support (MySQL, PostgreSQL)
- **Interactive Frontend** - JavaScript-powered user interface
- **Build Management** - Maven for dependency management and builds
- **Template Engine** - Thymeleaf for server-side rendering
- **Database Migration** - Structured SQL migration scripts

## 🔧 Prerequisites

Before you begin, ensure you have the following installed:

- **Java 17+** - Required for Spring Boot
- **Maven 3.8+** - For dependency management and builds
- **SQL Database** - MySQL, PostgreSQL, or compatible database

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/michaeljuren/job-portal-app.git
cd job-portal
```

### 2. Configure Database
Update your database settings in `src/main/resources/application.properties`:

```properties
# Example configuration
spring.datasource.url=jdbc:mysql://localhost:3397/job_portal
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Build the Project
```bash
mvn clean install
```

### 4. Run the Application
```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`

## 📁 Project Structure

```
job-portal/
├── src/main/java/                    # Java source code (Spring Boot)
├── src/main/resources/
│   ├── db/migration/                 # Database migration scripts (SQL)
│   ├── templates/                    # Thymeleaf templates
│   ├── static/                       # Static assets (CSS, JS, images)
│   └── application.properties        # Application configuration
├── src/test/                         # Test files
├── target/                           # Build output
└── pom.xml                          # Maven configuration
```

## 🧪 Running Tests

Execute the test suite with:

```bash
mvn test
```

## 🚀 Deployment

### Production Build
```bash
mvn clean package
```

This generates a JAR file in the `target/` directory.

### Deployment Steps

1. **Environment Setup**
    - Ensure target server has Java 17+, Maven, and SQL database
    - Configure production database settings

2. **Application Deployment**
   ```bash
   # Transfer JAR to server
   scp target/job-portal-*.jar user@server:/path/to/app/
   
   # Run the application
   java -jar job-portal-*.jar
   ```

3. **Production Configuration**
   ```bash
   # Run with production profile
   java -jar job-portal-*.jar --spring.profiles.active=prod
   ```

4. **Verification**
    - Verify application is accessible via server IP/domain
    - Check logs for any startup issues

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.

