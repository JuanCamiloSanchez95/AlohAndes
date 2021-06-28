# AlohAndes
Proyecto de sistrans 

## TODO


1. Carga de Datos
   - [ ] Crear tablas csv
   - [ ] Crear archivos loader
   - [ ] Probar SQL Loader

2. Requerimientos Funcionales
   - [ ] Iteracion 1
     - [X] RF1 - Registro de Operadores (DE CADA TIPO DE OPERADOR)
     - [X] RF2 - Registro Propuestas de Alojamiento
     - [X] RF3- Registro Usuarios
     - [X] RF4 - Registro Reserva
     - [X] RF5 - Cancelar Reserva
     - [X] RF6 - Retirar Oferta 
    - [ ] Iteracion 2
      - [ ] RF7 - Registrar reserva colectiva (Usando RF4)
      - [ ] RF8 - Cancelar reserva masiva (Usando RF5)
      - [ ] RF9 - Deshabilitar Oferta de Alojamiento (RF4 y RF5) 
      - [ ] RF10 - Rehabilitar Oferta


3. Requerimientos de consulta
   - [ ] Iteracion 1
     - [x] RFC1 - Mostrar Dinero recibido por Proveedor
     - [x] RFC2 - Mostrar 20 Ofertas Mas populares
   - [ ] Iteracion 2 
     - [x] RFC3 - Mostrar Indice de Ocupacion por Oferta
     - [ ] RFC4 - Mostrar Alojamientos en una fecha y con un conjunto de servicios
     - [x] RFC5 - Mostrar el Uso para cada tipo de usuario
     - [x] RFC6 - Mostrar el uso para un usuario dado
     - [x] RFC7 - Analizar la Operacion de AlohAndes
     - [x] RFC8 - Encontrar clientes frecuentes
     - [x] RFC9 - Encontrar ofertas que no tienen mucha demanda
   - [ ] Iteracion 3
     - [ ] RFC10 - Consultar consumo 
  	   - [ ] Eficiencia
     - [ ] RFC11 - Consultar consuma v2
  	   - [ ] Eficiencia
     - [x] RFC12 - Consultar Funcionamiento
  	   - [ ] Eficiencia
     - [ ] RFC13 - Consulta buenos clientes
  	   - [ ] Eficiencia


## Cambios

* Se cambia el parametro nombre en Hotel y Hostal a nombreHotel/nombreHostal, para no confundirlo con el nombre del operador.

* Se añadieron clases para poder diferenciar las habitaciones de  cada alojamiento Hotel/Hostal

* Se remueve la clase AlohAndes de la base de datos y sus tablas relacionadas

* Formato de horario para Hostal es de 24 h

* Se aÃ±ade el atributo Vigente a Reserva, para la cancelacion.

