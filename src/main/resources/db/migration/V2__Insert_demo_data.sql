INSERT INTO user (name, username, wins) VALUES
('John Doe', 'john_doe', 10),
('PC', 'Computer', 15),
('Alice Johnson', 'alice_johnson', 7),
('Bob Brown', 'bob_brown', 20);

INSERT INTO played_match (player1_id, player2_id, winner_id) VALUES
(1, 2, 1),
(2, 3, 3),
(3, 4, 4),
(1, 4, 1);
