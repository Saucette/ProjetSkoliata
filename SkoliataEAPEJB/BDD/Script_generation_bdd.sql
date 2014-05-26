-- Titre :             Cr�ation base skoliata.sql
-- Version :           1.1
-- Date cr�ation :     29.04.11
-- Date modification : 05.05.11
-- Auteur :            Collet Hugo
-- Description :       Script de cr�ation de la base de donn�es pour le SI "skoliata"
--                     Note : script pour PostgreSQL 8.4
--                     Ebauche du script : premier jet pour la cr�ation de la bdd.

-- +----------------------------------------------------------------------------------------------+
-- | Suppression des tables                                                                       |
-- +----------------------------------------------------------------------------------------------+
drop table if exists critere CASCADE;
drop table if exists grille CASCADE;
drop table if exists enseignement CASCADE;
drop table if exists enseignant CASCADE;
drop table if exists eleve CASCADE;
drop table if exists objectif_pedagogique CASCADE;
drop table if exists ASSO_3E CASCADE;
drop table if exists ASSO_OGE CASCADE;
drop table if exists DROIT CASCADE;

-- +----------------------------------------------------------------------------------------------+
-- | Cr�ation des tables                                                                          |
-- +----------------------------------------------------------------------------------------------+

CREATE TABLE enseignant
(
    id serial primary key,
    nom varchar(50) not null,
    prenom varchar(50) not null,
    adresse_mail varchar(50)
) ;

CREATE TABLE eleve
(
	id serial primary key,
    nom varchar(50) not null,
    prenom varchar(50) not null,
    adresse_mail varchar(50)
) ;

CREATE TABLE objectif_pedagogique
(
   id serial primary key,
   description text
) ; 

CREATE TABLE enseignement
(
	id serial primary key,
	nom varchar(100) not null
) ;

-- M�thode d'auto-g�n�ration des noms des grilles � d�terminer avec Mayte

CREATE TABLE grille
(
    id serial primary key,
    nom text not null,
    valide boolean,
    niveau_performance_1 varchar(50) not null, 	
    niveau_performance_2 varchar(50) not null, 	
    niveau_performance_3 varchar(50),			
    niveau_performance_4 varchar(50) 			
) ;

-- La description du niveau de performance X de la table crit�re correspond au niveau de performance X de la table grille
-- Exemple : desc_niveau_performance 1 = "L'�tudiant a rendu le projet termin� � 100%" -- niveau_performance_1 = "Tr�s Bien"

CREATE TABLE critere 
(
	id serial primary key,
	id_grille integer not null references grille,	 -- ID de la grille � laquelle appartient le crit�re
	description text,								 -- Description g�n�rale du crit�re (d�signation)
	desc_niveau_performance_1 varchar(500) not null, -- Description des niveaux de performances
	desc_niveau_performance_2 varchar(500) not null,
	desc_niveau_performance_3 varchar(500),
	desc_niveau_performance_4 varchar(500) 
) ;

-- Cette table repr�sente l'association entre un objectif p�dagogique, une grille, et un enseignement

CREATE TABLE ASSO_OGE 
(
    id_OP    integer not null references objectif_pedagogique,
	id_grille integer not null references grille,
    id_enseignement   integer not null references enseignement,
    primary key (id_grille, id_enseignement, id_OP)
) ;
  

-- Cette table repr�sente l'association entre un �l�ve, une enseignant, et un enseignement

CREATE TABLE ASSO_3E
 (
    id_enseignement integer not null references enseignement,
    id_enseignant   integer not null references enseignant,
    id_eleve        integer not null references eleve,
    primary key (id_enseignant, id_enseignement, id_eleve)
 ) ;
 
 -- Par d�faut : un enseignant quelconque poss�de un droit de lecture sur toutes les grilles,
-- On ne repr�sente dans cette table uniquement les droits autres que le droit de lecture par d�faut
-- Id est, un droit global qui comprend : modification, suppression...etc.

CREATE TABLE DROIT
(
	id_enseignant integer not null references enseignant,
	id_grille integer not null references grille,
	description text,
	primary key (id_enseignant,id_grille)
) ;
-- +----------------------------------------------------------------------------------------------+
-- | Insertion de quelques donn�es de pour les tests                                              |
-- +----------------------------------------------------------------------------------------------+

 -- ENSEIGNANT -------------------------------------------------------------------------------------------
 
