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

CREATE TABLE Empleados (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    telefono INT
);

CREATE TABLE Vehiculo (
    matricula VARCHAR(20) PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    año int NOT NULL,
    dni VARCHAR(9) NOT NULL,
    FOREIGN KEY (DNI) REFERENCES Cliente (DNI)
);

CREATE TABLE Citas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fecha VARCHAR(10) NOT NULL,
    hora VARCHAR(5) NOT NULL,
    descripcion VARCHAR(255),
    clienteDNI VARCHAR(20) NOT NULL,
    empleadoID INT NOT NULL,
    FOREIGN KEY (clienteDNI) REFERENCES Clientes(id),
    FOREIGN KEY (empleadoID) REFERENCES Empleados(id)
);