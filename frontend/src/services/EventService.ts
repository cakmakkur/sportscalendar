import type { EventType } from "../models/Event";
import type { EventFilterType } from "../models/EventFilter";

const BASE_URL = import.meta.env.VITE_BASE_URL;

// HTTP Request functions
export const fetchEventTypes = async () => {
  const response = await fetch(`${BASE_URL}/event/types`);

  if (!response.ok) {
    throw new Error(`${response.status}`);
  }

  const data = await response.json();
  return data;
};

export const fetchVenues = async () => {
  const response = await fetch(`${BASE_URL}/venue`);

  if (!response.ok) {
    throw new Error(`${response.status}`);
  }

  const data = await response.json();
  return data;
};

export const fetchEvents = async (ef: EventFilterType) => {
  const response = await fetch(
    `${BASE_URL}/event?date=${ef.date}&eventType=${ef.eventType}&country=${ef.country}`
  );

  if (!response.ok) {
    throw new Error(`${response.status}`);
  }
  const data = await response.json();
  return data;
};

export const fetchCountries = async () => {
  const response = await fetch(`${BASE_URL}/countries`);

  if (!response.ok) {
    throw new Error(`${response.status}`);
  }

  const data = await response.json();
  return data;
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
