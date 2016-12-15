
--
-- Table structure for table `proof`
--

CREATE TABLE `proof` (
  `id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `proof`
--

INSERT INTO `proof` (`id`, `title`, `description`, `file_path`, `created_date`, `created_by`, `modified_by`, `modified_date`, `start_time`, `end_time`, `parent_id`, `type`) VALUES
(1, 'aaa', 'bbb', 'D:\\upload\\14492589_798618500240930_2042992077575306937_n.png', '2016-12-14 13:27:28', 'admin', NULL, '2016-12-14 13:47:48', '2016-12-03 00:00:00', '2016-12-29 00:00:00', NULL, 'BRANCH'),
(2, 'bbb', 'ccc', 'D:\\upload\\ERD.png', '2016-12-14 13:48:04', 'admin', NULL, NULL, '2016-12-10 00:00:00', '2016-12-14 00:00:00', NULL, 'LEAF'),
(3, 'ccc', 'ccc', 'D:\\upload\\CV_Nguyen Thanh Phong.docx', '2016-12-15 11:17:47', 'admin', NULL, '2016-12-15 11:26:49', '2016-12-01 00:00:00', '2016-12-16 00:00:00', 1, 'LEAF');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `dob` date NOT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `role` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `email`, `full_name`, `dob`, `phone`, `role`) VALUES
(1, 'admin', '$2a$10$8j6usV/mz9NSESXmjmeV7eAwI6alCXtteViN5wCogRz7dSd10YuDS', 'phong@mail.com', 'Nguyen Thanh Phong', '2016-12-02', '123456789', 'ADMIN'),
(4, 'aaa', '$2a$10$KKYr3VMiaq64fXosr.vCs.uZJaJVAPYXpuliyDbs1dVvrtXWR3LzG', 'mrphongnt@gmail.com', 'aaa', '2016-12-16', '09412473', 'USER4');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `proof`
--
ALTER TABLE `proof`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `proof`
--
ALTER TABLE `proof`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;