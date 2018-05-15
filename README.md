# AlohAndes2018
Proyecto de sistrans 

## TODO


1. Carga de Datos
   - [ ] Crear tablas csv
   - [ ] Crear archivos loader
   - [ ] Probar SQL Loader

2. Requerimientos Funcionales
   - Iteracion 1
     - [ ] RF1 - Registro de Operadores
     - [ ] RF2 - Registro Propuestas de Alojamiento
     - [ ] RF3- Registro Usuarios
     - [ ] RF4 - Registro Reserva
     - [ ] RF5 - Cancelar Reserva
     - [ ] RF6 - Retirar Oferta 
    - Iteracion 2
      - [ ] RF7 - Registrar reserva colectiva (Usando RF4)
      - [ ] RF8 - Cancelar reserva masiva (Usando RF5)
      - [ ] RF9 - Deshabilitar Oferta de Alojamiento (RF4 y RF5)
      - [ ] RF10 - Rehabilitar Oferta


3. Requerimientos de consulta
   - Iteracion 1
     - [x] RFC1 - Mostrar Dinero recibido por Proveedor
		   - [ ] Eficiencia
     - [x] RFC2 - Mostrar 20 Ofertas Mas populares
		   - [ ] Eficiencia
   - Iteracion 2 
     - [x] RFC3 - Mostrar Indice de Ocupacion por Oferta
	 	   - [ ] Eficiencia
     - [ ] RFC4 - Mostrar Alojamientos en una fecha y con un conjunto de servicios
	  	   - [ ] Eficiencia
     - [ ] RFC5 - Mostrar el Uso para cada tipo de usuario
	  	   - [ ] Eficiencia
     - [ ] RFC6 - Mostrar el uso para un usuario dado
	  	   - [ ] Eficiencia
     - [ ] RFC7 - Analizar la Operacion de AlohAndes
	  	   - [ ] Eficiencia
     - [ ] RFC8 - Encontrar clientes frecuentes
	  	   - [ ] Eficiencia
     - [ ] RFC9 - Encontrar ofertas que no tienen mucha demanda
	  	   - [ ] Eficiencia
   - Iteracion 3
     - [ ] RFC10 - Consultar consumo 
	  	   - [ ] Eficiencia
     - [ ] RFC11 - Consultar consuma v2
	  	   - [ ] Eficiencia
     - [ ] RFC12 - Consultar Funcionamiento
	  	   - [ ] Eficiencia
     - [ ] RFC13 - Consulta buenos clientes
	  	   - [ ] Eficiencia


## Cambios

* Se cambia el parametro nombre en Hotel y Hostal a nombreHotel/nombreHostal, para no confundirlo con el nombre del operador.

* Se remueve la clase AlohAndes de la base de datos y sus tablas relacionadas

* Formato de horario para Hostal es de 24 h

