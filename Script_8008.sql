CREATE DATABASE IF NOT EXISTS `BD_80_08_ticketsexchange`;
USE `BD_80_08_ticketsexchange`;

DROP TABLE IF EXISTS `tickets`;
DROP TABLE IF EXISTS `mensaje`;
DROP TABLE IF EXISTS `eventos`;
DROP TABLE IF EXISTS `usuarios`;


CREATE TABLE `usuarios` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL,
  `completeName` varchar(45) NOT NULL,
  `passwd` varchar(45) NOT NULL,
  `direction` varchar(45) NOT NULL, 
  `telf`varchar(45) NOT NULL, 
  `permisos` varchar (45) NOT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `eventos` (
  `idevento` int(11) NOT NULL AUTO_INCREMENT,
  `eventName` varchar(45) NOT NULL,
  `eventCategory` varchar(45) DEFAULT NULL,
  `eventDate` date NOT NULL,
  `eventCity` varchar(45) NOT NULL,
  `eventRoom` varchar(45) NOT NULL,
  `eventPicture` longblob default Null,
  PRIMARY KEY (`idevento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `tickets` (
  `idtickets` int(11) NOT NULL ,
  `idevento` int(11) NOT NULL ,
  `idusuario` int(11) NOT NULL ,
  `numbefOfTickets` int(11) NOT NULL ,
  `ticketsType` varchar(45) NOT NULL,
  `totalCost` int(11) NOT NULL,
  PRIMARY KEY (`idtickets`),
  CONSTRAINT `fk_tickets_idUser` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`idusuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_tickets_idEvent` FOREIGN KEY (`idevento`) REFERENCES `eventos` (`idevento`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `mensaje`(
`idmensaje` int(11) NOT NULL AUTO_INCREMENT,
`texto` varchar(200) NOT NULL,
`receptor` int(11) NOT NULL,
`emisor` int(11) NOT NULL,
PRIMARY KEY (`idmensaje`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


LOCK TABLES `usuarios` WRITE;
INSERT INTO `usuarios` (`idusuario`, `usuario`, `completeName`, `passwd`, `direction`, `telf`, `permisos`) VALUES (6, 'admin', 'super admin', 'admin', 'avenida de san juan 5', '655 200 87', 'admin');
INSERT INTO `usuarios` (`idusuario`, `usuario`, `completeName`, `passwd`, `direction`, `telf`, `permisos`) VALUES (1, 'watchdogo', 'Dalton Waylon', 'secret', 'avenida de san juan 5', '655 25 90 87', 'admin');
INSERT INTO `usuarios` (`idusuario`, `usuario`, `completeName`, `passwd`, `direction`, `telf`, `permisos`) VALUES (2, 'unamed_user', 'John Wayne', 'k_frecuently', 'avenida de san juan 5', '788 25 90 87', 'user');
INSERT INTO `usuarios` (`idusuario`, `usuario`, `completeName`, `passwd`, `direction`, `telf`, `permisos`) VALUES (3, 'dont_tell', 'Rick', 'smith', 'avenida de san juan 5', '744 16 89 50', 'user');
INSERT INTO `usuarios` (`idusuario`, `usuario`, `completeName`, `passwd`, `direction`, `telf`, `permisos`) VALUES (4, 'roxxpole', 'Cyril ', 'love_paper_work', 'avenida de san juan 5', '544 36 88 87', 'user');
INSERT INTO `usuarios` (`idusuario`, `usuario`, `completeName`, `passwd`, `direction`, `telf`, `permisos`) VALUES (5, 'ashnikko', 'Pam ', 'hrpassword', 'avenida de san juan 5', '766 83 53 87', 'user');
UNLOCK TABLES;

LOCK TABLES `eventos` WRITE;
INSERT INTO `eventos` (`idevento`, `eventName`, `eventCategory`, `eventDate`, `eventCity`, `eventRoom`) VALUES (99, 'read festival', 'kpop', '2019-12-17', 'Leads','sala 5');
INSERT INTO `eventos` (`idevento`, `eventName`, `eventCategory`, `eventDate`, `eventCity`, `eventRoom`) VALUES (98, 'read festival', 'jpop', '2018-12-17', 'Leads','sala 5');
INSERT INTO `eventos` (`idevento`, `eventName`, `eventCategory`, `eventDate`, `eventCity`, `eventRoom`) VALUES (97, 'read festival', 'rap', '2012-12-17', 'Leads','sala 5');
UNLOCK TABLES;

LOCK TABLES `tickets` WRITE;
INSERT INTO `tickets` (`idtickets`, `idevento`, `idusuario`, `numbefOfTickets`, `ticketsType`, `totalCost`) VALUES (50, '99', '3', '2', 'anfiteatro','5');
INSERT INTO `tickets` (`idtickets`, `idevento`, `idusuario`, `numbefOfTickets`, `ticketsType`, `totalCost`) VALUES (51, '98', '3', '2', 'anfiteatro','5');
INSERT INTO `tickets` (`idtickets`, `idevento`, `idusuario`, `numbefOfTickets`, `ticketsType`, `totalCost`) VALUES (52, '97', '3', '2', 'anfiteatro','5');
UNLOCK TABLES ;

LOCK TABLES `mensaje` WRITE;
INSERT INTO `mensaje` (`idmensaje`,`texto`,`receptor`,`emisor`) VALUES (1,'Hola','3','2');
INSERT INTO `mensaje` (`idmensaje`,`texto`,`receptor`,`emisor`) VALUES (2,'Hola','2','3');
INSERT INTO `mensaje` (`idmensaje`,`texto`,`receptor`,`emisor`) VALUES (3,'te las compro','3','2');
INSERT INTO `mensaje` (`idmensaje`,`texto`,`receptor`,`emisor`) VALUES (4,'me las rebajas a 10 euros','1','3');
INSERT INTO `mensaje` (`idmensaje`,`texto`,`receptor`,`emisor`) VALUES (5,'NO, no las bajo precio','3','1');
UNLOCK TABLES;

commit;