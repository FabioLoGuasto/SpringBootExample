CREATE DATABASE  IF NOT EXISTS `negozio_scarpe` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `negozio_scarpe`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: negozio_scarpe
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `id_articolo` int NOT NULL AUTO_INCREMENT,
  `codice` varchar(45) DEFAULT NULL,
  `taglia` int DEFAULT '0',
  `negozio_id` int DEFAULT NULL,
  `brand` varchar(45) DEFAULT NULL,
  `categoria` varchar(45) DEFAULT NULL,
  `prezzo` double DEFAULT NULL,
  `sconto` int DEFAULT '0',
  `stagione` varchar(45) DEFAULT NULL,
  `venduto` tinyint DEFAULT '1',
  `fornitore_id` int DEFAULT NULL,
  `transazione_id` int DEFAULT NULL,
  PRIMARY KEY (`id_articolo`),
  KEY `fk_verso_fornitore_idx` (`fornitore_id`),
  KEY `fk_verso_negozio_idx` (`negozio_id`),
  KEY `fk_verso_transaction_idx` (`transazione_id`),
  CONSTRAINT `fk_verso_fornitore` FOREIGN KEY (`fornitore_id`) REFERENCES `supplier` (`cod_fornitore`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_verso_negozio` FOREIGN KEY (`negozio_id`) REFERENCES `shop` (`id_negozio`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_verso_transaction` FOREIGN KEY (`transazione_id`) REFERENCES `transaction` (`id_transazione`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,'A001',35,111,'NIKE','W',59.99,0,'SS23',1,1122,NULL),(2,'A001',36,111,'NIKE','W',59.99,0,'SS23',1,1122,NULL),(3,'A001',37,111,'NIKE','W',59.99,0,'SS23',1,1122,NULL),(4,'A001',38,111,'NIKE','W',59.99,0,'SS23',1,1122,NULL),(5,'A001',39,111,'NIKE','W',59.99,0,'SS23',1,1122,NULL),(6,'A001',40,111,'NIKE','W',59.99,0,'SS23',1,1122,NULL),(7,'A001',35,108,'NIKE','W',59.99,0,'SS23',1,1122,NULL),(8,'A001',36,108,'NIKE','W',59.99,0,'SS23',1,1122,NULL),(9,'A001',38,92,'NIKE','W',59.99,0,'SS23',1,1122,NULL),(10,'A001',40,92,'NIKE','W',59.99,0,'SS23',1,1122,NULL),(11,'A001',41,92,'NIKE','W',59.99,0,'SS23',1,1122,NULL),(12,'A002',18,111,'ADIDAS','K',49.99,0,'SS23',1,1144,NULL),(13,'A002',19,111,'ADIDAS','K',49.99,0,'SS23',1,1144,NULL),(14,'A002',20,111,'ADIDAS','K',49.99,0,'SS23',1,1144,NULL),(15,'A002',24,111,'ADIDAS','K',49.99,0,'SS23',1,1144,NULL),(16,'A002',25,111,'ADIDAS','K',49.99,0,'SS23',1,1144,NULL),(17,'A002',26,111,'ADIDAS','K',49.99,0,'SS23',1,1144,NULL),(18,'A002',20,108,'ADIDAS','K',49.99,0,'SS23',1,1144,NULL),(19,'A002',20,108,'ADIDAS','K',49.99,0,'SS23',1,1144,NULL),(20,'A002',21,108,'ADIDAS','K',49.99,0,'SS23',1,1144,NULL),(21,'A002',24,92,'ADIDAS','K',49.99,0,'SS23',1,1144,NULL),(22,'A002',25,92,'ADIDAS','K',49.99,0,'SS23',1,1144,NULL),(23,'A002',26,92,'ADIDAS','K',49.99,0,'SS23',1,1144,NULL),(24,'A003',40,111,'PUMA','M',55.99,0,'SS23',1,1155,NULL),(25,'A003',41,111,'PUMA','M',55.99,0,'SS23',1,1155,NULL),(26,'A003',43,111,'PUMA','M',55.99,0,'SS23',1,1155,NULL),(27,'A003',46,111,'PUMA','M',55.99,0,'SS23',1,1155,NULL),(28,'A003',40,108,'PUMA','M',55.99,0,'SS23',1,1155,NULL),(29,'A003',40,108,'PUMA','M',55.99,0,'SS23',1,1155,NULL),(30,'A003',40,108,'PUMA','M',55.99,0,'SS23',1,1155,NULL),(31,'A003',41,108,'PUMA','M',55.99,0,'SS23',1,1155,NULL),(32,'A003',42,108,'PUMA','M',55.99,0,'SS23',1,1155,NULL),(33,'A003',44,108,'PUMA','M',55.99,0,'SS23',1,1155,NULL),(34,'A003',40,92,'PUMA','M',55.99,0,'SS23',1,1155,NULL),(35,'A003',41,92,'PUMA','M',55.99,0,'SS23',1,1155,NULL),(36,'A003',41,92,'PUMA','M',55.99,0,'SS23',1,1155,NULL),(37,'A003',44,92,'PUMA','M',55.99,0,'SS23',1,1155,NULL),(38,'A003',45,92,'PUMA','M',55.99,0,'SS23',1,1155,NULL),(39,'A004',35,111,'LUMBERJACK','W',110.99,0,'SS22',1,1166,NULL),(40,'A004',36,111,'LUMBERJACK','W',110.99,0,'SS22',1,1166,NULL),(41,'A004',40,111,'LUMBERJACK','W',110.99,0,'SS22',1,1166,NULL),(42,'A004',35,108,'LUMBERJACK','W',110.99,0,'SS22',1,1166,NULL),(43,'A004',35,108,'LUMBERJACK','W',110.99,0,'SS22',1,1166,NULL),(44,'A004',38,108,'LUMBERJACK','W',110.99,0,'SS22',1,1166,NULL),(45,'A005',39,111,'CARRERA','M',35.99,0,'FW22',1,1111,NULL),(46,'A005',40,111,'CARRERA','M',35.99,0,'FW22',1,1111,NULL),(47,'A005',41,111,'CARRERA','M',35.99,0,'FW22',1,1111,NULL),(48,'A005',46,111,'CARRERA','M',35.99,0,'FW22',1,1111,NULL),(49,'A005',40,108,'CARRERA','M',35.99,0,'FW22',1,1111,NULL),(50,'A005',40,108,'CARRERA','M',35.99,0,'FW22',1,1111,NULL),(51,'A005',40,92,'CARRERA','M',35.99,0,'FW22',1,1111,NULL),(52,'A005',47,92,'CARRERA','M',35.99,0,'FW22',1,1111,NULL),(53,'A006',19,111,'NIKE','K',19.99,0,'SS23',1,1122,NULL),(54,'A006',20,111,'NIKE','K',19.99,0,'SS23',1,1122,NULL),(55,'A006',21,111,'NIKE','K',19.99,0,'SS23',1,1122,NULL),(56,'A006',19,108,'NIKE','K',19.99,0,'SS23',1,1122,NULL),(57,'A006',21,92,'NIKE','K',19.99,0,'SS23',1,1122,NULL),(58,'A007',35,111,'ADIDAS','W',99.99,0,'SS23',1,1144,NULL),(59,'A007',39,111,'ADIDAS','W',99.99,0,'SS23',1,1144,NULL),(60,'A007',39,108,'ADIDAS','W',99.99,0,'SS23',1,1144,NULL),(61,'A007',39,108,'ADIDAS','W',99.99,0,'SS23',1,1144,NULL),(62,'A007',41,92,'ADIDAS','W',99.99,0,'SS23',1,1144,NULL),(63,'A007',41,92,'ADIDAS','W',99.99,0,'SS23',1,1144,NULL),(64,'A007',41,92,'ADIDAS','W',99.99,0,'SS23',1,1144,NULL),(65,'A008',39,111,'ONIX','M',29.99,0,'SS23',1,1144,NULL),(66,'A008',39,111,'ONIX','M',29.99,0,'SS23',1,1144,NULL),(67,'A008',39,111,'ONIX','M',29.99,0,'SS23',1,1144,NULL),(68,'A008',40,111,'ONIX','M',29.99,0,'SS23',1,1144,NULL),(69,'A008',41,111,'ONIX','M',29.99,0,'SS23',1,1144,NULL),(70,'A008',43,111,'ONIX','M',29.99,0,'SS23',1,1144,NULL),(71,'A008',44,108,'ONIX','M',29.99,0,'SS23',1,1144,NULL),(72,'A008',39,108,'ONIX','M',29.99,0,'SS23',1,1144,NULL),(73,'A008',39,108,'ONIX','M',29.99,0,'SS23',1,1144,NULL),(74,'A008',44,108,'ONIX','M',29.99,0,'SS23',1,1144,NULL),(75,'A008',45,108,'ONIX','M',29.99,0,'SS23',1,1144,NULL),(76,'A009',20,111,'OCTOPUS','K',120.99,0,'FW22',1,1133,NULL),(77,'A009',23,108,'OCTOPUS','K',120.99,0,'FW22',1,1133,NULL),(78,'A009',24,108,'OCTOPUS','K',120.99,0,'FW22',1,1133,NULL),(79,'A009',20,108,'OCTOPUS','K',120.99,0,'FW22',1,1133,NULL),(80,'A009',20,108,'OCTOPUS','K',120.99,0,'FW22',1,1133,NULL),(81,'A009',19,92,'OCTOPUS','K',120.99,0,'FW22',1,1133,NULL),(82,'A010',40,92,'CHAMPION','M',39.99,0,'SS22',1,1133,NULL),(83,'A010',43,92,'CHAMPION','M',39.99,0,'SS22',1,1133,NULL),(84,'A010',44,92,'CHAMPION','M',39.99,0,'SS22',1,1133,NULL),(85,'A010',46,92,'CHAMPION','M',39.99,0,'SS22',1,1133,NULL),(86,'A010',47,92,'CHAMPION','M',39.99,0,'SS22',1,1133,NULL);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fidelity_client`
--

DROP TABLE IF EXISTS `fidelity_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fidelity_client` (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `cf` varchar(45) DEFAULT NULL,
  `localita` varchar(45) DEFAULT NULL,
  `provincia` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fidelity_client`
--

LOCK TABLES `fidelity_client` WRITE;
/*!40000 ALTER TABLE `fidelity_client` DISABLE KEYS */;
INSERT INTO `fidelity_client` VALUES (1,'CF0011','MILANO','MI'),(2,'CF0022','LIMBIATE','MB'),(3,'CF0033','LISSONE','MB'),(4,'CF0044','SEREGNO','MB'),(5,'CF0055','SESTO','MI'),(6,'CF0066','ROMA','RM'),(7,'CF0077','ROMA','RM'),(8,'CF0088','BOLZANO','BZ'),(9,'CF0099','BRESCIA','BS'),(10,'CF1100','CURNO','BG');
/*!40000 ALTER TABLE `fidelity_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop` (
  `id_negozio` int NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `localita` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_negozio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (92,'LISSONE','MONZA E BRIANZA'),(108,'SEREGNO','MONZA E BRIANZA'),(111,'LIMBIATE','MONZA E BRIANZA');
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `cod_fornitore` int NOT NULL,
  `ragione_sociale` varchar(45) DEFAULT NULL,
  `nazione` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cod_fornitore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1111,'LOG S.R.L.','ITALIA'),(1122,'ROX S.R.L.','INDONESIA'),(1133,'PROJECT S.PA.','CINA'),(1144,'ASTRO S.R.L.','VIETNAM'),(1155,'OCTO S.P.A.','FRANCIA'),(1166,'POK S.R.L.','INDONESIA');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id_transazione` int NOT NULL AUTO_INCREMENT,
  `numero_tessera` int DEFAULT NULL,
  `cliente_id` int DEFAULT NULL,
  PRIMARY KEY (`id_transazione`),
  KEY `fk_verso_fidelity_client_idx` (`cliente_id`),
  CONSTRAINT `fk_verso_fidelity_client` FOREIGN KEY (`cliente_id`) REFERENCES `fidelity_client` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-31 13:21:54
