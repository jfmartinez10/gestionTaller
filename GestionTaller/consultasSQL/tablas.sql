CREATE DATABASE GestionTaller;
USE GestionTaller;

CREATE TABLE Cliente (
    dni VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefono int NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE Empleado (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefono int NOT NULL,
);

CREATE TABLE Vehiculo (
    matricula VARCHAR(20) PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    año int NOT NULL,
    dni VARCHAR(9) NOT NULL,
    FOREIGN KEY (dni) REFERENCES Cliente (dni)
);

CREATE TABLE Cita (
    id INT PRIMARY KEY AUTO_INCREMENT,
    cliente VARCHAR(100) NOT NULL,

    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    descripcion VARCHAR(200) NOT NULL
);