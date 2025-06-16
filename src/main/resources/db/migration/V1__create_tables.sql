CREATE TABLE tb_users (
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(120)  NOT NULL,
    email        VARCHAR(120)  NOT NULL UNIQUE,
    password     VARCHAR(120)  NOT NULL,
    profile_type VARCHAR(40)   NOT NULL,        -- enum armazenado como texto
    phone        VARCHAR(30)   NOT NULL,
    instruments  VARCHAR(120)  NOT NULL,
    styles       TEXT,
    city         VARCHAR(60)   NOT NULL,
    state        VARCHAR(60)   NOT NULL,
    bio          TEXT,
    active       BOOLEAN       NOT NULL DEFAULT TRUE
);

CREATE TABLE tb_events (
    id                BIGSERIAL PRIMARY KEY,
    title             VARCHAR(255)  NOT NULL,
    description       TEXT,
    scheduled_at      TIMESTAMP     NOT NULL,
    location          VARCHAR(255)  NOT NULL,
    instrument_needed VARCHAR(120)  NOT NULL,
    status            VARCHAR(20)   NOT NULL DEFAULT 'OPEN',
    user_id           BIGINT        NOT NULL,
    CONSTRAINT fk_events_user
        FOREIGN KEY (user_id) REFERENCES tb_users(id)
        ON DELETE CASCADE
);

CREATE TABLE tb_applications (
    id          BIGSERIAL   PRIMARY KEY,
    user_id     BIGINT      NOT NULL,
    event_id    BIGINT      NOT NULL,
    status      VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    created_at  TIMESTAMP   NOT NULL DEFAULT now(),
    CONSTRAINT fk_applications_user
        FOREIGN KEY (user_id) REFERENCES tb_users(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_applications_event
        FOREIGN KEY (event_id) REFERENCES tb_events(id)
        ON DELETE CASCADE
);
