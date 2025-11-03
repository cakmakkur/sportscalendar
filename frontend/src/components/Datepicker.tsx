const extractMonth = (d: Date) => {
  const arr = d.toDateString().split(" ");
  return arr.slice(1, 2);
};

const extractDayOfMonth = (d: Date) => {
  const arr = d.toDateString().split(" ");
  return arr.slice(2, 3);
};

interface PropTypes {
  currentDate: Date;
  onChange: (d: Date) => void;
}

// datepicker UI component.
export default function Datepicker({ currentDate, onChange }: PropTypes) {
  const handleNext = () => {
    const newDate = new Date(currentDate);
    newDate.setDate(newDate.getDate() + 1);
    onChange(newDate);
  };

  const handlePrevious = () => {
    const newDate = new Date(currentDate);
    newDate.setDate(newDate.getDate() - 1);
    onChange(newDate);
  };

  return (
    <div
      style={{ display: "flex", flexDirection: "row", position: "relative" }}
    >
      <img
        className="datepicker-arrow"
        onClick={handlePrevious}
        style={{ width: "60px", zIndex: "10" }}
        src="/chevron_left_40dp_F3F3F3_FILL0_wght400_GRAD0_opsz40.svg"
        alt="datepicker previous button"
      />

      <div className="datepicker-display">
        <img
          style={{ width: "200px", height: "200px" }}
          src="/calendar-page-with-alpha.png"
          alt="calendar image to show the selected date"
        />
        <div className="calendar-image-date">
          <span>{extractDayOfMonth(currentDate)}</span>
          <span>{extractMonth(currentDate)}</span>
        </div>
      </div>
      <img
        className="datepicker-arrow"
        onClick={handleNext}
        style={{ width: "60px", zIndex: "10" }}
        src="/chevron_right_40dp_F3F3F3_FILL0_wght400_GRAD0_opsz40.svg"
        alt="datepicker next button"
      />
    </div>
  );
}
