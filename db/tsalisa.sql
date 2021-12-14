-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 14, 2021 at 11:28 AM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 7.3.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tsalisa`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `kode_barang` varchar(20) NOT NULL,
  `nama_barang` varchar(20) NOT NULL,
  `jenis_barang` varchar(15) NOT NULL,
  `warna` varchar(20) NOT NULL,
  `ukuran_kerudung` varchar(20) NOT NULL,
  `jenis_kain` varchar(20) NOT NULL,
  `harga_satuan` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`kode_barang`, `nama_barang`, `jenis_barang`, `warna`, `ukuran_kerudung`, `jenis_kain`, `harga_satuan`) VALUES
('B001', 'Cadar', 'Poni', 'Merah', 'M', 'Katun', 5000),
('B002', 'Kerudung', 'Segi Empat', 'Coklat', 'L', 'Katun', 2000),
('B003', 'Masker', 'Buff', 'Coklat', 'M', 'Drill', 8000),
('B004', 'Cadar', 'Massir', 'Coklat', 'XXL', 'Katun', 10);

-- --------------------------------------------------------

--
-- Table structure for table `master barang`
--

CREATE TABLE `master barang` (
  `Nama Barang` varchar(25) NOT NULL,
  `Stock` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `permintaan`
--

CREATE TABLE `permintaan` (
  `kode_pesanan` varchar(20) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `no_hp` int(12) NOT NULL,
  `jumlah_pesanan` int(100) NOT NULL,
  `total_harga` bigint(255) NOT NULL,
  `status_pesanan` varchar(16) NOT NULL,
  `dp` bigint(255) NOT NULL,
  `jenis_pesanan` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `permintaan`
--

INSERT INTO `permintaan` (`kode_pesanan`, `nama`, `no_hp`, `jumlah_pesanan`, `total_harga`, `status_pesanan`, `dp`, `jenis_pesanan`) VALUES
('P001', 'dasd', 432, 34, 3432, 'Tidak Terpenuhi', 200, 'asdas'),
('P002', 'Albert', 8932, 2, 2000, 'Terpenuhi', 60, 'teing'),
('P003', 'uyeh', 9342, 2, 200, 'Tidak Terpenuhi', 22, 'sdas');

-- --------------------------------------------------------

--
-- Table structure for table `tlogin`
--

CREATE TABLE `tlogin` (
  `user` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tlogin`
--

INSERT INTO `tlogin` (`user`, `password`) VALUES
('Faqih', 'Uhuy1');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id` bigint(11) NOT NULL,
  `kd_brg` varchar(25) NOT NULL,
  `qty` int(100) NOT NULL,
  `harga` bigint(100) NOT NULL,
  `tanggal` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id`, `kd_brg`, `qty`, `harga`, `tanggal`) VALUES
(1, '1', 2, 31110, '2021-08-23');

--
-- Triggers `transaksi`
--
DELIMITER $$
CREATE TRIGGER `qty` AFTER INSERT ON `transaksi` FOR EACH ROW BEGIN
UPDATE barang SET stok=stok-NEW.qty
WHERE id=NEW.id;
END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kode_barang`);

--
-- Indexes for table `permintaan`
--
ALTER TABLE `permintaan`
  ADD PRIMARY KEY (`kode_pesanan`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2342343;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
