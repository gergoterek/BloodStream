INSERT INTO application (applied_at) VALUES (CURRENT_DATE());
INSERT INTO application (applied_at) VALUES ('1997-12-12');

INSERT INTO donation_place (address, city, is_active, name) VALUES ('Czuczor utca 8', 'Budapest', 1, 'Plazma');
INSERT INTO donation_place (address, city, is_active, name) VALUES ('Czuczor utca 9', 'Debrecen', 1, 'OVSZ');

INSERT INTO donation (donation_time, is_used) VALUES (CURRENT_TIMESTAMP(), 1);
INSERT INTO donation (donation_time, is_used) VALUES (CURRENT_DATE(), 0);

INSERT INTO donor (blood_type, donor_name, password, role, user_name) VALUES ('A_POZ', 'Terek Gergo', 'almafa', 'ROLE_USER', 'rizsutt');
INSERT INTO donor (blood_type, donor_name, password, role, user_name) VALUES ('ZERO_POZ', 'Pinter Dorottya', 'almafa', 'ROLE_ADMIN', 'pdorka97');

INSERT INTO faq (question, answer) VALUES ('Mennyi ideig tart egy veradas?', 'Kb 30 perc');
INSERT INTO faq (question, answer) VALUES ('Kell-e varni veradas utan?', 'Igen celszeru pihenni utana 5-10 percet');

INSERT INTO message (message, posted) VALUES ('Koszontjuk!', CURRENT_TIMESTAMP());
INSERT INTO message (message, posted) VALUES ('Sajnos em megfelelő a verallapot!', CURRENT_TIMESTAMP());

INSERT INTO news (title, message, created_at) VALUES ('Nyitvatartasunk','Hosszabbitott nyitvatartassal varjuk veradoinkat!', CURRENT_TIMESTAMP());
INSERT INTO news (title, message, created_at) VALUES ('Unnepi nyitvatartasunk', 'Unnepi nyitvatartasunk a kovetkezo:', CURRENT_TIMESTAMP());