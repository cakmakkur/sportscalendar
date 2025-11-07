import { useEffect, useRef } from "react";
import { useErrorContext } from "../context/ErrorContext";

export default function ErrorMessageBar() {
  const { errorMessage, setErrorMessage } = useErrorContext();

  const divRef = useRef<HTMLDivElement>(null);

  const handleClick = () => {
    setErrorMessage("");
  };

  // closing error message automatically after 60 secs
  // if the user doesn"t close it manually
  useEffect(() => {
    if (errorMessage !== "") {
      setTimeout(() => {
        setErrorMessage("");
      }, 60000);
    }
  }, [errorMessage]);

  if (errorMessage === "") {
    return null;
  }

  return (
    <div className="error-message-bar" ref={divRef}>
      {errorMessage}
      <br />
      <button
        style={{
          backgroundColor: "white",
          border: "1px solid lightgray",
          borderRadius: "5px",
        }}
        onClick={handleClick}
      >
        <img src="/close.svg" alt="error message bar close button icon" />
      </button>
    </div>
  );
}
