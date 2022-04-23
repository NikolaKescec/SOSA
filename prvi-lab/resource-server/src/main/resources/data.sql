INSERT INTO record (id, record_name, record_date, record_origin, record_value)
VALUES (1, 'Record1', CURRENT_DATE, 'Croatia', '123');
INSERT INTO record (id, record_name, record_date, record_origin, record_value)
VALUES (2, 'Record2', CURRENT_DATE, 'USA', '321');
INSERT INTO record (id, record_name, record_date, record_origin, record_value)
VALUES (3, 'Record3', CURRENT_DATE, 'Russia', '231');
INSERT INTO record (id, record_name, record_date, record_origin, record_value)
VALUES (4, 'Record4', CURRENT_DATE, 'China', '312');

-- pass1
INSERT INTO student (id, user_name, pass_word)
VALUES (1, 'User1', '$2a$12$yytUQQtkrkZAu4S1wEjXZO8KD5cALukRIxZMOYTC701Jjh85D/dH6');
-- pass2
INSERT INTO student (id, user_name, pass_word)
VALUES (2, 'User2', '$2a$12$fE1N50RUSG2/pKE5FsMdvOT3oVoHWPdQN9XS2dbossZ3CtI0kH/sm');
