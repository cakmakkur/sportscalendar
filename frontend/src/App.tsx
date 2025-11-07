import { Route, Routes } from "react-router-dom";
import "./App.css";
import Home from "./pages/Home";
import Calendar from "./pages/Calendar";
import Layout from "./Layout";
import { ErrorContextProvider } from "./context/ErrorContext";

export default function App() {
  return (
    <>
      <ErrorContextProvider>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route index element={<Home />} />
            <Route path="/calendar" element={<Calendar />} />
          </Route>
        </Routes>
      </ErrorContextProvider>
    </>
  );
}
