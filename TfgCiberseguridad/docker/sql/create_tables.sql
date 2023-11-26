
CREATE DATABASE IF NOT EXISTS ciberseguridad;
DROP TABLE IF EXISTS bankAccount;
DROP TABLE IF EXISTS users;



CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     email VARCHAR(255) NOT NULL,
                                     name VARCHAR(255) NOT NULL,
                                     password VARCHAR(255) NOT NULL,
                                     telephone INT NOT NULL,
                                     dni VARCHAR(255) NOT NULL,
                                     rol VARCHAR(255) NOT NULL

);

CREATE TABLE IF NOT EXISTS bankAccount (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           numCuenta VARCHAR(255) UNIQUE,
                                           saldo DOUBLE NOT NULL,
                                           prestamos BOOLEAN NOT NULL,
                                           user_id BIGINT, -- Columna para establecer la relación

    -- Definir la clave foránea para relacionar "user_id" con "users.id"
                                           FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO users (email, name, password, telephone, dni,rol) VALUES
                                                                             ('usuario1@email.com', 'usuario1', 'contraseña1', 123456789, '12345678A','USER'),
                                                                             ('usuario2@email.com', 'usuario2', 'contraseña2', 987654321, '87654321B','USER'),
                                                                             ('alex@email.com','alexitto', 'prueba', 854411245, '87954514B','ADMIN'),
                                                                             ('xCard@email.com','xCard', 'prueba', 535844965, '36521452B','USER'),
                                                                             ('roberto@email.com','SupGAP', 'thresh', 585442142, '51421402N','USER');

INSERT INTO bankAccount (numCuenta, saldo, prestamos, user_id) VALUES
                                                                   ('ES1620804498074734802768', 1000.0, 0, 1),
                                                                   ('ES1702359427418849050124', 2000.0, 1, 1),
                                                                   ('ES4901862260739108972444', 2000.0, 1, 2),
                                                                   ('ES5420384294554089684354', 2000.0, 1, 3),
                                                                   ('ES5520484592275724734974', 500.0, 0, 3),
                                                                   ('ES7201865250519845921273', 500.0, 0, 3),
                                                                   ('ES1002373382267728986136', 500.0, 0, 4),
                                                                   ('ES3920956973527315385883', 500.0, 0, 4);


