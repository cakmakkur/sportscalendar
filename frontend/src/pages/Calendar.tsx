import AddEventBtn from "../components/AddEventBtn";
import Datepicker from "../components/Datepicker";

export default function Calendar() {
  return (
    <div className="calendar-main-div">
      <div className="calendar-display--left">
        <Datepicker />
        <AddEventBtn />
      </div>
      <div className="calendar-display--right"></div>
    </div>
  );
}
