SELECT extract(year from RESERVAS.FECHALLEGADA) as year, extract(month from RESERVAS.FECHALLEGADA) as month,extract(day from RESERVAS.FECHALLEGADA) as day, OFERTAS.PRECIOESTADIA
FROM RESERVAS, OFERTAS, ALOJAMIENTOS
WHERE RESERVAS.OFERTA=OFERTAS.ID
AND ALOJAMIENTOS.ID = OFERTAS.ALOJAMIENTOID
AND ALOJAMIENTOS.TIPO='Apartamento';

SELECT extract(year from RESERVAS.FECHALLEGADA) as year, sum(OFERTAS.PRECIOESTADIA*RESERVAS.CANTIDADDIAS)AS ingresos, count(*) as numreservas
FROM RESERVAS, OFERTAS, ALOJAMIENTOS
WHERE RESERVAS.OFERTA=OFERTAS.ID
AND ALOJAMIENTOS.ID = OFERTAS.ALOJAMIENTOID
AND ALOJAMIENTOS.TIPO='Apartamento' 
group by extract(year from RESERVAS.FECHALLEGADA);

--Mayor demanda
SELECT * FROM(
SELECT extract(year from RESERVAS.FECHALLEGADA) as year, sum(OFERTAS.PRECIOESTADIA*RESERVAS.CANTIDADDIAS)AS ingresos, count(*) as numreservas
FROM RESERVAS, OFERTAS, ALOJAMIENTOS
WHERE RESERVAS.OFERTA=OFERTAS.ID
AND ALOJAMIENTOS.ID = OFERTAS.ALOJAMIENTOID
AND ALOJAMIENTOS.TIPO='Apartamento' 
group by extract(year from RESERVAS.FECHALLEGADA)
order by count(*) desc)
WHERE rownum<=1;

--Menor ocupacion
SELECT * FROM(
SELECT extract(year from RESERVAS.FECHALLEGADA) as year, sum(OFERTAS.PRECIOESTADIA*RESERVAS.CANTIDADDIAS)AS ingresos, count(*) as numreservas
FROM RESERVAS, OFERTAS, ALOJAMIENTOS
WHERE RESERVAS.OFERTA=OFERTAS.ID
AND ALOJAMIENTOS.ID = OFERTAS.ALOJAMIENTOID
AND ALOJAMIENTOS.TIPO='Apartamento' 
group by extract(year from RESERVAS.FECHALLEGADA)
order by count(*) asc)
WHERE rownum<=1;

--Mayor ingresos
SELECT * FROM(
SELECT extract(year from RESERVAS.FECHALLEGADA) as year, sum(OFERTAS.PRECIOESTADIA*RESERVAS.CANTIDADDIAS)AS ingresos, count(*) as numreservas
FROM RESERVAS, OFERTAS, ALOJAMIENTOS
WHERE RESERVAS.OFERTA=OFERTAS.ID
AND ALOJAMIENTOS.ID = OFERTAS.ALOJAMIENTOID
AND ALOJAMIENTOS.TIPO='Apartamento' 
group by extract(year from RESERVAS.FECHALLEGADA)
order by sum(OFERTAS.PRECIOESTADIA*RESERVAS.CANTIDADDIAS) desc)
WHERE rownum<=1;