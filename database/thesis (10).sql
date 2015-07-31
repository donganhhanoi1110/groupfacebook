-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 09, 2015 at 08:12 PM
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
(3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `big_group_face`
--

CREATE TABLE IF NOT EXISTS `big_group_face` (
`id_big_group_face` int(11) NOT NULL,
  `client_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name_big_group_face` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=4 ;

--
-- Dumping data for table `big_group_face`
--

INSERT INTO `big_group_face` (`id_big_group_face`, `client_id`, `image`, `name_big_group_face`) VALUES
(1, '445983705552033', NULL, 'Minh'),
(2, '445983705552033', NULL, 'Sport'),
(3, '445983705552033', NULL, 'Education');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
`id` int(11) NOT NULL,
  `date_create` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `from_client_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_comment` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `like_count` int(11) DEFAULT NULL,
  `message` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name_client` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_post` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=19 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `date_create`, `from_client_id`, `id_comment`, `like_count`, `message`, `name_client`, `id_post`) VALUES
(1, '2015-07-07 21:01:08', '1617179881832546', '992969917420280', 0, 'haha', 'Nguyễn Anh Minh', 1),
(2, '2015-07-07 21:10:55', '1617179881832546', '992974900753115', 0, 'hoho', 'Nguyễn Anh Minh', 1),
(3, '2015-07-07 21:10:58', '1617179881832546', '992974920753113', 0, 'hihi', 'Nguyễn Anh Minh', 1),
(4, '2015-07-09 21:36:06', '445983705552033', '993974303986508', 0, 'Minh', 'Xêcô Mỏ Nhọn', 6),
(5, '2015-07-09 21:42:39', '1617179881832546', '993975900653015', 0, 'hahaha', 'Nguyễn Anh Minh', 6),
(6, '2015-07-09 22:46:59', '445983705552033', '993995393984399', 0, 'Comment from our website', 'Xêcô Mỏ Nhọn', 6),
(7, '2015-07-09 23:33:58', '1617179881832546', '994012190649386', 0, 'HOHOHO', 'Nguyễn Anh Minh', 6),
(8, '2015-07-09 23:57:25', '445983705552033', '994018140648791', 0, 'hihi', 'Xêcô Mỏ Nhọn', 6),
(9, '2015-07-09 23:57:34', '1617179881832546', '994018237315448', 0, 'hihi', 'Nguyễn Anh Minh', 6),
(10, '2015-07-09 23:59:13', '445983705552033', '994018593982079', 0, 'hihi', 'Xêcô Mỏ Nhọn', 1),
(11, '2015-07-10 00:00:28', '1617179881832546', '994018787315393', 0, 'test', 'Nguyễn Anh Minh', 6),
(12, '2015-07-10 00:00:32', '1617179881832546', '994018810648724', 0, 'test', 'Nguyễn Anh Minh', 4),
(13, '2015-07-10 00:01:10', '1617179881832546', '994019000648705', 0, 'comment', 'Nguyễn Anh Minh', 4),
(14, '2015-07-10 00:01:54', '1617179881832546', '994019230648682', 0, 'comment', 'Nguyễn Anh Minh', 5),
(15, '2015-07-10 00:01:58', '1617179881832546', '994019280648677', 0, 'comment', 'Nguyễn Anh Minh', 6),
(16, '2015-07-10 00:03:09', '1617179881832546', '994019650648640', 0, 'comment', 'Nguyễn Anh Minh', 5),
(17, '2015-07-10 00:11:19', '1617179881832546', '994021537315118', 0, 'HOHO', 'Nguyễn Anh Minh', 1),
(18, '2015-07-10 00:11:23', '1617179881832546', '994021853981753', 0, 'HOHO', 'Nguyễn Anh Minh', 6);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `fileupload`
--

INSERT INTO `fileupload` (`id`, `id_file`, `length`, `message`, `name`, `path`, `success`, `type`) VALUES
(1, '489058181244585', 122290, '<img class="img-responsive img-rectangle mypicture" src=''/spring-social-quickstart-30x/uploadAjax/get/1436369380462?fileType=post'' />', 'fruit.jpg', 'D:\\Monhoc\\JavaFresherCSC\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\ThesisSpringSocial\\resources\\files\\fruit.jpg', b'1', 'image/jpeg'),
(2, '489058314577905', 10185505, '<video width="500" controls><source src=''/spring-social-quickstart-30x/uploadAjax/get/1436369421215?fileType=post'' type=''video/mp4''>Your browser does not support HTML5 video.</video>', 'video-2012-08-05-09-28-04.mp4', 'D:\\Monhoc\\JavaFresherCSC\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\ThesisSpringSocial\\resources\\files\\video-2012-08-05-09-28-04.mp4', b'1', 'video/mp4'),
(3, '489087661241637', 88949, '<img class="img-responsive img-rectangle mypicture" src=''/spring-social-quickstart-30x/uploadAjax/get/1436372115640?fileType=post'' />', 'MVCArchitecture - Copy.png', 'D:\\Monhoc\\JavaFresherCSC\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\ThesisSpringSocial\\resources\\files\\MVCArchitecture - Copy.png', b'1', 'image/png'),
(4, '489096694574067', 68397, '<img class="img-responsive img-rectangle mypicture" src=''/spring-social-quickstart-30x/uploadAjax/get/1436372507192?fileType=post'' />', 'MVCArchitecture.png', 'D:\\Monhoc\\JavaFresherCSC\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\ThesisSpringSocial\\resources\\files\\MVCArchitecture.png', b'1', 'image/png'),
(5, '489177251232678', 122290, '<img class="img-responsive img-rectangle mypicture" src=''/spring-social-quickstart-30x/uploadAjax/get/1436381158524?fileType=post'' />', 'fruit - Copy.jpg', 'D:\\Monhoc\\JavaFresherCSC\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\ThesisSpringSocial\\resources\\files\\fruit - Copy.jpg', b'1', 'image/jpeg');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=11 ;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`id`, `application_id`, `date_created_time`, `date_updated_time`, `from_object_id`, `is_show`, `link`, `notification_id`, `title`, `to_object_id`, `unread`, `id_post`, `user_id`) VALUES
(1, '2361831622', 'Tue Jul 07 21:01:08 ICT 2015', '07/07/2015 21:10', '1617179881832546', b'1', 'http://www.facebook.com/groups/943562415694364/992968527420419/?comment_id=992969917420280', 'notif_445983705552033_1436277668587076', 'Nguyễn Anh Minh commented on your post in ThesisMinhHiep.', '445983705552033', b'1', 1, 1),
(2, '2392950137', 'Wed Jul 08 22:30:49 ICT 2015', 'Wed Jul 08 22:30:49 ICT 2015', '20531316728', b'1', 'http://www.facebook.com/100004212219695/videos/489058314577905/', 'notif_445983705552033_1436369449598147', 'Your video "Picture" is ready to view. You can now watch it.', '445983705552033', b'0', 3, 1),
(3, '2409997254', 'Wed Jul 08 23:50:53 ICT 2015', 'Wed Jul 08 23:50:53 ICT 2015', '1617179881832546', b'1', 'http://www.facebook.com/groups/943562415694364/993520567365215/', 'notif_445983705552033_1436374253425087', 'Nguyễn Anh Minh likes your video in ThesisMinhHiep.', '445983705552033', b'0', 4, 1),
(4, '2409997254', 'Wed Jul 08 23:50:51 ICT 2015', 'Wed Jul 08 23:50:51 ICT 2015', '1617179881832546', b'1', 'http://www.facebook.com/groups/943562415694364/993539454029993/', 'notif_445983705552033_1436374251410517', 'Nguyễn Anh Minh likes your photo in ThesisMinhHiep: "Test Picture".', '445983705552033', b'0', 4, 1),
(5, '2409997254', 'Wed Jul 08 23:50:49 ICT 2015', 'Wed Jul 08 23:50:49 ICT 2015', '1617179881832546', b'1', 'http://www.facebook.com/groups/943562415694364/993541094029829/', 'notif_445983705552033_1436374249930221', 'Nguyễn Anh Minh likes your photo in ThesisMinhHiep: "tets".', '445983705552033', b'0', 4, 1),
(6, '2361831622', 'Thu Jul 09 21:42:40 ICT 2015', '10/07/2015 00:11', '1617179881832546', b'1', 'http://www.facebook.com/groups/943562415694364/993580794025859/?comment_id=993975900653015', 'notif_445983705552033_1436452960087753', 'Nguyễn Anh Minh commented on your photo in ThesisMinhHiep.', '445983705552033', b'1', 1, 1),
(7, '2409997254', 'Thu Jul 09 21:42:37 ICT 2015', 'Thu Jul 09 21:42:37 ICT 2015', '1617179881832546', b'1', 'http://www.facebook.com/groups/943562415694364/993580794025859/', 'notif_445983705552033_1436452957718213', 'Nguyễn Anh Minh likes your photo in ThesisMinhHiep: "picutre".', '445983705552033', b'0', 1, 1),
(8, '2361831622', 'Fri Jul 10 00:00:33 ICT 2015', '10/07/2015 00:01', '1617179881832546', b'1', 'http://www.facebook.com/groups/943562415694364/993539454029993/?comment_id=994018810648724', 'notif_445983705552033_1436461233194464', 'Nguyễn Anh Minh commented on your photo in ThesisMinhHiep.', '445983705552033', b'1', 6, 1),
(9, '2361831622', 'Fri Jul 10 00:01:54 ICT 2015', '10/07/2015 00:03', '1617179881832546', b'1', 'http://www.facebook.com/groups/943562415694364/993541094029829/?comment_id=994019230648682', 'notif_445983705552033_1436461314978085', 'Nguyễn Anh Minh commented on your photo in ThesisMinhHiep.', '445983705552033', b'1', 4, 1),
(10, '2361831622', 'Fri Jul 10 00:11:20 ICT 2015', '10/07/2015 00:11', '1617179881832546', b'1', 'http://www.facebook.com/groups/943562415694364/992968527420419/?comment_id=994021537315118', 'notif_445983705552033_1436461880199793', 'Nguyễn Anh Minh commented on your post in ThesisMinhHiep.', '445983705552033', b'0', 5, 1);

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
  `photo_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `picture` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `source` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updated_date` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `video_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_group_face` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `post_facebook`
