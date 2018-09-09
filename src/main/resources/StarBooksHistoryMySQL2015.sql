DROP DATABASE IF EXISTS `StarBooksHistory`;

CREATE DATABASE `StarBooksHistory`;

USE `StarBooksHistory`;

CREATE TABLE `Author` (
  `authorId` int(11) NOT NULL AUTO_INCREMENT,
  `authorName` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`authorId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Publisher` (
  `publisherId` varchar(2) NOT NULL DEFAULT '',
  `publisherName` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`publisherId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Book` (
  `bookId` int(11) NOT NULL AUTO_INCREMENT,
  `isbn` varchar(10) NOT NULL DEFAULT '',
  `title` varchar(100) NOT NULL DEFAULT '',
  `releaseDate` datetime DEFAULT NULL,
  `listPrice` decimal(6,2) DEFAULT NULL,
  `pubId` varchar(2) NOT NULL DEFAULT '',
  `coverImage` longblob,
  PRIMARY KEY (`bookId`),
  KEY `pubId` (`pubId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `BookAuthor` (
  `bkId` int(11) NOT NULL DEFAULT '0',
  `arId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`bkId`,`arId`),
  KEY `arId` (`arId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

