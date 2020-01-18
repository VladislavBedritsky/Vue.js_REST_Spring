USE vue;

INSERT INTO users (id, name, user_pic, email, gender, locale, last_visit) VALUES
            ('1','test','test','test','test','test','2019-12-24 23:59:59');

INSERT INTO messages (id, text, date_time, user_id) VALUES (1, 'First Message', '2019-12-24 23:59:59', 1);
INSERT INTO messages (id, text, date_time, user_id) VALUES (2, 'Second Message', '2019-12-24 23:59:59', 1);
INSERT INTO messages (id, text, date_time, user_id) VALUES (3, 'Third Message', '2019-12-24 23:59:59', 1);

INSERT INTO comments (id, text, message_id, user_id) VALUES (3, 'well done', 3, 1);


