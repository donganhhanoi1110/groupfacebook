-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 15, 2015 at 08:54 PM
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
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(3, 3),
(4, 1),
(4, 2),
(4, 3),
(4, 4);

-- --------------------------------------------------------

--
-- Table structure for table `big_group_face`
--

CREATE TABLE IF NOT EXISTS `big_group_face` (
`id_big_group_face` int(11) NOT NULL,
  `client_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name_big_group_face` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `big_group_face`
--

INSERT INTO `big_group_face` (`id_big_group_face`, `client_id`, `image`, `name_big_group_face`) VALUES
(1, '445983705552033', NULL, 'Ä�áº¡i Há»�c TÃ´n Ä�á»©c THáº¯ng'),
(2, '445983705552033', NULL, 'Education'),
(3, '445983705552033', NULL, 'Class 1'),
(4, '445983705552033', NULL, 'Class 2 IT');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
`id` int(11) NOT NULL,
  `date_create` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `from_client_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_comment` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `like_count` int(11) DEFAULT NULL,
  `message` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name_client` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_like` bit(1) DEFAULT NULL,
  `id_post` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=14 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `date_create`, `from_client_id`, `id_comment`, `like_count`, `message`, `name_client`, `user_like`, `id_post`) VALUES
(1, '15/07/2015 07:58', '1617179881832546', '996605380390067', 0, '123', 'Nguyá»…n Anh Minh', b'0', 1),
(2, '15/07/2015 07:58', '1617179881832546', '996605360390069', 0, '123', 'Nguyá»…n Anh Minh', b'0', 2),
(3, '15/07/2015 19:23', '445983705552033', '996792230371382', 1, 'comment', 'XÃªcÃ´ Má»� Nhá»�n', b'1', 2),
(4, '15/07/2015 21:52', '445983705552033', '996853763698562', 1, 'hbhb', 'XÃªcÃ´ Má»� Nhá»�n', b'1', 3),
(5, '16/07/2015 00:24', '1617179881832546', '996905380360067', 1, 'like', 'Nguyá»…n Anh Minh', b'1', 3),
(6, '16/07/2015 00:24', '445983705552033', '996905517026720', 1, 'ok thank you', 'XÃªcÃ´ Má»� Nhá»�n', b'0', 3),
(7, '16/07/2015 00:25', '445983705552033', '996905567026715', 1, 'your wel', 'XÃªcÃ´ Má»� Nhá»�n', b'0', 3),
(8, '16/07/2015 00:25', '1617179881832546', '996905627026709', 0, 'hihi ok', 'Nguyá»…n Anh Minh', b'0', 3),
(9, '16/07/2015 01:20', '1617179881832546', '996921347025137', 0, 'hoho', 'Nguyá»…n Anh Minh', b'0', 3),
(10, '16/07/2015 01:20', '1617179881832546', '996921660358439', 0, 'again', 'Nguyá»…n Anh Minh', b'0', 3),
(11, '16/07/2015 01:21', '1617179881832546', '996921680358437', 0, 'hoho', 'Nguyá»…n Anh Minh', b'0', 3),
(12, '16/07/2015 01:21', '1617179881832546', '437302999807151', 0, 'liek thui', 'Nguyá»…n Anh Minh', b'0', 6),
(13, '16/07/2015 01:23', '1617179881832546', '437303513140433', 0, 'dnot see anything', 'Nguyá»…n Anh Minh', b'0', 6);

-- --------------------------------------------------------

--
-- Table structure for table `fileupload`
--

