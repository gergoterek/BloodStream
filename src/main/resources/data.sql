INSERT INTO applied_users (applied_at) VALUES (CURRENT_DATE());
INSERT INTO applied_users (applied_at) VALUES ('1997-12-12');

INSERT INTO donation_places (address, city, is_active, name) VALUES ('Czuczor utca 8', 'Budapest', 1, 'Plazma');
INSERT INTO donation_places (address, city, is_active, name) VALUES ('Czuczor utca 9', 'Debrecen', 1, 'OVSZ');

INSERT INTO donations (donation_time, is_used) VALUES (CURRENT_TIMESTAMP(), 1);
INSERT INTO donations (donation_time, is_used) VALUES (CURRENT_DATE(), 0);

INSERT INTO donors (blood_type, donor_name, password, role, total_donations, user_name) VALUES ('A_POZ', 'Terek Gergo', 'almafa', 'ROLE_USER', 2, 'rizsutt');
INSERT INTO donors (blood_type, donor_name, password, role, total_donations, user_name) VALUES ('0_POZ', 'Pinter Dorottya', 'almafa', 'ROLE_ADMIN', 2, 'pdorka97');

INSERT INTO faq (question, answer) VALUES ('Mennyi ideig tart egy veradas?', 'Kb 30 perc');
INSERT INTO faq (question, answer) VALUES ('Kell-e varni veradas utan?', 'Igen celszeru pihenni utana 5-10 percet');

INSERT INTO messages (message, posted) VALUES ('Koszontjuk!', CURRENT_TIMESTAMP());
INSERT INTO messages (message, posted) VALUES ('Sajnos em megfelelő a verallapot!', CURRENT_TIMESTAMP());

INSERT INTO news (title, message, created_at) VALUES ('Nyitvatartasunk','Hosszabbitott nyitvatartassal varjuk veradoinkat!', CURRENT_TIMESTAMP());
INSERT INTO news (title, message, created_at) VALUES ('Unnepi nyitvatartasunk', 'Unnepi nyitvatartasunk a kovetkezo:', CURRENT_TIMESTAMP());