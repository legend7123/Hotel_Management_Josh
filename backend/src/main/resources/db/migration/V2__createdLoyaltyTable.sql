CREATE TABLE IF NOT EXISTS loyalty (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL UNIQUE,
    points INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT loyalty_user_fk FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);
