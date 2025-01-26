
# Projet de Gestion de Tâches, Missions et Projets

Ce projet est une application console permettant de gérer des tâches, des missions et des projets. Elle utilise le design pattern **Observer** pour notifier les vues lors des modifications des données, et des fichiers CSV pour persister les informations.

## Structure du projet

Le projet est organisé de manière modulaire avec les dossiers suivants :

```
src/
├── controllers       # Contient les contrôleurs pour gérer les entités
├── interfaces        # Contient les interfaces comme Observateur et Sujet
├── models            # Contient les entités et leurs référentiels
│   ├── Entities      # Les entités principales : Tâche, Mission, Projet
│   └── Repositories  # Les référentiels pour gérer les entités
├── utils             # Les classes utilitaires pour la gestion des fichiers CSV
└── views             # Les vues pour interagir avec l'utilisateur
```

## Prérequis

- Java 8 ou une version ultérieure
- Un terminal ou un IDE pour exécuter les commandes

## Compilation et exécution

### Compilation

Pour compiler le projet, utilisez la commande suivante dans le répertoire racine du projet :

```bash
javac -d build -sourcepath src src/Main.java
```

- **`-d build`** : Spécifie le dossier de sortie pour les fichiers `.class`.
- **`-sourcepath src`** : Indique le chemin des fichiers sources Java.
- **`src/Main.java`** : Fichier principal contenant le point d'entrée de l'application.

### Exécution

Une fois la compilation terminée, vous pouvez exécuter le programme avec la commande suivante :

```bash
java -cp build Main
```

- **`-cp build`** : Indique le chemin vers le dossier contenant les fichiers compilés.
- **`Main`** : Nom de la classe contenant la méthode `main`.

## Fonctionnalités

L'application permet de :

1. **Gérer les tâches** :
   - Ajouter, modifier, supprimer et afficher des tâches.
   - Visualiser les détails d'une tâche.

2. **Gérer les missions** :
   - Ajouter, modifier, supprimer et afficher des missions.
   - Associer des noms et des dates à une mission.

3. **Gérer les projets** :
   - Ajouter, modifier, supprimer et afficher des projets.
   - Associer des missions et des tâches à un projet.

## Données persistantes

Les données sont stockées dans des fichiers CSV situés dans le dossier `resources` :

- `resources/taches.csv` : Contient les tâches.
- `resources/missions.csv` : Contient les missions.
- `resources/projets.csv` : Contient les projets.

### Exemple de format CSV

**`taches.csv`** :
```
1,"Titre de la tâche","Description de la tâche","2025-01-01",false
```

**`missions.csv`** :
```
1,"Mission Exemple","2025-01-01","2025-01-15","Nom1;Nom2"
```

**`projets.csv`** :
```
1,"Projet Exemple","Description du projet","1"
```

## Générer la documentation

Pour générer la documentation Javadoc en HTML, utilisez la commande suivante :

```bash
javadoc -d doc -sourcepath src -subpackages controllers:interfaces:models:utils:views
```

La documentation sera générée dans le dossier `doc`. Vous pourrez l'ouvrir avec un navigateur via le fichier `doc/index.html`.

---

## Auteurs

**Yacine Le Mouel, Amine Boujemaaoui, Sami Laouaj**

---

### Licence

Ce projet est libre de droits et peut être modifié selon vos besoins.
