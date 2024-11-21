INSERT INTO ROL (name)
VALUES
    ('ADMIN'),
    ('HELPER');

INSERT INTO USERS (username, email, password, enabled)
VALUES
    ('jose@mail.com', 'jose@mail.com', '$2a$12$cvT5dLzgwyPRUKywdYCtXeg2J7SNSyJGAsrhDJjJnuQNiZz.K/fxu', true),
    ('helper1@mail.com', 'helper1@mail.com', '$2a$12$cvT5dLzgwyPRUKywdYCtXeg2J7SNSyJGAsrhDJjJnuQNiZz.K/fxu', true);

INSERT INTO USER_ROLES (id_usuario, id_rol)
VALUES
    (1, 1),
    (1, 2),
    (2, 2);

INSERT INTO PRODUCTO(CANT_PRODUCTO,DESC_PRODUCTO,ESTADO_PRODUCTO,
FECHA_INGRESO,FECHA_VENCIMIENTO,USUARIO_ID_USUARIO)
VALUES(100,'Lata de atun','Activo','2024-11-01','2024-12-01',1);