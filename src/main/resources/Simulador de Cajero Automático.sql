/**Tablas**/
CREATE TABLE usuario (
	usuario_id BIGINT PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(255) NOT NULL,
	apellido VARCHAR(255) NOT NULL,
	dni VARCHAR(20) NOT NULL UNIQUE,
	email VARCHAR(150) NOT NULL UNIQUE,
	password_hash VARCHAR(255) NOT NULL,
    cuenta_id BIGINT
);
RENAME TABLE usuario TO usuarios;
CREATE TABLE tarjetas(
	tarjeta_id BIGINT PRIMARY KEY auto_increment,
    numero_tarjeta varchar(255) NOT NULL UNIQUE,
    pin_hash varchar(255) NOT NULL,
	fecha_vencimiento DATE NOT NULL,
	estado ENUM("Activa","Bloqueda") NOT NULL,
    /*cuenta_id UNIQUE*/
    cuenta_id BIGINT NOT NULL UNIQUE
);
CREATE TABLE cuentas (
	cuenta_id BIGINT PRIMARY KEY AUTO_INCREMENT,
	usuario_id BIGINT NOT NULL,
	numero_cuenta VARCHAR(255) NOT NULL UNIQUE,
	tipo_cuenta ENUM ("Ahorro","Corriente") NOT NULL,
	saldo DECIMAL(15,2) NOT NULL DEFAULT 0.00
);

/**FOREIGN KEYS**/
ALTER TABLE cuentas
ADD CONSTRAINT fk_usuario_cuentas
FOREIGN KEY(usuario_id) REFERENCES usuario(usuario_id);

ALTER TABLE tarjetas
ADD CONSTRAINT fk_cuenta_tarjetas
FOREIGN KEY(cuenta_id) REFERENCES cuentas(cuenta_id)

/**Inserts**/
INSERT INTO usuario (nombre, apellido, dni, email, password_hash) 
VALUES 
('Juan', 'Perez', '71234567', 'juan.perez@email.com', '$2a$10$eImiTXuWVxjM72fGCLBbOuGwv'),
('Maria', 'Lopez', '72345678', 'maria.lopez@email.com', '$2a$10$86/yGvU6v9F6R7uK9z.Rze34m');
INSERT INTO cuentas (usuario_id, numero_cuenta, tipo_cuenta, saldo) 
VALUES 
(1, '191-98765432-0-01', 'Ahorro', 1500.50),
(1, '191-12345678-0-99', 'Corriente', 500.00),
(2, '191-55443322-0-44', 'Ahorro', 3000.00);
INSERT INTO tarjetas (numero_tarjeta, pin_hash, fecha_vencimiento, estado, cuenta_id) 
VALUES 
('4557880123456789', '1234', '2030-12-31', 'Activa', 1),
('4557880000001111', '4321', '2029-06-15', 'Activa', 2),
('4557889999998888', '0000', '2028-01-01', 'Activa', 3);
select * from tarjetas