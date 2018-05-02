SELECT OFERTAS.ID 
FROM OFERTAS,RESERVAS 
WHERE RESERVAS.OFERTA=OFERTAS.ID 
AND ROWNUM <=20 
GROUP BY OFERTAS.ID 
ORDER BY COUNT(OFERTAS.ID) DESC;