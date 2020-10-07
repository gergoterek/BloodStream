INSERT INTO donor (blood_type, donor_name, password, role, user_name, TAJ, id_card, birth_date) VALUES ('A_POZ', 'Terek Gergo', 'almafa', 'ROLE_USER', 'rizsutt', 234231123, '1D899R', '2000-03-23');
INSERT INTO donor (blood_type, donor_name, password, role, user_name, TAJ, id_card, birth_date) VALUES ('ZERO_POZ', 'Pinter Dorottya', 'almafa', 'ROLE_ADMIN', 'pdorka97', 423431123, '1D899R', '2000-03-23');
INSERT INTO donor (blood_type, donor_name, password, role, user_name, TAJ, id_card, birth_date) VALUES ('ZERO_NEG', 'Kiss Jeno', 'almafa', 'ROLE_ADMIN', 'jeno72', 423423123, '1D899R', '2000-03-23');
INSERT INTO donor (blood_type, donor_name, password, role, user_name, TAJ, id_card, birth_date) VALUES ('B_NEG', 'Kathi Bela', 'almafa', 'ROLE_USER', 'kkaati', 423431123, '1D899R', '2000-03-23');
INSERT INTO donor (blood_type, donor_name, password, role, user_name, TAJ, id_card, birth_date) VALUES ('A_NEG', 'Godeny Gyorrgy', 'almafa', 'ROLE_USER', 'donor', 423423113, '1D899R', '2000-03-23');

INSERT INTO donation_place (address, city, is_active, place) VALUES ('Czuczor utca 8', 'Budapest', 1, 'Plazma');
INSERT INTO donation_place (address, city, is_active, place) VALUES ('Czuczor utca 9', 'Debrecen', 1, 'OVSZ');

INSERT INTO application (applied_time, has_appeared, donor_id, place_id) VALUES (CURRENT_DATE(), 1, 1, 2);
INSERT INTO application (applied_time, has_appeared, donor_id, place_id) VALUES ('1997-12-12', 0, 2,1);
INSERT INTO application (applied_time, has_appeared, donor_id, place_id) VALUES ('1997-12-12', 0, 1,1);


INSERT INTO donation (donation_time, is_used, donor_id, place_id ) VALUES (CURRENT_DATE(), 0,1,2 );
INSERT INTO donation (donation_time, is_used, donor_id, place_id ) VALUES (CURRENT_DATE(), 0, 2,1);


INSERT INTO faq (question, answer) VALUES ('Mennyi ideig tart egy veradas?', 'Kb 30 perc');
INSERT INTO faq (question, answer) VALUES ('Kell-e varni veradas utan?', 'Igen celszeru pihenni utana 5-10 percet');

INSERT INTO message (title, message, posted, donor_id) VALUES ('Korabbi veradasa', 'Koszontjuk!', CURRENT_TIMESTAMP(), 1);
INSERT INTO message (title, message, posted, donor_id) VALUES ('Visszajelzes', 'Sajnos em megfelelő a verallapot!', CURRENT_TIMESTAMP(), 2);

INSERT INTO news (title, message, publish_date) VALUES ('Nyitvatartasunk','Hosszabbitott nyitvatartassal varjuk veradoinkat!', CURRENT_TIMESTAMP());
INSERT INTO news (title, message, publish_date) VALUES ('Unnepi nyitvatartasunk', 'Unnepi nyitvatartasunk a kovetkezo:', CURRENT_TIMESTAMP());

INSERT INTO response (title, response, send_at, donation_id) VALUES ('Unnepi nyitvatartasunk', 'Unnepi nyitvatartasunk a kovetkezo:', CURRENT_TIMESTAMP(), 2);


