# Travel-management
Ce projet contient un ensemble de microservices qui travaillent ensemble pour fournir un système qui permet de fournir une plateforme en ligne pour la réservation d'hôtels, de voyages, de locations de voiture, d'activités touristiques, ainsi que pour la gestion des réservations et la facturation.

L'architecture du projet est basée sur un fichier Docker Compose qui contient les services suivants :
●Service de réservation d'hôtels
● service de réservation de voyages
● service de réservation de voitures
● service de gestion des utilisateurs
● service de gestion des agences


config-server : serveur Spring Cloud Config utilisé pour externaliser la configuration des services.
eureka-server : serveur de découverte de service utilisé par les services pour s'enregistrer et se découvrir mutuellement.
gateway : passerelle API qui fournit un seul point d'entrée aux microservices.

