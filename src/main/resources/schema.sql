CREATE TABLE IF NOT EXISTS `scm_users` (
  `user_id` int AUTO_INCREMENT  PRIMARY KEY,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `contact_number` varchar(10) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `role` varchar(25) NOT NULL,
  `dob` date NOT NULL,
  `is_enabled` varchar(10) NULL,
  `profile_img` varchar(50) NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `scm_contacts` (
    `contact_id` int AUTO_INCREMENT PRIMARY KEY,
    `user_id` int NOT NULL,
    `name` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL,
    `phone` varchar(10) NOT NULL,
    `relationship` varchar(20) NOT NULL,
    `profile_img` varchar(50) NOT NULL,
    `description` varchar(250) NULL,
    `is_favorite` varchar(10) NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    `created_by` varchar(50) NOT NULL,
    `updated_at` TIMESTAMP DEFAULT NULL,
    `updated_by` varchar(50) DEFAULT NULL,
     FOREIGN KEY (user_id) REFERENCES scm_users(user_id)
    );