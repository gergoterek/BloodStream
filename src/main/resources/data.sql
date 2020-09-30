INSERT INTO applied_users (applied_at) VALUES (CURRENT_TIMESTAMP());

INSERT INTO donation_places (address, city, is_active, name) VALUES ('Czuczor utca 8', 'Budapest', 1, 'Terek');

INSERT INTO donations (donation_time, is_used) VALUES (CURRENT_TIMESTAMP(), 1);

INSERT INTO donors (blood_type, donor_name, password, role, total_donations, user_name) VALUES ('A_POZ', 'Terek Gergo', 'almafa', 'ROLE_USER', 2, 'rizsutt');

INSERT INTO gyik (text) VALUES ('rgreg');

INSERT INTO messages (message, send_at) VALUES ('Koszontjuk!', CURRENT_TIMESTAMP());

INSERT INTO news (message, created_at) VALUES ('Koszontjuk!', CURRENT_TIMESTAMP());