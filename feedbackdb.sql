-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 11, 2020 at 01:14 AM
-- Server version: 5.7.21
-- PHP Version: 5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `feedbackdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `AdminID` int(11) NOT NULL,
  `UserName` varchar(10) NOT NULL,
  `Password` varchar(10) NOT NULL,
  PRIMARY KEY (`AdminID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`AdminID`, `UserName`, `Password`) VALUES
(7187, 'Lexa', 'lexa123'),
(2153, 'Anna', 'anna123');

-- --------------------------------------------------------

--
-- Table structure for table `analytics`
--

DROP TABLE IF EXISTS `analytics`;
CREATE TABLE IF NOT EXISTS `analytics` (
  `LecturerID` int(11) NOT NULL,
  `QuestionID` int(11) NOT NULL,
  `Answer1Count` int(11) NOT NULL,
  `Answer2Count` int(11) NOT NULL,
  `Answer3Count` int(11) NOT NULL,
  `Answer4Count` int(11) NOT NULL,
  KEY `QuestionID` (`QuestionID`),
  KEY `LecturerID` (`LecturerID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `analytics`
--

INSERT INTO `analytics` (`LecturerID`, `QuestionID`, `Answer1Count`, `Answer2Count`, `Answer3Count`, `Answer4Count`) VALUES
(1, 1000, 12, 14, 13, 11),
(1, 1001, 23, 21, 24, 11),
(1, 1002, 13, 21, 24, 19),
(1, 1003, 21, 25, 20, 10),
(1, 1004, 7, 5, 3, 11),
(2, 1000, 13, 16, 17, 21),
(2, 1001, 13, 17, 20, 15),
(2, 1002, 15, 0, 0, 7),
(2, 1003, 12, 13, 15, 15),
(2, 1004, 12, 13, 14, 15),
(3, 1000, 13, 15, 16, 16),
(3, 1001, 22, 23, 23, 24),
(3, 1002, 21, 11, 16, 17),
(3, 1003, 15, 1, 0, 19),
(3, 1004, 21, 11, 16, 18),
(4, 1000, 12, 13, 14, 15),
(4, 1001, 23, 21, 22, 24),
(4, 1002, 12, 12, 13, 14),
(4, 1003, 21, 22, 23, 20),
(4, 1004, 21, 12, 15, 15);

-- --------------------------------------------------------

--
-- Table structure for table `lecturer`
--

DROP TABLE IF EXISTS `lecturer`;
CREATE TABLE IF NOT EXISTS `lecturer` (
  `LecturerID` int(11) NOT NULL,
  `LecturerName` varchar(20) NOT NULL,
  PRIMARY KEY (`LecturerID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lecturer`
--

INSERT INTO `lecturer` (`LecturerID`, `LecturerName`) VALUES
(1, 'Jane Kolins'),
(2, 'Adam Crody'),
(3, 'Gemma Reyaz'),
(4, 'Ryan Krizty');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
CREATE TABLE IF NOT EXISTS `questions` (
  `QuestionID` int(11) NOT NULL,
  `Question` varchar(100) NOT NULL,
  `Answer1` varchar(20) NOT NULL,
  `Answer2` varchar(20) NOT NULL,
  `Answer3` varchar(20) NOT NULL,
  `Answer4` varchar(20) NOT NULL,
  PRIMARY KEY (`QuestionID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`QuestionID`, `Question`, `Answer1`, `Answer2`, `Answer3`, `Answer4`) VALUES
(1000, 'Is the lecturer use VLE to share learning materials with students?', 'Always', 'Usually', 'Some times', 'Not at all'),
(1001, 'How about the student participation in the lecture?', 'High', 'Usually', 'Low', 'Not at all'),
(1002, 'Is the lecturer flexible when students ask questions during the lecture?', 'Always', 'Usually', 'Some timesMay', 'Not at all'),
(1003, 'Is lecturer checks to make sure students understand the lecture?', 'Always', 'Usually', 'Some times', 'Not at all'),
(1004, 'The lecturer takes time to summarize the lecture end of the day?', 'Always', 'Usually', 'Some times', 'Not at all');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `analytics`
--
ALTER TABLE `analytics`
  ADD CONSTRAINT `analytics_ibfk_1` FOREIGN KEY (`QuestionID`) REFERENCES `questions` (`QuestionID`),
  ADD CONSTRAINT `analytics_ibfk_2` FOREIGN KEY (`LecturerID`) REFERENCES `lecturer` (`LecturerID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
