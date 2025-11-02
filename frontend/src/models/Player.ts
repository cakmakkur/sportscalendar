export type PlayerType = {
  id: string;
  firstname: string;
  lastname: string;
  country: string;
};

export const defaultPlayer: PlayerType = {
  id: "default",
  firstname: "placeholder",
  lastname: "placeholder",
  country: "placeholder",
};
