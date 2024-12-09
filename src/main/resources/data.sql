INSERT INTO ROL (name)
VALUES
    ('ADMIN'),
    ('HELPER');

INSERT INTO USERS (username, email, password, enabled)
VALUES
    ('u19300224@utp.edu.pe', 'u19300224@utp.edu.pe', '$2a$12$cvT5dLzgwyPRUKywdYCtXeg2J7SNSyJGAsrhDJjJnuQNiZz.K/fxu', true),
    ('jareed-n0505@hotmail.com', 'jareed-n0505@hotmail.com', '$2a$12$cvT5dLzgwyPRUKywdYCtXeg2J7SNSyJGAsrhDJjJnuQNiZz.K/fxu', true),
    ('U22232669@utp.edu.pe', 'U22232669@utp.edu.pe', '$2a$12$cvT5dLzgwyPRUKywdYCtXeg2J7SNSyJGAsrhDJjJnuQNiZz.K/fxu', true),
    ('helper1@mail.com', 'helper1@mail.com', '$2a$12$cvT5dLzgwyPRUKywdYCtXeg2J7SNSyJGAsrhDJjJnuQNiZz.K/fxu', true);

INSERT INTO USER_ROLES (id_usuario, id_rol)
VALUES
    (1, 1),
    (1, 2),
    (2, 1),
    (2, 2),
    (3, 1),
    (3, 2),
    (4, 1),
    (4, 2);

INSERT INTO categoria (nom_categoria, estado)
VALUES
    ('Frutas y Verduras', 1),
    ('Carnes y Pescados', 1),
    ('Lácteos y Huevos', 1),
    ('Panadería y Repostería', 1),
    ('Abarrotes', 1),
    ('Bebidas', 1);

INSERT INTO DONADOR (nombre)
VALUES
    ('Luis caceres'),
    ('Jose Morales');

INSERT INTO PRODUCTO(CANTIDAD,DESC_PRODUCTO,ESTADO_PRODUCTO,
FECHA_INGRESO,FECHA_VENCIMIENTO,USUARIO_ID_USUARIO,CATEGORIA_ID,DONADOR_ID_DONADOR)
VALUES
(100,'Lata de atun','Activo','2024-11-01','2024-12-01',1,2,1),
(90,'Bolsa de Arroz de 1kg','Activo','2024-11-01','2024-12-10',1,5,1),
(110,'Lata de durazno','Activo','2024-11-01','2024-12-11',1,1,1),
(50,'Bolsa de lentejas de 1kg','Activo','2024-11-01','2024-12-12',1,5,1);

INSERT INTO DON_MONETARIA (DESC_DON_MONETARIA,MONTO_DONACION,FECHA_DONACION,DONADOR_ID_DONADOR,USUARIO_ID_USUARIO)
VALUES
('Donacion personal Jose',2000.00,'2024-12-01',1,1),
('Donacion personal Piero',12000.00,'2024-12-01',1,1);

INSERT INTO HISTORICO_ALIMENTARIA (CANTIDAD,FECHA_USO,PRODUCTO_ID_PRODUCTO)
VALUES
(100,'2024-12-01',1),
(90,'2024-12-01',2),
(110,'2024-12-01',3),
(50,'2024-12-01',4);

INSERT INTO HISTORICO_DONACION (CANTIDAD,FECHA_USO,DON_MONETARIA_ID_DON_MONETARIA)
VALUES
(2000.00,'2024-12-01',1),
(12000.00,'2024-12-01',2);
