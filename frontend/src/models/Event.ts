// status = FUTURE, LIVE, FINISHED

import type { EventTypeType } from "./EventType";
import { defaultEventType } from "./EventType";
import { defaultPlayer, type PlayerType } from "./PlayerType";
import { defaultTeam, type TeamType } from "./TeamType";
import { defaultVenue } from "./Venue";
import type { VenueType } from "./Venue";

export const defaultEvent: EventType = {
  id: "",
  date: "",
  createdAt: "",
  status: "",
  eventType: defaultEventType,
  description: "placeholder event description",
  players: [defaultPlayer],
  teams: [defaultTeam],
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
  id: string;
  date: string;
  createdAt: string;
  status: string;
  eventType: EventTypeType;
  description: string;
  players: PlayerType[];
  teams: TeamType[];
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
