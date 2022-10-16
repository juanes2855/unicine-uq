---------- Adminisitrador --------------
insert into Administrador values (1094899, "jf@uqvirtual.co", "John", "12345");
insert into Administrador values (1094599, "Jm@uqvirtual.co", "Julian", "1234");
insert into Administrador values (1093845, "Jh@uqvirtual.co", "Juan", "123");
insert into Administrador values (1091003, "ch@uqvirtual.co", "Ana", "12345a");
insert into Administrador values (1078954, "chil@uqvirtual.co", "Carolina", "12345b");

---------- AdminisitradorTeatro----------
insert into Administrador_teatro values (1091899, "jfmd@uqvirtual.co", "Fredy", "12345a");
insert into Administrador_teatro values (1092599, "Jaml@uqvirtual.co", "Andres", "1234b");
insert into Administrador_teatro values (1093846, "Jeh@uqvirtual.co", "Esteban", "123c");
insert into Administrador_teatro values (1094003, "chap@uqvirtual.co", "Lucia", "12345d");
insert into Administrador_teatro values (1075954, "chilin@uqvirtual.co", "Laura", "12345be");

------------ Ciudad ---------------------
insert into ciudad values (1,"Armenia");
insert into ciudad values (2,"Calarca");
insert into ciudad values (3,"Tebaida");
insert into ciudad values (4,"Montenegro");
insert into ciudad values (5,"Filandia");

--------- Cliente ------------------------
insert into Cliente values (1094899, "jf@uqvirtual.co", "John", "12345", 0, "http:");
insert into Cliente values (1094599, "Jm@uqvirtual.co", "Julian", "1234", 0, "http:1");
insert into Cliente values (1093845, "Jh@uqvirtual.co", "Juan", "123", 0, "http:2");
insert into Cliente values (1091003, "ch@uqvirtual.co", "Ana", "12345a", 1, "http");
insert into Cliente values (1078954, "chil@uqvirtual.co", "Carolina", "12345b", 0, "http");

------------ Cliente_Telefonos ------------
insert into cliente_telefonos values (1094899, "31143122");
insert into cliente_telefonos values (1094599, "31143122");
insert into cliente_telefonos values (1093845, "31143122");
insert into cliente_telefonos values (1078954, "31143122");
insert into cliente_telefonos values (1091003, "31143122");
insert into cliente_telefonos values (1078954, "31143122");

----------- Compra ---------------------

/*insert into compra values (1, 2018-09-08 17:51:04.78,TARJETA_UNICINE, 35000.00,1094899,1,1);
insert into compra values (2);
insert into compra values (3);
insert into compra values (4);
insert into compra values (5);
*/

----------- CompraComfiteria ------------

----------- Comfiteria ------------------
----------- Cupon -----------------------

insert into cupon values  (1,"Descuento 10%",0.10,current_timestamp());
insert into cupon values  (2,"Descuento 20%",0.20,current_timestamp());
insert into cupon values  (3,"Descuento 15%",0.15,current_timestamp());
insert into cupon values  (4,"Descuento 15%",0.15,current_timestamp());
insert into cupon values  (5,"Descuento 20%",0.20,current_timestamp());

------------ CuponCliente ---------------
------------ DistribucionSillas ---------
------------ Entrada --------------------
------------ Funcion --------------------
------------ Horario --------------------
------------ Pelicula -------------------
/*insert into pelicula values  (1,"En cartelera","worldwest","Evan Rachel Wood, Jeffrey Wright", "Experimentar el Viejo Oeste","http:@oeste","https://www.youtube.com/watch?v=VyX0gMULtLM");
insert into pelicula values  (2,"En cartelera","Halloween: La Noche Final","Jamie Lee Curtis, James Jude Courtney", "Halloween Kills: La Noche Aún No Termina","http:@halloween","https://www.cinecolombia.com/bogota/peliculas/halloween-la-noche-final");
insert into pelicula values  (3,"En estreno","Black Adam","Dwayne Johnson, Sarah Shahi", "años después de haber sido dotado de los poderes omnipotentes de los antiguos dioses","http:@black.adam","https://www.cinecolombia.com/bogota/peliculas/black-adam");
insert into pelicula values  (4,"En estreno","Fobias","Hana Mae Lee, Leo Nam", "Cinco pacientes peligrosos, que sufren de fobias extremas, son sometidos a prueba","http:@fobias","https://www.cinecolombia.com/bogota/peliculas/fobias");
insert into pelicula values  (5,"En cartelera","Vértigo","Grace Caroline Currey, Virginia Gardner", " Consiste en conquistar los miedos y superar los límites.","http:@Vertigo","https://www.cinecolombia.com/bogota/peliculas/vertigo");
*/------------ Persona --------------------
------------ Sala -----------------------
------------ Teatro ---------------------







