CREATE TABLE IF NOT EXISTS user_address (
    user_id VARCHAR(50) NOT NULL,
    user_address VARCHAR(100) NOT NULL,
    user_zipcode VARCHAR(30) NOT NULL,
    PRIMARY KEY(user_id),
    FOREIGN KEY(user_id) REFERENCES user (user_id)
);