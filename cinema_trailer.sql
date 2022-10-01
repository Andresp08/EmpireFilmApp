-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.28 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para cinema_trailer
CREATE DATABASE IF NOT EXISTS `cinema_trailer` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cinema_trailer`;

-- Volcando estructura para tabla cinema_trailer.genero
CREATE TABLE IF NOT EXISTS `genero` (
  `idgenero` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(30) NOT NULL,
  PRIMARY KEY (`idgenero`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

-- Volcando datos para la tabla cinema_trailer.genero: ~11 rows (aproximadamente)
REPLACE INTO `genero` (`idgenero`, `titulo`) VALUES
	(1, 'Comedia'),
	(2, 'Fantasia'),
	(3, 'Acción'),
	(4, 'Aventura'),
	(5, 'Terror'),
	(6, 'Sci-Fi'),
	(7, 'Crimen'),
	(8, 'Drama'),
	(9, 'Misterio'),
	(10, 'Suspenso');

-- Volcando estructura para tabla cinema_trailer.generopelicula
CREATE TABLE IF NOT EXISTS `generopelicula` (
  `idpelicula` int NOT NULL,
  `idgenero` int NOT NULL,
  KEY `fk_genpel_pelicula_idx` (`idpelicula`),
  KEY `fk_genpel_genero_idx` (`idgenero`),
  CONSTRAINT `fk_genpel_genero` FOREIGN KEY (`idgenero`) REFERENCES `genero` (`idgenero`),
  CONSTRAINT `fk_genpel_pelicula` FOREIGN KEY (`idpelicula`) REFERENCES `pelicula` (`idpelicula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Volcando datos para la tabla cinema_trailer.generopelicula: ~1 rows (aproximadamente)
REPLACE INTO `generopelicula` (`idpelicula`, `idgenero`) VALUES
	(3, 9),
	(3, 10),
	(13, 10),
	(14, 3),
	(14, 10),
	(15, 4),
	(15, 1),
	(16, 1),
	(16, 2),
	(16, 5),
	(17, 1),
	(17, 8);

-- Volcando estructura para tabla cinema_trailer.pelicula
CREATE TABLE IF NOT EXISTS `pelicula` (
  `idpelicula` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(250) NOT NULL,
  `sinopsis` varchar(1000) NOT NULL,
  `fecha_estreno` date NOT NULL,
  `youtube_trailer_id` varchar(30) NOT NULL,
  `ruta_portada` varchar(250) NOT NULL,
  PRIMARY KEY (`idpelicula`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;

-- Volcando datos para la tabla cinema_trailer.pelicula: ~2 rows (aproximadamente)
REPLACE INTO `pelicula` (`idpelicula`, `titulo`, `sinopsis`, `fecha_estreno`, `youtube_trailer_id`, `ruta_portada`) VALUES
	(3, 'Titanic', 'Jack es un joven artista que gana un pasaje para viajar a América en el Titanic, el transatlántico más grande y seguro jamás construido. A bordo del buque conoce a Rose, una chica de clase alta que viaja con su madre y su prometido Cal, un millonario engreído a quien solo interesa el prestigio de la familia de su prometida. Jack y Rose se enamoran a pesar de las trabas que ponen la madre de ella y Cal en su relación. Mientras, el lujoso transatlántico se acerca a un inmenso iceberg.', '2022-09-01', 's', 'titanic.jpg'),
	(13, ' Lethal Love Letter (2021)', 'Una mujer de negocios exitosa debe determinar quién está tratando de sabotear su vida antes de que lo pierda todo para siempre..', '2021-06-17', 'prueba', 'letahl.jpg'),
	(14, 'An Organized Killer', 'La vida de Grace comienza a desmoronarse cuando una mujer con intenciones siniestras se muda a su casa de huéspedes..', '2021-12-03', 'link', 'anorg.jpg'),
	(15, 'Padre no hay más que uno 3', 'Se acercan las Navidades. Los niños rompen accidentalmente una figurilla del Belén de colección de su padre y deben conseguir por todos los medios una igual, el problema es que es una pieza única de anticuario. Sara, la hija mayor rompe con su novio, Ocho, que intentará recuperar sus favores con la ayuda de su suegro, Javier. Precisamente el suegro de Javier, el padre de Marisa, será acogido en la casa familiar para pasar las fiestas tras su reciente separación, lo cual no dejará indiferente a la madre de Javier, Milagros. Rocío, la folclórica de la familia, que hacía de Virgen desde hace varias Navidades, es relegada este año a hacer de pastorcilla, algo que su padre, Javier, no está dispuesto a asumir..', '2022-07-15', 'link', 'padre.jpg'),
	(16, 'El retorno de las brujas 2', 'Han pasado 29 años desde que alguien encendió la Vela de la Llama Negra y resucitó a las hermanas Sanderson del siglo XVII, y ahora buscan venganza. De tres estudiantes de secundaria depende impedir que las voraces brujas causen un nuevo tipo de estrago en Salem antes del amanecer el día de Halloween..', '2022-09-27', 'link', 'brujas.jpg'),
	(17, 'Yo me encargo de la cerveza', 'Chickie (Zac Efron) quiere apoyar a sus amigos que están peleando en Vietnam, así que hace algo loco: él mismo les lleva cerveza estadounidense. Lo que comienza como un viaje bien intencionado, cambia la vida y la perspectiva de Chickie. Basada en una historia real..', '2022-09-30', 'link', 'cerveza.jpg');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
