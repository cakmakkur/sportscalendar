import { useEffect, useState } from "react";
import { fetchEvents } from "../services/EventService";
import type { EventFilterType } from "../models/EventFilter";
import type { EventType } from "../models/Event";

export default function EventDisply({ date }: { date: Date }) {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    const init = async () => {
      const filter: EventFilterType = {
        date: date.toISOString().slice(0, 10),
        eventType: 0,
        country: 0,
      };
      const data = await fetchEvents(filter);
      setEvents(data);
    };
    init();
  }, [date]);

  return (
    <div className="event-display-main">
      <ul>
        {events.map((e: EventType) => {
          return <li>{e.description}</li>;
        })}
      </ul>
    </div>
  );
}
