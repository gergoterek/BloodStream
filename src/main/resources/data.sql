INSERT INTO donor (blood_type, donor_name, password, role, username, TAJ, id_card, birth_date, next_donation_date, male, total_donations) VALUES
        ('A_POZ', 'Kiss Balázs', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..',
         'ROLE_DONOR', 'donor1', 234231123, '1D899R', '2000-03-23', CURRENT_TIMESTAMP(), 1, 3);
INSERT INTO donor (blood_type, donor_name, password, role, username, TAJ, id_card, birth_date, next_donation_date, male) VALUES
        ('ZERO_POZ', 'Kiss Szilvia', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..',
         'ROLE_NURSE', 'nurse1', 423431123, '1D899R', '1993-03-23', CURRENT_TIMESTAMP(), 0);
INSERT INTO donor (blood_type, donor_name, password, role, username, TAJ, id_card, birth_date, next_donation_date, male) VALUES
        ('ZERO_NEG', 'Kiss Jenő', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..',
         'ROLE_ADMIN', 'admin1', 423423123, '1D899R', '2001-03-23', CURRENT_TIMESTAMP(), 1);
--INSERT INTO donor (blood_type, donor_name, password, role, username, TAJ, id_card, birth_date, next_donation_date, male) VALUES
--        (null, 'Kathi Béla', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..',
--        'ROLE_NURSE', 'kati', 423431123, '1D899R', '2010-03-23', CURRENT_TIMESTAMP(), 1);
--INSERT INTO donor (blood_type, donor_name, password, role, username, TAJ, id_card, birth_date, next_donation_date, male) VALUES
--        (null, 'Kiss Balázs', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..',
--         'ROLE_DONOR', 'kissbalazs', 123456789, '1D899R', '1985-03-23', CURRENT_TIMESTAMP(), 1);

INSERT INTO place (address, city, active, name) VALUES ('Czuczor street 8', 'Budapest', 1, 'Plazma');
INSERT INTO place (address, city, active, name) VALUES ('Izabella street 9', 'Debrecen', 1, 'OVSZ');


INSERT INTO donation (donation_date, transport_date) VALUES (CURRENT_DATE(), null);
INSERT INTO donation (donation_date, transport_date) VALUES (CURRENT_DATE(), '2020-12-12');
INSERT INTO donation (donation_date, transport_date) VALUES (CURRENT_DATE(), '2020-12-13');

INSERT INTO application (applied_date, directed_donation_code, donor_id, place_id, donation_id)
    VALUES ('2020-01-19',null, 1, 2,3);
INSERT INTO application (applied_date, directed_donation_code, donor_id, place_id, donation_id)
    VALUES ('2020-01-03',234341223, 1, 2,2);
INSERT INTO application (applied_date, directed_donation_code, donor_id, place_id, donation_id)
    VALUES ('2021-01-01',null, 1,1, null);


INSERT INTO faq (question, answer) VALUES ('How long does blood donation take?', 'Around half an hour');
INSERT INTO faq (question, answer) VALUES ('Should I wait after donation?', 'Yes, 5-10 minutes rest time sugggested');

INSERT INTO message (title, message, send_date, seen, donor_id, apply_id) VALUES ('Your previous donation', 'Thank you!', CURRENT_TIMESTAMP(), 0, 1, null);
INSERT INTO message (title, message, send_date, seen, donor_id, apply_id) VALUES ('Feedback', 'Unfortunately, we could not use your donation, because blood quality was not acceptable!', CURRENT_TIMESTAMP(), 0, 2, null);

INSERT INTO news (title, text, publish_date) VALUES ('Opening hours','We wainting our donors with longer opening hours!', CURRENT_TIMESTAMP());
INSERT INTO news (title, text, publish_date) VALUES ('Celebration period', 'Our opening hours changed in celebration period', CURRENT_TIMESTAMP());


INSERT INTO opening_time (place_id, monday, tuesday, wednesday, thursday, friday, saturday, sunday, start_time, closing_time) VALUES
        (1, 0, 0, 0, 1, 1, 1, 1, 450, 900);
INSERT INTO opening_time (place_id, monday, tuesday, wednesday, thursday, friday, saturday, sunday, start_time, closing_time) VALUES
        (2, 1, 1, 0, 0, 1, 0, 1, 600, 800);


