export const defaultEventType: EventTypeType = {
  id: 1,
  name: "placeholder",
  eventCategory: {
    id: 1,
    name: "placeholder",
    description: "placeholder",
  },
  competitionType: "placeholder",
};

export type EventTypeType = {
  id: number;
  name: string;
  eventCategory: {
    id: number;
    name: string;
    description: string;
  };
  competitionType: string;
};