CREATE TABLE IF NOT EXISTS `fileupload` (
`id` int(11) NOT NULL,
  `id_file` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `length` int(11) DEFAULT NULL,
  `message` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `success` bit(1) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=9 ;

--
-- Dumping data for table `fileupload`
--

INSERT INTO `fileupload` (`id`, `id_file`, `length`, `message`, `name`, `path`, `success`, `type`) VALUES
(1, '491623547654715', 40813, '<img class="img-responsive img-rectangle mypicture" src=''/ThesisSpringSocial/uploadAjax/get/1436897389967?fileType=post'' />', 'Exercise-and-Sport-Sciences6.jpg', '/ThesisSpringSocial/resources/files/Exercise-and-Sport-Sciences6.jpg', b'1', 'image/jpeg'),
(2, 'Ä�áº¡i Há»�c TÃ´n Ä�á»©c THáº¯ng', 13144, '<img class="img-responsive img-rectangle mypicture" src=''/ThesisSpringSocial/uploadAjax/get/1436962842875?fileType=biggroup'' />', 'logo.png', '/ThesisSpringSocial/resources/files/logo.png', b'1', 'image/png'),
(3, 'Education', 34986, '<img class="img-responsive img-rectangle mypicture" src=''/ThesisSpringSocial/uploadAjax/get/1436962880546?fileType=biggroup'' />', 'education2.jpg', '/ThesisSpringSocial/resources/files/education2.jpg', b'1', 'image/jpeg'),
(4, 'Class 1', 34024, '<img class="img-responsive img-rectangle mypicture" src=''/ThesisSpringSocial/uploadAjax/get/1436962903132?fileType=biggroup'' />', 'education.jpg', '/ThesisSpringSocial/resources/files/education.jpg', b'1', 'image/jpeg'),
(5, 'Class 2 IT', 39695, '<img class="img-responsive img-rectangle mypicture" src=''/ThesisSpringSocial/uploadAjax/get/1436962924940?fileType=biggroup'' />', 'sport.jpg', '/ThesisSpringSocial/resources/files/sport.jpg', b'1', 'image/jpeg'),
(6, 'adff', 40813, '<img class="img-responsive img-rectangle mypicture" src=''/ThesisSpringSocial/uploadAjax/get/1436971042311?fileType=biggroup'' />', 'Exercise-and-Sport-Sciences6.jpg', '/ThesisSpringSocial/resources/files/Exercise-and-Sport-Sciences6.jpg', b'1', 'image/jpeg'),
(7, '491870477630022', 437424, '<h1>&nbsp;&nbsp;Successful UpLoad!!! You can watch your video after post to Facebook!!!</h1>', 'Capture_20150627.wmv', 'D:\\Monhoc\\JavaFresherCSC\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\ThesisSpringSocial\\resources\\files\\Capture_20150627.wmv', b'1', 'video/x-ms-wmv'),
(8, '491924720957931', 1981736, '<h1>&nbsp;&nbsp;Successful UpLoad!!! You can watch your video after post to Facebook!!!</h1>', 'Capture_20150712.wmv', 'D:\\Monhoc\\JavaFresherCSC\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\ThesisSpringSocial\\resources\\files\\Capture_20150712.wmv', b'1', 'video/x-ms-wmv');

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
(1, b'0', 4, '445983705552033', NULL, NULL, NULL, '374979216039530', NULL, 'ThesisMinhHiep3', 'CLOSED', NULL, NULL, 0),
(2, b'0', 1, '445983705552033', NULL, NULL, NULL, '943562415694364', NULL, 'ThesisMinhHiep', 'OPEN', NULL, NULL, 0),
(3, b'0', 2, '445983705552033', NULL, NULL, NULL, '1642294075990555', NULL, 'ThesisMinhHiep2', 'CLOSED', NULL, NULL, 0),
(4, b'0', 3, '445983705552033', NULL, NULL, NULL, '434472413309957', NULL, 'DJ Báº¯c Kiáº¿n XÆ°Æ¡ng', 'OPEN', NULL, NULL, 0);

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
  `id_group` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
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
  `work` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `group_face` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=30 ;

--
-- Dumping data for table `membergroup`
--

INSERT INTO `membergroup` (`id`, `bio`, `birthday`, `education`, `email`, `favorite_athletes`, `favorite_teams`, `first_name`, `gender`, `hometown`, `id_group`, `interested_in`, `languages`, `last_name`, `link`, `locale`, `location`, `member_id`, `middle_name`, `name`, `picture`, `political`, `quotes`, `relationship_status`, `relition`, `significant_other`, `timezone`, `updated_time`, `username`, `verified`, `website`, `work`, `group_face`) VALUES
(1, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '374979216039530', '', '', NULL, NULL, NULL, NULL, '445983705552033', NULL, 'XÃªcÃ´ Má»� Nhá»�n', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 1),
(2, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '374979216039530', '', '', NULL, NULL, NULL, NULL, '1617179881832546', NULL, 'Nguyá»…n Anh Minh', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 1),
(3, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '374979216039530', '', '', NULL, NULL, NULL, NULL, '708394165956241', NULL, 'Hiep Nguyen', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 1),
(4, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '1625996871001548', NULL, 'TÆ°Æ¡i HÃ¹ng', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(5, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '492726677549424', NULL, 'Richard Phuong', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(6, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '1462410677410574', NULL, 'JinÄ‘Ã´ Ä�inhÃ´', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(7, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '1611825195752210', NULL, 'Vitamin GÃ¢y MÃª', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(8, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '497542353741844', NULL, 'ážšážŸáŸ‹ áž”áž‰áŸ’áž‰áž¶', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(9, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '527075700776426', NULL, 'LiÃªn LÆ°u', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(10, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '1601642040090920', NULL, 'HoÃ ng Nguyá»…n', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(11, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '1608130996120401', NULL, 'Trung Nguyá»…n', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(12, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '1632399540361588', NULL, 'Tuyáº¿t Trinh', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(13, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '1703147776580333', NULL, 'Pháº¡m Diá»‡u HÆ°Æ¡ng', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(14, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '457949287713291', NULL, 'Huyen KhongKhoc', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(15, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '527201310776380', NULL, 'Yáº¿n Trinh', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(16, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '1575717556000601', NULL, 'Cá»© Tháº¿ Mong Chá»�', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(17, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '371596813049593', NULL, 'Triá»‡u Viá»‡t HÃ¹ng', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(18, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '1459273564372962', NULL, 'HiÃªp Phamngoc', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(19, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '1609375992672982', NULL, 'Tháº¯ng Foods', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(20, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '1444457602527205', NULL, 'Å á»¯a Ã‡hua', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(21, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '1670961716466960', NULL, 'Do Thu Nhai', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(22, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '785674154881378', NULL, 'ChÃ o MÃ¬nh LÃ  tá»’', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(23, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '1469571276674146', NULL, 'Mina Takei', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(24, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '474751276020442', NULL, 'Ngan Hang', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(25, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '401895919996118', NULL, 'XÃ³a Háº¿t', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(26, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '1659994934223867', NULL, 'ÊšoÉž Vá»‹t ÊšoÉž', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(27, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '579602882181114', NULL, 'Káº» Giáº¥u Máº·t', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(28, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '434472413309957', '', '', NULL, NULL, NULL, NULL, '404958973037448', NULL, 'Linh Ä�Ã´la', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 4),
(29, NULL, NULL, '', NULL, '', '', NULL, NULL, NULL, '943562415694364', '', '', NULL, NULL, NULL, NULL, '401178693407252', NULL, 'Minh Nguyá»…n', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, b'0', NULL, '', 2);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=14 ;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`id`, `application_id`, `date_created_time`, `date_updated_time`, `from_object_id`, `is_show`, `link`, `notification_id`, `title`, `to_object_id`, `unread`, `id_post`, `user_id`) VALUES
(1, '2409997254', '15/07/2015 01:10', '15/07/2015 01:10', '1617179881832546', b'1', 'http://www.facebook.com/groups/943562415694364/996492667068005/', 'notif_445983705552033_1436897424061095', 'Nguyá»…n Anh Minh likes your photo in ThesisMinhHiep: "123".', '445983705552033', b'0', 2, 1),
(2, '2409997254', '15/07/2015 01:10', '15/07/2015 01:10', '1617179881832546', b'1', 'http://www.facebook.com/groups/943562415694364/996492690401336/', 'notif_445983705552033_1436897422565841', 'Nguyá»…n Anh Minh likes your post in ThesisMinhHiep: "video".', '445983705552033', b'0', 2, 1),
(3, '2361831622', '15/07/2015 00:31', '15/07/2015 00:31', '708394165956241', b'1', 'http://www.facebook.com/groups/1642294075990555/1678355605717735/?comment_id=1678685779018051', 'notif_445983705552033_1436895074130605', 'Hiep Nguyen commented on your photo in ThesisMinhHiep2.', '445983705552033', b'0', 2, 1),
(4, '2361831622', '15/07/2015 00:22', '15/07/2015 01:05', '1617179881832546', b'0', 'http://www.facebook.com/groups/943562415694364/996474283736510/', 'notif_445983705552033_1436894572242339', 'Nguyá»…n Anh Minh and Hiep Nguyen commented on your photo in ThesisMinhHiep.', '445983705552033', b'0', 2, 1),
(5, '2361831622', '15/07/2015 00:22', '15/07/2015 01:04', '1617179881832546', b'1', 'http://www.facebook.com/groups/943562415694364/996474340403171/', 'notif_445983705552033_1436894569734807', 'Nguyá»…n Anh Minh and Hiep Nguyen commented on your video in ThesisMinhHiep.', '445983705552033', b'0', 2, 1),
(6, '2361831622', '15/07/2015 07:58', '15/07/2015 07:58', '1617179881832546', b'1', 'http://www.facebook.com/groups/943562415694364/996492667068005/?comment_id=996605380390067', 'notif_445983705552033_1436921936973959', 'Nguyá»…n Anh Minh commented on your photo in ThesisMinhHiep.', '445983705552033', b'0', 2, 1),
(7, '2361831622', '15/07/2015 07:58', '15/07/2015 07:58', '1617179881832546', b'1', 'http://www.facebook.com/groups/943562415694364/996492690401336/?comment_id=996605360390069', 'notif_445983705552033_1436921929926812', 'Nguyá»…n Anh Minh commented on your post in ThesisMinhHiep.', '445983705552033', b'0', 2, 1),
(8, '2392950137', '15/07/2015 21:50', '15/07/2015 21:50', '20531316728', b'1', 'http://www.facebook.com/100004212219695/videos/491870477630022/', 'notif_445983705552033_1436971824194368', 'Your video "minh" is ready to view. You can now watch it.', '445983705552033', b'0', 3, 1),
(9, '2409997254', '16/07/2015 00:24', '16/07/2015 00:24', '1617179881832546', b'1', 'http://www.facebook.com/groups/943562415694364/996844477032824/', 'notif_445983705552033_1436981062279175', 'Nguyá»…n Anh Minh likes your video in ThesisMinhHiep.', '445983705552033', b'0', 3, 1),
(10, '2361831622', '16/07/2015 00:24', '16/07/2015 00:25', '1617179881832546', b'1', 'http://www.facebook.com/groups/943562415694364/996844477032824/?comment_id=996905380360067', 'notif_445983705552033_1436981065028602', 'Nguyá»…n Anh Minh commented on your video in ThesisMinhHiep.', '445983705552033', b'0', 3, 1),
(11, '2409997254', '16/07/2015 00:25', '16/07/2015 00:25', '1617179881832546', b'1', 'http://www.facebook.com/groups/943562415694364/996844477032824/?comment_id=996905517026720', 'notif_445983705552033_1436981122082175', 'Nguyá»…n Anh Minh likes your comment: "ok thank you".', '445983705552033', b'0', 3, 1),
(12, '2409997254', '16/07/2015 00:25', '16/07/2015 00:25', '1617179881832546', b'1', 'http://www.facebook.com/groups/943562415694364/996844477032824/?comment_id=996905567026715', 'notif_445983705552033_1436981121626676', 'Nguyá»…n Anh Minh likes your comment: "your wel".', '445983705552033', b'0', 3, 1),
(13, '2392950137', '16/07/2015 01:23', '16/07/2015 01:23', '20531316728', b'1', 'http://www.facebook.com/100004212219695/videos/491924720957931/', 'notif_445983705552033_1436984615636428', 'Your video "Hello video" is ready to view. You can now watch it.', '445983705552033', b'0', 7, 1);

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
  `full_picture` text COLLATE utf8mb4_unicode_ci,
  `id_group` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_post` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `like_count` int(11) DEFAULT NULL,
  `link` text COLLATE utf8mb4_unicode_ci,
  `message` text COLLATE utf8mb4_unicode_ci,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `photo_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `picture` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `share_count` int(11) DEFAULT NULL,
  `source` text COLLATE utf8mb4_unicode_ci,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updated_date` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_like` bit(1) DEFAULT NULL,
  `video_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_group_face` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=8 ;

--
-- Dumping data for table `post_facebook`
--

INSERT INTO `post_facebook` (`id`, `caption`, `created_date`, `description`, `file_path`, `from_client_id`, `full_picture`, `id_group`, `id_post`, `like_count`, `link`, `message`, `name`, `photo_id`, `picture`, `share_count`, `source`, `type`, `updated_date`, `user_like`, `video_id`, `id_group_face`) VALUES
(1, NULL, '15/07/2015 01:10', NULL, '/ThesisSpringSocial/resources/files/Exercise-and-Sport-Sciences6.jpg', '445983705552033', NULL, '943562415694364', '943562415694364_996492667068005', 1, 'https://www.facebook.com/photo.php?fbid=491623547654715&set=gm.996492667068005&type=1', '123', NULL, '491623547654715', 'https://fbcdn-sphotos-h-a.akamaihd.net/hphotos-ak-xtf1/v/t1.0-9/s130x130/11745916_491623547654715_5932514533070047430_n.jpg?oh=25d2f74c57359664cc92e0e28f1924f0&oe=565875F4&__gda__=1444618458_39e05300a4cc50b9b335bb47fb2c9ca6', 0, NULL, 'photo', '15/07/2015 07:58', b'0', NULL, 2),
(2, NULL, '15/07/2015 01:10', NULL, NULL, '445983705552033', NULL, '943562415694364', '943562415694364_996492690401336', 1, NULL, 'video', NULL, NULL, NULL, 0, NULL, 'status', '15/07/2015 19:23', b'0', NULL, 2),
(3, NULL, '15/07/2015 21:50', NULL, NULL, '445983705552033', NULL, '943562415694364', '943562415694364_996844477032824', 1, 'https://www.facebook.com/445983705552033/videos/491870477630022', NULL, 'minh', NULL, 'https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-xaf1/v/t15.0-10/s130x130/11407439_491870614296675_838356738_n.jpg?oh=68698f890dd1c896517c52cc7d6676d8&oe=5623B470&__gda__=1444687152_a6010403eb90e1b49ccefc41fd0f5101', 0, 'https://fbcdn-video-f-a.akamaihd.net/hvideo-ak-xpf1/v/t42.1790-2/11242717_491870494296687_365305517_n.mp4?efg=eyJybHIiOjMwMCwicmxhIjo1MTJ9&rl=300&vabr=90&oh=714aa9600ea924cd9c43934a695e903e&oe=55A96024&__gda__=1437108638_44fcf7535f90474953ecd948a7350c08', 'video', '16/07/2015 01:21', b'0', '491870477630022', 2),
(4, 'mp3.zing.vn', '16/07/2015 01:12', 'Zing Mp3 vÃ´ cÃ¹ng xin lá»—i báº¡n vÃ¬ sá»± cá»‘ Ä‘Äƒng nháº­p xáº£y ra cÃ¡ch Ä‘Ã¢y vÃ i ngÃ y Ä‘Ã£ áº£nh hÆ°á»Ÿng Ä‘áº¿n tráº£i nghiá»‡m nghe nháº¡c cá»§a báº¡n trÃªn website Zing Mp3.', NULL, '445983705552033', NULL, '1642294075990555', '1642294075990555_1678995582320404', 0, 'http://mp3.zing.vn/video-clip/Hurt-Locker-Ndasd', NULL, 'mp3.zing.vn', NULL, 'https://fbexternal-a.akamaihd.net/safe_image.php?d=AQDSkAQ7Io2ZhSRe&w=130&h=130&url=http%3A%2F%2Fstatic.mp3.zdn.vn%2Fskins%2Fzmp3-v4.1%2Fimages%2Flogo200.png&cfs=1', 0, NULL, 'link', '16/07/2015 01:12', b'0', NULL, 3),
(5, 'mp3.zing.vn', '16/07/2015 01:12', 'Zing Mp3 vÃ´ cÃ¹ng xin lá»—i báº¡n vÃ¬ sá»± cá»‘ Ä‘Äƒng nháº­p xáº£y ra cÃ¡ch Ä‘Ã¢y vÃ i ngÃ y Ä‘Ã£ áº£nh hÆ°á»Ÿng Ä‘áº¿n tráº£i nghiá»‡m nghe nháº¡c cá»§a báº¡n trÃªn website Zing Mp3.', NULL, '445983705552033', NULL, '1642294075990555', '1642294075990555_1678995605653735', 0, 'http://mp3.zing.vn/video-clip/Hurt-Locker-Ndasd', 'hihi', 'mp3.zing.vn', NULL, 'https://fbexternal-a.akamaihd.net/safe_image.php?d=AQDSkAQ7Io2ZhSRe&w=130&h=130&url=http%3A%2F%2Fstatic.mp3.zdn.vn%2Fskins%2Fzmp3-v4.1%2Fimages%2Flogo200.png&cfs=1', 0, NULL, 'link', '16/07/2015 01:12', b'0', NULL, 3),
(6, NULL, '16/07/2015 01:21', NULL, NULL, '445983705552033', NULL, '374979216039530', '374979216039530_437302873140497', 1, NULL, 'Tesst', NULL, NULL, NULL, 0, NULL, 'status', '16/07/2015 01:23', b'0', NULL, 1),
(7, NULL, '16/07/2015 01:23', NULL, NULL, '445983705552033', NULL, '374979216039530', '374979216039530_437303359807115', 1, 'https://www.facebook.com/445983705552033/videos/491924720957931', NULL, 'Hello video', NULL, 'https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-xfa1/v/t15.0-10/s130x130/11413803_491924830957920_1395923131_n.jpg?oh=6452b49338b19c87b8735253b9fb9982&oe=564ECCF0&__gda__=1445109599_330f94e8336bc561a41984a1c9a0ca2f', 0, 'https://fbcdn-video-g-a.akamaihd.net/hvideo-ak-xfa1/v/t42.1790-2/11738851_491924780957925_1667405892_n.mp4?efg=eyJybHIiOjMwMCwicmxhIjo1MTJ9&rl=300&vabr=76&oh=1806f9f3281adc8c9f4286d6c8480fed&oe=55A98EA5&__gda__=1437094019_3fdcddf15308a5d03c1072640e2e303c', 'video', '16/07/2015 01:23', b'1', '491924720957931', 1);

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
  `user_facebook_fullname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `access_token`, `first_name`, `last_name`, `password`, `user_facebook_fullname`, `user_id`, `user_name`) VALUES
(1, 'CAAUcRcDZBhwIBAAm3i2NM03A9z9sxwRY6cTIZBocIr2Kb76c0HnLrpC4iZAFPc8aYo3satGMBF9JQBKQzfpTjBxMZBiBHitam5n5bccZB0EQ8WwCZAG1hr6n75pNvO0W1nQn7yFHBVzfOpBDKkXdKxjC6T3thJIoe9bUCQR63vFMvPTtF3sP4UZBeN3WznWniEb5PCY72HMbKORZAhdE2EVv', '12312312', '312312', '12345', 'XÃªcÃ´ Má»� Nhá»�n', '445983705552033', 'mnguyen74');

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
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `UK_7ldjpumyetrvk2mpo6cqkjy4g` (`member_id`), ADD KEY `FK_q9q6u66q6sbah1vjq9py20htr` (`group_face`);

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
MODIFY `id_big_group_face` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `fileupload`
--
ALTER TABLE `fileupload`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `group_face`
--
ALTER TABLE `group_face`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `membergroup`
--
ALTER TABLE `membergroup`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `post_facebook`
--
ALTER TABLE `post_facebook`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
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
