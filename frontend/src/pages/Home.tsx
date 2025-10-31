import { Link } from "react-router-dom";

export default function Home() {
  return (
    <div className="home-main-div">
      <img
        className="homepage-bg-image"
        src="/homepage-bg.jpg"
        alt="homepage background image"
      />
      <Link className="homepage-link" to="/calendar">
        BROWSE EVENTS
      </Link>
    </div>
  );
}
