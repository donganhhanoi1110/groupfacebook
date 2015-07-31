-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 03, 2015 at 05:17 PM
-- Server version: 5.5.39
-- PHP Version: 5.4.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `thesis`
--

-- --------------------------------------------------------

--
-- Table structure for table `biggroup_group`
--

CREATE TABLE IF NOT EXISTS `biggroup_group` (
  `id_big_group_face` int(11) NOT NULL,
  `id_group_face` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `biggroup_group`
--

INSERT INTO `biggroup_group` (`id_big_group_face`, `id_group_face`) VALUES
(1, 1),
(1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `big_group_face`
--

CREATE TABLE IF NOT EXISTS `big_group_face` (
`id_big_group_face` int(11) NOT NULL,
  `client_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name_big_group_face` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `big_group_face`
--

INSERT INTO `big_group_face` (`id_big_group_face`, `client_id`, `image`, `name_big_group_face`) VALUES
(1, '445983705552033', NULL, 'Sport');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
`id` int(11) NOT NULL,
  `date_create` datetime DEFAULT NULL,
  `from_client_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_comment` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `like_count` int(11) DEFAULT NULL,
  `message` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name_client` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_post` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `date_create`, `from_client_id`, `id_comment`, `like_count`, `message`, `name_client`, `id_post`) VALUES
(1, '2015-07-03 17:49:34', '708394165956241', '990752297642042', 0, '@@', 'Hiep Nguyen', 1);

-- --------------------------------------------------------

--
-- Table structure for table `fileupload`
--

CREATE TABLE IF NOT EXISTS `fileupload` (
`id` int(11) NOT NULL,
  `length` int(11) DEFAULT NULL,
  `message` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `success` bit(1) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `fileupload`
--

INSERT INTO `fileupload` (`id`, `length`, `message`, `name`, `path`, `success`, `type`) VALUES
(1, 122290, '<img class="img-responsive img-rectangle mypicture" src=''/spring-social-quickstart-30x/uploadAjax/get/1435920401809'' />', 'fruit.jpg', 'D:\\Monhoc\\JavaFresherCSC\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\ThesisSpringSocial\\resources\\files\\fruit.jpg', b'1', 'image/jpeg'),
(2, 437424, '<video width="500" controls><source src=''/spring-social-quickstart-30x/uploadAjax/get/1435922383748'' type=''video/x-ms-wmv''>Your browser does not support HTML5 video.</video>', 'Capture_20150627.wmv', 'D:\\Monhoc\\JavaFresherCSC\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\ThesisSpringSocial\\resources\\files\\Capture_20150627.wmv', b'1', 'video/x-ms-wmv');

-- --------------------------------------------------------

--
-- Table structure for table `group_face`
--

CREATE TABLE IF NOT EXISTS `group_face` (
`id` int(11) NOT NULL,
  `administrator` bit(1) DEFAULT NULL,
  `bookmark_order` int(11) DEFAULT NULL,
  `client_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `icon` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_group_face` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_owner` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name_group_face` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `privacy` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updated_time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `venue` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `version` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `group_face`
--

INSERT INTO `group_face` (`id`, `administrator`, `bookmark_order`, `client_id`, `description`, `email`, `icon`, `id_group_face`, `id_owner`, `name_group_face`, `privacy`, `updated_time`, `venue`, `version`) VALUES
(1, b'0', 1, '445983705552033', NULL, NULL, NULL, '943562415694364', NULL, 'ThesisMinhHiep', NULL, NULL, NULL, 0),
(2, b'0', 2, '445983705552033', NULL, NULL, NULL, '1642294075990555', NULL, 'ThesisMinhHiep2', NULL, NULL, NULL, 0),
(3, b'0', 3, '445983705552033', NULL, NULL, NULL, '434472413309957', NULL, 'DJ Bắc Kiến Xương', NULL, NULL, NULL, 0),
(4, b'0', 4, '445983705552033', NULL, NULL, NULL, '302113079862502', NULL, 'Lớp 11TH2D - Khoa CNTT ĐH Tôn Đức Thắng', NULL, NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `membergroup`
--

CREATE TABLE IF NOT EXISTS `membergroup` (
`id` int(11) NOT NULL,
  `bio` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `birthday` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `education` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `favorite_athletes` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `favorite_teams` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `first_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `hometown` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `interested_in` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `languages` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `link` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `locale` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `location` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `member_id` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `middle_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `picture` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `political` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `quotes` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `relationship_status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `relition` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `significant_other` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `timezone` double DEFAULT NULL,
  `updated_time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `verified` bit(1) DEFAULT NULL,
  `website` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `work` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE IF NOT EXISTS `notification` (
`id` int(11) NOT NULL,
  `application_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date_created_time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date_updated_time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `from_object_id` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_show` bit(1) DEFAULT NULL,
  `link` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `notification_id` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `to_object_id` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `unread` bit(1) DEFAULT NULL,
  `id_post` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `post_facebook`
--

CREATE TABLE IF NOT EXISTS `post_facebook` (
`id` int(11) NOT NULL,
  `caption` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `file_path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `from_client_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `full_picture` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_group` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_post` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `like_count` int(11) DEFAULT NULL,
  `link` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `message` text COLLATE utf8mb4_unicode_ci,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `picture` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `source` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updated_date` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_group_face` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `post_facebook`
--

INSERT INTO `post_facebook` (`id`, `caption`, `created_date`, `description`, `file_path`, `from_client_id`, `full_picture`, `id_group`, `id_post`, `like_count`, `link`, `message`, `name`, `picture`, `source`, `type`, `updated_date`, `id_group_face`) VALUES
(1, NULL, '03/07/2015 17:46', NULL, NULL, '445983705552033', NULL, '943562415694364', '486095801540823', 0, 'https://www.facebook.com/photo.php?fbid=486095801540823&set=gm.990751784308760&type=1', NULL, 'HIHI', 'https://scontent.xx.fbcdn.net/hphotos-xta1/v/t1.0-9/s130x130/11403292_486095801540823_7394768922050121817_n.jpg?oh=588de5866faadedf83d31c7988e48e4a&oe=5627852A', 'https://scontent.xx.fbcdn.net/hphotos-xta1/v/t1.0-9/11403292_486095801540823_7394768922050121817_n.jpg?oh=b71e2110bfda806a7bb7b77dbacacf7a&oe=561974F2', NULL, '03/07/2015 17:46', 1),
(2, NULL, '03/07/2015 17:51', NULL, NULL, '445983705552033', NULL, '943562415694364', '486096698207400', 0, 'https://www.facebook.com/photo.php?fbid=486096698207400&set=gm.990752660975339&type=1', NULL, 'HAHA', 'https://fbcdn-sphotos-g-a.akamaihd.net/hphotos-ak-xaf1/v/t1.0-9/s130x130/11667364_486096698207400_733686594412207441_n.jpg?oh=2f40334bb6392ac1e00aa42b85d97ba1&oe=561268E8&__gda__=1445094541_8a2532b2b7689d1e9087393e088ba5e1', 'https://fbcdn-sphotos-g-a.akamaihd.net/hphotos-ak-xaf1/v/t1.0-9/11667364_486096698207400_733686594412207441_n.jpg?oh=9cf4fe4debd3e7bd58f94072fe8cee19&oe=562EDAE9&__gda__=1446280087_cf6b790102edcb973cca9c6fa3554c6f', NULL, '03/07/2015 17:51', 1),
(3, NULL, '03/07/2015 17:55', NULL, NULL, '445983705552033', NULL, '943562415694364', '486097134874023', 0, 'https://www.facebook.com/photo.php?fbid=486097134874023&set=gm.990754040975201&type=1', NULL, 'HOHO', 'https://scontent.xx.fbcdn.net/hphotos-xpt1/v/t1.0-9/s130x130/11709777_486097134874023_4219154949760141657_n.jpg?oh=8769ae0a4f4dc7d97abe3f4de69f38b4&oe=5612DBC6', 'https://scontent.xx.fbcdn.net/hphotos-xpt1/v/t1.0-9/11709777_486097134874023_4219154949760141657_n.jpg?oh=d7563ad840f2d6f1e0f3b43eb8cbd376&oe=5627A71E', NULL, '03/07/2015 17:55', 1),
(4, NULL, '03/07/2015 18:20', NULL, NULL, '445983705552033', NULL, '943562415694364', '486102174873519', 0, NULL, NULL, 'Video', 'https://fbstatic-a.akamaihd.net/rsrc.php/v2/yN/r/AAqMW82PqGg.gif', NULL, NULL, '03/07/2015 18:20', 1),
(5, NULL, '03/07/2015 18:36', NULL, NULL, '445983705552033', NULL, '943562415694364', '486105118206558', 0, NULL, NULL, 'Video', 'https://fbstatic-a.akamaihd.net/rsrc.php/v2/yN/r/AAqMW82PqGg.gif', NULL, NULL, '03/07/2015 18:36', 1);

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE IF NOT EXISTS `schedule` (
`id_schedule` int(11) NOT NULL,
  `client_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date_post` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `group_caption` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `group_description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `group_image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `group_link` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `group_message` text COLLATE utf8mb4_unicode_ci,
  `group_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `state` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `schedule_group`
--

CREATE TABLE IF NOT EXISTS `schedule_group` (
  `id_schedule` int(11) NOT NULL,
  `id_group_face` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`id` int(11) NOT NULL,
  `access_token` text COLLATE utf8mb4_unicode_ci,
  `first_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `access_token`, `first_name`, `last_name`, `password`, `user_id`, `user_name`) VALUES
(1, 'CAAUcRcDZBhwIBAOoKScmTvg6gcX0cR0fQ16ZBx8vizzTRMUL9XZCDSKGoZByJFwIm2T8eHcNpnZCqtnIZCOU7wWSdLyH1XMaFRYMqrnMKyBnPDdzrfi5HCB888JnBcwUGKteUfBG2rdzIcgZAZAKAyQjKIl8RwxZALUnmiXtKAqmt7sahBxZAx7yZBMioM4UB4gznzDj6MBEYuAtqiiu9WVKG0o', 'Minh', 'Nguyen', '123', '445983705552033', 'mnguyen74');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `biggroup_group`
--
ALTER TABLE `biggroup_group`
 ADD KEY `FK_d6uolqw4kgb4t8ubogrhbi6p5` (`id_group_face`), ADD KEY `FK_4m3hnub17ou8ah6xqdir0sdvh` (`id_big_group_face`);

--
-- Indexes for table `big_group_face`
--
ALTER TABLE `big_group_face`
 ADD PRIMARY KEY (`id_big_group_face`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
 ADD PRIMARY KEY (`id`), ADD KEY `FK_qp41ionc07ros07rta0rh5nnf` (`id_post`);

--
-- Indexes for table `fileupload`
--
ALTER TABLE `fileupload`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `group_face`
--
ALTER TABLE `group_face`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `membergroup`
--
ALTER TABLE `membergroup`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `UK_7ldjpumyetrvk2mpo6cqkjy4g` (`member_id`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `UK_7pna3u9o32qbcf2ts0u4ooqc2` (`notification_id`), ADD KEY `FK_j9a3okmjfh7x8mwtl3j7v1hap` (`id_post`), ADD KEY `FK_1urdwwsh2ti15ta6f6p5dbdcp` (`user_id`);

--
-- Indexes for table `post_facebook`
--
ALTER TABLE `post_facebook`
 ADD PRIMARY KEY (`id`), ADD KEY `FK_kpjc7vormd936fuyv4210br5t` (`id_group_face`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
 ADD PRIMARY KEY (`id_schedule`);

--
-- Indexes for table `schedule_group`
--
ALTER TABLE `schedule_group`
 ADD KEY `FK_qqx8gfqgr77b73xff24acbrfm` (`id_group_face`), ADD KEY `FK_9emyyvrk9mqxt1ou0you9ffpp` (`id_schedule`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `UK_lqjrcobrh9jc8wpcar64q1bfh` (`user_name`), ADD UNIQUE KEY `UK_a3imlf41l37utmxiquukk8ajc` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `big_group_face`
--
ALTER TABLE `big_group_face`
MODIFY `id_big_group_face` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `fileupload`
--
ALTER TABLE `fileupload`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `group_face`
--
ALTER TABLE `group_face`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `membergroup`
--
ALTER TABLE `membergroup`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `post_facebook`
--
ALTER TABLE `post_facebook`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `schedule`
--
ALTER TABLE `schedule`
MODIFY `id_schedule` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
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
