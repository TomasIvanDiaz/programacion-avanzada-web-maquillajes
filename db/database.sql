CREATE DATABASE IF NOT EXISTS `maquillajes-bd` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `maquillajes-bd`;

CREATE TABLE categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT
);

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    esAdmin TINYINT(1) NOT NULL DEFAULT 0
);

CREATE TABLE producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    activo TINYINT(1) NOT NULL DEFAULT 1,
    idCategoria INT NOT NULL,
    FOREIGN KEY (idCategoria) REFERENCES categoria(id)
);

INSERT INTO categoria (nombre, descripcion) VALUES
('Labiales', 'Productos para labios'),
('Bases', 'Bases y correctores'),
('Ojos', 'Máscaras, sombras y delineadores');

INSERT INTO usuario (usuario, password, esAdmin) VALUES
('admin', '1234', 1),
('cliente', '1234', 0);

INSERT INTO producto (nombre, descripcion, precio, stock, idCategoria) VALUES
('Labial Rojo', 'Larga duración', 1500.00, 20, 1),
('Base Líquida', 'Cobertura total', 2800.00, 15, 2),
('Máscara de Pestañas', 'Volumen y definición', 1200.00, 30, 3);