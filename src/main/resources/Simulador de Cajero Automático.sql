/**Tablas**/
CREATE TABLE usuarios (
	usuario_id BIGINT PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(255) NOT NULL,
	apellido VARCHAR(255) NOT NULL,
	dni VARCHAR(20) NOT NULL UNIQUE,
	email VARCHAR(150) NOT NULL UNIQUE,
	password_hash VARCHAR(255) NOT NULL
);

CREATE TABLE cuentas (
	cuenta_id BIGINT PRIMARY KEY AUTO_INCREMENT,
	usuario_id BIGINT NOT NULL,
	numero_cuenta VARCHAR(255) NOT NULL UNIQUE,
	tipo_cuenta ENUM ("Ahorro","Corriente") NOT NULL,
	saldo DECIMAL(15,2) NOT NULL DEFAULT 0.00
);

/**ALTERS**/
ALTER TABLE cuentas
ADD COLUMN nombre VARCHAR(20) NOT NULL ,
MODIFY COLUMN numero_cuenta VARCHAR(20) NOT NULL UNIQUE;

ALTER TABLE usuarios
MODIFY COLUMN nombre VARCHAR(100) NOT NULL,
MODIFY COLUMN apellido VARCHAR(100) NOT NULL,
MODIFY COLUMN dni VARCHAR(8) NOT NULL UNIQUE;

CREATE TABLE tarjetas(
	tarjeta_id BIGINT PRIMARY KEY auto_increment,
    numero_tarjeta varchar(255) NOT NULL UNIQUE,
    pin_hash varchar(255) NOT NULL,
	fecha_vencimiento DATE NOT NULL,
	estado ENUM("Activa","Bloqueda") NOT NULL,
    /*cuenta_id UNIQUE*/
    cuenta_id BIGINT NOT NULL UNIQUE
);


/**FOREIGN KEYS**/
ALTER TABLE cuentas
ADD CONSTRAINT fk_usuario_cuentas
FOREIGN KEY(usuario_id) REFERENCES usuario(usuario_id);

ALTER TABLE cuentas ADD CONSTRAINT chk_saldo_positivo CHECK (saldo >= 0);

ALTER TABLE tarjetas
ADD CONSTRAINT fk_cuenta_tarjetas
FOREIGN KEY(cuenta_id) REFERENCES cuentas(cuenta_id);

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


/* Limpieza rápida para evitar duplicados si ya ejecutaste los anteriores */
DELETE FROM tarjetas;
DELETE FROM cuentas;
DELETE FROM usuarios;

/* 5 Usuarios con perfiles distintos */
INSERT INTO usuarios (usuario_id, nombre, apellido, dni, email, password_hash) VALUES 
(1, 'Juan', 'Perez', '71234567', 'juan.perez@email.com', '$2a$10$hash1'),
(2, 'Maria', 'Lopez', '72345678', 'maria.lopez@email.com', '$2a$10$hash2'),
(3, 'Carlos', 'Ruiz', '73456789', 'carlos.ruiz@email.com', '$2a$10$hash3'),
(4, 'Ana', 'Gomez', '74567890', 'ana.gomez@email.com', '$2a$10$hash4'),
(5, 'Luis', 'Torres', '75678901', 'luis.torres@email.com', '$2a$10$hash5');

/* 8 Cuentas distribuibles (Juan y Carlos tienen 2 cuentas cada uno) */
INSERT INTO cuentas (usuario_id, numero_cuenta, tipo_cuenta, saldo, nombre) VALUES 
(1, '191-98765432-0-01', 'Ahorro', 1500.50, 'Ahorro Personal'),
(1, '191-12345678-0-99', 'Corriente', 500.00, 'Cuenta Gastos'),
(2, '191-55443322-0-44', 'Ahorro', 3000.00, 'Fondo de Reserva'),
(3, '191-11112222-0-01', 'Ahorro', 10500.00, 'Ahorro Vivienda'),
(3, '191-33334444-0-02', 'Corriente', 250.75, 'Pago de Servicios'),
(4, '191-55556666-0-03', 'Ahorro', 120.00, 'Viaje Cusco'),
(5, '191-77778888-0-04', 'Ahorro', 0.00, 'Cuenta Nueva'),
(2, '191-99990000-0-10', 'Ahorro', 50.00, 'Ahorro Secundario');

