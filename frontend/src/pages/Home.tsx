import { Link } from "react-router-dom";

export default function Home() {
  return (
    <div className="home-main-div">
      <img
        className="homepage-bg-image"
        src="/homepage-bg.jpg"
        alt="homepage background image"
      />
      <article
        style={{
          marginRight: "10vw",
          display: "flex",
          flexDirection: "column",
          alignItems: "end",
        }}
      >
        <p style={{ fontSize: "4rem", color: "lightgray" }}>
          Live Your Passion
        </p>
        <h1 style={{ color: "lightgray", marginBottom: "5vh" }}>
          Livestreams. Events. Sports.
        </h1>
        <Link className="homepage-link" to="/calendar">
          BROWSE EVENTS
        </Link>
      </article>
    </div>
  );
}
