-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.10-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para rios
CREATE DATABASE IF NOT EXISTS `rios` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `rios`;

CREATE TABLE IF NOT EXISTS departamento (
	IdDepartamento INT auto_increment NOT NULL,
	Nombre varchar(100) NULL,
	PRIMARY KEY (`IdDepartamento`)
)
ENGINE=InnoDB
DEFAULT CHARSET=latin1
COLLATE=latin1_swedish_ci
AUTO_INCREMENT=1;

-- Volcando estructura para tabla rios.contaminacion
CREATE TABLE IF NOT EXISTS `contaminacion` (
  `IdRio` INT auto_increment NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `contami` varchar(1000) NOT NULL,
  `IdDepartamento` int(11) NOT NULL,
  PRIMARY KEY (`IdRio`),
  CONSTRAINT fk_departamento
  FOREIGN KEY (`IdDepartamento`) REFERENCES departamento(IdDepartamento)
) ENGINE=InnoDB DEFAULT CHARSET=latin1
AUTO_INCREMENT=1;

DELETE FROM `departamento`;
INSERT INTO `departamento` (`Nombre`) VALUES
	('quiché'),
	('alta verapaz'),

	('baja verapaz'),
	('sololá'),

	('jalapa'),
	('chiquimula'),

	('el progreso'),
	('zacapa'),

	('totonicapán'),
	('retalhuleu'),

	('guatemala'),
	('petén'),

	('huehuetenango'),
	('izabal'),
	
	('chimaltenango'),
	('sacatepéquez'),
	
	('escuintla'),
	('jutiapa'),
	
	('santa rosa'),
	('suchitepéquez'),
	
	('quetzaltenango'),
	('san marcos');


-- Volcando datos para la tabla rios.contaminacion: ~14 rows (aproximadamente)
DELETE FROM `contaminacion`;
INSERT INTO `contaminacion` (`nombre`, `IdDepartamento` , `contami`) VALUES
	('Río Coyolate', 15, '417,000kg de plástico son emitido anualmente'),
	('Río Coyolate', 16, '417,000kg de plástico son emitido anualmente'),
	('Río Coyolate', 17, '417,000kg de plástico son emitido anualmente'),
	('Río Coyolate', 20, '417,000kg de plástico son emitido anualmente'),

	('Río de los Esclavos', 19, 'Uno de los contaminantes más grandes al río es la fuente agrícola, o sea, las aguas,\r\nmieles y pulpa de café'),

	('Río Dulce', 14,'401,000kg de plástico son emitido anualmente'),

	('Río Icán', 20, '365,000kg de plástico son emitido anualmente'),

	('Río Ixcán', 13, 'El rastro municipal contamina al río con heces, sangre y restos de animales'),

	('Río La Pasión', 12, 'La Pasión es una de las tragedias ambientales más importantes del país. En el 2015\r\n químicos fueron vertidos al río, los cuales causaron un envenenamiento de varios\r\n organismos'),

	('Río Las Vacas', 11, '20 mil tonelada van del río las vacas al río Motagua anualmente'),
	('Río María Linda', 17, '1,258,000kg de plástico son emitido anualmente'),
	
	('Río Motagua', 1, '20,000,000 kg de basura fluyen por el río anualmente'),
	('Río Motagua', 2, '20,000,000 kg de basura fluyen por el río anualmente'),
	('Río Motagua', 3, '20,000,000 kg de basura fluyen por el río anualmente'),
	('Río Motagua', 4, '20,000,000 kg de basura fluyen por el río anualmente'),
	('Río Motagua', 5, '20,000,000 kg de basura fluyen por el río anualmente'),
	('Río Motagua', 6, '20,000,000 kg de basura fluyen por el río anualmente'),
	('Río Motagua', 7, '20,000,000 kg de basura fluyen por el río anualmente'),
	('Río Motagua', 8, '20,000,000 kg de basura fluyen por el río anualmente'),
	('Río Motagua', 11, '20,000,000 kg de basura fluyen por el río anualmente'),
	('Río Motagua', 14, '20,000,000 kg de basura fluyen por el río anualmente'),
	('Río Motagua', 15, '20,000,000 kg de basura fluyen por el río anualmente'),

	('Río Nahualate', 20, '523,000kg de plástico son emitido anualmente'),
	
	('Río Naranjo', 21, '552,000kg de plástico son emitido anualmente'),
	('Río Naranjo', 22, '552,000kg de plástico son emitido anualmente'),
	('Río Naranjo', 4, '552,000kg de plástico son emitido anualmente'),
	('Río Naranjo', 9, '552,000kg de plástico son emitido anualmente'),
	('Río Naranjo', 10, '552,000kg de plástico son emitido anualmente'),
	('Río Naranjo', 20, '552,000kg de plástico son emitido anualmente'),

	('Río Paz', 18, 'En el 2016 el río fue contaminado con melaza, la cual causó la muerte de varios\r\n peces'),

	('Río Samalá', 9, '626,000kg de plástico son emitido anualmente'),
	('Río Samalá', 10, '626,000kg de plástico son emitido anualmente'),
	('Río Samalá', 21, '626,000kg de plástico son emitido anualmente'),

	('Río Suchiate', 22, '419,000kg de plástico son emitido anualmente');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
