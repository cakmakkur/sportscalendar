import type { EventType } from "../models/Event";
import type { EventFilterType } from "../models/EventFilter";

const BASE_URL = import.meta.env.VITE_BASE_URL;

export const fetchEventTypes = async () => {
  const response = await fetch(`${BASE_URL}/event/types`);
  const data = await response.json();
  return data;
};

export const fetchVenues = async () => {
  const response = await fetch(`${BASE_URL}/event/venues`);
  const data = await response.json();
  return data;
};

export const fetchEvents = async (ef: EventFilterType) => {
  try {
    const response = await fetch(
      `${BASE_URL}/event?date=${ef.date}&eventType=${ef.eventType}&country=${ef.country}`
    );
    const data = await response.json();
    return data;
  } catch (error) {
    console.log("todo: implement error handling: " + error);
  }
};

export const addEvent = async (event: EventType) => {
  try {
    const response = await fetch(`${BASE_URL}/event/create`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(event),
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.log("basic console-debugging :) : " + error);
  }
};
