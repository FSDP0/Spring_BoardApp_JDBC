CREATE TABLE IF NOT EXISTS board (
    board_id BIGINT NOT NULL AUTO_INCREMENT,
    board_title VARCHAR(50) NOT NULL,
    board_author VARCHAR(30) NOT NULL,
    board_content VARCHAR(200) NULL,
    created_date DATETIME NOT NULL,
    modified_date DATETIME NULL,
    PRIMARY KEY (board_id)
);