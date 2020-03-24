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
  `sex` varchar(255) DEFAULT NULL,
  `id` bigint(32) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKfjkt9xbjvf26s6t19sa0l5cb4` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table eib.citizen: ~0 rows (approximately)
/*!40000 ALTER TABLE `citizen` DISABLE KEYS */;
INSERT IGNORE INTO `citizen` (`address`, `birth_date`, `blood_group`, `citizen_point`, `emergency_mobile`, `emergency_relation`, `image_url`, `mobile`, `nid`, `sex`, `id`) VALUES
	('BRAC University, Mohakhali', '2020-03-11', 'O+', 1, '546516456465', 'Brother', 'citizen2020-36-24_11-36-12putIn.png', '01843771138', 564654654, 'Male', 2),
	('Heaven', '2020-03-17', 'O-', 1, '54654564564654', 'Fellow', 'citizen2020-44-24_11-44-12093b6b02-5389-4b7f-804b-d1f87eda9f2d.sized-1000x1000.jpg', '65654654', 5454546546546546546, 'Other', 5);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table eib.criminal_record: ~0 rows (approximately)
/*!40000 ALTER TABLE `criminal_record` DISABLE KEYS */;
INSERT IGNORE INTO `criminal_record` (`id`, `active`, `date`, `description`, `location`, `title`, `citizen_id`, `police_station_id`) VALUES
	(1, b'1', '2020-03-09', '<p>Criminal data, images and stuff&nbsp;</p>', 'Mohakhali', 'Theft at Jacky\'s House', 2, 4),
	(2, b'1', '2020-03-19', '<p>bla bla bla</p>', 'Heaven', 'Got no chill', 5, 4);
/*!40000 ALTER TABLE `criminal_record` ENABLE KEYS */;

-- Dumping structure for table eib.hospital
CREATE TABLE IF NOT EXISTS `hospital` (
  `address` varchar(255) DEFAULT NULL,
  `id` bigint(32) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKburp17s1diyv4sjsgs4yggq4l` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table eib.hospital: ~0 rows (approximately)
/*!40000 ALTER TABLE `hospital` DISABLE KEYS */;
INSERT IGNORE INTO `hospital` (`address`, `id`) VALUES
	('Mohakhali', 3);
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

-- Dumping data for table eib.medical_record: ~0 rows (approximately)
/*!40000 ALTER TABLE `medical_record` DISABLE KEYS */;
INSERT IGNORE INTO `medical_record` (`id`, `date`, `description`, `doctor`, `testbp`, `testbs`, `testcbc`, `testecg`, `testlp`, `testurine`, `title`, `citizen_id`, `hospital_id`) VALUES
	(1, '2020-03-18', '<p><strong>This Person is sick</strong></p>\r\n<p><em>Takecare</em></p>\r\n<p><em><img src="/citizen-records/2/medical/2020-39-24_11-39-58/d1992971e172a5471d6e0e1de843d747.jpg" alt="" width="440" height="622" /></em></p>', 'Dr Strange', '80', '50', '1600', '', '', '', 'roger bus magni illum.', 2, 3),
	(2, '2020-03-12', '<p>Left tooth brokern</p>\r\n<p><img src="/citizen-records/2/medical/2020-41-24_11-41-43/d1992971e172a5471d6e0e1de843d747.jpg" alt="" width="440" height="622" /></p>', 'Dr George', '70', '', '1200', '', '', '', 'Toothache', 2, 3),
	(3, '2020-03-26', '<p>bla bla bla</p>', 'Dr Strange', '90', '', '1200', '', '', '', 'Sick of staying home', 2, 3),
	(4, '2020-03-10', '<p><img src="/citizen-records/5/medical/2020-50-24_11-50-29/d1992971e172a5471d6e0e1de843d747.jpg" alt="" width="440" height="622" /></p>\r\n<p>lord oh lord</p>', 'Dr Strange', '80', '55', '1200', '', '', '', 'Broke left arm', 5, 3),
	(5, '2020-03-30', '<p><img src="/citizen-records/5/medical/2020-51-24_11-51-08/d1992971e172a5471d6e0e1de843d747.jpg" alt="" width="440" height="622" /></p>\r\n<p>sad jesus</p>', 'Dr Strange', '80', '50', '1200', '', '', '', 'Broke right arm', 5, 3);
/*!40000 ALTER TABLE `medical_record` ENABLE KEYS */;

-- Dumping structure for table eib.police_station
CREATE TABLE IF NOT EXISTS `police_station` (
  `address` varchar(255) DEFAULT NULL,
  `id` bigint(32) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKgyhsl82f40pmqd96wdittydui` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table eib.police_station: ~0 rows (approximately)
/*!40000 ALTER TABLE `police_station` DISABLE KEYS */;
INSERT IGNORE INTO `police_station` (`address`, `id`) VALUES
	('Banani', 4);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table eib.user: ~0 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT IGNORE INTO `user` (`id`, `email`, `name`, `password`, `permissions`, `role`) VALUES
	(1, 'ad@a.c', 'Admin', '$2y$12$hOE2pbMYFI89vwMFjbmo9.92sXzjff7Prkq5VFLSxOVpyKE/0IDHS', '', 'ADMIN'),
	(2, 'a@a.c', 'Ahmed Nafisul Bari', '$2a$10$Y5CrNFm/1TaYmUPOyHKmjudgwv8I3WGWRJ0tA8afWsgryHgGNwPXO', '', 'CITIZEN'),
	(3, 'h@a.c', 'Mohakhali HOS', '$2a$10$5bSebZpznd4PkhQnu6kXQOb7NRADnWtJ2q9taSX2sWtwjH1s8NRuy', '', 'HOSPITAL'),
	(4, 'p@a.c', 'Banani Thanna', '$2a$10$z.e8kBMwkFRR8lpZZek3J.UwMLxIQbeLke.YU9uICrNq.K2V5KC7O', '', 'POLICE'),
	(5, 'j@a.c', 'Jesus', '$2a$10$HO5TaVfcDHLFIND1O.a0AuAJcyP4iZpvHcDmsUQNRZ3e9Vuvc9xny', '', 'CITIZEN');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
