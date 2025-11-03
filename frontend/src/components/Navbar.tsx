import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <menu
      className="navbar-main-div"
      style={{
        padding: "3vh 0px",
        height: "10vh",
        backgroundColor: "rgba(255,255,255,0.05",
        borderBottom: "1px solid black",
        boxShadow: "0px 0px 3px 5px rgba(0, 0, 0, 0.25)",
        display: "flex",
        flexDirection: "row",
        justifyContent: "center",
        alignItems: "end",
        columnGap: "3vw",
      }}
    >
      <img width={120} height={40} src="/sportssonar.png" alt="logo" />
      <ul
        style={{
          display: "flex",
          flexDirection: "row",
          justifyContent: "center",
          columnGap: "3vw",
        }}
      >
        <li>
          <Link
            style={{ display: "flex", alignItems: "start", columnGap: "7px" }}
            className="navbar-link"
            to="/"
          >
            <img src="/home.svg" alt="home nav button icon" />
            HOME
          </Link>
        </li>
        <li>
          <Link
            style={{ display: "flex", alignItems: "start", columnGap: "7px" }}
            className="navbar-link"
            to="/calendar"
          >
            <img src="/calendar.svg" alt="calendar nav button icon" />
            CALENDAR
          </Link>
        </li>
      </ul>
    </menu>
  );
}
