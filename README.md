# AlohAndes2018
Poryecto de sistrans 

CREATE TABLE Clientes(
documento int,
nombre varchar(32) NOT NULL,
vinculo varchar(32),
CONSTRAINT PKcliente PRIMARY KEY (documento),
CONSTRAINT CKvinculo CHECK (vinculo = 'estudiante' OR vinculo ='padre'OR vinculo ='egresado'OR vinculo ='empleado'OR vinculo ='profesor'OR vinculo ='evento'OR vinculo ='profinvitado')
);
