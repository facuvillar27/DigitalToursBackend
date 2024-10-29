CREATE TABLE `sys`.`producto` (
  `id_producto` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `descripcion` VARCHAR(150) NULL,
  `categoria` VARCHAR(45) NULL,
  `precio` DECIMAL NULL,
  `imagen` VARCHAR(200) NULL,
  PRIMARY KEY (`id_producto`));