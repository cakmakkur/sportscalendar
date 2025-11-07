import { useEffect, useState } from "react";
import {
  fetchCountries,
  fetchEvents,
  fetchEventTypes,
} from "../services/EventService";
import type { EventTypeType } from "../models/EventType";
import type { CountryType } from "../models/CountryDto";
import {
  defaultEventFilter,
  type EventFilterType,
} from "../models/EventFilter";
import type { EventType } from "../models/Event";
import { useErrorContext } from "../context/ErrorContext";

interface EventFilterProps {
  date: Date;
  updateEvents: (e: EventType[]) => void;
}

export default function EventFilter({ date, updateEvents }: EventFilterProps) {
  const [types, setTypes] = useState<EventTypeType[]>([]);
  const [countries, setCountries] = useState<CountryType[]>([]);

  const [filter, setFilter] = useState(defaultEventFilter);

  const { setErrorMessage } = useErrorContext();

  const handleChange = (e: React.ChangeEvent<any>) => {
    e.preventDefault();

    switch (e.target.name) {
      case "eventType":
        setFilter({
          ...filter,
          eventType: Number(e.target.value),
        });
        break;
      case "country":
        setFilter({
          ...filter,
          country: Number(e.target.value),
        });
    }
  };

  const submitFilter = async (e: React.FormEvent<HTMLButtonElement>) => {
    e.preventDefault();
    const finalFIlter: EventFilterType = {
      date: date.toISOString().slice(0, 10),
      eventType: filter.eventType,
      country: filter.country,
    };
    try {
      const data = await fetchEvents(finalFIlter);
      updateEvents(data);

      // eslint-disable-next-line @typescript-eslint/no-unused-vars
    } catch (error) {
      setErrorMessage("Couldn't fetch events");
    }
  };

  useEffect(() => {
    const init = async () => {
      try {
        const countries = await fetchCountries();
        setCountries(countries);
        const types = await fetchEventTypes();
        setTypes(types);

        // eslint-disable-next-line @typescript-eslint/no-unused-vars
      } catch (error) {
        setErrorMessage("Something went wrong while initiating the page");
      }
    };
    init();
  }, []);

  return (
    <section>
      <form
        style={{
          display: "flex",
          columnGap: "3vw",
          marginBottom: "3vh",
          paddingBottom: "2vh",
          borderBottom: "1px solid darkgray",
          flexWrap: "wrap",
          rowGap: "3vh",
        }}
      >
        <button className="apply-filter-btn" onClick={(e) => submitFilter(e)}>
          <img width={20} src="/filter.svg" alt="apply filter button icon" />
          Apply Filter
        </button>
        <label>
          <select
            style={{
              background: "none",
              color: "white",
              fontSize: "1.2rem",
              padding: "5px 10px",
              border: "1px solid lightgray",
              borderRadius: "5px",
              maxWidth: "200px",
            }}
            name="eventType"
            value={filter.eventType}
            onChange={handleChange}
            required
          >
            <option value="0">All event types</option>
            {types.map((v) => (
              <option key={v.id} value={v.id}>
                {v.name}
              </option>
            ))}
          </select>
        </label>

        <label>
          <select
            style={{
              background: "none",
              color: "white",
              fontSize: "1.2rem",
              padding: "5px 10px",
              border: "1px solid lightgray",
              borderRadius: "5px",
              maxWidth: "200px",
            }}
            name="country"
            value={filter.country}
            onChange={handleChange}
            required
          >
            <option value="0">All countries</option>
            {countries.map((v) => (
              <option key={v.id} value={v.id}>
                {v.name}
              </option>
            ))}
          </select>
        </label>
      </form>
    </section>
  );
}
