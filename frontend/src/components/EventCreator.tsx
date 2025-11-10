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

type DisplayStatus = "success" | "create";

export default function EventCreator() {
  const [displayStatus, setDisplayStatus] = useState<DisplayStatus>("create");

  const [types, setTypes] = useState<EventTypeType[]>([]);
  const [venues, setVenues] = useState<VenueType[]>([defaultVenue]);
  const [withLivestream, setWithLivestream] = useState<boolean>(true);

  const [formEvent, setFormEvent] = useState<EventType>(defaultEvent);

  const [errorMessage, setErrorMessage] = useState<string>("");

  // fetching event types and venues on mounting the component
  useEffect(() => {
    const init = async () => {
      try {
        const types = await fetchEventTypes();
        setTypes(types);
        setFormEvent({
          ...formEvent,
          eventType: types[0],
        });
        const venues = await fetchVenues();
        setVenues(venues);

        // eslint-disable-next-line @typescript-eslint/no-unused-vars
      } catch (error) {
        setErrorMessage("Something went wrong while initiating the page");
      }
    };
    init();
  }, []);

  const handleAddAnother = () => {
    setFormEvent(defaultEvent);
    setDisplayStatus("create");
  };

  // submit function for post request
  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const updatedEvent = {
      ...formEvent,
      livestream: {
        ...formEvent.livestream,
        price: withLivestream ? formEvent.livestream.price * 100 : 0,
        url: withLivestream ? formEvent.livestream.url : "",
        membershipRequired: withLivestream
          ? formEvent.livestream.membershipRequired
          : false,
      },
    };

    if (
      updatedEvent.date === "" ||
      updatedEvent.eventType.id === 0 ||
      updatedEvent.venue.id === ""
    ) {
      setErrorMessage("Missing required fields");
      return;
    }

    try {
      await addEvent(updatedEvent);
      setDisplayStatus("success");
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
    } catch (error) {
      setErrorMessage("Couldn't create the event");
    }
  };

  // user input change handler
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  const handleChange = (e: React.ChangeEvent<any>) => {
    switch (e.target.name) {
      case "date":
        setFormEvent((prev) => ({ ...prev, date: e.target.value }));
        break;

      case "eventType":
        setFormEvent((prev) => ({
          ...prev,
          eventType: types.filter((t) => t.id === Number(e.target.value))[0],
        }));
        break;

      case "membershipRequired":
        setFormEvent((prev) => ({
          ...prev,
          livestream: {
            ...prev.livestream,
            membershipRequired: e.target.checked,
          },
        }));
        break;

      case "venueName":
        setFormEvent((prev) => ({
          ...prev,
          venue: {
            ...prev.venue,
            id: e.target.value,
          },
        }));
        break;

      case "competitionType":
        setFormEvent((prev) => ({
          ...prev,
          eventType: {
            ...prev.eventType,
            competitionType: e.target.value,
          },
        }));
        break;

      case "price":
        setFormEvent((prev) => ({
          ...prev,
          livestream: {
            ...prev.livestream,
            price: Number(e.target.value),
          },
        }));
        break;

      case "url":
        setFormEvent((prev) => ({
          ...prev,
          livestream: {
            ...prev.livestream,
            url: e.target.value,
          },
        }));
        break;

      case "firstname1":
        setFormEvent((prev) => ({
          ...prev,
          players: prev.players.map((p, i) =>
            i === 0 ? { ...p, firstname: e.target.value } : p
          ),
        }));
        break;

      case "lastname1":
        setFormEvent((prev) => ({
          ...prev,
          players: prev.players.map((p, i) =>
            i === 0 ? { ...p, lastname: e.target.value } : p
          ),
        }));
        break;

      case "firstname2":
        setFormEvent((prev) => ({
          ...prev,
          players: prev.players.map((p, i) =>
            i === 1 ? { ...p, firstname: e.target.value } : p
          ),
        }));
        break;

      case "lastname2":
        setFormEvent((prev) => ({
          ...prev,
          players: prev.players.map((p, i) =>
            i === 1 ? { ...p, lastname: e.target.value } : p
          ),
        }));
        break;

      case "team1":
        setFormEvent((prev) => ({
          ...prev,
          teams: prev.teams.map((p, i) =>
            i === 0 ? { ...p, name: e.target.value } : p
          ),
        }));
        break;

      case "team2":
        setFormEvent((prev) => ({
          ...prev,
          teams: prev.teams.map((p, i) =>
            i === 1 ? { ...p, name: e.target.value } : p
          ),
        }));
        break;

      default:
        break;
    }

    // making the error message disappear after user types into the form
    setErrorMessage("");
  };

  if (displayStatus === "success") {
    return (
      <div className="add-event-success">
        <span style={{ color: "lightgray", fontSize: "2.6rem" }}>
          Event created successfully :)
        </span>
        <button className="add-event-submit-btn" onClick={handleAddAnother}>
          Create another
        </button>
      </div>
    );
  }

  // used AI to generate template html, then adjusted it to my needs
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
              placeholder="Url"
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
              onChange={handleChange}
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

      {errorMessage && (
        <p style={{ color: "red" }}>{errorMessage} - Try again</p>
      )}

      <button className="add-event-submit-btn" type="submit">
        Create Event
      </button>
    </form>
  );
}
