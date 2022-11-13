
insert into administrador values (1094899, "jf@uqvirtual.co", "John", "12345");
insert into administrador values (1094599, "Jm@uqvirtual.co", "Julian", "1234");
insert into administrador values (1093845, "Jh@uqvirtual.co", "Juan", "123");
insert into administrador values (1091003, "ch@uqvirtual.co", "Ana", "12345a");
insert into administrador values (1078954, "chil@uqvirtual.co", "Carolina", "12345b");


insert into administrador_teatro values (1091899, "jfmd@uqvirtual.co", "Fredy", "12345a");
insert into administrador_teatro values (1092599, "Jaml@uqvirtual.co", "Andres", "1234b");
insert into administrador_teatro values (1093846, "Jeh@uqvirtual.co", "Esteban", "123c");
insert into administrador_teatro values (1094003, "chap@uqvirtual.co", "Lucia", "12345d");
insert into administrador_teatro values (1075954, "chilin@uqvirtual.co", "Laura", "12345be");


insert into ciudad values (1,"Armenia");
insert into ciudad values (2,"Calarca");
insert into ciudad values (3,"Tebaida");
insert into ciudad values (4,"Montenegro");
insert into ciudad values (5,"Filandia");


insert into cliente values (1078954, "chil@uqvirtual.co", "Carolina", "12345b", 0, "http");
insert into cliente values (1091003, "ch@uqvirtual.co", "Ana", "12345a", 1, "http");
insert into cliente values (1093845, "Jh@uqvirtual.co", "Juan", "123", 0, "http:2");
insert into cliente values (1094599, "Jm@uqvirtual.co", "Julian", "1234", 0, "http:1");
insert into cliente values (1094899, "jfmunozd@uqvirtual.edu.co", "John", "12345", 0, "http:");
insert into cliente values (1004917021, "juajaja@uqvirtual.co", "Juanes", "12345", 0, "http:");



insert into cliente_telefonos values (1094899, "31143122");
insert into cliente_telefonos values (1094599, "31143122");
insert into cliente_telefonos values (1093845, "31143122");
insert into cliente_telefonos values (1078954, "31143122");
insert into cliente_telefonos values (1091003, "31143122");
insert into cliente_telefonos values (1078954, "31143122");



insert into cupon values  (1,"Descuento 10%",0.10,"2022-10-10");
insert into cupon values  (2,"Descuento 20%",0.20,"2022-11-09");
insert into cupon values  (3,"Descuento 15%",0.15,"2022-11-12");
insert into cupon values  (4,"Descuento 18%",0.15,"2022-11-25");
insert into cupon values  (5,"Descuento 21%",0.20,"2022-11-25");



insert into cupon_cliente values (1,0,1094899, 1);
insert into cupon_cliente values (2,0,1094599, 2);
insert into cupon_cliente values (3,0,1078954, 3);
insert into cupon_cliente values (4,0,1094899, 4);
insert into cupon_cliente values (5,0,1078954, 5);




insert into confiteria values (1,"Crispetas", 20000, "url");
insert into confiteria values (2,"Gaseosa", 20000, "url");
insert into confiteria values (3,"Perro caliente", 18000, "url");
insert into confiteria values (4,"Hamburguesa", 23000, "url");
insert into confiteria values (5,"Papas", 15000, "url");



insert into pelicula values  (1,"CARTELERA","worldwest","Evan Rachel Wood, Jeffrey Wright", "Experimentar el Viejo Oeste","https://www.youtube.com/watch?v=VyX0gMULtLM");
insert into pelicula values  (2,"CARTELERA","Halloween: La Noche Final","Jamie Lee Curtis, James Jude Courtney", "Halloween Kills: La Noche Aún No Termina","https://www.cinecolombia.com/bogota/peliculas/halloween-la-noche-final");
insert into pelicula values  (3,"CARTELERA","Black Adam","Dwayne Johnson, Sarah Shahi", "años después de haber sido dotado de los poderes omnipotentes de los antiguos dioses","https://www.cinecolombia.com/bogota/peliculas/black-adam");
insert into pelicula values  (4,"CARTELERA","Fobias","Hana Mae Lee, Leo Nam", "Cinco pacientes peligrosos, que sufren de fobias extremas, son sometidos a prueba","https://www.cinecolombia.com/bogota/peliculas/fobias");
insert into pelicula values  (5,"CARTELERA","Vértigo","Grace Caroline Currey, Virginia Gardner", " Consiste en conquistar los miedos y superar los límites.","https://www.cinecolombia.com/bogota/peliculas/vertigo");

