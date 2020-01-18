USE vue;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS comments;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE IF NOT EXISTS users (
  id VARCHAR(255) NOT NULL,
  name VARCHAR(255),
  user_pic VARCHAR(255),
  email VARCHAR(255),
  gender VARCHAR(255),
  locale VARCHAR(255),
  last_visit DATETIME,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS messages (
  id INT NOT NULL AUTO_INCREMENT,
  text VARCHAR(255),
  date_time DATETIME,
  link varchar(255),
  link_title varchar(255),
  link_description varchar(255),
  link_cover varchar(255),
  user_id VARCHAR(255),

  PRIMARY KEY (id),
  INDEX user_id (user_id),

  FOREIGN KEY (user_id)
       REFERENCES users(id)
       ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS comments (
  id INT NOT NULL AUTO_INCREMENT,
  text VARCHAR(255),
  message_id INT,
  user_id VARCHAR(255),

  PRIMARY KEY (id),
  INDEX message_id (message_id),
  INDEX user_id (user_id),

  FOREIGN KEY (message_id)
       REFERENCES messages(id)
       ON DELETE CASCADE,

  FOREIGN KEY (user_id)
       REFERENCES users(id)
       ON DELETE CASCADE
);