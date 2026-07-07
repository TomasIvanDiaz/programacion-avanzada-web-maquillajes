CREATE DATABASE IF NOT EXISTS `maquillajes-bd` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `maquillajes-bd`;

CREATE TABLE cp (
    codigoPostal VARCHAR(10) PRIMARY KEY,
    localidad VARCHAR(100) NOT NULL,
    provincia VARCHAR(100) NOT NULL
);

CREATE TABLE categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT
);

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    esAdmin TINYINT(1) NOT NULL DEFAULT 0,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    domicilio VARCHAR(200) NOT NULL,
    telefono VARCHAR(20),
    codigoPostal VARCHAR(10),
    mail VARCHAR(150) NOT NULL,
    FOREIGN KEY (codigoPostal) REFERENCES cp(codigoPostal)
);

CREATE TABLE producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    activo TINYINT(1) NOT NULL DEFAULT 1,
    marca VARCHAR(100) NOT NULL,
    imagen VARCHAR(255),
    idCategoria INT NOT NULL,
    FOREIGN KEY (idCategoria) REFERENCES categoria(id)
);

INSERT INTO cp (codigoPostal, localidad, provincia) VALUES
('2000', 'Rosario', 'Santa Fe'),
('1000', 'Buenos Aires', 'Buenos Aires'),
('5000', 'Córdoba', 'Córdoba');
('3600', 'Formosa', 'Fornosa');

INSERT INTO categoria (nombre, descripcion) VALUES
('Labiales', 'Productos para labios'),
('Bases', 'Bases y correctores'),
('Ojos', 'Máscaras, sombras y delineadores');
('Rostro','Contornos, Rubores');
('Accesorios', 'Brochas, Esponjitas');

INSERT INTO usuario (usuario, password, esAdmin, nombre, apellido, domicilio, telefono, codigoPostal) VALUES
('admin', '1234', 1, 'Admin', 'Admin', 'Calle Falsa 123', '3411234567', '2000'),
('cliente', '1234', 0, 'Cliente', 'Prueba', 'Av. Siempreviva 742', '3419876543', '2000');

INSERT INTO producto (nombre, descripcion, precio, stock, marca, idCategoria) VALUES
('Labial Rojo', 'Larga duración', 1500.00, 20, 'MaybelLine', 1),
('Base Líquida', 'Cobertura total', 2800.00, 15, 'L Oreal', 2),
('Máscara de Pestañas', 'Volumen y definición', 1200.00, 30, 'Rimmel', 3);