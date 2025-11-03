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

interface EventFilterProps {
  date: Date;
  updateEvents: (e: EventTypeType) => void;
}

export default function EventFilter({ date, updateEvents }: EventFilterProps) {
  const [types, setTypes] = useState<EventTypeType[]>([]);
  const [countries, setCountries] = useState<CountryType[]>([]);

  const [filter, setFilter] = useState(defaultEventFilter);

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

  const submitFilter = async () => {
    const finalFIlter: EventFilterType = {
      date: date.toISOString().slice(0, 10),
      eventType: filter.eventType,
      country: filter.country,
    };
    const data = await fetchEvents(finalFIlter);
    updateEvents(data);
  };

  useEffect(() => {
    const init = async () => {
      const types = await fetchEventTypes();
      setTypes(types);
      const countries = await fetchCountries();
      setCountries(countries);
    };
    init();
  }, []);

  return (
    <div>
      <form action="">
        <label>
          Event Type:
          <select
            name="eventType"
            value={filter.eventType}
            onChange={handleChange}
            required
          >
            <option value="">Select event type</option>
            {types.map((v) => (
              <option key={v.id} value={v.id}>
                {v.name}
              </option>
            ))}
          </select>
        </label>

        <label>
          Country:
          <select
            name="country"
            value={filter.country}
            onChange={handleChange}
            required
          >
            <option value="">Select country</option>
            {countries.map((v) => (
              <option key={v.id} value={v.id}>
                {v.name}
              </option>
            ))}
          </select>
        </label>
        <button type="submit" onClick={submitFilter}></button>
      </form>
    </div>
  );
}
