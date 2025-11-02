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
      }}
    >
      <ul
        style={{
          display: "flex",
          flexDirection: "row",
          justifyContent: "center",
          columnGap: "2vw",
        }}
      >
        <li>
          <Link className="navbar-link" to="/">
            HOME
          </Link>
        </li>
        <li>
          <Link className="navbar-link" to="/calendar">
            CALENDAR
          </Link>
        </li>
      </ul>
    </menu>
  );
}
