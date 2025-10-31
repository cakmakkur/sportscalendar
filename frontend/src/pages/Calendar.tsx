import Datepicker from "../components/Datepicker";

export default function Calendar() {
  return (
    <div className="calendar-main-div">
      <div className="calendar-display--left">
        <Datepicker />
      </div>
      <div className="calendar-display--right"></div>
    </div>
  );
}