insert into pelicula_imagenes values (1, "http://res.cloudinary.com/dmlzu0bmr/image/upload/v1668369227/unicine/peliculas/w9mchj4mp6hb8jklfq23.jpg", "unicine/peliculas/w9mchj4mp6hb8jklfq23");
insert into pelicula_imagenes values (2, "http://res.cloudinary.com/dmlzu0bmr/image/upload/v1668369227/unicine/peliculas/w9mchj4mp6hb8jklfq23.jpg", "unicine/peliculas/w9mchj4mp6hb8jklfq23");
insert into pelicula_imagenes values (3, "http://res.cloudinary.com/dmlzu0bmr/image/upload/v1668369227/unicine/peliculas/w9mchj4mp6hb8jklfq23.jpg", "unicine/peliculas/w9mchj4mp6hb8jklfq23");
insert into pelicula_imagenes values (4, "http://res.cloudinary.com/dmlzu0bmr/image/upload/v1668369227/unicine/peliculas/w9mchj4mp6hb8jklfq23.jpg", "unicine/peliculas/w9mchj4mp6hb8jklfq23");
insert into pelicula_imagenes values (5, "http://res.cloudinary.com/dmlzu0bmr/image/upload/v1668369227/unicine/peliculas/w9mchj4mp6hb8jklfq23.jpg", "unicine/peliculas/w9mchj4mp6hb8jklfq23");




insert into pelicula_generos values (1,"TERROR");
insert into pelicula_generos values (1,"CIENCIA_FICCION");
insert into pelicula_generos values (1,"ROMANCE");
insert into pelicula_generos values (2,"CIENCIA_FICCION");
insert into pelicula_generos values (5,"NOVELA");


insert into horario values (1,"LMXJVSD","2022-10-06","2022-09-16","15:00");
insert into horario values (2,"XJVSD","2022-10-06","2022-09-16","15:00");
insert into horario values (3,"VSD","2022-10-06","2022-09-16","15:00");
insert into horario values (4,"LMX","2022-09-30","2022-09-10","15:00");
insert into horario values (5,"MXJ","2022-10-10","2022-09-18","15:00");



insert into teatro values (1,"la casa de alguien", "213214",1091899,1);
insert into teatro values (2,"la casa de alguien", "213214",1094003,2);
insert into teatro values (3,"la casa de alguien", "213214",1091899,3);
insert into teatro values (4,"la casa de alguien", "213214",1094003,4);
insert into teatro values (5,"la casa de alguien", "213214",1091899,5);


insert into distribucion_sillas values (1,4,"ruta",4,16);
insert into distribucion_sillas values (2,4,"ruta",4,16);
insert into distribucion_sillas values (3,4,"ruta",4,16);
insert into distribucion_sillas values (4,4,"ruta",4,16);
insert into distribucion_sillas values (5,4,"ruta",4,16);


insert into sala values (1,"XD",1,1);
insert into sala values (2,"XD2",2,2);
insert into sala values (3,"XD3",3,3);
insert into sala values (4,"XD4",4,4);
insert into sala values (5,"XD5",5,5);



insert into funcion values (1,24000, 1,1,1);
insert into funcion values (2,24000, 2,2,2);
insert into funcion values (3,24000, 3,3,3);
insert into funcion values (4,24000, 4,4,4);
insert into funcion values (5,24000, 5,5,5);



insert into compra values (1, "2022-09-18","TARJETA_UNICINE", 35000,1091003,1,1);
insert into compra values (2, "2022-09-10","TARJETA_CREDITO", 34000,1078954,2,2);
insert into compra values (3, "2022-10-18","TARJETA_UNICINE", 33000,1094899,3,3);
insert into compra values (4, "2022-10-28","TARJETA_CREDITO", 31000,1091003,4,4);
insert into compra values (5, "2022-11-08","TARJETA_DEBITO", 30000,1094899,5,5);


insert into compra_confiteria values  (1,23000,2,1,1);
insert into compra_confiteria values  (2,50000,2,2,2);
insert into compra_confiteria values  (3,30000,2,3,3);
insert into compra_confiteria values  (4,26000,2,4,4);
insert into compra_confiteria values  (5,25000,2,5,5);



insert into entrada values (1,2,3,15000,1);
insert into entrada values (2,2,3,25000,2);
insert into entrada values (3,2,3,55000,3);
insert into entrada values (4,2,3,65000,4);
insert into entrada values (5,2,3,25000,5);









