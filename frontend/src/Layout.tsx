import { Outlet } from "react-router-dom";
import Navbar from "./components/Navbar";
import ErrorMessageBar from "./components/ErrorMessageBar";

// basic global layout for the UI
export default function Layout() {
  return (
    <div className="layout-main">
      <ErrorMessageBar />
      <Navbar />
      <Outlet />
    </div>
  );
}
