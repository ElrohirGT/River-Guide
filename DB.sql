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

DROP TABLE IF EXISTS `departamento`;
CREATE TABLE `departamento` (
	`IdDepartamento` INT auto_increment NOT NULL,
	`Nombre` varchar(100) NULL,
	PRIMARY KEY (`IdDepartamento`)
)
ENGINE=InnoDB
DEFAULT CHARSET=latin1
COLLATE=latin1_swedish_ci
AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `rio`;
CREATE TABLE `rio` (
  `IdRio` INT auto_increment NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  `Contami` varchar(1000) NOT NULL,
  `ExtraInfo` varchar(1000) NOT NULL,
  `IdDepartamento` int(11) NOT NULL,
  PRIMARY KEY (`IdRio`),
  CONSTRAINT fk_departamento
  FOREIGN KEY (`IdDepartamento`) REFERENCES departamento(IdDepartamento)
) ENGINE=InnoDB DEFAULT CHARSET=latin1
AUTO_INCREMENT=1;

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

INSERT INTO `rio` (`Nombre`, `IdDepartamento` , `Contami`, `ExtraInfo`) VALUES
	('Río Coyolate', 15, '417,000kg de plástico son emitido anualmente', 'Coordinación para liberación de 2,500 alevines de \n mojarras nativas (tusa, balcera y prieta) con el propósito de contribuir \n y conservar la biodiversidad acuática.'),
	('Río Coyolate', 16, '417,000kg de plástico son emitido anualmente', 'Coordinación para liberación de 2,500 alevines de \n mojarras nativas (tusa, balcera y prieta) con el propósito de contribuir \n y conservar la biodiversidad acuática.'),
	('Río Coyolate', 17, '417,000kg de plástico son emitido anualmente', 'Coordinación para liberación de 2,500 alevines de \n mojarras nativas (tusa, balcera y prieta) con el propósito de contribuir \n y conservar la biodiversidad acuática.'),
	('Río Coyolate', 20, '417,000kg de plástico son emitido anualmente', 'Coordinación para liberación de 2,500 alevines de \n mojarras nativas (tusa, balcera y prieta) con el propósito de contribuir \n y conservar la biodiversidad acuática.'),

	('Río de los Esclavos', 19, 'Uno de los contaminantes más grandes al río es la fuente agrícola, o sea, las aguas,\r\nmieles y pulpa de café', 'La delegación del Ministerio de Ambiente y Recursos Naturales MARN \n en Santa Rosa, realizó la entrega de 600 árboles de limón criollo a los \n pobladores de aldeas de la cuenca baja de río los Esclavos,\n  con el objetivo de incrementar la cobertura de árboles \n frutales en esa área.'),

	('Río Dulce', 14,'401,000kg de plástico son emitido anualmente', 'se encuentra ubicado dentro del área protegida \"Parque \n Nacional Río Dulce\" el cual protege al ecosistema de Guatemala desde \n el año 1955.'),

	('Río Icán', 20, '365,000kg de plástico son emitido anualmente', 'saneamiento del manto de agua, reforestación y \n recuperación de la biodiversidad.'),

	('Río Ixcán', 13, 'El rastro municipal contamina al río con heces, sangre y restos de animales', '¡Desafortunadamente Nada!'),

	('Río La Pasión', 12, 'La Pasión es una de las tragedias ambientales más importantes del país. En el 2015\r\n químicos fueron vertidos al río, los cuales causaron un envenenamiento de varios\r\n organismos', 'Se ha tratado de firmar acuerdos para combatir la \n contaminación causada por empresas acusadas de la contaminación del \n río.'),

	('Río Las Vacas', 11, '20 mil tonelada van del río las vacas al río Motagua anualmente', 'El Ministerio de Ambiente y Recursos Naturales (MARN) \n anunció que colocará cercas en el río Las Vacas para recolectar desechos \n sólidos y evitar que lleguen al océano.'),
	('Río María Linda', 17, '1,258,000kg de plástico son emitido anualmente', '¡Desafortunadamente Nada!'),
	
	('Río Motagua', 1, '20,000,000 kg de basura fluyen por el río anualmente', 'En la Cuenca del Motagua, se capacitó a 17 932 personas, \n y en lo referente a la reforestación se plantaron 144 mil árboles, para \nlograr la recuperación de 145 hectáreas de zonas degradadas.'),
	('Río Motagua', 2, '20,000,000 kg de basura fluyen por el río anualmente', 'En la Cuenca del Motagua, se capacitó a 17 932 personas, \n y en lo referente a la reforestación se plantaron 144 mil árboles, para \nlograr la recuperación de 145 hectáreas de zonas degradadas.'),
	('Río Motagua', 3, '20,000,000 kg de basura fluyen por el río anualmente', 'En la Cuenca del Motagua, se capacitó a 17 932 personas, \n y en lo referente a la reforestación se plantaron 144 mil árboles, para \nlograr la recuperación de 145 hectáreas de zonas degradadas.'),
	('Río Motagua', 4, '20,000,000 kg de basura fluyen por el río anualmente', 'En la Cuenca del Motagua, se capacitó a 17 932 personas, \n y en lo referente a la reforestación se plantaron 144 mil árboles, para \nlograr la recuperación de 145 hectáreas de zonas degradadas.'),
	('Río Motagua', 5, '20,000,000 kg de basura fluyen por el río anualmente', 'En la Cuenca del Motagua, se capacitó a 17 932 personas, \n y en lo referente a la reforestación se plantaron 144 mil árboles, para \nlograr la recuperación de 145 hectáreas de zonas degradadas.'),
	('Río Motagua', 6, '20,000,000 kg de basura fluyen por el río anualmente', 'En la Cuenca del Motagua, se capacitó a 17 932 personas, \n y en lo referente a la reforestación se plantaron 144 mil árboles, para \nlograr la recuperación de 145 hectáreas de zonas degradadas.'),
	('Río Motagua', 7, '20,000,000 kg de basura fluyen por el río anualmente', 'En la Cuenca del Motagua, se capacitó a 17 932 personas, \n y en lo referente a la reforestación se plantaron 144 mil árboles, para \nlograr la recuperación de 145 hectáreas de zonas degradadas.'),
	('Río Motagua', 8, '20,000,000 kg de basura fluyen por el río anualmente', 'En la Cuenca del Motagua, se capacitó a 17 932 personas, \n y en lo referente a la reforestación se plantaron 144 mil árboles, para \nlograr la recuperación de 145 hectáreas de zonas degradadas.'),
	('Río Motagua', 11, '20,000,000 kg de basura fluyen por el río anualmente', 'En la Cuenca del Motagua, se capacitó a 17 932 personas, \n y en lo referente a la reforestación se plantaron 144 mil árboles, para \nlograr la recuperación de 145 hectáreas de zonas degradadas.'),
	('Río Motagua', 14, '20,000,000 kg de basura fluyen por el río anualmente', 'En la Cuenca del Motagua, se capacitó a 17 932 personas, \n y en lo referente a la reforestación se plantaron 144 mil árboles, para \nlograr la recuperación de 145 hectáreas de zonas degradadas.'),
	('Río Motagua', 15, '20,000,000 kg de basura fluyen por el río anualmente', 'En la Cuenca del Motagua, se capacitó a 17 932 personas, \n y en lo referente a la reforestación se plantaron 144 mil árboles, para \nlograr la recuperación de 145 hectáreas de zonas degradadas.'),

	('Río Nahualate', 20, '523,000kg de plástico son emitido anualmente', '¡Desafortunadamente Nada!'),
	
	('Río Naranjo', 21, '552,000kg de plástico son emitido anualmente', 'La cartera tiene como  prioridad el cuidado de las cuencas \n del país; por ello, se han establecido diferentes mesas técnicas de \n trabajo que definen acciones para su resguardo.'),
	('Río Naranjo', 22, '552,000kg de plástico son emitido anualmente', 'La cartera tiene como  prioridad el cuidado de las cuencas \n del país; por ello, se han establecido diferentes mesas técnicas de \n trabajo que definen acciones para su resguardo.'),
	('Río Naranjo', 4, '552,000kg de plástico son emitido anualmente', 'La cartera tiene como  prioridad el cuidado de las cuencas \n del país; por ello, se han establecido diferentes mesas técnicas de \n trabajo que definen acciones para su resguardo.'),
	('Río Naranjo', 9, '552,000kg de plástico son emitido anualmente', 'La cartera tiene como  prioridad el cuidado de las cuencas \n del país; por ello, se han establecido diferentes mesas técnicas de \n trabajo que definen acciones para su resguardo.'),
	('Río Naranjo', 10, '552,000kg de plástico son emitido anualmente', 'La cartera tiene como  prioridad el cuidado de las cuencas \n del país; por ello, se han establecido diferentes mesas técnicas de \n trabajo que definen acciones para su resguardo.'),
	('Río Naranjo', 20, '552,000kg de plástico son emitido anualmente', 'La cartera tiene como  prioridad el cuidado de las cuencas \n del país; por ello, se han establecido diferentes mesas técnicas de \n trabajo que definen acciones para su resguardo.'),

	('Río Paz', 18, 'En el 2016 el río fue contaminado con melaza, la cual causó la muerte de varios\r\n peces', 'organizar y desarrollar de manera coordinada y priorizada en el \n espacio y en el tiempo, las diferentes acciones y actividades que son \n necesarias, para la gestión de los recursos naturales de la cuenca.'),

	('Río Samalá', 9, '626,000kg de plástico son emitido anualmente', 'El Ministerio de Ambiente y Recursos Naturales (MARN) \n impulsa y da seguimiento a este proyecto. En ese marco, se efectuó un \n taller en el cual participaron usuarios de la cuenca y personal del \n Viceministerio del Agua. La actividad tuvo lugar en Santa Cruz Muluá, \n Retalhuleu.'),
	('Río Samalá', 10, '626,000kg de plástico son emitido anualmente', 'El Ministerio de Ambiente y Recursos Naturales (MARN) \n impulsa y da seguimiento a este proyecto. En ese marco, se efectuó un \n taller en el cual participaron usuarios de la cuenca y personal del \n Viceministerio del Agua. La actividad tuvo lugar en Santa Cruz Muluá, \n Retalhuleu.'),
	('Río Samalá', 21, '626,000kg de plástico son emitido anualmente', 'El Ministerio de Ambiente y Recursos Naturales (MARN) \n impulsa y da seguimiento a este proyecto. En ese marco, se efectuó un \n taller en el cual participaron usuarios de la cuenca y personal del \n Viceministerio del Agua. La actividad tuvo lugar en Santa Cruz Muluá, \n Retalhuleu.'),

	('Río Suchiate', 22, '419,000kg de plástico son emitido anualmente', 'La Delegación de San Marcos del Ministerio de Ambiente \n y Recursos Naturales (MARN), hizo una jornada de limpieza \n en la ribera del río Suchiate, \n fronterizo con México, como parte de las actividades de la campaña \n “Hacé tu parte, no más basura”.');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
