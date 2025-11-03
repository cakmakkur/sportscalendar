import { useState } from "react";
import AddEventBtn from "../components/AddEventBtn";
import Datepicker from "../components/Datepicker";
import EventDisply from "../components/EventDisplay";
import EventCreator from "../components/EventCreator";

type DisplayMode = "show" | "add";

export default function Calendar() {
  const [displayMode, setDisplayMode] = useState<DisplayMode>("show");
  const [currentDate, setCurrentDate] = useState(new Date("2025-08-30"));
  // intentionally starting with a static date
  // because the db is only static dummy data

  const onDateChange = (d: Date) => {
    setCurrentDate(d);
  };

  return (
    <div className="calendar-main-div">
      <div className="calendar-display--left">
        <div onClick={() => setDisplayMode("show")}>
          <Datepicker currentDate={currentDate} onChange={onDateChange} />
        </div>
        <div onClick={() => setDisplayMode("add")}>
          <AddEventBtn />
        </div>
      </div>
      <div className="calendar-display--right">
        {displayMode === "add" ? (
          <EventCreator />
        ) : displayMode === "show" ? (
          <EventDisply date={currentDate} />
        ) : null}
      </div>
    </div>
  );
}
