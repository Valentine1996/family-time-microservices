INSERT INTO family (id, name) VALUES
  (1, 'Family1'),
  (2, 'Family2');

INSERT INTO user(id, family_id, first_name, last_name, middle_name, username, password, birthday, gender, locale, isParent, isActive) VALUES
  (1, 1, 'Valentyn','Namisnyk','Yaroslavovuch','valentunnamisnuk@gmail.com','Softjourn1996',DATE '1996-02-26',TRUE,'uk_UA',TRUE,TRUE);
--   (2, 2, 'Oleh','Semankiv','Olehovuch','OlehSemankiv@ukr.net','Softjourn1887',TO_DATE(1987-03-15,'yyyy-MM-dd'),TRUE,'uk_UA',TRUE,TRUE);


INSERT INTO role(id, authority) VALUES
  (1, 'PARENT'),
  (2, 'CHILD');

INSERT INTO user_role(user_id, role_id) VALUES
  (1, 1);

INSERT INTO  bonus_type(id, family_id, short_name, icon_name, description) VALUES
  (1, 1, 'Points', 'mock', 'Some points'),
  (2, 1, 'Pizza', 'mock', 'Some pizza');

INSERT INTO bonus(id, bonus_type_id, title,price,description) VALUES
  (1, 2, 'Kaprichoza', 0, 'pizza');

INSERT INTO  complexity(id, family_id, type, description) VALUES
  (1, 1, 'EASY', 'EASY'),
  (2, 1, 'HARD', 'HARD');

INSERT INTO task_type(id, family_id, shortName, description) VALUES
  (1, 1, 'Homework', 'Homework'),
  (2, 1, 'Housework', 'Housework');

INSERT INTO task(id, bonus_id, complexity_id, task_type_id, creator_id, performer_id, status, description, prize, has_subtasks, step, parent_id,close_to, created_at) VALUES
  (1000, 1,1,1,1,1, 'OPEN', 'Make Math',100, false,null,null, DATE '2017-01-21', DATE '2017-01-21'),
  (1001, 1,1,2,1,1,'OPEN', 'Clean house', 100, false,null,null, DATE '2017-01-21', DATE '2017-01-21'),
  (1002, 1,1,2,1,1,'OPEN', 'Clean corridor', 100, false, 1, 1001, DATE '2017-01-21', DATE '2017-01-21');