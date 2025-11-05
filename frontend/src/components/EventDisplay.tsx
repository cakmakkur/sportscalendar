import { useEffect, useState } from "react";
import { fetchEvents } from "../services/EventService";
import type { EventFilterType } from "../models/EventFilter";
import type { EventType } from "../models/Event";
import EventFilter from "./EventFilter";
import Flag from "./Flag";

// displays the events according to the filter
export default function EventDisply({ date }: { date: Date }) {
  const [loading, setLoading] = useState(true);
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

  // helper function to pass event state setter to the child component (EventFilter)
  const updateEvents = (newEvents: EventType[]) => {
    setEvents(newEvents);
  };

  // initiates the filter on mounting the component
  // then fetches the evens of the selected date
  useEffect(() => {
    const init = async () => {
      setLoading(true);
      const filter: EventFilterType = {
        date: date.toISOString().slice(0, 10),
        eventType: 0, // 0 equals all in BE implementation
        country: 0, // 0 equals all in BE implementation
      };
      try {
        const data = await fetchEvents(filter);
        setEvents(data);
      } catch (error) {
        console.log("error : " + error);
      } finally {
        setLoading(false);
      }
    };
    init();
  }, [date]);

  if (loading)
    return (
      <div className="event-display-main">
        <EventFilter date={date} updateEvents={updateEvents} />
        <div
          style={{
            fontSize: "1.2rem",
          }}
        >
          Loading events...
        </div>
      </div>
    );

  if (events.length === 0)
    return (
      <div className="event-display-main">
        <EventFilter date={date} updateEvents={updateEvents} />
        <div
          style={{
            fontSize: "1.2rem",
          }}
        >
          No events found
        </div>
      </div>
    );

  return (
    <div className="event-display-main">
      <EventFilter date={date} updateEvents={updateEvents} />
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

          // if there is neither player (lastname) nor team (name), doesnt render the event
          if (!nameA || !nameB) return null;

          return (
            <div key={e.id}>
              <li className="display-event-row" key={e.id}>
                <div>
                  <span style={{ color: "lightgray" }}>
                    {nameA} - {nameB}
                  </span>
                  <span style={{ padding: "0px 8px" }}> &#183; </span>
                  <span>{e.eventType?.name ?? ""}</span>
                  <span style={{ padding: "0px 8px" }}> &#183; </span>
                  <span style={{ color: "lightgray" }}>
                    <Flag name={e.venue.country} />
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
            </div>
          );
        })}
      </ul>
    </div>
  );
}
