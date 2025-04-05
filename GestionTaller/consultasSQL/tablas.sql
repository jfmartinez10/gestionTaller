CREATE DATABASE GestionTaller;
USE GestionTaller;

CREATE TABLE Clientes ( 
    id INT PRIMARY KEY AUTO_INCREMENT,
    dni VARCHAR(20) UNIQUE NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    telefono INT,
    email VARCHAR(255)
);

CREATE TABLE Empleados (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefono INT NOT NULL
);

CREATE TABLE Proveedores (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    telefono INT NOT NULL
);

CREATE TABLE Vehiculo (
    matricula VARCHAR(20) PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    ano int NOT NULL,
    dni VARCHAR(9) NOT NULL,
    FOREIGN KEY (dni) REFERENCES Clientes(dni)
);

CREATE TABLE Citas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fecha VARCHAR(10) NOT NULL,
    hora VARCHAR(5) NOT NULL,
    descripcion VARCHAR(255),
    clienteDNI VARCHAR(20) NOT NULL,
    FOREIGN KEY (clienteDNI) REFERENCES Clientes(dni)
);