-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3307
-- Generation Time: May 14, 2021 at 03:36 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `paymentapi`
--

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `paymentID` int(11) NOT NULL,
  `Amount` int(11) NOT NULL,
  `payDate` varchar(10) NOT NULL,
  `cardHolder` varchar(50) NOT NULL,
  `cardNo` varchar(20) NOT NULL,
  `cvv` int(3) NOT NULL,
  `expDate` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`paymentID`, `Amount`, `payDate`, `cardHolder`, `cardNo`, `cvv`, `expDate`) VALUES
(210, 50000, '11-03-2021', 'Alwis', '9876 235 2634 1265', 516, '12/23'),
(211, 40000, '11-03-2021', 'Munasinghe', '4235 3165 8864 5124', 156, '10/22'),
(212, 45000, '12-03-2021', 'Pathinnayake', '4890 4321 9587 3412', 253, '05/22'),
(214, 56000, '17-03-2021', 'Welivitigoda', '4912 5193 9472 6019', 341, '02/24'),
(215, 25000, '19-03-2021', 'Gamage', '4913 4916 8135 5193', 283, '03/23');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`paymentID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
