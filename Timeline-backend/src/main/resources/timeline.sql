# ************************************************************
# Sequel Pro SQL dump
# Version 5446
#
# https://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 152.136.173.30 (MySQL 5.7.28)
# Database: timeline
# Generation Time: 2019-11-27 12:51:28 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table timeline_items
# ------------------------------------------------------------

DROP TABLE IF EXISTS `timeline_items`;

CREATE TABLE `timeline_items` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(16) NOT NULL DEFAULT '',
  `title` varchar(30) NOT NULL DEFAULT '',
  `post_date_time` datetime NOT NULL,
  `text` varchar(240) NOT NULL DEFAULT '',
  `image_url` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `timeline_items` WRITE;
/*!40000 ALTER TABLE `timeline_items` DISABLE KEYS */;

INSERT INTO `timeline_items` (`id`, `user_name`, `title`, `post_date_time`, `text`, `image_url`)
VALUES
	(1,'bigoldxx','I love PlayStation 4','2019-10-31 21:33:11','I love PlayStation 4.',NULL),
	(2,'bigoldxx','I love Switch','2019-10-31 21:35:31','I love Switch too.',NULL),
	(3,'bigoldxx','Sleeping...','2019-10-31 21:39:11','Sleeping.... is great',NULL),
	(4,'JJAYCHEN','My First Post','2019-11-05 23:26:09','This is my first post :D.','http://152.136.173.30/images/gBNibyDrL7I.jpg'),
	(5,'JJAYCHEN','My Second Post','2019-11-05 23:32:34','This is my second post.',NULL),
	(7,'JJAYCHEN','A Post From iPad','2019-11-05 23:38:55','This is a post from iPad.',NULL),
	(22,'YueChen','It\'s over.','2019-11-13 21:04:03','Good Job.',NULL),
	(24,'HavenTong','Done!','2019-11-13 21:11:42','Donate a cup of coffee~~','http://152.136.173.30/images/Coffee-National-Coffee-Day-FT-Blog0919.jpg'),
	(26,'JJAYCHEN','一天掉多少根头发','2019-11-14 11:05:14','一天掉多少根头发，发生了会如何，不发生又会如何。 一天掉多少根头发因何而发生?总结的来说， 文森特·皮尔曾经提到过，改变你的想法，你就改变了自己的世界。这启发了我， 既然如此， 我们不得不面对一个非常尴尬的事实，那就是， 一天掉多少根...',NULL),
	(27,'MufanX','Upload','2019-11-15 22:46:38','I just want to upload a pic...','http://152.136.173.30/images/Snipaste_2019-11-15_22-45-34.png'),
	(28,'MufanX','好冷诶','2019-11-15 22:48:18','秋风一日凉比一日',NULL);

/*!40000 ALTER TABLE `timeline_items` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
