// status = FUTURE, LIVE, FINISHED

import type { EventTypeType } from "./EventType";
import { defaultEventType } from "./EventType";
import { defaultVenue } from "./Venue";
import type { VenueType } from "./Venue";

export const defaultEvent: EventType = {
  date: "",
  createdAt: "",
  status: "",
  eventType: defaultEventType,
  description: "placeholder event description",
  playerIds: [],
  teamIds: [],
  livestream: {
    url: "placeholder.kc",
    membershipRequired: false,
    price: 0,
  },
  scores: [
    {
      playerId: "",
      teamId: "",
      score: "",
      scoredAt: "",
    },
  ],
  venue: defaultVenue,
};

export type EventType = {
  date: string;
  createdAt: string;
  status: string;
  eventType: EventTypeType;
  description: string;
  playerIds: string[];
  teamIds: string[];
  livestream: {
    url: string;
    membershipRequired: boolean;
    price: number;
  };
  scores: [
    {
      playerId: string;
      teamId: string;
      score: string;
      scoredAt: string;
    }
  ];
  venue: VenueType;
};