--

INSERT INTO `post_facebook` (`id`, `caption`, `created_date`, `description`, `file_path`, `from_client_id`, `full_picture`, `id_group`, `id_post`, `like_count`, `link`, `message`, `name`, `photo_id`, `picture`, `source`, `type`, `updated_date`, `video_id`, `id_group_face`) VALUES
(1, NULL, '07/07/2015 20:59', NULL, NULL, '445983705552033', NULL, '943562415694364', '943562415694364_992968527420419', 1, NULL, 'Minh', NULL, NULL, NULL, NULL, 'status', '10/07/2015 00:11', NULL, 1),
(2, NULL, '08/07/2015 22:29', NULL, NULL, '445983705552033', NULL, '943562415694364', '943562415694364_993520350698570', 0, 'https://www.facebook.com/photo.php?fbid=489058181244585&set=gm.993520350698570&type=1', 'Picture', NULL, '489058181244585', 'https://fbcdn-sphotos-c-a.akamaihd.net/hphotos-ak-xfp1/v/t1.0-9/s130x130/11058222_489058181244585_6169955268383233728_n.jpg?oh=a237feac0697a9839d80d2069770cb23&oe=5657FBBA&__gda__=1445125514_d1d319a3115d75193304f02eaacbdb28', NULL, 'photo', '08/07/2015 22:29', NULL, 1),
(3, NULL, '08/07/2015 22:30', NULL, NULL, '445983705552033', NULL, '943562415694364', '943562415694364_993520567365215', 1, 'https://www.facebook.com/445983705552033/videos/489058314577905', NULL, 'Picture', NULL, 'https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-xat1/v/t15.0-10/s130x130/11331714_489058407911229_1572582110_n.jpg?oh=5010b6ff7c77efc403cd6ceea0e186f0&oe=562D821E&__gda__=1444251806_55ca39426e6836ac239f2811ad60e79e', 'https://video.xx.fbcdn.net/hvideo-xaf1/v/t42.1790-2/11643892_480249358792134_2138911313_n.mp4?efg=eyJybHIiOjY1NSwicmxhIjo3Mjd9&rl=655&vabr=364&oh=82596b19286f12389fb7d38565f33344&oe=55A07DBC', 'video', '08/07/2015 22:30', '489058314577905', 1),
(4, NULL, '08/07/2015 23:15', NULL, 'null', '445983705552033', NULL, '943562415694364', '943562415694364_993539454029993', 1, 'https://www.facebook.com/photo.php?fbid=489087661241637&set=gm.993539454029993&type=1', 'Test Picture', NULL, '489087661241637', 'https://scontent.xx.fbcdn.net/hphotos-xpt1/v/t1.0-9/s130x130/11694880_489087661241637_6957096391798071236_n.jpg?oh=6074157aea17f0916308ff0636da1ecd&oe=561AF730', NULL, 'photo', '10/07/2015 00:01', NULL, 1),
(5, NULL, '08/07/2015 23:22', NULL, 'resources/files/MVCArchitecture.png', '445983705552033', NULL, '943562415694364', '943562415694364_993541094029829', 1, 'https://www.facebook.com/photo.php?fbid=489096694574067&set=gm.993541094029829&type=1', 'tets', NULL, '489096694574067', 'https://fbcdn-sphotos-g-a.akamaihd.net/hphotos-ak-xaf1/v/t1.0-9/s130x130/11694854_489096694574067_3694072564885630872_n.jpg?oh=09b31b88e145b62c2a3600dbbcac8c67&oe=5612BA6A&__gda__=1445668749_86b8b25102cdf87e99b8012abccfcf92', NULL, 'photo', '10/07/2015 00:03', NULL, 1),
(6, NULL, '09/07/2015 01:46', NULL, 'resources/files/fruit - Copy.jpg', '445983705552033', NULL, '943562415694364', '943562415694364_993580794025859', 1, 'https://www.facebook.com/photo.php?fbid=489177251232678&set=gm.993580794025859&type=1', 'picutre', NULL, '489177251232678', 'https://fbcdn-sphotos-f-a.akamaihd.net/hphotos-ak-xfa1/v/t1.0-9/s130x130/11667437_489177251232678_7877523461323871910_n.jpg?oh=2a37645826b1442377544c109fe5eb83&oe=565A6665&__gda__=1448523191_8984701bd68e6d44a69d402ce8befce3', NULL, 'photo', '10/07/2015 00:11', NULL, 1);

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
(1, 'CAAUcRcDZBhwIBAGVm32qO9SgA2N1zz66RLmxg6ztLx8SvkvFCyIiCGerCkufBiZBzLBLt3ZBEYjyUZBjUZCztUBcSBvXLJYyBjRnwAtxPHqAPMhZA4g3jRYdQyDp5130MgZBurJV7rAtYY3OZA4Hv11drgvZC1aoX8yrFxMKZBd3go6EuUNqVsMpZClm7xWuaOh2xPv3XUEtqqOJKwcbsHXYU9G', 'Minh', '123', '123', '445983705552033', 'mnguyen74');

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
MODIFY `id_big_group_face` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `fileupload`
--
ALTER TABLE `fileupload`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
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
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `post_facebook`
--
ALTER TABLE `post_facebook`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
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
