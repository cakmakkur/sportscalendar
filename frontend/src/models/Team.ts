export type TeamType = {
  id: string;
  name: string;
  country: string;
};

export const defaultTeam: TeamType = {
  id: "0",
  name: "Placeholder team name",
  country: "Austria",
};
