CREATE TABLE IF NOT EXISTS countries (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(64);
)

CREATE TABLE IF NOT EXISTS players (
    id TEXT PRIMARY KEY,
    firstname VARCHAR(256),
    lastname VARCHAR(256),
    country BIGINT FOR,

)