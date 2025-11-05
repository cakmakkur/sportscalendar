import type { EventType } from "../models/Event";
import type { EventFilterType } from "../models/EventFilter";

const BASE_URL = import.meta.env.VITE_BASE_URL;

// HTTP Request functions
// no user-friedndly error handling is implemented since it is not required by the task. Instead simple console logging is used
export const fetchEventTypes = async () => {
  try {
    const response = await fetch(`${BASE_URL}/event/types`);
    const data = await response.json();
    return data;
  } catch (error) {
    console.log("error : " + error);
  }
};

export const fetchVenues = async () => {
  try {
    const response = await fetch(`${BASE_URL}/event/venues`);
    const data = await response.json();
    return data;
  } catch (error) {
    console.log("error : " + error);
  }
};

export const fetchEvents = async (ef: EventFilterType) => {
  try {
    const response = await fetch(
      `${BASE_URL}/event?date=${ef.date}&eventType=${ef.eventType}&country=${ef.country}`
    );
    const data = await response.json();
    return data;
  } catch (error) {
    console.log("error : " + error);
  }
};

export const fetchCountries = async () => {
  try {
    const response = await fetch(`${BASE_URL}/event/countries`);
    const data = await response.json();
    return data;
  } catch (error) {
    console.log("error : " + error);
  }
};

export const addEvent = async (event: EventType) => {
  const response = await fetch(`${BASE_URL}/event/create`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(event),
  });

  if (!response.ok) {
    throw new Error(`${response.status}`);
  }

  return;
};
