import { createContext, useContext, useState } from "react";

interface ErrorContextType {
  errorMessage: string;
  setErrorMessage: (profile: string) => void;
}

const ErrorContext = createContext<ErrorContextType | undefined>(undefined);

// eslint-disable-next-line react-refresh/only-export-components
export const useErrorContext = (): ErrorContextType => {
  const context = useContext(ErrorContext);
  if (!context) {
    throw new Error("Something went bad");
  }
  return context;
};

export const ErrorContextProvider = ({
  children,
}: {
  children: React.ReactNode;
}) => {
  const [errorMessage, setErrorMessage] = useState<string>("");

  const value = {
    errorMessage,
    setErrorMessage,
  };

  return (
    <ErrorContext.Provider value={value}>{children}</ErrorContext.Provider>
  );
};
