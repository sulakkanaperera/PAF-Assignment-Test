-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2020 at 06:50 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `doctor`
--

-- --------------------------------------------------------

--
-- Table structure for table `doctor1`
--

CREATE TABLE `doctor1` (
  `DocID` int(11) NOT NULL,
  `Dname` varchar(20) NOT NULL,
  `Nic` varchar(30) NOT NULL,
  `DoctorPrice` float(5,2) NOT NULL,
  `Phone` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doctor1`
--

INSERT INTO `doctor1` (`DocID`, `Dname`, `Nic`, `DoctorPrice`, `Phone`) VALUES
(4, 'chintha', '889546423V', 475.24, '0775138704'),
(5, 'Devid', '785469324V', 950.50, '0117854793'),
(6, 'Ronaldo', '658244978V', 899.75, '0665633248'),
(7, 'Undertacker', '598631200V', 782.33, '0779862241');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `doctor1`
--
ALTER TABLE `doctor1`
  ADD PRIMARY KEY (`DocID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `doctor1`
--
ALTER TABLE `doctor1`
  MODIFY `DocID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
