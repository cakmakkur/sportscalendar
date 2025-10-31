import { Route, Routes } from "react-router-dom";
import "./App.css";
import Home from "./pages/Home";
import Calendar from "./pages/Calendar";
import Layout from "./Layout";

export default function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="/calendar" element={<Calendar />} />
        </Route>
      </Routes>
    </>
  );
}
