const BASE_URL = import.meta.env.VITE_BASE_URL;

export const fetchEventTypes = async () => {
  const response = await fetch(`${BASE_URL}/eventtypes`);
  const data = await response.json();
  return data;
};

export const fetchVenues = async () => {
  const response = await fetch(`${BASE_URL}/venues`);
  const data = await response.json();
  return data;
};
