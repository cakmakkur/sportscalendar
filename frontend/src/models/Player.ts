export type PlayerType = {
  id: string;
  firstname: string;
  lastname: string;
  country: string;
};

export const defaultPlayer: PlayerType = {
  id: "0",
  firstname: "",
  lastname: "",
  country: "Austria",
};
