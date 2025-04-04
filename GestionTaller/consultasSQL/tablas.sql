CREATE DATABASE GestionTaller;
USE GestionTaller;

CREATE TABLE Clientes ( 
    id INT PRIMARY KEY AUTO_INCREMENT,
    DNI VARCHAR(20) UNIQUE NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    telefono INT,
    email VARCHAR(255),
    contraseña VARCHAR(255)
);

<<<<<<< HEAD
CREATE TABLE Empleado (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefono int NOT NULL,
=======
CREATE TABLE Empleados (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    telefono INT
>>>>>>> e043f7cc9baa48a91b14bc69e45f70c3ea84aed0
);

CREATE TABLE Vehiculo (
    matricula VARCHAR(20) PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    año int NOT NULL,
    dni VARCHAR(9) NOT NULL,
    FOREIGN KEY (dni) REFERENCES Cliente (dni)
);

<<<<<<< HEAD
CREATE TABLE Cita (
    id INT PRIMARY KEY AUTO_INCREMENT,
    cliente VARCHAR(100) NOT NULL,

    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    descripcion VARCHAR(200) NOT NULL
=======
CREATE TABLE Citas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fecha VARCHAR(10) NOT NULL,
    hora VARCHAR(5) NOT NULL,
    descripcion VARCHAR(255),
    clienteDNI VARCHAR(20) NOT NULL,
    empleadoID INT NOT NULL,
    FOREIGN KEY (clienteDNI) REFERENCES Clientes(id),
    FOREIGN KEY (empleadoID) REFERENCES Empleados(id)
>>>>>>> e043f7cc9baa48a91b14bc69e45f70c3ea84aed0
);