-- DROP SCHEMA  IF EXISTS `cs5200`;

-- CREATE SCHEMA `cs5200`;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `Module`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `Module`(
`id` varchar(255) NOT NULL DEFAULT '',
PRIMARY KEY (`id`)
) ENGINE=InnoDB;

INSERT INTO `Module` (`id`) VALUES ('Project1'), ('Project2'), ('Assignment1'), ('Assignment2'), ('Quiz1'), ('Quiz2'), ('Exam'), ('Logistics');

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `question`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text` varchar(255) NOT NULL,
  `user_id` int NOT NULL,
  `posted_on` date DEFAULT NULL,
  `length` int DEFAULT 0,
  `views` int DEFAULT 0,
  `endorsedByinstructor` tinyint(1) DEFAULT 0,
  `module` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `question_asked_by_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `module_enum` FOREIGN KEY (`module`) REFERENCES `Module`(`id`)
)ENGINE=InnoDB;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `answer`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `answer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text` varchar(255) NOT NULL,
  `user_id` int NOT NULL,
  `posted_on` date DEFAULT NULL,
  `correctAnswer` tinyint(1) DEFAULT 0,
  `upVotes` int DEFAULT 0,
  `downVotes` int DEFAULT 0,
  `question_id` int NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT answer_user_id_user FOREIGN KEY (`user_id`) REFERENCES `user`(`id`),
  CONSTRAINT answer_of_question FOREIGN KEY (question_id) REFERENCES question(`id`) ON DELETE CASCADE
)ENGINE=InnoDB;