# --- !Ups

CREATE TABLE `cinema`.`movie_ratings`(
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `movie_id` INT(11) NOT NULL,
  `rating` DECIMAL(7,1) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY(`id`),
  CONSTRAINT `movie_id_ratings_fk`
    FOREIGN KEY (`movie_id`)
    REFERENCES `cinema`.`movies` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
);

# --- !Downs

DROP TABLE `cinema`.`movie_ratings`;
