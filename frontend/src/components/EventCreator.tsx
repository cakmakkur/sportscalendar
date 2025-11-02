import { useEffect, useState } from "react";
import { defaultEvent } from "../models/Event";
import type { EventType } from "../models/Event";

import type {EventTypeType} from "./EventType";
import { fetchEventTypes } from "../services/EventService";


export default function EventCreator() {
  const [types, setTypes] = useState<EventTypeType[]>([]);
  const [event, setEvent] = useState<EventType>(defaultEvent);

  useEffect(() => {
    const init = async () => {
      const types = await fetchEventTypes();
      setTypes(types);
    };
    await init();
  }, []);
    
  

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log(event);
  };

  // I have used AI to fast-prototype form element
  // based on my event dto, Then I adapted the 
  // template to my logic
  return (
    <form onSubmit={handleSubmit} style={{ display: "flex", flexDirection: "column", gap: "10px", width: "300px" }}>
      <label>
        Date:
        <input type="datetime-local" name="date" value={event.date} required />
      </label>

      {/* Event Type */}
      <label>
        Event Type:
        <select name="eventTypeId" value={event.} >
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
        Player IDs (comma-separated):
        <input type="text" name="playerIds" value={formData.playerIds} onChange={handleChange} placeholder="e.g. 12, 43, 88" />
      </label>

      {/* Team IDs */}
      <label>
        Team IDs (comma-separated):
        <input type="text" name="teamIds" value={formData.teamIds} onChange={handleChange} placeholder="e.g. 5, 9" />
      </label>

      {/* Livestream */}
      <fieldset style={{ border: "1px solid #ccc", padding: "8px" }}>
        <legend>Livestream</legend>

        <label>
          URL:
          <input type="url" name="livestreamUrl" value={formData.livestreamUrl} onChange={handleChange} placeholder="https://..." />
        </label>

        <label>
          Membership required:
          <input type="checkbox" name="membershipRequired" checked={formData.membershipRequired} onChange={handleChange} />
        </label>

        <label>
          Price:
          <input type="number" name="price" min="0" step="0.01" value={formData.price} onChange={handleChange} />
        </label>
      </fieldset>

      {/* Add Score */}
      <label>
        Add Score:
        <input type="checkbox" name="addScore" checked={formData.addScore} onChange={handleChange} />
      </label>

      {formData.addScore && (
        <fieldset style={{ border: "1px solid #ccc", padding: "8px" }}>
          <legend>Score Details</legend>

          <label>
            Player ID:
            <input type="text" name="playerId" value={formData.score.playerId} onChange={handleScoreChange} />
          </label>

          <label>
            Team ID:
            <input type="text" name="teamId" value={formData.score.teamId} onChange={handleScoreChange} />
          </label>

          <label>
            Score:
            <input type="number" name="score" value={formData.score.score} onChange={handleScoreChange} />
          </label>

          <label>
            Scored At:
            <input type="datetime-local" name="scoredAt" value={formData.score.scoredAt} onChange={handleScoreChange} />
          </label>
        </fieldset>
      )}

      {/* Venue */}
      <label>
        Venue:
        <select name="venueId" value={formData.venueId} onChange={handleChange} required>
          <option value="">Select venue</option>
          {venues.map((v) => (
            <option key={v.id} value={v.id}>
              {v.name}
            </option>
          ))}
        </select>
      </label>

      <button type="submit">Create Event</button>
    </form>
  );
}

