import { Link } from "react-router-dom";

export default function Home() {
  return (
    <div className="home-main-div">
      <img
        className="homepage-bg-image"
        src="/homepage-bg.jpg"
        alt="homepage background image"
      />
      <div
        style={{
          marginRight: "10vw",
          display: "flex",
          flexDirection: "column",
          alignItems: "end",
        }}
      >
        <h1 style={{ fontSize: "4rem", color: "lightgray" }}>
          Live Your Passion
        </h1>
        <h1 style={{ color: "lightgray", marginBottom: "5vh" }}>
          Livestreams. Events. Sports.
        </h1>
        <Link className="homepage-link" to="/calendar">
          BROWSE EVENTS
        </Link>
      </div>
    </div>
  );
}
