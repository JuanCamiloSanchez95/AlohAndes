load data 
infile 'clientes.csv' "str '\r\n'"
append
into table CLIENTES
fields terminated by ','
OPTIONALLY ENCLOSED BY '"' AND '"'
trailing nullcols
           ( DOCUMENTO,
             NOMBRE CHAR(32),
             VINCULO CHAR(32)
           )
