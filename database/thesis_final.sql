-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 12, 2015 at 07:26 AM
-- Server version: 5.6.11
-- PHP Version: 5.5.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `thesis`
--
CREATE DATABASE IF NOT EXISTS `thesis` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `thesis`;

-- --------------------------------------------------------

--
-- Table structure for table `biggroup_group`
--

CREATE TABLE IF NOT EXISTS `biggroup_group` (
  `id_big_group_face` int(11) NOT NULL,
  `id_group_face` int(11) NOT NULL,
  KEY `FK_d6uolqw4kgb4t8ubogrhbi6p5` (`id_group_face`),
  KEY `FK_4m3hnub17ou8ah6xqdir0sdvh` (`id_big_group_face`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `big_group_face`
--

CREATE TABLE IF NOT EXISTS `big_group_face` (
  `id_big_group_face` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name_big_group_face` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_big_group_face`),
  UNIQUE KEY `UK_hchgklg1r612yrhsc9ig9xc08` (`name_big_group_face`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_create` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `from_client_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `like_count` int(11) DEFAULT NULL,
  `message` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name_client` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_like` bit(1) DEFAULT NULL,
  `id_post` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_835pow8x9ek2ht2mjb9dsfau4` (`id_comment`),
  KEY `FK_qp41ionc07ros07rta0rh5nnf` (`id_post`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `fileupload`
--

CREATE TABLE IF NOT EXISTS `fileupload` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_file` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `length` int(11) DEFAULT NULL,
  `message` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `path` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `success` bit(1) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `group_face`
--

CREATE TABLE IF NOT EXISTS `group_face` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `administrator` bit(1) DEFAULT NULL,
  `bookmark_order` int(11) DEFAULT NULL,
  `client_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8_unicode_ci,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `icon` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_group_face` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_owner` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name_group_face` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `privacy` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_time` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `venue` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hanxyq360iqcwog3rudvy5kj8` (`id_group_face`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `membergroup`
--

CREATE TABLE IF NOT EXISTS `membergroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bio` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birthday` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `education` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `favorite_athletes` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `favorite_teams` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `first_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `hometown` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_group` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `interested_in` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `languages` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `link` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `locale` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `location` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `member_id` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `middle_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `picture` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `political` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `quotes` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `relationship_status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `relition` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `significant_other` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `timezone` double DEFAULT NULL,
  `updated_time` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `verified` bit(1) DEFAULT NULL,
  `website` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `work` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `group_face` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7ldjpumyetrvk2mpo6cqkjy4g` (`member_id`),
  KEY `FK_q9q6u66q6sbah1vjq9py20htr` (`group_face`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE IF NOT EXISTS `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `application_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date_created_time` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date_updated_time` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `from_object_id` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_show` bit(1) DEFAULT NULL,
  `link` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `notification_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `to_object_id` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `unread` bit(1) DEFAULT NULL,
  `id_post` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7pna3u9o32qbcf2ts0u4ooqc2` (`notification_id`),
  KEY `FK_j9a3okmjfh7x8mwtl3j7v1hap` (`id_post`),
  KEY `FK_1urdwwsh2ti15ta6f6p5dbdcp` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `post_facebook`
--

CREATE TABLE IF NOT EXISTS `post_facebook` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `caption` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_date` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8_unicode_ci,
  `file_path` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `from_client_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `full_picture` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_group` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_post` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `like_count` int(11) DEFAULT NULL,
  `link` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `message` text COLLATE utf8_unicode_ci,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `photo_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `picture` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `share_count` int(11) DEFAULT NULL,
  `source` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_date` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_like` bit(1) DEFAULT NULL,
  `video_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_group_face` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_l73ahu0e9iw4k44m0uqx9ps7o` (`id_post`),
  KEY `FK_kpjc7vormd936fuyv4210br5t` (`id_group_face`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE IF NOT EXISTS `schedule` (
  `id_schedule` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date_post` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `group_caption` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `group_description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `group_image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `group_link` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `group_message` text COLLATE utf8_unicode_ci,
  `group_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `state` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_schedule`),
  UNIQUE KEY `UK_lu2b1sg4xf4h2hyi8ns1an1e5` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `schedule_group`
--

CREATE TABLE IF NOT EXISTS `schedule_group` (
  `id_schedule` int(11) NOT NULL,
  `id_group_face` int(11) NOT NULL,
  KEY `FK_qqx8gfqgr77b73xff24acbrfm` (`id_group_face`),
  KEY `FK_9emyyvrk9mqxt1ou0you9ffpp` (`id_schedule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `access_token` text COLLATE utf8_unicode_ci,
  `first_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_facebook_fullname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lqjrcobrh9jc8wpcar64q1bfh` (`user_name`),
  UNIQUE KEY `UK_a3imlf41l37utmxiquukk8ajc` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `biggroup_group`
--
ALTER TABLE `biggroup_group`
  ADD CONSTRAINT `FK_4m3hnub17ou8ah6xqdir0sdvh` FOREIGN KEY (`id_big_group_face`) REFERENCES `big_group_face` (`id_big_group_face`),
  ADD CONSTRAINT `FK_d6uolqw4kgb4t8ubogrhbi6p5` FOREIGN KEY (`id_group_face`) REFERENCES `group_face` (`id`);

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK_qp41ionc07ros07rta0rh5nnf` FOREIGN KEY (`id_post`) REFERENCES `post_facebook` (`id`);

--
-- Constraints for table `membergroup`
--
ALTER TABLE `membergroup`
  ADD CONSTRAINT `FK_q9q6u66q6sbah1vjq9py20htr` FOREIGN KEY (`group_face`) REFERENCES `group_face` (`id`);

--
-- Constraints for table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `FK_1urdwwsh2ti15ta6f6p5dbdcp` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_j9a3okmjfh7x8mwtl3j7v1hap` FOREIGN KEY (`id_post`) REFERENCES `post_facebook` (`id`);

--
-- Constraints for table `post_facebook`
--
ALTER TABLE `post_facebook`
  ADD CONSTRAINT `FK_kpjc7vormd936fuyv4210br5t` FOREIGN KEY (`id_group_face`) REFERENCES `group_face` (`id`);

--
-- Constraints for table `schedule_group`
--
ALTER TABLE `schedule_group`
  ADD CONSTRAINT `FK_9emyyvrk9mqxt1ou0you9ffpp` FOREIGN KEY (`id_schedule`) REFERENCES `schedule` (`id_schedule`),
  ADD CONSTRAINT `FK_qqx8gfqgr77b73xff24acbrfm` FOREIGN KEY (`id_group_face`) REFERENCES `group_face` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
