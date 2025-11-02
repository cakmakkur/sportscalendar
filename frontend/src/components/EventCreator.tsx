import { useEffect, useState } from "react";
import { defaultEvent } from "../models/Event";
import type { EventType } from "../models/Event";

import type { EventTypeType } from "../models/EventType";
import { fetchEventTypes, fetchVenues } from "../services/EventService";
import { defaultVenue } from "../models/Venue";
import type { VenueType } from "../models/Venue";

export default function EventCreator() {
  const [types, setTypes] = useState<EventTypeType[]>([]);
  const [venues, setVenues] = useState<VenueType[]>([defaultVenue]);

  const [formEvent, setFormEvent] = useState<EventType>(defaultEvent);

  useEffect(() => {
    const init = async () => {
      const types = await fetchEventTypes();
      setTypes(types);
      const venues = await fetchVenues();
      setVenues(venues);
    };
    init();
  }, []);

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log(formEvent);
  };

  const handleChange = () => {
    // TODO: impl this
    setFormEvent(defaultEvent);
  };

  // I have used AI to fast-prototype form element
  // based on my event dto, Then I adapted the
  // template to my logic
  return (
    <form
      onSubmit={handleSubmit}
      style={{
        display: "flex",
        flexDirection: "column",
        gap: "10px",
        width: "300px",
      }}
    >
      <label>
        Date:
        <input
          type="datetime-local"
          name="date"
          value={formEvent.date}
          required
        />
      </label>

      {/* Event Type */}
      <label>
        Event Type:
        <select name="eventType" value={formEvent.eventType.name}>
          <option value="">Select type</option>
          {types.map((t) => (
            <option key={t.id} value={t.id}>
              {t.name}
            </option>
          ))}
        </select>
      </label>

      {/* Player IDs */}
      <label>
        Players:
        <input type="text" name="player" value={formEvent.playerIds} />
      </label>

      {/* Team IDs */}
      <label>
        Teams:
        <input type="text" name="team" value={formEvent.teamIds} />
      </label>

      {/* Livestream */}
      <fieldset style={{ border: "1px solid #ccc", padding: "8px" }}>
        <legend>Livestream</legend>

        <label>
          URL:
          <input
            type="url"
            name="livestreamUrl"
            value={formEvent.livestream.url}
          />
        </label>

        <label>
          Membership required:
          <input
            type="checkbox"
            name="membershipRequired"
            checked={formEvent.livestream.membershipRequired}
          />
        </label>

        <label>
          Price:
          <input
            type="number"
            name="price"
            min="0"
            step="0.01"
            value={formEvent.livestream.price}
            onChange={handleChange}
          />
        </label>
      </fieldset>

      {/* Venue */}
      <label>
        Venue:
        <select
          name="venueName"
          value={formEvent.venue.name}
          onChange={handleChange}
          required
        >
          <option value="">Select venue</option>
          {venues.map((v) => (
            <option key={v.name} value={v.id}>
              {v.name}
            </option>
          ))}
        </select>
      </label>

      <button type="submit">Create Event</button>
    </form>
  );
}
