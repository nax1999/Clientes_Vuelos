-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 06-10-2020 a las 19:22:46
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.3.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ADAT_VUELOS`
--
CREATE DATABASE IF NOT EXISTS `ADAT_VUELOS` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci;
USE `ADAT_VUELOS`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Vuelos`
--

CREATE TABLE IF NOT EXISTS `Vuelos` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODIGO VUELO` varchar(5) COLLATE latin1_spanish_ci NOT NULL,
  `ORIGEN` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `DESTINO` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `FECHA` date NOT NULL,
  `HORA` varchar(5) COLLATE latin1_spanish_ci NOT NULL,
  `PLAZA TOTALES` int(11) NOT NULL,
  `PLAZAS DISPONIBLES` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `Vuelos`
--

INSERT INTO `Vuelos` (`ID`, `CODIGO VUELO`, `ORIGEN`, `DESTINO`, `FECHA`, `HORA`, `PLAZA TOTALES`, `PLAZAS DISPONIBLES`) VALUES
(5, 'IB777', 'Marbella', 'Barcelona', '2020-07-10', '12:00', 666, 120),
(6, 'IB808', 'Las Vegas', 'Peru', '2020-09-01', '10:00', 200, 100),
(7, 'IL102', 'Portugal', 'Luxemburgo', '2020-09-24', '09:00', 330, 102);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
