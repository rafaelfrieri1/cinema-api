# --- !Ups

CREATE TABLE `cinema`.`movies`(
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `imdb_id` VARCHAR(30) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY(`id`)
);

INSERT INTO `cinema`.`movies` (`name`, `imdb_id`)
  VALUES ('The Fast and the Furious', 'tt0232500'),
         ('2 Fast 2 Furious', 'tt0322259'),
         ('The Fast and the Furious: Tokyo Drift', 'tt0463985'),
         ('Fast & Furious', 'tt1013752'),
         ('Fast Five', 'tt1596343'),
         ('Fast & Furious 6', 'tt1905041'),
         ('Furious 7', 'tt2820852'),
         ('The Fate of the Furious', 'tt4630562'),
         ('F9: The Fast Saga', 'tt5433138');

# --- !Downs

DROP TABLE `cinema`.`movies`;
