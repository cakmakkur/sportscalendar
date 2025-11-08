# Sports-Calendar

Sports-Calendar is a mini full-stack app, that I created for the coding task of Sportsradar Code Academy 2025. Users can browse different sports events in simple calendar app, see event details like the venue information and the livestream url, filter the events as needed and also create new events.

Since I took the Backend-Challenge, my main focus lied on backend, which I created as a Maven/Java/SpringBoot project. It has a layered structure. Service class methods handle various operations on data in connection to a Postgres DB, validate and sanitize requests and handle errors and exceptions. Controllers expose api endpoints and provide frontend with necessary data. Models represent the databank entities. Pom File includes the necessary dependencies for the project and also build configuration to include frontend in the jar. Global error handling ensures correct and informative HTTP Responses at the endpoints. WebConfiguration eliminates CORS-Problems while running the app in development. Data is handled by the DTO mappers between the user and the databank. Other runtime configurations can be found in .properties files. Tests use an in-memory H2 databank.

Frontend is a Vite+React/TypeScrpt project. It has a component-based architechture. Services handle data fetching, models are used to structure the fetched data, pages present them using components. Clean, user-friendly UI design includes a homepage, basic navigation and event display/creation pages. It features minimal UI for success/error/loading messages. It uses a fictional AI-Generated logo and brand name, google icons and a well-known color scheme as public resources. Dependencies, scripts, etc... are defined in package.json. A single 'main css' file compliments the inline styles. It uses semantic tags in html and a few symbolic useful tags for SEO.

## Setup Options

After cloning the repository, user needs the following locally installed:

- JDK (at least 21)
- Maven
- PostgreSQL

The PostgreSQL driver should be up and running on port 5432. User needs to create a new databank and name it to 'sportscalendar' with owner 'postgres'. The application will populate it later.

**Example:**

```sql
CREATE DATABASE sportscalendar OWNER postgres;
```

### 1) To build this project and run the jar

Executing following at `/backend`:

```bash
mvn clean package
```

will create a jar file of the full app at `/backend/target`, which can be run by executing:

```bash
java -jar <jar-name>
```

Upon successful start, the databank will be populated. The app can be viewed directly on http://localhost:8080

### 2) To run it directly from the source code

User also needs following to start the frontend in development mode:

- Node.js
- npm

1. Executing following at `/backend`:

```bash
mvn spring-boot:run
```

starts the server on port 8080

2. Executing following at `/frontend`:

```bash
npm install
npm run dev
```

will install frontend dependencies and start the it on http://localhost:5173
