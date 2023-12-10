CREATE DATABASE IF NOT EXISTS ciberseguridad;
DROP TABLE IF EXISTS bankAccount;
DROP TABLE IF EXISTS users;



CREATE TABLE IF NOT EXISTS users
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    email     VARCHAR(255) NOT NULL,
    name      VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    telephone INT          NOT NULL,
    dni       VARCHAR(255) NOT NULL,
    rol       VARCHAR(255) NOT NULL,
    contador  BIGINT       NOT NULL,
    bloqueado BOOLEAN      NOT NULL

);

CREATE TABLE IF NOT EXISTS bankAccount
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    numCuenta VARCHAR(255) UNIQUE,
    saldo     DOUBLE  NOT NULL,
    prestamos BOOLEAN NOT NULL,
    user_id   BIGINT, -- Columna para establecer la relación

    -- Definir la clave foránea para relacionar "user_id" con "users.id"
    FOREIGN KEY (user_id) REFERENCES users (id)
);

INSERT INTO users (email, name, password, dni, telephone, rol, contador, bloqueado)
VALUES ('usuario1@email.com', 'usuario1', '$2a$12$Ua/cLtECVqKIGC2kJ0bMYe/4mE39iyMftnHeB/SnleO8mdAo3outi', '12345678A',
        123456789, 'USER', 0, false),
       ('usuario2@email.com', 'usuario2', '$2a$12$QQZTmcP5lt83PdSCxtW6E.1qkGJn.vB2A.If.5HqiubYEgW/jptce', '87654321B',
        987654321, 'USER', 0, false),
       ('alex@email.com', 'alexitto', '$2a$12$yFCP2LbUqQLPJKbmmJZ53.RbG/.TidEfqYABMU.rrdSc1FB84b84u', '87954514B',
        854411245, 'ADMIN', 0, false),
       ('alberto.madera@educa.madrid.org', 'Alberto', '$2a$12$XeOMeHCDtlu/ST9aSoKzS.ApLPqaz9JCFYAZcOffdxaJam2I3CinG',
        '36521452B', 535664965, 'ADMIN', 0, false),
       ('test@email.com', 'test', '$2a$12$i0v3N.Kz8V5KWOBf7/ARo.9OrSaNQcLaiYH696ypPtaPR4X3J/HFO', '37451452B',
        537844965, 'USER', 0, false),
       ('pig@email.com', 'Pig', '$2a$12$QfhQbCX7TSGStT.U/pexRe9J4GGwnBOSH5R7/cAGZfi4k0It4KGji', '36321452B', 536544965,
        'USER', 0, false),
       ('teco@email.com', 'Teco', '$2a$12$LS2hOojFtdKfGI.0WhHFtuml5WSpCQw1XqG2dVQzUycIlBXqrIHKC', '32121452C',
        535614965, 'USER', 0, false),
       ('xCard@email.com', 'xCard', '$2a$12$l/du4Bldpo7OEamQGFkEnuCoFff1FrxUmds.H1JnVuq7tMVqQzvr6', '36521462A',
        535145965, 'USER', 0, false),
       ('lara@email.com', 'lara', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '12345678A',
        612345678, 'USER', 0, false),
       ('paco@email.com', 'paco', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '87654321B',
        687654321, 'USER', 0, false),
       ('mila@email.com', 'mila', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '23456789C',
        723456789, 'USER', 0, false),
       ('juan@email.com', 'juan', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '98765432D',
        798765432, 'USER', 0, false),
       ('lucia@email.com', 'lucia', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '34567890E',
        834567890, 'USER', 0, false),
       ('pedro@email.com', 'pedro', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '45678901F',
        945678901, 'USER', 0, false),
       ('sara@email.com', 'sara', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '56789012G',
        156789012, 'USER', 0, false),
       ('carlos@email.com', 'carlos', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '67890123H',
        267890123, 'USER', 0, false),
       ('clara@email.com', 'clara', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '78901234J',
        378901234, 'USER', 0, false),
       ('david@email.com', 'david', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '89012345K',
        489012345, 'USER', 0, false),
       ('ana@email.com', 'ana', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '90123456L',
        590123456, 'USER', 0, false),
       ('luis@email.com', 'luis', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '01234567M',
        601234567, 'USER', 0, false),
       ('marta@email.com', 'marta', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '12345678N',
        712345678, 'USER', 0, false),
       ('raul@email.com', 'raul', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '23456789P',
        823456789, 'USER', 0, false),
       ('lola@email.com', 'lola', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '34567890Q',
        934567890, 'USER', 0, false),
       ('marco@email.com', 'marco', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '45678901R',
        145678901, 'USER', 0, false),
       ('nuria@email.com', 'nuria', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '56789012S',
        256789012, 'USER', 0, false),
       ('jorge@email.com', 'jorge', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '67890123T',
        367890123, 'USER', 0, false),
       ('lucia@email.com', 'lucia', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '78901234V',
        478901234, 'USER', 0, false),
       ('pablo@email.com', 'pablo', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '89012345W',
        589012345, 'USER', 0, false),
       ('eva@email.com', 'eva', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '90123456X',
        690123456, 'USER', 0, false),
       ('alberto@email.com', 'alberto', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '01234567Y',
        701234567, 'USER', 0, false),
       ('laura@email.com', 'laura', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '12345678Z',
        812345678, 'USER', 0, false),
       ('mario@email.com', 'mario', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '23456789A',
        923456789, 'USER', 0, false),
       ('rocio@email.com', 'rocio', '$2a$12$cohTRJ.2OOYdjkiBwpcLueJ27CAMKuV8Vw55PpFgrS/IVoBddq6hK', '34567890B',
        134567890, 'USER', 0, false),
       ('roberto@email.com', 'SupGAP', '$2a$12$4N2HAZh3H3.H8ufJKYPO9eCzJw2qPg8rrNdWQgFUjCJmvY5DAFsxS', '86121402N',
        589842142, 'USER', 0, false);


