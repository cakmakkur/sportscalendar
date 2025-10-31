import { useEffect, useState } from "react";

const extractMonth = (d: Date) => {
  const arr = d.toDateString().split(" ");
  return arr.slice(1, 2);
};

const extractDayOfMonth = (d: Date) => {
  const arr = d.toDateString().split(" ");
  return arr.slice(2, 3);
};

export default function Datepicker() {
  const placeholderDate = "2025-12-05 19:15:00.000 +0100";

  const [currentDate, setCurrentDate] = useState(new Date(placeholderDate));

  useEffect(() => {
    console.log(currentDate.toDateString());
  }, [currentDate]);

  const handleNext = () => {
    setCurrentDate((prev) => {
      const next = new Date(prev);
      next.setDate(next.getDate() + 1);
      return next;
    });
  };

  const handlePrevious = () => {
    setCurrentDate((prev) => {
      const next = new Date(prev);
      next.setDate(next.getDate() - 1);
      return next;
    });
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

      <div>
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
