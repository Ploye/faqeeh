-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 10 Des 2021 pada 12.39
-- Versi server: 10.4.19-MariaDB
-- Versi PHP: 7.4.20

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
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `kode_barang` varchar(6) NOT NULL,
  `nama_barang` varchar(8) NOT NULL,
  `jenis_barang` varchar(15) NOT NULL,
  `warna` varchar(7) NOT NULL,
  `ukuran_kerudung` varchar(3) NOT NULL,
  `jenis_kain` varchar(10) NOT NULL,
  `harga_satuan` double NOT NULL,
  `stok` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`kode_barang`, `nama_barang`, `jenis_barang`, `warna`, `ukuran_kerudung`, `jenis_kain`, `harga_satuan`, `stok`) VALUES
('E-0001', 'adaw', 'adaw', 'adaw', '', 'asd', 123, 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `master barang`
--

CREATE TABLE `master barang` (
  `Nama Barang` varchar(25) NOT NULL,
  `Stock` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `permintaan`
--

CREATE TABLE `permintaan` (
  `id` int(11) NOT NULL,
  `kode_pesanan` int(100) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `no_hp` int(12) NOT NULL,
  `jumlah_pesanan` int(100) NOT NULL,
  `total_harga` bigint(255) NOT NULL,
  `status_pesanan` varchar(16) NOT NULL,
  `dp` bigint(255) NOT NULL,
  `jenis_pesanan` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `permintaan`
--

INSERT INTO `permintaan` (`id`, `kode_pesanan`, `nama`, `no_hp`, `jumlah_pesanan`, `total_harga`, `status_pesanan`, `dp`, `jenis_pesanan`) VALUES
(1, 12, 'faqih', 613435251, 2, 250000, 'Terpenuhi', 80000, 'kerudung pasminah');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tlogin`
--

CREATE TABLE `tlogin` (
  `user` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tlogin`
--

INSERT INTO `tlogin` (`user`, `password`) VALUES
('Faqih', 'Uhuy1');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id` bigint(11) NOT NULL,
  `kd_brg` varchar(25) NOT NULL,
  `qty` int(100) NOT NULL,
  `harga` bigint(100) NOT NULL,
  `tanggal` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id`, `kd_brg`, `qty`, `harga`, `tanggal`) VALUES
(1, '1', 2, 31110, '2021-08-23');

--
-- Trigger `transaksi`
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
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kode_barang`);

--
-- Indeks untuk tabel `permintaan`
--
ALTER TABLE `permintaan`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `permintaan`
--
ALTER TABLE `permintaan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2342343;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
