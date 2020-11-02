INSERT INTO donor (blood_type, donor_name, password, role, username, TAJ, id_card, birth_date, next_donation_date, male) VALUES
        ('A_POZ', 'Terék Gergő', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..',
         'ROLE_DONOR', 'rizsutt', 234231123, '1D899R', '2000-03-23', CURRENT_TIMESTAMP(), 1);
INSERT INTO donor (blood_type, donor_name, password, role, username, TAJ, id_card, birth_date, next_donation_date, male) VALUES
        ('ZERO_POZ', 'Pintér Dorottya', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..',
         'ROLE_ADMIN', 'pdorka97', 423431123, '1D899R', '1993-03-23', CURRENT_TIMESTAMP(), 0);
INSERT INTO donor (blood_type, donor_name, password, role, username, TAJ, id_card, birth_date, next_donation_date, male) VALUES
        ('ZERO_NEG', 'Kiss Jenő', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..',
         'ROLE_ADMIN', 'jeno72', 423423123, '1D899R', '2001-03-23', CURRENT_TIMESTAMP(), 1);
INSERT INTO donor (blood_type, donor_name, password, role, username, TAJ, id_card, birth_date, next_donation_date, male) VALUES
        ('B_NEG', 'Kathi Béla', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..',
        'ROLE_DONOR', 'kkaati', 423431123, '1D899R', '2010-03-23', CURRENT_TIMESTAMP(), 1);
INSERT INTO donor (blood_type, donor_name, password, role, username, TAJ, id_card, birth_date, next_donation_date, male) VALUES
        ('A_NEG', 'Godeny Gyorrgy', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..',
         'ROLE_NURSE', 'donor', 423423113, '1D899R', '2009-03-23', CURRENT_TIMESTAMP(), 0);

INSERT INTO place (address, city, is_active, name) VALUES ('Czuczor utca 8', 'Budapest', 1, 'Plazma');
INSERT INTO place (address, city, is_active, name) VALUES ('Czuczor utca 9', 'Debrecen', 1, 'OVSZ');

INSERT INTO donation (donation_date, transport_date) VALUES (CURRENT_DATE(),  CURRENT_TIMESTAMP());
INSERT INTO donation (donation_date, transport_date) VALUES (CURRENT_DATE(), null);
INSERT INTO donation (donation_date, transport_date) VALUES (CURRENT_DATE(), null);

INSERT INTO application (applied_date, donor_id, place_id, donation_id) VALUES (CURRENT_DATE(), 1, 2,1);
INSERT INTO application (applied_date, donor_id, place_id, donation_id) VALUES ('1997-12-12',  2,1,3);
INSERT INTO application (applied_date, donor_id, place_id, donation_id) VALUES ('1997-12-12', 1,1,null);


INSERT INTO faq (question, answer) VALUES ('Mennyi ideig tart egy veradas?', 'Kb 30 perc');
INSERT INTO faq (question, answer) VALUES ('Kell-e varni veradas utan?', 'Igen celszeru pihenni utana 5-10 percet');

INSERT INTO message (title, message, send_date, seen, donor_id, apply_id) VALUES ('Korabbi veradasa', 'Koszontjuk!', CURRENT_TIMESTAMP(), 1, 1, null);
INSERT INTO message (title, message, send_date, seen, donor_id, apply_id) VALUES ('Visszajelzes', 'Sajnos em megfelelő a verallapot!', CURRENT_TIMESTAMP(), 0, 2, null);

INSERT INTO news (title, message, publish_date) VALUES ('Nyitvatartásunk','Hosszabbitott nyitvatartassal varjuk veradoinkat!', CURRENT_TIMESTAMP());
INSERT INTO news (title, message, publish_date) VALUES ('Unnepi nyitvatartasunk', 'Unnepi nyitvatartasunk a kovetkezo:', CURRENT_TIMESTAMP());


INSERT INTO opening_time (place_id, monday, tuesday, wednesday, thursday, friday, saturday, sunday, opening_time, closing_time) VALUES
        (1, 1, 1, 1, 1, 1, 1, 1, 60, 170);
INSERT INTO opening_time (place_id, monday, tuesday, wednesday, thursday, friday, saturday, sunday, opening_time, closing_time) VALUES
        (2, 1, 1, 1, 1, 1, 1, 1, 60, 800);


