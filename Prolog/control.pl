/*
E: String que representa el nombre de una trabajador/apellido
S: Entero que representa el salario de un trabajador
S_ID: Entero que representa el identificador del salario que recibe un trabajador
ID_D: Entero que representa el identificador de un departamento de trabajo
*/
%Predicados
/*
employee(E,ID_D,S_ID)
department(ID_D, ***nombre del departamento**)
salary(S_ID, S)
salaryByEmployee(E, S)
expensiveEmployee(E)
rrhEmployee(E)
*/

%Metas
%principales: expensiveEmployee y rrhEmployee

%Clausulas
%Hechos
employee(mcardon,1,5).
employee(treeman,2,3).
employee(chapman,1,2).
employee(claessen,4,1).
employee(petersen,5,8).
employee(cohn,1,7).
employee(duffy,1,9).

department(1,board).
department(2,human_resources).
department(3,production).
department(4,technical_services).
department(5,administration).

salary(1,1000).
salary(2,1500).
salary(3,2000).
salary(4,2500).
salary(5,3000).
salary(6,3500).
salary(7,4000).
salary(8,4500).
salary(9,5000).

%Reglas
salaryByEmployee(E, S) :- employee(E, _, S_ID), salary(S_ID, S).
expensiveEmployee(E) :- salaryByEmployee(E, SAL), SAL >= 3000.
rrhEmployee(E) :- department(ID_D, human_resources), employee(E, ID_D, _).

preguntaFour(D, RI, RS, E) :- department(ID_D, D), employee(E, ID_D,_), salaryByEmployee(E,SAL), SAL < RS, SAL > RI.
inexpensiveEmployee(Empl) :- not(expensiveEmployee(Empl)), 
									employee(Empl,_,_).