INSERT INTO bankAccount (numCuenta, saldo, prestamos, user_id)
VALUES ('ES1620804498074734802768', 1000.0, 0, 1),
       ('ES1702359427418849050124', 2000.0, 1, 1),
       ('ES4901862260739108972444', 2000.0, 1, 2),
       ('ES5420384294554089684354', 2000.0, 1, 3),
       ('ES5520484592275724734974', 550.0, 0, 3),
       ('ES7701865250519845921273', 1500.0, 0, 3),
       ('ES1002393382267728986784', 2500.0, 0, 4),
       ('ES1002173382267728986136', 3400.0, 1, 4),
       ('ES1002373382267728986136', 51200.0, 0, 4),
       ('ES1002973382267728986456', 7800.0, 0, 5),
       ('ES1002773382267728986567', 65200.0, 0, 6),
       ('ES1012473382267728986987', 3600.0, 0, 7),
       ('ES1002873382267728986345', 69500.0, 0, 8),
       ('ES1302373382267728986678', 8400.0, 0, 9),
       ('ES1002373382267728986656', 8900.0, 1, 10),
       ('ES1002373382267728986344', 9870.0, 0, 11),
       ('ES1002374382267728986567', 6900.0, 0, 12),
       ('ES1082373382267728986412', 7800.0, 1, 13),
       ('ES1002373382267728986258', 65400.0, 0, 14),
       ('ES1322373382267728986159', 6500.0, 0, 15),
       ('ES1002373382267728986964', 8570.0, 1, 16),
       ('ES1054373382267728986462', 87740.0, 0, 17),
       ('ES2002373382267728986197', 9800.0, 0, 18),
       ('ES3002373382267728986347', 870.0, 1, 19),
       ('ES3920956973527315385883', 580.0, 0, 20);


