import { useEffect, useState } from "react";
import { fetchEvents } from "../services/EventService";
import type { EventFilterType } from "../models/EventFilter";
import type { EventType } from "../models/Event";

export default function EventDisply({ date }: { date: Date }) {
  const [events, setEvents] = useState<EventType[]>([]);

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
      <ul
        style={{
          display: "flex",
          flexDirection: "column",
          rowGap: "10px",
        }}
      >
        {events.map((e: EventType) => {
          const isPlayerEvent = e.eventType.competitionType === "players";

          const nameA = isPlayerEvent
            ? e.players[0]?.lastname
            : e.teams?.[0]?.name;
          const nameB = isPlayerEvent
            ? e.players[1]?.lastname
            : e.teams?.[1]?.name;

          if (!nameA || !nameB) return null;

          return (
            <li className="display-event-row" key={e.id}>
              <div>
                <span>
                  {nameA} - {nameB}
                </span>
                <span> &#183; </span>
                <span style={{ color: "lightgray" }}>
                  {e.eventType?.name ?? ""}
                </span>
                <span> &#183; </span>
                <span>{e.venue.country ?? ""}</span>
              </div>
              <button className="display-event-details-btn">Details</button>
            </li>
          );
        })}
      </ul>
    </div>
  );
}
