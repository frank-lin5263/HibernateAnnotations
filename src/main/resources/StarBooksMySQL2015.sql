DROP DATABASE IF EXISTS `StarBooks`;

CREATE DATABASE `StarBooks`;

USE `StarBooks`;

CREATE TABLE `Author` (
  `authorId` int(11) NOT NULL AUTO_INCREMENT,
  `authorName` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`authorId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO Author(authorName) VALUES('Craig Walls');               /* 1 */
INSERT INTO Author(authorName) VALUES('Tom White');                 /* 2 */
INSERT INTO Author(authorName) VALUES('John Resig');                /* 3 */
INSERT INTO Author(authorName) VALUES('Bear Bibeault');             /* 4 */
INSERT INTO Author(authorName) VALUES('Rod Johnson');               /* 5 */
INSERT INTO Author(authorName) VALUES('Christian Bauer');           /* 6 */
INSERT INTO Author(authorName) VALUES('Gavin King');                /* 7 */
INSERT INTO Author(authorName) VALUES('Douglas Crockford');         /* 8 */
INSERT INTO Author(authorName) VALUES('Jesse James Garrett');       /* 9 */
INSERT INTO Author(authorName) VALUES('松本行弘');                  /* 10 */
INSERT INTO Author(authorName) VALUES('Sam Ruby');                  /* 11 */
INSERT INTO Author(authorName) VALUES('Dave Thomas');               /* 12 */
INSERT INTO Author(authorName) VALUES('David Heinemeier Hansson');  /* 13 */
INSERT INTO Author(authorName) VALUES('James Gosling');             /* 14 */
INSERT INTO Author(authorName) VALUES('Bill Joy');                  /* 15 */
INSERT INTO Author(authorName) VALUES('Guy L. Steele Jr.');         /* 16 */
INSERT INTO Author(authorName) VALUES('Gilad Bracha');              /* 17 */
INSERT INTO Author(authorName) VALUES('Alex Buckley');              /* 18 */

CREATE TABLE `Publisher` (
  `publisherId` varchar(2) NOT NULL DEFAULT '',
  `publisherName` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`publisherId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO Publisher VALUES('MP', 'Manning Publication');
INSERT INTO Publisher VALUES('OA', 'O''Reilly & Associates');
INSERT INTO Publisher VALUES('WP', 'Wrox Press');
INSERT INTO Publisher VALUES('NR', 'New Riders');
INSERT INTO Publisher VALUES('GI', 'Gotop Information');
INSERT INTO Publisher VALUES('PB', 'Pragmatic Bookshelf');
INSERT INTO Publisher VALUES('AW', 'Addison Wesley');

CREATE TABLE `Book` (
  `bookId` int(11) NOT NULL AUTO_INCREMENT,
  `isbn` varchar(10) NOT NULL DEFAULT '',
  `title` varchar(100) NOT NULL DEFAULT '',
  `releaseDate` datetime DEFAULT NULL,
  `listPrice` decimal(6,2) DEFAULT NULL,
  `pubId` varchar(2) NOT NULL DEFAULT '',
  `coverImage` longblob,
  PRIMARY KEY (`bookId`),
  KEY `pubId` (`pubId`),
  CONSTRAINT `Book_ibfk_1` FOREIGN KEY (`pubId`) REFERENCES `Publisher` (`publisherId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into Book(isbn, title, releaseDate, listPrice, pubId, coverImage) VALUES('161729120X', 'Spring in Action, 4/e', '2014-11-27', 49.99, 'MP', LOAD_FILE('C:/javaeeSpring/mysql/bin/161729120X.jpg')); /* 1 */
insert into Book(isbn, title, releaseDate, listPrice, pubId, coverImage) VALUES('1491901632', 'Hadoop: The Definitive Guide, 4/e', '2015-04-10', 49.99, 'OA', LOAD_FILE('C:/javaeeSpring/mysql/bin/1491901632.jpg')); /* 2 */
insert into Book(isbn, title, releaseDate, listPrice, pubId, coverImage) VALUES('193398869X', 'Secrets of the JavaScript Ninja', '2012-12-27', 39.99, 'MP', LOAD_FILE('C:/javaeeSpring/mysql/bin/193398869X.jpg'));   /* 3 */
insert into Book(isbn, title, releaseDate, listPrice, pubId, coverImage) VALUES('0764543857', 'Expert One-on-One J2EE Design and Development', '2002-09-30', 59.99, 'WP', LOAD_FILE('C:/javaeeSpring/mysql/bin/0764543857.jpg')); /* 4 */
insert into Book(isbn, title, releaseDate, listPrice, pubId, coverImage) VALUES('1932394885', 'Java Persistence with Hibernate', '2006-11-23', 59.95, 'MP', LOAD_FILE('C:/javaeeSpring/mysql/bin/1932394885.jpg'));   /* 5 */
insert into Book(isbn, title, releaseDate, listPrice, pubId, coverImage) VALUES('0596517742', 'JavaScript: The Good Parts', '2008-05-30', 29.99, 'OA', LOAD_FILE('C:/javaeeSpring/mysql/bin/0596517742.jpg'));    /* 6 */
insert into Book(isbn, title, releaseDate, listPrice, pubId, coverImage) VALUES('0321683684', 'The Elements of User Experience: User-Centered Design for the Web and Beyond, 2/e', '2010-12-25', 49.99, 'NR', LOAD_FILE('C:/javaeeSpring/mysql/bin/0321683684.jpg')); /* 7 */
insert into Book(isbn, title, releaseDate, listPrice, pubId, coverImage) VALUES('9863473316', '松本行弘談程式世界的未來', '2014-10-30', 14.95, 'GI', LOAD_FILE('C:/javaeeSpring/mysql/bin/9863473316.jpg'));  /* 8 */
insert into Book(isbn, title, releaseDate, listPrice, pubId, coverImage) VALUES('1937785564', 'Agile Web Development with Rails 4, 4/e', '2013-10-07', 43.95, 'PB', LOAD_FILE('C:/javaeeSpring/mysql/bin/1937785564.jpg'));   /* 9 */
insert into Book(isbn, title, releaseDate, listPrice, pubId, coverImage) VALUES('013390069X', 'The Java Language Specification, Java SE 8 Edition', ' 2014-05-15', 59.99, 'AW', LOAD_FILE('C:/javaeeSpring/mysql/bin/013390069X.jpg'));   /* 10 */

CREATE TABLE `BookAuthor` (
  `bkId` int(11) NOT NULL DEFAULT '0',
  `arId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`bkId`,`arId`),
  KEY `arId` (`arId`),
  CONSTRAINT `BookAuthor_ibfk_1` FOREIGN KEY (`arId`) REFERENCES `Author` (`authorId`),
  CONSTRAINT `BookAuthor_ibfk_2` FOREIGN KEY (`bkId`) REFERENCES `Book` (`bookId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO BookAuthor VALUES(1, 1);
INSERT INTO BookAuthor VALUES(2, 2);
INSERT INTO BookAuthor VALUES(3, 3);
INSERT INTO BookAuthor VALUES(3, 4);
INSERT INTO BookAuthor VALUES(4, 5);
INSERT INTO BookAuthor VALUES(5, 6);
INSERT INTO BookAuthor VALUES(5, 7);
INSERT INTO BookAuthor VALUES(6, 8);
INSERT INTO BookAuthor VALUES(7, 9);
INSERT INTO BookAuthor VALUES(8, 10);
INSERT INTO BookAuthor VALUES(9, 11);
INSERT INTO BookAuthor VALUES(9, 12);
INSERT INTO BookAuthor VALUES(9, 13);
INSERT INTO BookAuthor VALUES(10, 14);
INSERT INTO BookAuthor VALUES(10, 15);
INSERT INTO BookAuthor VALUES(10, 16);
INSERT INTO BookAuthor VALUES(10, 17);
INSERT INTO BookAuthor VALUES(10, 18);

/* Misc */

CREATE TABLE `PublisherNoPK` (
  `publisherId` varchar(2) NOT NULL DEFAULT '',
  `publisherName` varchar(50) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO PublisherNoPK VALUES('MP', 'Manning Publication');
INSERT INTO PublisherNoPK VALUES('OA', 'O''Reilly & Associates');
INSERT INTO PublisherNoPK VALUES('WP', 'Wrox Press');
INSERT INTO PublisherNoPK VALUES('NR', 'New Riders');
INSERT INTO PublisherNoPK VALUES('GI', 'Gotop Information');
INSERT INTO PublisherNoPK VALUES('PB', 'Pragmatic Bookshelf');
INSERT INTO PublisherNoPK VALUES('AW', 'Addison Wesley');

CREATE TABLE `Reader` (
  `name` varchar(50) NOT NULL,
  `readerId` varchar(10) NOT NULL,
  `birthday` date DEFAULT NULL,
  `married` tinyint(1) DEFAULT '0',
  `maxAllowed` int(3) NOT NULL,
  `email` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`readerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `BooksWithPublisher`
AS SELECT
   `book`.`bookId` AS `bookId`,
   `book`.`isbn` AS `isbn`,
   `book`.`title` AS `title`,
   `book`.`releaseDate` AS `releaseDate`,
   `book`.`listPrice` AS `listPrice`,
   `book`.`coverImage` AS `coverImage`,
   `publisher`.`publisherName` AS `publisherName`
FROM (`book` join `publisher` on((`book`.`pubId` = `publisher`.`publisherId`)));

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `books_of_a_publisher`(IN pPublisherId VARCHAR(2))
BEGIN
  SELECT * FROM Book WHERE Book.pubId = pPublisherId;
END $$

DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_reader`(IN rReaderId VARCHAR(10))
BEGIN
  DELETE FROM Reader WHERE readerId = rReaderId;
END $$

DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `find_all_readers`()
BEGIN
  SELECT * FROM Reader;
END $$

DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `find_reader`(IN rReaderId VARCHAR(10))
BEGIN
  SELECT * FROM Reader WHERE readerId = rReaderId;
END $$

DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_reader`(IN rName VARCHAR(50), IN rReaderId VARCHAR(10), IN rBirthday DATE, IN rMarried TINYINT(1), IN rMaxAllowed INT(3), IN rEmail VARCHAR(50))
BEGIN
  INSERT INTO Reader VALUES (rName, rReaderId, rBirthday, rMarried, rMaxAllowed, rEmail);
END $$

DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_reader`(IN rName VARCHAR(50), IN rReaderId VARCHAR(10), IN rBirthday DATE, IN rMarried TINYINT(1), IN rMaxAllowed INT(3), IN rEmail VARCHAR(50))
BEGIN
  UPDATE Reader SET NAME = rName, birthday = rBirthday, married = rMarried, maxAllowed = rMaxAllowed, email = rEmail WHERE readerId = rReaderId;
END $$
DELIMITER ;