insert into enseignant values (nextval('enseignant_id_seq'), 'Tim', 'Johnson', 'tim.johnson@gmail.com' );
insert into enseignant values (nextval('enseignant_id_seq'), 'Maria', 'Fourrier', 'maria.fourrier@yahoo.com' );
insert into enseignant values (nextval('enseignant_id_seq'), 'Edward', 'Snowden', 'eddieisinjail@free.fr' );
insert into enseignant values (nextval('enseignant_id_seq'), 'Pierre', 'Rubini', 'PR-UBO@univ-brest.fr' );
 
-- ELEVE ------------------------------------------------------------------------------------------------

insert into eleve values (nextval('eleve_id_seq'), 'Baptiste', 'Bakous', 'bbisdabest@gmail.com' );
insert into eleve values (nextval('eleve_id_seq'), 'Baptiste', 'Perroud', 'bbisdabest1@gmail.com' );
insert into eleve values (nextval('eleve_id_seq'), 'Alexandre', 'Bakous', 'bbisdabest2@gmail.com' );
insert into eleve values (nextval('eleve_id_seq'), 'Julia', 'Frasson', 'flower@gmail.com' );
 
 
  -- OBJECTIF PEDAGOGIQUE -------------------------------------------------------------------------------

insert into objectif_pedagogique values (nextval('objectif_pedagogique_id_seq'), 'Etre capable de d�velopper une application en Java');
insert into objectif_pedagogique values (nextval('objectif_pedagogique_id_seq'), 'Savoir se pr�senter lors d''un entretien d''embauche');
insert into objectif_pedagogique values (nextval('objectif_pedagogique_id_seq'), 'R�aliser une r�animation d''une personne en arr�t cardio-respiratoire');
insert into objectif_pedagogique values (nextval('objectif_pedagogique_id_seq'), 'Conna�tre le contenu obligatoire d''un sac de secourisme');
  
  -- ENSEIGNEMENT -------------------------------------------------------------------------------------------

insert into enseignement values (nextval('enseignement_id_seq'), 'Genie logiciel et programmation objet et concurrente');
insert into enseignement values (nextval('enseignement_id_seq'), 'Bases de donn�es');
insert into enseignement values (nextval('enseignement_id_seq'), 'Secourisme');
insert into enseignement values (nextval('enseignement_id_seq'), 'Pr�paration � la vie professionnelle');
  
  -- GRILLE -------------------------------------------------------------------------------------------------
  
insert into grille values (nextval('grille_id_seq'), 'Evaluation g�n�rale de Java',FALSE,'Bien', 'Assez Bien', 'Moyen','Insuffisant');
insert into grille values (nextval('grille_id_seq'), 'Evaluation g�n�rale de projet BDD',FALSE,'Bien', 'Moyen', 'Insuffisant');
insert into grille values (nextval('grille_id_seq'), 'Evaluation pratique de secourisme',FALSE,'Acquis', 'Non-acquis');
 
  -- CRITERE -------------------------------------------------------------------------------------------------
 
insert into critere values (nextval('critere_id_seq'), '1','Respecter les d�lais de livraison', 
'La livraison a �t� faite avant la date limite',
'La livraison a �t� faite apr�s la date limite mais soit l''�tudiant peut justifier le retard soit le retard n''est pas significatif',
'',
'La livraison a �t� faite bien apr�s la date limite sans justification'
);
 
insert into critere values (nextval('critere_id_seq'), '1', 'Prendre en compte les remarques', 
'Toutes les remarques du tuteur sont prises en compte. L''�tudiant demande des explications suppl�mentaires',
'Les remarques sont prises en compte sans interaction avec le tuteur',
'Certaines remarques n�cessitant peu d''effort sont prises en compte. Les autres sont ignor�es',
'Aucune remarque n''est prise en compte'
);
 
insert into critere values (nextval('critere_id_seq'), '1','G�rer les versions de la documentation',
'Tous les documents contiennent les informations suivantes : nom du document, nom des auteurs, date de r�alisation, num�ro de version et num�ration des pages. La pr�sentation de ces informations respecte un canevas uniforme',
'Tous les documents contiennent au minimum les informations suivantes : nom du document, nom des auteurs, date de r�alisation. La pr�sentation de ces informations souvent ne respecte pas un canevas uniforme',
'Certains documents (hormis le CdC) contiennent au minimum les informations suivantes : nom du document, nom des auteurs. La pr�sentation de ces informations ne respecte pas un canevas uniforme',
'Aucun document ne contient les informations n�cessaires pour le suivi et le versionning des documents'
);
 
