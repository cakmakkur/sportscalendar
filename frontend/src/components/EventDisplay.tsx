import { useEffect, useState } from "react";
import { fetchEvents } from "../services/EventService";
import type { EventFilterType } from "../models/EventFilter";
import type { EventType } from "../models/Event";

export default function EventDisply() {
  const [events, setEvents] = useState([]);
  const [date, setDate] = useState(new Date());

  const [filter, setFilter] = useState<EventFilterType>({
    date: date.toISOString(),
    eventType: undefined,
    country: undefined,
  });

  useEffect(() => {
    const init = async () => {
      const data = await fetchEvents(filter);
      setEvents(data);
    };
    init();
  }, [date, filter]);

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
