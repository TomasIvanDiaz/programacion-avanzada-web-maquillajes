-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-07-2026 a las 01:41:16
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `maquillajes-bd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `nombre`, `descripcion`) VALUES
(1, 'Labiales', 'Productos para labios'),
(2, 'Bases', 'Bases y correctores'),
(3, 'Ojos', 'Máscaras, sombras y delineadores'),
(4, 'Rostro', 'Contornos, Rubores'),
(5, 'Accesorios', 'Brochas, Esponjitas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cp`
--

CREATE TABLE `cp` (
  `codigoPostal` varchar(10) NOT NULL,
  `localidad` varchar(100) NOT NULL,
  `provincia` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `cp`
--

INSERT INTO `cp` (`codigoPostal`, `localidad`, `provincia`) VALUES
('1000', 'Buenos Aires', 'Buenos Aires'),
('2000', 'Rosario', 'Santa Fe'),
('3600', 'Formosa', 'Fornosa'),
('5000', 'Córdoba', 'Córdoba');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `precio` decimal(10,2) NOT NULL,
  `stock` int(11) NOT NULL DEFAULT 0,
  `activo` tinyint(1) NOT NULL DEFAULT 1,
  `marca` varchar(100) NOT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `idCategoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `nombre`, `descripcion`, `precio`, `stock`, `activo`, `marca`, `imagen`, `idCategoria`) VALUES
(1, 'Labial Rojo', 'Larga duración', 1500.00, 20, 1, 'MaybelLine', 'labial.png', 1),
(2, 'Base Líquida', 'Cobertura total', 2800.00, 15, 1, 'L Oreal', 'baseliquida.png', 2),
(3, 'Máscara de Pestañas', 'Volumen y definición', 1200.00, 30, 1, 'Rimmel', 'mascara.png', 3),
(4, 'Corrector de Ojeras', 'Cobertura natural', 2000.00, 15, 1, 'Pink 21', 'corrector.png', 3),
(5, 'Delineador Liquido', 'Trazo preciso', 5000.00, 10, 1, 'Ruby Rose', 'delineador.png', 3),
(6, 'Labial Matte', 'Acabado mate, larga duracion', 3000.00, 12, 1, 'Pink 21', 'labial-matte.png', 1),
(7, 'Sombras Noche', 'Colores intensos y fáciles de difuminar', 15000.00, 5, 1, 'TEI', 'sombra.png', 3),
(8, 'Rubor ', 'Acabado natural', 4500.00, 10, 1, 'Ruby Rose', 'rubor-ruby.png', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `usuario` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `esAdmin` tinyint(1) NOT NULL DEFAULT 0,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `domicilio` varchar(200) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `codigoPostal` varchar(10) DEFAULT NULL,
  `mail` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `usuario`, `password`, `esAdmin`, `nombre`, `apellido`, `domicilio`, `telefono`, `codigoPostal`, `mail`) VALUES
(1, 'admin', '1234', 1, 'Admin', 'Admin', 'Calle Falsa 123', '3411234567', '2000', ''),
(2, 'cliente', '1234', 0, 'Cliente', 'Prueba', 'Av. Siempreviva 742', '3419876543', '2000', ''),
(3, 'belen', '123', 0, 'belen', 'rojas', 'ba', '3704282197', '2000', 'rojasritabelen1@hotmail.com');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cp`
--
ALTER TABLE `cp`
  ADD PRIMARY KEY (`codigoPostal`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idCategoria` (`idCategoria`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `usuario` (`usuario`),
  ADD KEY `codigoPostal` (`codigoPostal`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`id`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`codigoPostal`) REFERENCES `cp` (`codigoPostal`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
