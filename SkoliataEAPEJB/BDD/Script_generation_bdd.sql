-- Titre :             Création base skoliata.sql
-- Version :           1.1
-- Date création :     29.04.11
-- Date modification : 05.05.11
-- Auteur :            Collet Hugo
-- Description :       Script de création de la base de données pour le SI "skoliata"
--                     Note : script pour PostgreSQL 8.4
--                     Ebauche du script : premier jet pour la création de la bdd.

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
-- | Création des tables                                                                          |
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

-- Méthode d'auto-génération des noms des grilles à déterminer avec Mayte

CREATE TABLE grille
(
    id serial primary key,
    nom text not null, 							 
    niveau_performance_1 varchar(50) not null, 	
    niveau_performance_2 varchar(50) not null, 	
    niveau_performance_3 varchar(50),			
    niveau_performance_4 varchar(50) 			
) ;

-- La description du niveau de performance X de la table critère correspond au niveau de performance X de la table grille
-- Exemple : desc_niveau_performance 1 = "L'étudiant a rendu le projet terminé à 100%" -- niveau_performance_1 = "Très Bien"

CREATE TABLE critere 
(
	id serial primary key,
	id_grille integer not null references grille,	 -- ID de la grille à laquelle appartient le critère
	description text,								 -- Description générale du critère (désignation)
	desc_niveau_performance_1 varchar(500) not null, -- Description des niveaux de performances
	desc_niveau_performance_2 varchar(500) not null,
	desc_niveau_performance_3 varchar(500),
	desc_niveau_performance_4 varchar(500) 
) ;

-- Cette table représente l'association entre un objectif pédagogique, une grille, et un enseignement

CREATE TABLE ASSO_OGE 
(
    id_OP    integer not null references objectif_pedagogique,
	id_grille integer not null references grille,
    id_enseignement   integer not null references enseignement,
    primary key (id_grille, id_enseignement, id_OP)
) ;
  

-- Cette table représente l'association entre un élève, une enseignant, et un enseignement

CREATE TABLE ASSO_3E
 (
    id_enseignement integer not null references enseignement,
    id_enseignant   integer not null references enseignant,
    id_eleve        integer not null references eleve,
    primary key (id_enseignant, id_enseignement, id_eleve)
 ) ;
 
 -- Par défaut : un enseignant quelconque possède un droit de lecture sur toutes les grilles,
-- On ne représente dans cette table uniquement les droits autres que le droit de lecture par défaut
-- Id est, un droit global qui comprend : modification, suppression...etc.

CREATE TABLE DROIT
(
	id_enseignant integer not null references enseignant,
	id_grille integer not null references grille,
	description text,
	primary key (id_enseignant,id_grille)
) ;
-- +----------------------------------------------------------------------------------------------+
-- | Insertion de quelques données de pour les tests                                              |
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

insert into objectif_pedagogique values (nextval('objectif_pedagogique_id_seq'), 'Etre capable de développer une application en Java');
insert into objectif_pedagogique values (nextval('objectif_pedagogique_id_seq'), 'Savoir se présenter lors d''un entretien d''embauche');
insert into objectif_pedagogique values (nextval('objectif_pedagogique_id_seq'), 'Réaliser une réanimation d''une personne en arrêt cardio-respiratoire');
insert into objectif_pedagogique values (nextval('objectif_pedagogique_id_seq'), 'Connaître le contenu obligatoire d''un sac de secourisme');
  
  -- ENSEIGNEMENT -------------------------------------------------------------------------------------------

insert into enseignement values (nextval('enseignement_id_seq'), 'Genie logiciel et programmation objet et concurrente');
insert into enseignement values (nextval('enseignement_id_seq'), 'Bases de données');
insert into enseignement values (nextval('enseignement_id_seq'), 'Secourisme');
insert into enseignement values (nextval('enseignement_id_seq'), 'Préparation à la vie professionnelle');
  
  -- GRILLE -------------------------------------------------------------------------------------------------
  
insert into grille values (nextval('grille_id_seq'), 'Evaluation générale de Java','Bien', 'Assez Bien', 'Moyen','Insuffisant');
insert into grille values (nextval('grille_id_seq'), 'Evaluation générale de projet BDD','Bien', 'Moyen', 'Insuffisant');
insert into grille values (nextval('grille_id_seq'), 'Evaluation pratique de secourisme','Acquis', 'Non-acquis');
 
  -- CRITERE -------------------------------------------------------------------------------------------------
 
insert into critere values (nextval('critere_id_seq'), '1','Respecter les délais de livraison', 
'La livraison a été faite avant la date limite',
'La livraison a été faite après la date limite mais soit l''étudiant peut justifier le retard soit le retard n''est pas significatif',
'',
'La livraison a été faite bien après la date limite sans justification'
);
 
insert into critere values (nextval('critere_id_seq'), '1', 'Prendre en compte les remarques', 
'Toutes les remarques du tuteur sont prises en compte. L''étudiant demande des explications supplémentaires',
'Les remarques sont prises en compte sans interaction avec le tuteur',
'Certaines remarques nécessitant peu d''effort sont prises en compte. Les autres sont ignorées',
'Aucune remarque n''est prise en compte'
);
 
insert into critere values (nextval('critere_id_seq'), '1','Gérer les versions de la documentation',
'Tous les documents contiennent les informations suivantes : nom du document, nom des auteurs, date de réalisation, numéro de version et numération des pages. La présentation de ces informations respecte un canevas uniforme',
'Tous les documents contiennent au minimum les informations suivantes : nom du document, nom des auteurs, date de réalisation. La présentation de ces informations souvent ne respecte pas un canevas uniforme',
'Certains documents (hormis le CdC) contiennent au minimum les informations suivantes : nom du document, nom des auteurs. La présentation de ces informations ne respecte pas un canevas uniforme',
'Aucun document ne contient les informations nécessaires pour le suivi et le versionning des documents'
);
 
insert into critere values (nextval('critere_id_seq'), '1', 'Trouver la couverture fonctionnelle à partir d''un texte de besoins détaillé ou des indications du client', 
'Toutes les fonctions explicitées par le client sont présentes',
'La majorité des fonctions explicitées par le client sont présentes',
'Une partie des fonctions explicitées par le client sont présentes',
'Très peu des fonctions explicitées par le client sont présentes'
);

-- ASSO 3E -------------------------------------------------------------------------------------------------

insert into ASSO_3E values ('1','1','1'); -- Tim est responsable de l'enseignement "Genie logiciel et programmation objet et concurrente" et donne
insert into ASSO_3E values ('1','1','2'); -- des cours au groupe d'élèves "Baptiste Bakous, Baptiste Perroud, Alexandre Bakous"
insert into ASSO_3E values ('1','1','3');

-- ASSO OGE -------------------------------------------------------------------------------------------------

insert into ASSO_OGE values ('1','1','1'); -- Java - Grille 1 - Génie logiciel
insert into ASSO_OGE values ('1','2','2'); -- Java - Grille 2 - Bases de données

-- DROIT -----------------------------------------------------------------------------------------------------

insert into DROIT values ('1','1', 'Modification / Suppression');

