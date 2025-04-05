INSERT INTO Clientes (dni, nombre, apellido, telefono, email, contraseña) VALUES
('12345678A', 'Ana', 'García', 612345678, 'ana.garcia@email.com', 'clave123'),
('98765432B', 'Pedro', 'López', 698765432, 'pedro.lopez@email.com', 'secreto456'),
('56789012C', 'Sofía', 'Martínez', 654321098, 'sofia.martinez@email.com', 'micontra789'),
('34567890D', 'Javier', 'Ruiz', 632109876, 'javier.ruiz@email.com', 'password00');

INSERT INTO Empleados (nombre, apellido, telefono) VALUES
('Laura', 'Fernández', 678901234),
('Carlos', 'Sánchez', 643210987),
('Elena', 'Díaz', 621098765),
('Miguel', 'Pérez', 687654321);

INSERT INTO Proveedores (nombre, email, telefono) VALUES
('Proveedor A', 'proveedor.a@email.com', 911223344),
('Suministros B', 'contacto@suministrosb.es', 655667788),
('Distribuciones C', 'pedidos.c@distribucionesc.net', 976543210),
('Materiales D', 'info@materialesd.org', 601112233);

INSERT INTO Vehiculo (matricula, marca, modelo, año, dni) VALUES
('1111AAA', 'Seat', 'Ibiza', 2018, '12345678A'),
('2222BBB', 'Ford', 'Focus', 2020, '98765432B'),
('3333CCC', 'Renault', 'Clio', 2019, '56789012C'),
('4444DDD', 'Peugeot', '208', 2021, '34567890D');

INSERT INTO Citas (fecha, hora, descripcion, clienteDNI, empleadoID) VALUES
('2025-04-10', '10:00', 'Revisión de frenos', '12345678A', 1),
('2025-04-12', '16:30', 'Cambio de aceite', '98765432B', 2),
('2025-04-15', '09:15', 'Sustitución de neumáticos', '56789012C', 3),
('2025-04-18', '11:45', 'Alineación y equilibrado', '34567890D', 4);