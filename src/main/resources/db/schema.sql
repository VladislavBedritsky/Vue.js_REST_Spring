USE vue;

DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS messages (
  id INT NOT NULL AUTO_INCREMENT,
  text VARCHAR(255),
  date_time DATETIME,
  link varchar(255),
  link_title varchar(255),
  link_description varchar(255),
  link_cover varchar(255),
  PRIMARY KEY (id)
);

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