---------- Adminisitrador --------------
insert into administrador values (1094899, "jf@uqvirtual.co", "John", "12345");
insert into administrador values (1094599, "Jm@uqvirtual.co", "Julian", "1234");
insert into administrador values (1093845, "Jh@uqvirtual.co", "Juan", "123");
insert into administrador values (1091003, "ch@uqvirtual.co", "Ana", "12345a");
insert into administrador values (1078954, "chil@uqvirtual.co", "Carolina", "12345b");

---------- AdminisitradorTeatro----------
insert into administrador_teatro values (1091899, "jfmd@uqvirtual.co", "Fredy", "12345a");
insert into administrador_teatro values (1092599, "Jaml@uqvirtual.co", "Andres", "1234b");
insert into administrador_teatro values (1093846, "Jeh@uqvirtual.co", "Esteban", "123c");
insert into administrador_teatro values (1094003, "chap@uqvirtual.co", "Lucia", "12345d");
insert into administrador_teatro values (1075954, "chilin@uqvirtual.co", "Laura", "12345be");

------------ Ciudad ---------------------
insert into ciudad values (1,"Armenia");
insert into ciudad values (2,"Calarca");
insert into ciudad values (3,"Tebaida");
insert into ciudad values (4,"Montenegro");
insert into ciudad values (5,"Filandia");

--------- Cliente ------------------------
insert into cliente values (1094899, "jf@uqvirtual.co", "John", "12345", 0, "http:");
insert into cliente values (1094599, "Jm@uqvirtual.co", "Julian", "1234", 0, "http:1");
insert into cliente values (1093845, "Jh@uqvirtual.co", "Juan", "123", 0, "http:2");
insert into cliente values (1091003, "ch@uqvirtual.co", "Ana", "12345a", 1, "http");
insert into cliente values (1078954, "chil@uqvirtual.co", "Carolina", "12345b", 0, "http");

------------ Cliente_Telefonos ------------
insert into cliente_telefonos values (1094899, "31143122");
insert into cliente_telefonos values (1094599, "31143122");
insert into cliente_telefonos values (1093845, "31143122");
insert into cliente_telefonos values (1078954, "31143122");
insert into cliente_telefonos values (1091003, "31143122");
insert into cliente_telefonos values (1078954, "31143122");

----------- Cupon -----------------------

insert into cupon values  (1,"Descuento 10%",0.10,current_timestamp());
insert into cupon values  (2,"Descuento 20%",0.20,current_timestamp());
insert into cupon values  (3,"Descuento 15%",0.15,current_timestamp());
insert into cupon values  (4,"Descuento 15%",0.15,current_timestamp());
insert into cupon values  (5,"Descuento 20%",0.20,current_timestamp());


------------ CuponCliente ---------------
insert into cupon_cliente values (1,0,1094899, 1);
insert into cupon_cliente values (2,0,1094599, 2);
insert into cupon_cliente values (3,0,1078954, 3);
insert into cupon_cliente values (4,0,1094899, 4);
insert into cupon_cliente values (5,0,1078954, 5);

----------- Comfiteria ------------------

insert into confiteria values (1,"Crispetas", 20000, "url");
insert into confiteria values (2,"Gaseosa", 20000, "url");
insert into confiteria values (3,"Perro caliente", 18000, "url");
insert into confiteria values (4,"Hamburguesa", 23000, "url");
insert into confiteria values (5,"Papas", 15000, "url");

----------- Pelicula ------------------

insert into pelicula values  (1,"En cartelera","worldwest","Evan Rachel Wood, Jeffrey Wright", "Experimentar el Viejo Oeste","http:@oeste","https://www.youtube.com/watch?v=VyX0gMULtLM");
insert into pelicula values  (2,"En cartelera","Halloween: La Noche Final","Jamie Lee Curtis, James Jude Courtney", "Halloween Kills: La Noche Aún No Termina","http:@halloween","https://www.cinecolombia.com/bogota/peliculas/halloween-la-noche-final");
insert into pelicula values  (3,"En estreno","Black Adam","Dwayne Johnson, Sarah Shahi", "años después de haber sido dotado de los poderes omnipotentes de los antiguos dioses","http:@black.adam","https://www.cinecolombia.com/bogota/peliculas/black-adam");
insert into pelicula values  (4,"En estreno","Fobias","Hana Mae Lee, Leo Nam", "Cinco pacientes peligrosos, que sufren de fobias extremas, son sometidos a prueba","http:@fobias","https://www.cinecolombia.com/bogota/peliculas/fobias");
insert into pelicula values  (5,"En cartelera","Vértigo","Grace Caroline Currey, Virginia Gardner", " Consiste en conquistar los miedos y superar los límites.","http:@Vertigo","https://www.cinecolombia.com/bogota/peliculas/vertigo");

----------- Pelicula Genero ---------------------

insert into pelicula_generos values (1,"TERROR");
insert into pelicula_generos values (1,"CIENCIA_FICCION");
insert into pelicula_generos values (1,"ROMANCE");
insert into pelicula_generos values (2,"CIENCIA_FICCION");
insert into pelicula_generos values (5,"NOVELA");

------------ Horario --------------------
insert into horario values (1,17,current_timestamp(),current_timestamp(),"15:00");
insert into horario values (2,18,current_timestamp(),current_timestamp(),"15:00");
insert into horario values (3,19,current_timestamp(),current_timestamp(),"15:00");
insert into horario values (4,20,current_timestamp(),current_timestamp(),"15:00");
insert into horario values (5,21,current_timestamp(),current_timestamp(),"15:00");

------------ Teatro ---------------------

insert into teatro values (1,"la casa de alguien", "213214",1091899,1);
insert into teatro values (2,"la casa de alguien", "213214",1094003,2);
insert into teatro values (3,"la casa de alguien", "213214",1091899,3);
insert into teatro values (4,"la casa de alguien", "213214",1094003,4);
insert into teatro values (5,"la casa de alguien", "213214",1091899,5);

------------ DistribucionSillas ---------

insert into distribucion_sillas values (1,4,"esquema",4,16);
insert into distribucion_sillas values (2,4,"esquema",4,16);
insert into distribucion_sillas values (3,4,"esquema",4,16);
insert into distribucion_sillas values (4,4,"esquema",4,16);
insert into distribucion_sillas values (5,4,"esquema",4,16);

------------ Sala -----------------------

insert into sala values (1,"xd",1,1);
insert into sala values (2,"xd2",2,2);
insert into sala values (3,"xd3",3,3);
insert into sala values (4,"xd4",4,4);
insert into sala values (5,"xd5",5,5);

------------ Funcion --------------------

insert into funcion values (1,24000, 1,1,1);
insert into funcion values (2,24000, 2,2,2);
insert into funcion values (3,24000, 3,3,3);
insert into funcion values (4,24000, 4,4,4);
insert into funcion values (5,24000, 5,5,5);


----------- Compra ---------------------

insert into compra values (1, current_timestamp(),"TARJETA_UNICINE", 35000,1091003,1,1);
insert into compra values (2, current_timestamp(),"TARJETA_CREDITO", 35000,1078954,2,2);
insert into compra values (3, current_timestamp(),"TARJETA_UNICINE", 35000,1094899,3,3);
insert into compra values (4, current_timestamp(),"TARJETA_CREDITO", 35000,1091003,4,4);
insert into compra values (5, current_timestamp(),"TARJETA_DEBITO", 35000,1094899,5,5);



----------- CompraComfiteria ------------

---




------------ Entrada --------------------