/* 8 Tarjetas asociadas a cada cuenta con distintos estados y fechas */
INSERT INTO tarjetas (numero_tarjeta, pin_hash, fecha_vencimiento, estado, cuenta_id) VALUES 
('4557880000007777', 'hash7', '2026-05-20', 'Activa', 7),   -- Vence este año
('4557880000008888', 'hash8', '2031-10-10', 'Activa', 8),
('4557880000009999', 'hash9', '2026-11-15', 'Bloqueda', 9), -- Vence este año pero bloqueada
('4557880000001010', 'hash10', '2027-08-10', 'Activa', 10),
('4557880000011111', 'hash11', '2026-02-01', 'Activa', 11);  -- Vence este año
INSERT INTO cuentas (usuario_id, numero_cuenta, tipo_cuenta, saldo, nombre) 
VALUES (2, '191-44445555-0-44', 'Corriente', 850.00, 'Cuenta Dólares');

/**Consultas**/
/**Filtrar por el Estado, entendiendo que al negocio no le sirve una tarjeta bloqueada para una operación común.**/
select CONCAT(u.nombre," ",u.apellido) as nombre_apellido, c.numero_cuenta,t.numero_tarjeta from
usuarios u 
inner join cuentas c on c.usuario_id=u.usuario_id
inner join tarjetas t on t.cuenta_id=c.cuenta_id
where  t.estado="Activa";

/**Filtrar el nombre del usuario,saldo total, numero de cuentas pero solo de aquellos que tengan más de 2000 soles en total.**/
select u.nombre,sum(c.saldo) as total_saldo,count(c.cuenta_id) as total_cuentas
from usuarios u inner join cuentas c on u.usuario_id=c.usuario_id
group by u.nombre
having sum(c.saldo)>200;

/**Obtener una lista de email, nombre del usuario y la fecha de vencimiento de la tarjeta activas que venzan en el año actual (2026).**/
select u.email,u.nombre,t.fecha_vencimiento
from usuarios u inner join cuentas c on u.usuario_id=c.usuario_id
inner join tarjetas t on c.cuenta_id=t.cuenta_id
where year(t.fecha_vencimiento)=2026 and t.estado="activa";

/**Mostrar el nombre del usuario, su apellido y la cantidad total de cuentas que tiene registradas.**/
select concat(u.nombre," ",u.apellido) as nombre_apellido, count(c.cuenta_id)as total_cuentas
from usuarios u inner join cuentas c on u.usuario_id=c.usuario_id
group by u.usuario_id
having count(c.cuenta_id)>1;

/**Mostrar cuentas sin tarjetas**/
select c.nombre,u.email
from usuarios u inner join cuentas c on u.usuario_id=c.usuario_id
left join tarjetas t on c.cuenta_id=t.cuenta_id
where t.tarjeta_id is null;

/**Procedimientos almacenados: (No es logica de negocio)
Es un bloque de código SQL el cual se puede ejecutar cuantas veces quieras
contiene: logica, parametros, multiples acciones (update, insert, select, etc)
**/
DELIMITER $$
CREATE PROCEDURE sp_transferir_dinero(IN p_cuenta_origen INT,IN p_cuenta_destino INT, IN p_monto DECIMAL(10,2))
BEGIN
	DECLARE v_saldo_origen DECIMAL(10,2);
    DECLARE v_existe_destino INT;
    START TRANSACTION;
    
    /**Obtener saldo de la cuenta origen**/
    /**FOR UPDATE: garantiza que nadie más toque ese saldo en esos milisegundos**/
    SELECT saldo INTO v_saldo_origen FROM cuentas WHERE cuenta_id=p_cuenta_origen FOR UPDATE;
    
    /**Verificar si la cuenta destino existe: como es pk si se repite solo se muestra 1 vez**/
    SELECT COUNT(*) INTO v_existe_destino FROM cuentas WHERE cuenta_id=p_cuenta_destino;
    
    /**Logica Validacion**/
    IF v_saldo_origen<p_monto THEN
    
		/**Si no hay plata, cancelamos todo**/
		ROLLBACK;
        SELECT "ERROR: Saldo insuficiente" AS mensaje;
	ELSEIF v_existe_destino=0 THEN
    
		/**Si la cuenta destino no existe, cancelamos**/
        ROLLBACK;
		
END











