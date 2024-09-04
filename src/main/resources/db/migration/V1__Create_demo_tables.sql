
CREATE TABLE user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    wins INT DEFAULT 0
);

CREATE TABLE played_match (
    match_id INT AUTO_INCREMENT PRIMARY KEY,
    player1_id INT,
    player2_id INT,
    winner_id INT,
    FOREIGN KEY (player1_id) REFERENCES user(user_id),
    FOREIGN KEY (player2_id) REFERENCES user(user_id),
    FOREIGN KEY (winner_id) REFERENCES user(user_id)
);
