import React, { useEffect, useState } from "react";
import { defaultEvent } from "../models/Event";
import type { EventType } from "../models/Event";

import type { EventTypeType } from "../models/EventType";
import {
  addEvent,
  fetchEventTypes,
  fetchVenues,
} from "../services/EventService";
import { defaultVenue } from "../models/Venue";
import type { VenueType } from "../models/Venue";

export default function EventCreator() {
  const [types, setTypes] = useState<EventTypeType[]>([]);
  const [venues, setVenues] = useState<VenueType[]>([defaultVenue]);
  const [withLivestream, setWithLivestream] = useState<boolean>(true);

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

  const validateForm = () => {
    // this is a placeholder error handling with quick alert() feedback

    // backend expects price in cents, adjusting it before sending
    setFormEvent({
      ...formEvent,
      livestream: {
        ...formEvent.livestream,
        price: formEvent.livestream.price * 100,
      },
    });

    return (
      // simple/placeholder validation
      formEvent.date !== "" &&
      formEvent.eventType.id !== 0 &&
      formEvent.venue.id !== ""
    );
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    validateForm();
    try {
      await addEvent(formEvent);
      // simulating/placeholding success message with the alert
      // and refreshing back to calendar page
      alert("Event created successfully");
      window.location.reload();
    } catch (error) {
      console.log("Couldn't create the event: " + error);
    }
  };

  const handleChange = (e: React.ChangeEvent<any>) => {
    e.preventDefault();

    switch (e.target.name) {
      case "date":
        setFormEvent({ ...formEvent, date: e.target.value });
        break;

      case "eventType":
        setFormEvent({
          ...formEvent,
          eventType: {
            ...formEvent.eventType,
            id: Number(e.target.value),
          },
        });
        break;

      case "membershipRequired":
        setFormEvent({
          ...formEvent,
          livestream: {
            ...formEvent.livestream,
            membershipRequired: e.target.checked,
          },
        });
        break;

      case "venueName":
        setFormEvent({
          ...formEvent,
          venue: {
            ...formEvent.venue,
            id: e.target.value,
          },
        });
        break;

      case "competitionType":
        setFormEvent({
          ...formEvent,
          eventType: {
            ...formEvent.eventType,
            competitionType: e.target.value,
          },
        });
        break;

      case "price":
        setFormEvent({
          ...formEvent,
          livestream: {
            ...formEvent.livestream,
            price: Number(e.target.value),
          },
        });
        break;

      case "url":
        setFormEvent({
          ...formEvent,
          livestream: {
            ...formEvent.livestream,
            url: e.target.value,
          },
        });
        break;

      case "firstname1":
        setFormEvent({
          ...formEvent,
          players: formEvent.players.map((p, i) =>
            i === 0 ? { ...p, firstname: e.target.value } : p
          ),
        });
        break;

      case "lastname1":
        setFormEvent({
          ...formEvent,
          players: formEvent.players.map((p, i) =>
            i === 0 ? { ...p, lastname: e.target.value } : p
          ),
        });
        break;

      case "firstname2":
        setFormEvent({
          ...formEvent,
          players: formEvent.players.map((p, i) =>
            i === 1 ? { ...p, firstname: e.target.value } : p
          ),
        });
        break;

      case "lastname2":
        setFormEvent({
          ...formEvent,
          players: formEvent.players.map((p, i) =>
            i === 1 ? { ...p, lastname: e.target.value } : p
          ),
        });
        break;

      case "team1":
        setFormEvent({
          ...formEvent,
          teams: formEvent.teams.map((p, i) =>
            i === 0 ? { ...p, name: e.target.value } : p
          ),
        });
        break;

      case "team2":
        setFormEvent({
          ...formEvent,
          teams: formEvent.teams.map((p, i) =>
            i === 1 ? { ...p, name: e.target.value } : p
          ),
        });
        break;

      default:
        break;
    }
  };

  // I have used AI to fast-prototype form element
  // based on my event dto, Then I adapted the
  // template to my logic
  return (
    <form
      className="add-event-form"
      onSubmit={handleSubmit}
      style={{
        display: "flex",
        flexDirection: "column",
        alignItems: "end",
        gap: "10px",
        width: "400px",
        fontSize: "1.1rem",
        rowGap: "20px",
      }}
    >
      <h1
        style={{
          color: "white",
          borderBottom: "1px solid darkgray",
          width: "100%",
          textAlign: "end",
          marginBottom: "15px",
        }}
      >
        Add an event
      </h1>

      <label>
        Date:
        <input
          type="date"
          name="date"
          value={formEvent.date}
          onChange={(e) => handleChange(e)}
          required
        />
      </label>

      <label>
        Event Type:
        <select name="eventType" onChange={handleChange}>
          {types.map((t) => (
            <option key={t.id} value={t.id}>
              {t.name}
            </option>
          ))}
        </select>
      </label>

      <fieldset
        style={{
          borderRadius: "5px",
          border: "1px solid #ccc",
          padding: "10px",
          display: "flex",
          flexDirection: "column",
          alignItems: "end",
          rowGap: "10px",
        }}
      >
        <legend>Competition</legend>

        <label>
          Competition Type:
          <select
            value={formEvent.eventType.competitionType}
            name="competitionType"
            onChange={handleChange}
          >
            <option value={"players"}>Player vs player</option>
            <option value={"teams"}>Teams vs teams</option>
          </select>
        </label>

        <label
          style={{
            display: "flex",
            flexDirection: "column",
            alignItems: "end",
            rowGap: "10px",
          }}
        >
          {formEvent.eventType.competitionType === "players" ? (
            <>
              Players:
              <input
                value={formEvent.players[0].firstname}
                onChange={handleChange}
                name="firstname1"
                type="text"
                placeholder="Player 1 Firstname"
              />
              <input
                value={formEvent.players[0].lastname}
                onChange={handleChange}
                name="lastname1"
                type="text"
                placeholder="Player 1 Lastname"
              />
              <span style={{ fontSize: "1.4rem" }}>vs</span> <br />
              <input
                value={formEvent.players[1].firstname}
                onChange={handleChange}
                name="firstname2"
                type="text"
                placeholder="Player 2 Firstname"
              />
              <input
                value={formEvent.players[1].lastname}
                onChange={handleChange}
                name="lastname2"
                type="text"
                placeholder="Player 2 Lastname"
              />
            </>
          ) : (
            <>
              Teams:
              <input
                onChange={handleChange}
                value={formEvent.teams[0].name}
                type="text"
                name="team1"
                placeholder="Team 1 name"
              />
              <span style={{ fontSize: "1.4rem" }}>vs</span> <br />
              <input
                onChange={handleChange}
                value={formEvent.teams[1].name}
                type="text"
                name="team2"
                placeholder="Team 2 name"
              />
            </>
          )}
          <br />
        </label>
      </fieldset>

      <label>
        Livestream:
        <input
          style={{ marginLeft: "10px" }}
          type="checkbox"
          name="livestream"
          checked={withLivestream}
          onChange={() => setWithLivestream(!withLivestream)}
        />
      </label>

      {withLivestream && (
        <fieldset
          style={{
            borderRadius: "5px",
            border: "1px solid #ccc",
            padding: "10px",
            display: "flex",
            flexDirection: "column",
            alignItems: "end",
            rowGap: "10px",
          }}
        >
          <legend>Livestream</legend>

          <label>
            URL:
            <input
              type="url"
              name="url"
              onChange={handleChange}
              value={formEvent.livestream.url}
            />
          </label>

          <label>
            Membership required:
            <input
              style={{ marginLeft: "10px" }}
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
              step="1"
              value={formEvent.livestream.price}
              onChange={handleChange}
            />
          </label>
        </fieldset>
      )}

      <label>
        Venue:
        <select
          name="venueName"
          value={formEvent.venue.id}
          onChange={handleChange}
          required
        >
          <option value="">Select venue</option>
          {venues.map((v) => (
            <option key={v.id} value={v.id}>
              {v.name}
            </option>
          ))}
        </select>
      </label>

      <button className="add-event-submit-btn" type="submit">
        Create Event
      </button>
    </form>
  );
}
