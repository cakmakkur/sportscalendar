export type EventFilterType = {
  date: string;
  eventType: number;
  country: number;
};

export const defaultEventFilter: EventFilterType = {
  date: new Date().toISOString().slice(0, 10),
  eventType: 1, // football
  country: 197, // 197 = online event
};
