-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               10.4.8-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for eib
CREATE DATABASE IF NOT EXISTS `eib` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `eib`;

-- Dumping structure for table eib.citizen
CREATE TABLE IF NOT EXISTS `citizen` (
  `address` varchar(255) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `blood_group` varchar(255) DEFAULT NULL,
  `citizen_point` int(11) NOT NULL,
  `emergency_mobile` varchar(255) DEFAULT NULL,
  `emergency_relation` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `nid` bigint(20) DEFAULT NULL,
  `qr_url` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `id` bigint(32) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKfjkt9xbjvf26s6t19sa0l5cb4` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table eib.citizen: ~2 rows (approximately)
/*!40000 ALTER TABLE `citizen` DISABLE KEYS */;
INSERT IGNORE INTO `citizen` (`address`, `birth_date`, `blood_group`, `citizen_point`, `emergency_mobile`, `emergency_relation`, `image_url`, `mobile`, `nid`, `qr_url`, `sex`, `id`) VALUES
	('Frankia', '2020-04-15', 'O+', 0, '123456789123', 'Wife', '/citizen-records/2/citizen2020-38-06_06-38-17john-smith-9486928-1-402.jpg', '123456789123', 6546546546546546546, '/citizen-records/2/2.png', 'Male', 2),
	('Heavan, Oh Heavaaan', '2020-04-07', 'O-', 3, '111111111111', 'Follower', '/citizen-records/3/citizen2020-40-06_06-40-21093b6b02-5389-4b7f-804b-d1f87eda9f2d.sized-1000x1000.jpg', '000000000000', 54654654654, '/citizen-records/3/3.png', 'Male', 3);
/*!40000 ALTER TABLE `citizen` ENABLE KEYS */;

-- Dumping structure for table eib.citizen_request
CREATE TABLE IF NOT EXISTS `citizen_request` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `emergency_mobile` varchar(255) DEFAULT NULL,
  `emergency_relation` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `citizen_id` bigint(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkqf3rkll19ag33xlkknugaqro` (`citizen_id`),
  CONSTRAINT `FKkqf3rkll19ag33xlkknugaqro` FOREIGN KEY (`citizen_id`) REFERENCES `citizen` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table eib.citizen_request: ~0 rows (approximately)
/*!40000 ALTER TABLE `citizen_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `citizen_request` ENABLE KEYS */;

-- Dumping structure for table eib.criminal_record
CREATE TABLE IF NOT EXISTS `criminal_record` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `date` date DEFAULT NULL,
  `description` longtext DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `citizen_id` bigint(32) DEFAULT NULL,
  `police_station_id` bigint(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdys5t870iojrv0ke91gg61dip` (`citizen_id`),
  KEY `FK2qm7fy6x8xw0vq57baht1ur4w` (`police_station_id`),
  CONSTRAINT `FK2qm7fy6x8xw0vq57baht1ur4w` FOREIGN KEY (`police_station_id`) REFERENCES `police_station` (`id`),
  CONSTRAINT `FKdys5t870iojrv0ke91gg61dip` FOREIGN KEY (`citizen_id`) REFERENCES `citizen` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table eib.criminal_record: ~2 rows (approximately)
/*!40000 ALTER TABLE `criminal_record` DISABLE KEYS */;
INSERT IGNORE INTO `criminal_record` (`id`, `active`, `date`, `description`, `location`, `title`, `citizen_id`, `police_station_id`) VALUES
	(1, b'1', '2020-04-03', '<p>Bad man&nbsp;&nbsp;</p>\r\n<p>and some details and docs</p>\r\n<p><img src="/citizen-records/2/criminal/2020-51-06_06-51-42/max-pooling.png" alt="" width="401" height="161" /></p>', 'India', 'Broke into india', 2, 5),
	(3, b'1', '2020-04-24', '<p>Stole macy\'s apple&nbsp;</p>\r\n<p>apple\'s image attached</p>\r\n<p><img src="/citizen-records/3/criminal/2020-53-06_06-53-38/6000200094514.jpg" alt="" width="460" height="460" /></p>', 'Heaven', 'Stole an apple', 3, 5),
	(4, b'1', '2020-04-14', '<p>supplied illegal drugs to queen</p>', 'England', 'Drug dealing ', 2, 7),
	(5, b'1', '2020-04-07', '<p>Stole a chair in paris</p>', 'Hell', 'Stole a chair', 3, 7);
/*!40000 ALTER TABLE `criminal_record` ENABLE KEYS */;

-- Dumping structure for table eib.hospital
CREATE TABLE IF NOT EXISTS `hospital` (
  `address` varchar(255) DEFAULT NULL,
  `id` bigint(32) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKburp17s1diyv4sjsgs4yggq4l` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table eib.hospital: ~1 rows (approximately)
/*!40000 ALTER TABLE `hospital` DISABLE KEYS */;
INSERT IGNORE INTO `hospital` (`address`, `id`) VALUES
	('Mohakhali', 4),
	('Ctg', 6);
/*!40000 ALTER TABLE `hospital` ENABLE KEYS */;

-- Dumping structure for table eib.medical_record
CREATE TABLE IF NOT EXISTS `medical_record` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `description` longtext DEFAULT NULL,
  `doctor` varchar(255) DEFAULT NULL,
  `testbp` varchar(255) DEFAULT NULL,
  `testbs` varchar(255) DEFAULT NULL,
  `testcbc` varchar(255) DEFAULT NULL,
  `testecg` varchar(255) DEFAULT NULL,
  `testlp` varchar(255) DEFAULT NULL,
  `testurine` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `citizen_id` bigint(32) DEFAULT NULL,
  `hospital_id` bigint(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjj3tdte1bvcjpobed7bvgncny` (`citizen_id`),
  KEY `FK1dwbc0qn49qgbejvc1663kk8h` (`hospital_id`),
  CONSTRAINT `FK1dwbc0qn49qgbejvc1663kk8h` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`id`),
  CONSTRAINT `FKjj3tdte1bvcjpobed7bvgncny` FOREIGN KEY (`citizen_id`) REFERENCES `citizen` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table eib.medical_record: ~5 rows (approximately)
/*!40000 ALTER TABLE `medical_record` DISABLE KEYS */;
INSERT IGNORE INTO `medical_record` (`id`, `date`, `description`, `doctor`, `testbp`, `testbs`, `testcbc`, `testecg`, `testlp`, `testurine`, `title`, `citizen_id`, `hospital_id`) VALUES
	(1, '2020-04-20', '<p>John broke his left arm&nbsp;</p>\r\n<p>Check prescription below</p>\r\n<p><img src="/citizen-records/2/medical/2020-44-06_06-44-41/drbeen-sample-prescription.jpg" alt="" width="604" height="782" /></p>', 'Dr Strange', '80', '55', '1600', '', '', '', 'Broke left arm', 2, 6),
	(2, '2020-04-29', '<p>Broke right arm&nbsp;</p>\r\n<p>3 broken bones</p>\r\n<p><img src="/citizen-records/2/medical/2020-46-06_06-46-07/drbeen-sample-prescription.jpg" alt="" width="711" height="920" /></p>', 'Dr George', '70', '56', '1200', '', '', '', 'Broke right arm', 2, 6),
	(3, '2020-04-14', '<p>Sad Jesus</p>\r\n<p><img src="/citizen-records/3/medical/2020-47-06_06-47-25/drbeen-sample-prescription.jpg" alt="" width="646" height="836" /></p>', 'Dr Strange', '90', '', '1600', '', '', '', 'Broke tooth', 3, 6),
	(4, '2020-04-07', '<p>dummy txt</p>\r\n<p><img src="/citizen-records/2/medical/2020-49-06_06-49-18/drbeen-sample-prescription.jpg" alt="" width="584" height="756" /></p>\r\n<p>&nbsp;</p>\r\n<p>&nbsp;</p>', 'Dr Strange', '70', '80', '1100', '', '', '', 'Got polio', 2, 6),
	(5, '2020-04-16', '<p>Prescribed 5 chills</p>', 'Dr George', '80', '50', '1200', '', '', '', 'Got no chill', 3, 6);
/*!40000 ALTER TABLE `medical_record` ENABLE KEYS */;

-- Dumping structure for table eib.police_station
CREATE TABLE IF NOT EXISTS `police_station` (
  `address` varchar(255) DEFAULT NULL,
  `id` bigint(32) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKgyhsl82f40pmqd96wdittydui` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table eib.police_station: ~1 rows (approximately)
/*!40000 ALTER TABLE `police_station` DISABLE KEYS */;
INSERT IGNORE INTO `police_station` (`address`, `id`) VALUES
	('Mohakhali', 5),
	('Dublin', 7);
/*!40000 ALTER TABLE `police_station` ENABLE KEYS */;

-- Dumping structure for table eib.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `permissions` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table eib.user: ~5 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT IGNORE INTO `user` (`id`, `email`, `name`, `password`, `permissions`, `role`) VALUES
	(1, 'ad@a.c', 'Admin', '$2y$12$hOE2pbMYFI89vwMFjbmo9.92sXzjff7Prkq5VFLSxOVpyKE/0IDHS', '', 'ADMIN'),
	(2, 'a@a.c', 'John Smith', '$2a$10$iQk8qPlGWczFKyM/H.vcs.C6BG9ICAg0W9EZxb84QlTyU1RhPtsPa', '', 'CITIZEN'),
	(3, 'j@a.c', 'Jesus Christ', '$2a$10$8Dco9lhH.eUfp4VLS/iJTu/jnHuOYu31.yARHCWobK4ciuOZxc6yu', '', 'CITIZEN'),
	(4, 'h@a.c', 'Mohakhali HOS', '$2a$10$g1MataR6de5vvVcfNeHuUOi9RTXPGZy1PveF8Tp9FMkZ/9KyXPSXW', '', 'HOSPITAL'),
	(5, 'p@a.c', 'Mohalakhali Police Station', '$2a$10$J2k7Dd3VVvQZbe6OFRspruN9Qyv77WCxMbkxBQM/.GZMKhN1cuGiK', '', 'POLICE'),
	(6, 'hh@a.c', 'Ctg Hospital', '$2a$10$n5TpI4LJmT9t/fY03SOGbepyUi7UO/fjJsc3HpGfw6ZeQGAvOgPUK', '', 'HOSPITAL'),
	(7, 'pp@a.c', 'Dublin POL', '$2a$10$YOFS0myrY.T.e.8gSrxreOdSqaUTPkkK.hkTmsUOHlld.coPq1UhK', '', 'POLICE');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