insert into critere values (nextval('critere_id_seq'), '1', 'Trouver la couverture fonctionnelle � partir des indications du client', 
'Toutes les fonctions explicit�es par le client sont pr�sentes',
'La majorit� des fonctions explicit�es par le client sont pr�sentes',
'Une partie des fonctions explicit�es par le client sont pr�sentes',
'Tr�s peu des fonctions explicit�es par le client sont pr�sentes'
);

insert into critere values (nextval('critere_id_seq'), '2','Respecter les d�lais de livraison', 
'La livraison a �t� faite avant la date limite',
'La livraison a �t� faite apr�s la date limite mais soit l''�tudiant peut justifier le retard soit le retard n''est pas significatif',
'La livraison a �t� faite bien apr�s la date limite sans justification',
''
);

insert into critere values (nextval('critere_id_seq'), '2','G�n�rer le sch�ma th�orique', 
'L''�tudiant a propos� un choix de conception du mod�le th�orique coh�rent r�pondant � toutes les attentes du client ',
'L''�tudiant a propos� une solution perfectible ou incompl�te ',
'L''�tudiant a propos� une solution insuffisante qui ne permet pas de passer en phase de d�veloppement sereinement',
''
);

insert into critere values (nextval('critere_id_seq'), '2','G�rer les versions de la documentation',
'Tous les documents contiennent les informations suivantes : nom du document, nom des auteurs, date de r�alisation, num�ro de version et num�ration des pages. La pr�sentation de ces informations respecte un canevas uniforme',
'Tous les documents contiennent au minimum les informations suivantes : nom du document, nom des auteurs, date de r�alisation. La pr�sentation de ces informations souvent ne respecte pas un canevas uniforme',
'Aucun document ne contient les informations n�cessaires pour le suivi et le versionning des documents',
''
);

insert into critere values (nextval('critere_id_seq'), '3','Faire une r�animation cardiaque',
'L''apprenti secouriste sait prodiguer les gestes appropri�s � la r�animation d''une personne en arr�t cardio-respiratoire sans interruption, h�sitation',
'L''apprenti secouriste fait une erreur majeure qui peut conduire � la mort de la victime, et/ou h�site trop lors de l''execution de la proc�dure',
'',
''
);

insert into critere values (nextval('critere_id_seq'), '3','H�morragie externe',
'L''apprenti secouriste utilise le coussin h�mostatique et a le bon r�flexe d''arr�ter l''h�morragie rapidement en rassurant la victime et en lui administrant de l''oxyg�ne',
'L''apprenti secouriste n''utilise pas le coussin, n''utilise pas d''O2 et attend trop avant de s''occuper de la plaie',
'',
''
);

insert into critere values (nextval('critere_id_seq'), '3','Malaise breton',
'L''apprenti secouriste met la victime en PLS, prend les constantes r�guli�rement, rassure la victime et v�rifie que la victime ne s''�touffe pas si elle vomit',
'L''apprenti secouriste ne rassure pas ou peu la victime, oublie la PLS, laisse s''�touffer la victime dans son vomi, ne prend pas les constantes',
'',
''
);

-- ASSO 3E -------------------------------------------------------------------------------------------------

insert into ASSO_3E values ('1','1','1'); -- Tim est responsable de l'enseignement "Genie logiciel et programmation objet et concurrente" et donne
insert into ASSO_3E values ('1','1','2'); -- des cours au groupe d'�l�ves "Baptiste Bakous, Baptiste Perroud, Alexandre Bakous"
insert into ASSO_3E values ('1','1','3');

-- ASSO OGE -------------------------------------------------------------------------------------------------

insert into ASSO_OGE values ('1','1','1'); -- Java - Grille 1 - G�nie logiciel
insert into ASSO_OGE values ('1','2','2'); -- Java - Grille 2 - Bases de donn�es

-- DROIT -----------------------------------------------------------------------------------------------------

insert into DROIT values ('1','1', 'Modification / Suppression');

