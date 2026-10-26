-- create Users table
CREATE TABLE users
(
    id              UUID PRIMARY KEY,
    email           VARCHAR(255)             NOT NULL UNIQUE,
    hashed_password VARCHAR(255)             NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL
);

-- create Groups table
CREATE TABLE groups
(
    id         UUID PRIMARY KEY,
    title      VARCHAR(255)             NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL
);

-- create Group_Members table
CREATE TABLE group_members
(
    user_id   UUID,
    group_id  UUID,
    role      VARCHAR(50)              NOT NULL,
    joined_at TIMESTAMP WITH TIME ZONE NOT NULL,

    PRIMARY KEY (user_id, group_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES groups (id) ON DELETE CASCADE
);