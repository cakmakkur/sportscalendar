——————————————————————————————————————————————————————————————————————————————————————

# Sports-Calendar

——————————————————————————————————————————————————————————————————————————————————————

Sports-Calendar is a mini full-stack app, that I created for the coding task of Sportsradar Code Academy 2025. Users can browse different sports events in simple calendar app, see event details like the venue information and the livestream url, filter the events as needed and also create new events.

Since I took the Backend-Challenge, my main focus lied on backend, which I created as a Maven/Java/SpringBoot project. It has a layered structure. Service class methods handle various operations on data in connection to a Postgres DB, validate and sanitize requests and handle errors and exceptions. Controllers expose api endpoints and provide frontend with necessary data. Models represent the databank entities. Pom File includes the necessary dependencies for the project and also build configuration to include frontend in the jar. Global error handling ensures correct and informative HTTP Responses at the endpoints. WebConfiguration eliminates CORS-Problems while running the app in development. Data is handled by the DTO mappers between the user and the databank. Other runtime configurations can be found in .properties files. Tests use an in-memory H2 databank. Endpoints can be tested with Swagger on [http://localhost:8080/swagger-ui/index.html#](http://localhost:8080/swagger-ui/index.html#)

Postgres is used as main DBMS. schema.sql and data.sql define the create and insert statements to recreate and populate db on every start. Some columns, that are used in select queries are indexed. It is populated by AI-generated dummy data.

Frontend is a Vite/React/TypeScrpt project. It has a component-based architechture. Services handle data fetching, models are used to structure the fetched data, pages present them using components. Clean, user-friendly UI design includes a homepage, basic navigation and event display/creation pages. It features minimal UI for success/error/loading messages. It uses a fictional AI-Generated logo and company name, google icons and a well-known color scheme as public resources. Dependencies, scripts, etc... are defined in package.json. A single 'main css' file compliments the inline styles. It uses semantic tags in html and a few symbolic useful tags for SEO.

——————————————————————————————————————————————————————————————————————————————————————

## Setup Options

——————————————————————————————————————————————————————————————————————————————————————

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

will install frontend dependencies and start it on http://localhost:5173

——————————————————————————————————————————————————————————————————————————————————————

## Assumptions and Decisions during the development

——————————————————————————————————————————————————————————————————————————————————————

I chose this tech-set because I have the most practical experience with it. Throughout the project, I have tried to balance the use of frameworks/libraries versus custom code/manual work to display my skills in both approaches. Ex: I used my own SQL queries instead of JPA Syntax, preferred plain css over Bootstrap classes, created my own Dto-Mappers instead of using libraries, etc… I have also left some non-implemented methods just as placeholders to simulate a minimal CRUD setup: not implemented because they are not required by the task.

While deciding the attributes of my tables, I thought about possible advantages of denormalisation of some tables for a read-heavy app like this. Such as, denormalising events table by having ‘country name’ and ‘event_category name’ directly in there would reduce joins but introduce redundant data and integrity issues. I discussed this idea with AI, used AI to research common best practices dealing with this kind of problem in the industry. I decided to go with 3NF at the end, since it is also required for this task.

To increase the data security, I decided to use long text-based ids for the 'more sensitive' data like events and players and to use simple incrementing integers for 'less sensitive' data like countries. I created indexes for certain db columns that are used in select statements, with aim to enhance db performance.

For the frontend styles, I decided to handle every class in a single .css file due to the small scale of this project. I decided to use as much inline-styles as possible to reduce the classes in the .css file.
I decided to base my UI design on Sportradar’s color scheme, made global css variables out of them. I used google icons and an AI-generated fictional logo to create a consistent, user-friendly content in the UI. I have fine-tuned my visual elements with programs like GIMP.

I assumed, an auth mechanism would be in place for /create endpoint for events.

I decided to use AI to quickly generate uniform Javadocs and Swagger documentation for my controller classes but preferred manual comments in the service classes to describe my methods better.

During development, I decided to change a few of my initial data types of table columns to maintain consistency between entities and simplicity in my backend.

I have occasionally referenced back to my earlier works to quickly remind me of a piece of syntax, like some certain configuration syntax/boilerplate code such as in .properties and .pom file, but also boilerplate code like for creating a context in react, reseting css defaults...
