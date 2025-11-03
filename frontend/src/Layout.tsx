import { Outlet } from "react-router-dom";
import Navbar from "./components/Navbar";

// basic global layout for the UI
export default function Layout() {
  return (
    <div className="layout-main">
      <Navbar />
      <Outlet />
    </div>
  );
}
