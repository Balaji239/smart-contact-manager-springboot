INSERT INTO `scm_users` (`first_name`, `last_name`,`email`,`contact_number`,`dob`,`gender`,`password`,`is_enabled`, `created_at`,`created_by`,`role`)
VALUES ('Balaji','R','baji2391999@gmail.com','8310850942','1999-09-23','Male','$2a$12$q0NuZaeI9V4AwmBz.E2nx.d8UCm9yWIBLrNw/h.K0q33af9XZHlOK','true',CURDATE(),'DBA','ROLE_NORMAL');

INSERT INTO `scm_contacts` (`name`,`email`,`phone`,`relationship`,`description`,`is_favorite`,`profile_img`,`user_id`,`created_at`,`created_by`)
VALUES ('John','john@gmail.com','1234567894','Friend','My Friend John','false','default.png',1,CURDATE(),'DBA')