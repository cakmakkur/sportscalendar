import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <menu
      className="navbar-main-div"
      style={{ padding: "3vh 0px", height: "10vh" }}
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
