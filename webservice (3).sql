-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 11, 2016 at 06:40 PM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 7.0.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `webservice`
--

-- --------------------------------------------------------

--
-- Table structure for table `busy_table`
--

CREATE TABLE `busy_table` (
  `phone_no_b` bigint(10) NOT NULL,
  `start_date_b` date DEFAULT NULL,
  `end_date_b` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `contractor_details`
--

CREATE TABLE `contractor_details` (
  `user_name_c` varchar(255) NOT NULL,
  `phone_num_c` bigint(10) NOT NULL,
  `village_name_c` varchar(255) NOT NULL,
  `password_c` varchar(32) NOT NULL,
  `gender_c` enum('male','female') NOT NULL,
  `license_num_c` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `contractor_details`
--

INSERT INTO `contractor_details` (`user_name_c`, `phone_num_c`, `village_name_c`, `password_c`, `gender_c`, `license_num_c`) VALUES
('', 0, '', 'd41d8cd98f00b204e9800998ecf8427e', '', ''),
('Parag', 7485961425, 'sampa', 'e10adc3949ba59abbe56e057f20f883e', 'male', '123456789'),
('Tirth', 7698110802, 'Zapan', '123456', 'male', '789456122'),
('Rakesh', 9574126146, 'Gandhinagar', '25d55ad283aa400af464c76d713c07ad', 'male', '789456123'),
('Ramesh', 9574126147, 'Gandhinagar', 'e10adc3949ba59abbe56e057f20f883e', 'male', '789456122'),
('Suresh', 9574126149, 'Gandhinagar', 'e10adc3949ba59abbe56e057f20f883e', 'male', '789456121'),
('Rajesh', 9876543211, 'Gandhinagar', 'e10adc3949ba59abbe56e057f20f883e', 'male', '789456120');

-- --------------------------------------------------------

--
-- Table structure for table `gcm_registration`
--

CREATE TABLE `gcm_registration` (
  `id` bigint(10) NOT NULL,
  `token` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `laborer_details`
--

CREATE TABLE `laborer_details` (
  `user_name_l` varchar(255) NOT NULL,
  `phone_num_l` bigint(10) NOT NULL,
  `password_l` varchar(32) NOT NULL,
  `gender_l` tinyint(1) NOT NULL,
  `age_l` int(2) NOT NULL,
  `rating_l` int(11) NOT NULL,
  `village_l` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `laborer_details`
--

INSERT INTO `laborer_details` (`user_name_l`, `phone_num_l`, `password_l`, `gender_l`, `age_l`, `rating_l`, `village_l`) VALUES
('Ishita', 986532455, '25d55ad283aa400af464c76d713c07ad', 1, 25, 0, 'sampa'),
('Ishita', 986532456, '25d55ad283aa400af464c76d713c07ad', 1, 25, 0, 'sampa'),
('mrugesh', 1234567888, '25d55ad283aa400af464c76d713c07ad', 1, 58, 0, 'addvss'),
('ishita1', 2525252525, '25d55ad283aa400af464c76d713c07ad', 0, 25, 0, 'gandhinagar'),
('gsknsbvvs', 3625145555, 'ed2b1f468c5f915f3f1cf75d7068baae', 1, 55, 0, 'ggg'),
('mukeshha', 5555663311, '25f9e794323b453885f5181f1b624d0b', 1, 25, 0, 'ghaka'),
('mukeshha', 5555663316, '25f9e794323b453885f5181f1b624d0b', 1, 25, 0, 'ghaka'),
('mukeshha', 5555666625, '25f9e794323b453885f5181f1b624d0b', 1, 25, 0, 'ghaka'),
('gaksnns', 5863698866, '25d55ad283aa400af464c76d713c07ad', 0, 56, 0, 'jsvsbannan'),
('meugesh', 6936258556, '25d55ad283aa400af464c76d713c07ad', 1, 21, 0, 'amdavad'),
('gshnana', 6936258574, '25d55ad283aa400af464c76d713c07ad', 1, 58, 0, 'dghanamaks'),
('tirth', 6958742345, '25d55ad283aa400af464c76d713c07ad', 0, 0, 0, 'sampa'),
('parag', 7405155555, '25d55ad283aa400af464c76d713c07ad', 1, 28, 0, 'daiict'),
('rajan', 8456238945, '25d55ad283aa400af464c76d713c07ad', 1, 58, 0, 'ahahnsns'),
('lakaha', 8536142536, '25d55ad283aa400af464c76d713c07ad', 0, 56, 0, 'sampa'),
('smruti', 8569472514, '25d55ad283aa400af464c76d713c07ad', 0, 20, 0, 'baroda'),
('gshsjnaa', 8569742536, '25d55ad283aa400af464c76d713c07ad', 0, 56, 0, 'sganjaks'),
('Parag', 8596859685, '25d55ad283aa400af464c76d713c07ad', 1, 25, 0, 'sampa'),
('misha', 9548263710, '410746c08cd21d734bc5afbbd5c08f6b', 0, 23, 0, 'kudasan'),
('Ishiti', 9865321245, '25d55ad283aa400af464c76d713c07ad', 1, 25, 0, 'sampa'),
('Ishita', 9865321246, '25d55ad283aa400af464c76d713c07ad', 1, 25, 0, 'sampa'),
('Ishita', 9865321248, '25d55ad283aa400af464c76d713c07ad', 1, 25, 0, 'sampa');

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE `notifications` (
  `id` int(11) NOT NULL,
  `phone_no` bigint(10) NOT NULL,
  `timestamp` datetime NOT NULL,
  `notification_type` enum('0','1','2') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `rating_table`
--

CREATE TABLE `rating_table` (
  `Phone_no_r` bigint(10) NOT NULL,
  `rating_r` enum('1','2','3','4','5','0') NOT NULL,
  `rated_by_r` bigint(10) NOT NULL,
  `rate_time_r` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `request_table`
--

CREATE TABLE `request_table` (
  `request_id` int(11) NOT NULL,
  `request_start_date` date NOT NULL,
  `request_end_date` date NOT NULL,
  `requester` bigint(10) NOT NULL,
  `acceptor` bigint(10) NOT NULL,
  `request_response` enum('0','1','2') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `request_table`
--

INSERT INTO `request_table` (`request_id`, `request_start_date`, `request_end_date`, `requester`, `acceptor`, `request_response`) VALUES
(1, '2016-05-24', '2016-06-22', 9574126146, 6936258574, '0');

-- --------------------------------------------------------

--
-- Table structure for table `skills_table`
--

CREATE TABLE `skills_table` (
  `Phone_no` bigint(10) NOT NULL,
  `inter_cultivation` int(11) NOT NULL,
  `ploughing` int(11) NOT NULL,
  `pesticides` int(11) NOT NULL,
  `crop_seeding` int(11) NOT NULL,
  `plumbing` int(11) NOT NULL,
  `digging` int(11) NOT NULL,
  `flooring_ceiling` int(11) NOT NULL,
  `brick_work` int(11) NOT NULL,
  `other` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `skills_table`
--

INSERT INTO `skills_table` (`Phone_no`, `inter_cultivation`, `ploughing`, `pesticides`, `crop_seeding`, `plumbing`, `digging`, `flooring_ceiling`, `brick_work`, `other`) VALUES
(986532456, 1, 0, 0, 1, 0, 1, 1, 1, 0),
(5555663311, 1, 1, 1, 0, 0, 1, 0, 1, 1),
(5555663316, 1, 1, 1, 0, 0, 1, 0, 1, 1),
(8989898989, 1, 1, 1, 1, 1, 0, 0, 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `contractor_details`
--
ALTER TABLE `contractor_details`
  ADD PRIMARY KEY (`phone_num_c`),
  ADD UNIQUE KEY `phone_num_c` (`phone_num_c`);

--
-- Indexes for table `laborer_details`
--
ALTER TABLE `laborer_details`
  ADD PRIMARY KEY (`phone_num_l`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `request_table`
--
ALTER TABLE `request_table`
  ADD PRIMARY KEY (`request_id`);

--
-- Indexes for table `skills_table`
--
ALTER TABLE `skills_table`
  ADD PRIMARY KEY (`Phone_no`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `request_table`
--
ALTER TABLE `request_table`
  MODIFY `request_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
