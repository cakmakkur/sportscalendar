CREATE TABLE IF NOT EXISTS countries (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(64)
);

CREATE TABLE IF NOT EXISTS players (
    id TEXT PRIMARY KEY,
    firstname VARCHAR(256),
    lastname VARCHAR(256),
    country BIGINT,
    CONSTRAINT _player_country FOREIGN KEY (country) REFERENCES countries(id)
);

CREATE TABLE IF NOT EXISTS teams (
    id TEXT PRIMARY KEY,
    name VARCHAR(255),
    country BIGINT,
    CONSTRAINT _country_id FOREIGN KEY (country) REFERENCES countries(id)
);

CREATE TABLE IF NOT EXISTS teams_player (
    id BIGSERIAL PRIMARY KEY,
    player TEXT,
    team TEXT,
    CONSTRAINT _player_id FOREIGN KEY (player) REFERENCES players(id),
    CONSTRAINT _team_id FOREIGN KEY (team) REFERENCES teams(id)
);



CREATE TABLE IF NOT EXISTS venues (
    id TEXT PRIMARY KEY,
    name VARCHAR(255),
    country BIGINT,
    CONSTRAINT _country_id FOREIGN KEY (country) REFERENCES countries(id)
);

CREATE TABLE IF NOT EXISTS event_categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(64),
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS competition_types (
     id BIGSERIAL PRIMARY KEY,
     event_type BIGINT,
     type VARCHAR(64)
    );

CREATE TABLE IF NOT EXISTS event_types (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(64),
    event_category BIGINT,
    competition_type BIGINT,
    CONSTRAINT _country_id FOREIGN KEY (event_category) REFERENCES event_categories(id),
    CONSTRAINT _competition_types_id FOREIGN KEY (competition_type) REFERENCES competition_types(id)
    );

CREATE TABLE IF NOT EXISTS events (
    id TEXT PRIMARY KEY,
    date TIMESTAMPTZ,
    created_at TIMESTAMPTZ,
    status VARCHAR(8),
    event_type BIGINT,
    description VARCHAR(255),
    venue TEXT,
    CONSTRAINT _event_type_id FOREIGN KEY (event_type) REFERENCES event_types(id),
    CONSTRAINT _venue_id FOREIGN KEY (venue) REFERENCES venues(id)
);

CREATE TABLE IF NOT EXISTS event_player (
    id BIGSERIAL PRIMARY KEY,
    event TEXT,
    player TEXT,
    CONSTRAINT _player_id FOREIGN KEY (player) REFERENCES players(id),
    CONSTRAINT _event_id FOREIGN KEY (event) REFERENCES events(id)
    );



CREATE TABLE IF NOT EXISTS scores (
    id TEXT PRIMARY KEY,
    event TEXT,
    player TEXT,
    team TEXT,
    score VARCHAR(255),
    scored_at TIMESTAMPTZ,
    CONSTRAINT _event_id FOREIGN KEY (event) REFERENCES events(id),
    CONSTRAINT _player_id FOREIGN KEY (player) REFERENCES players(id),
    CONSTRAINT _team_id FOREIGN KEY (team) REFERENCES teams(id)
);

CREATE TABLE IF NOT EXISTS livestreams (
    id TEXT PRIMARY KEY,
    event TEXT,
    url TEXT,
    membership_required BOOLEAN,
    price INTEGER,
    CONSTRAINT _event_id FOREIGN KEY (event) REFERENCES events(id)
);

CREATE TABLE IF NOT EXISTS event_teams (
    id BIGSERIAL PRIMARY KEY,
    team TEXT,
    event TEXT,
    CONSTRAINT _event_id FOREIGN KEY (event) REFERENCES events(id),
    CONSTRAINT _team_id FOREIGN KEY (team) REFERENCES teams(id)
);