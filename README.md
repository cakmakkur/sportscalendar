# Sports-Calendar

Sports-Calendar is a mini full stack app, created by me for the coding task of Sportsradar Code Academy 2025. Users can browse different sports events in the simple calendar app, see event details like the venue and the livestream url, filter the events as needed and also create new events.

Since I took the Backend-Challenge, my main focus lied on backend, which I created as a Maven/SpringBoot project in Java. It uses a Postgres DB for persistence and uses dummy data to populate it. Frontend is a Vite+React project in TypeScript. It is mainly complimentary to my backend-task but it still has clean UI design und key functionalities.

## Setup Options

After cloning the repository, user needs the following locally installed:

- JDK (at least 21)
- Maven
- PostgreSQL

The PostgreSQL driver should be up and running on port 5432. User needs to create a new databank and name it to 'sportscalendar' with owner 'postgres'.

**Example:**

```sql
CREATE DATABASE sportscalendar OWNER postgres;
```

### 1) To build this project and run the jar

Executing following at `/backend`:

```bash
mvn clean package
```

will create a jar file at `/backend/target`, which can be run by executing:

```bash
java -jar <jar-name>
```

Upon successful start, the app can be viewed on http://localhost:8080

### 2) To run it directly from the source code

User also needs following to start the frontend in development mode:

- Node.js
- npm

Executing following at `/backend`:

```bash
mvn spring-boot:run
```

starts the server on port 8080

Executing following at `/frontend`:

```bash
npm install
npm run dev
```

will install dependencies and start the the frontend on http://localhost:5173
