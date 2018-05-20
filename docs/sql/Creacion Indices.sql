
CREATE INDEX semanas_idx ON RESERVAS (TO_CHAR(FECHALLEGADA,'IW'));

CREATE INDEX fechallegada_idx ON RESERVAS (FECHALLEGADA);

CREATE INDEX precioestadia_idx ON OFERTAS (PRECIOESTADIA);


DROP INDEX semanas_idx;

DROP INDEX fechallegada_idx;

DROP INDEX precioestadia_idx;