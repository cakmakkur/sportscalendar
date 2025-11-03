import { useEffect, useState } from "react";
import { fetchEvents } from "../services/EventService";
import type { EventFilterType } from "../models/EventFilter";
import type { EventType } from "../models/Event";
import EventFilter from "./EventFilter";

// displays the events according to the filter
export default function EventDisply({ date }: { date: Date }) {
  const [events, setEvents] = useState<EventType[]>([]);
  const [detailsOpenIds, setDetailsOpenIds] = useState<string[]>([]);

  // toggles the details of an event
  const toggleDetails = (id: string) => {
    if (detailsOpenIds.includes(id)) {
      setDetailsOpenIds(detailsOpenIds.filter((i) => i !== id));
    } else {
      setDetailsOpenIds([...detailsOpenIds, id]);
    }
  };

  // initiates the filter on mounting the component
  // then fetches the evens of the selected date
  useEffect(() => {
    const init = async () => {
      const filter: EventFilterType = {
        date: date.toISOString().slice(0, 10),
        eventType: 0, // 0 equals all in BE implementation
        country: 0, // 0 equals all in BE implementation
      };
      const data = await fetchEvents(filter);
      setEvents(data);
    };
    init();
  }, [date]);

  return (
    <div className="event-display-main">
      <EventFilter />
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
            <>
              <li className="display-event-row" key={e.id}>
                <div>
                  <span style={{ color: "lightgray" }}>
                    {nameA} - {nameB}
                  </span>
                  <span style={{ padding: "0px 8px" }}> &#183; </span>
                  <span>{e.eventType?.name ?? ""}</span>
                  <span style={{ padding: "0px 8px" }}> &#183; </span>
                  <span style={{ color: "lightgray" }}>
                    {e.venue.country ?? ""}
                  </span>
                </div>
                <button
                  onClick={() => toggleDetails(e.id)}
                  className="display-event-details-btn"
                >
                  {detailsOpenIds.includes(e.id) ? "Hide" : "Details"}
                </button>
              </li>
              {detailsOpenIds.includes(e.id) && (
                <div
                  style={{
                    padding: "20px",
                    backgroundColor: "rgba(255,255,255,0.05)",
                    borderRadius: "5px",
                  }}
                >
                  <span>
                    <span style={{ color: "lightgray" }}>Status: </span>
                    <span>{e.status}</span>
                  </span>
                  <br />
                  <span>
                    <span style={{ color: "lightgray" }}>Venue: </span>
                    <span>{e.venue.name ?? ""}</span>
                  </span>
                  <br />
                  {e.livestream ? (
                    <span>
                      <span style={{ color: "lightgray" }}>
                        Livestream at:{" "}
                      </span>
                      <a
                        style={{
                          color: "lightblue",
                          textDecoration: "underline",
                        }}
                        href={e.livestream.url}
                        target="_blank"
                      >
                        {e.livestream.url}
                      </a>
                      <br />
                      <span>
                        <span style={{ color: "lightgray" }}>
                          Membership required:{" "}
                        </span>
                        <span>Yes</span>
                      </span>

                      <br />
                      {e.livestream.price ? (
                        <span>
                          <span style={{ color: "lightgray" }}>Price: </span>
                          <span>$ {e.livestream.price / 100}</span>
                        </span>
                      ) : (
                        ""
                      )}
                    </span>
                  ) : (
                    ""
                  )}
                </div>
              )}
            </>
          );
        })}
      </ul>
    </div>
  );
}
