# Exercice H.5 — Words Stats

Projet Java Swing pour NetBeans, avec une classe `MainFrame` pour la fenêtre et deux classes pour le calcul : `WordsStats` et `StatsEntry`. Les statistiques sont recalculées pendant la saisie, mais aussi après un collage ou une suppression. La liste place les mots les plus fréquents en premier ; les égalités sont départagées par le mot (`String.compareTo`).

## Exécuter dans NetBeans

1. Ouvrir le dossier **H5_WordStats** avec **File > Open Project**.
2. Lancer le projet avec **Run Project (F6)**. La classe principale `h5_wordstats.H5_WordStats` ouvre `MainFrame`.
3. Saisir du texte et choisir l'une des deux versions pour comparer leur fonctionnement.

Le bouton **Effacer** vide le texte. La fenêtre affiche également le nombre total de mots, le nombre de mots distincts et la durée du dernier calcul.

Le projet utilise Java 11 ou une version plus récente. Le fichier `MainFrame.form` permet d'ouvrir la fenêtre dans l'éditeur graphique de NetBeans.

## Règles utilisées pour les mots

- Les majuscules et minuscules sont regroupées : `Bonjour` et `BONJOUR` comptent comme `bonjour`.
- Les lettres Unicode sont reconnues, avec leurs marques d'accent. Les accents sont conservés : `a` et `à` sont deux mots différents.
- La normalisation Unicode NFC regroupe un accent composé et le même accent écrit séparément.
- Les espaces, retours à la ligne, signes de ponctuation, apostrophes, tirets et chiffres séparent les mots. Les chiffres seuls ne sont pas comptés. Par exemple, `l'été` donne `l` et `été`, et `arc-en-ciel` donne trois mots.
- Un texte vide ou sans lettre donne une liste vide.

Ces conventions sont identiques dans les deux versions grâce à la méthode privée `getWords`.

## Version 1 : HashMap

`calculateWithHashMap` parcourt les mots et utilise une `HashMap<String, Integer>` : la clé est le mot et la valeur son nombre d'occurrences. Un mot absent est ajouté avec la valeur 1 ; un mot déjà présent voit sa valeur augmenter. La table est ensuite convertie en liste de `StatsEntry`, puis triée.

## Version 2 : liste de StatsEntry

`calculateWithList` utilise uniquement une `ArrayList<StatsEntry>` pour compter. Pour chaque mot, elle parcourt la liste jusqu'à trouver l'entrée correspondante. Si elle la trouve, elle incrémente `count` ; sinon, elle ajoute une nouvelle entrée. La liste est ensuite triée avec la même règle que la version 1.

`StatsEntry` possède les deux attributs publics demandés dans le diagramme : `word : String` et `count : int`. Sa méthode `toString` produit l'affichage `mot --> nombre`.

## Complexité et performances

On note **L** la longueur du texte, **N** le nombre de mots et **U** le nombre de mots distincts, avec U ≤ N. Les coûts ci-dessous sont ceux d'un recalcul complet. Pour le comptage et le tri, on considère d'abord que comparer ou traiter un mot a un coût constant, comme dans l'analyse usuelle de ces structures.

| Étape | Version 1 : HashMap | Version 2 : liste |
| --- | --- | --- |
| Normaliser et extraire les mots | O(L) | O(L) |
| Compter les occurrences | O(N) en moyenne | O(N × U) au pire |
| Trier les U entrées | O(U log U) | O(U log U) |
| Total | O(L + N + U log U) en moyenne | O(L + N × U + U log U) au pire |

L'accès à une `HashMap` est constant **en moyenne**, sous l'hypothèse d'une bonne répartition des codes de hachage ; ce n'est pas une garantie de coût constant pour chaque accès. Le calcul des codes de hachage lit aussi les caractères des mots, ce qui contribue à un coût proportionnel au texte.

Avec la liste, lorsque tous les mots sont différents, les recherches examinent 0 + 1 + 2 + … + (N − 1) entrées : le comptage est donc **O(N²)**. Si le vocabulaire reste très petit, U reste petit et le comptage se rapproche de O(N). La longueur des mots compte aussi en pratique : une comparaison peut lire plusieurs caractères. Avec une longueur maximale de mot K, les recherches et comparaisons de tri peuvent chacune coûter O(K), au lieu de O(1).

Les deux compteurs conservent O(U) entrées. La version 1 a aussi les objets internes de sa table associative et sa liste de résultat, donc un surcoût mémoire par rapport à la version 2. Dans cette implémentation, l'extraction conserve tous les mots avant le comptage : il faut O(N) références, O(L) caractères et le texte normalisé. Le tri peut utiliser O(U) mémoire auxiliaire. La mémoire auxiliaire totale est ainsi **O(L + N + U)** pour les deux versions, et pas seulement O(U).

Pour un court texte, les deux versions conviennent. Pour un long texte contenant beaucoup de mots différents, la version `HashMap` est généralement bien plus rapide. À chaque modification du texte, le programme repart de zéro : les comptes restent corrects même lors d'une suppression, mais tous ces coûts sont répétés. Une très longue saisie peut donc ralentir l'interface, surtout avec la version à liste. Une amélioration pour de gros documents serait de regrouper les frappes rapprochées ou de calculer en arrière-plan ; l'exercice garde ici un recalcul direct et lisible.

La durée affichée dans la fenêtre reste indicative : elle varie avec la machine, l'activité du système et l'échauffement de la JVM. Pour observer une tendance, utiliser le même texte et répéter les calculs après quelques premiers essais. Une mesure isolée, surtout sur un petit texte, n'est pas un benchmark fiable et ne prouve pas qu'une version est toujours plus rapide.

## Vérification du calcul

Le programme de test `test/h5_wordstats/WordsStatsTest.java` fonctionne sans dépendance externe. Depuis le dossier du projet, avec un JDK disponible :

```powershell
New-Item -ItemType Directory -Force build/model-check
javac -encoding UTF-8 --release 11 -d build/model-check src/h5_wordstats/StatsEntry.java src/h5_wordstats/WordsStats.java test/h5_wordstats/WordsStatsTest.java
java -cp build/model-check h5_wordstats.WordsStatsTest
```

Il vérifie les comptes attendus, le tri, les égalités, les accents Unicode, les séparateurs, les textes vides, la suppression de texte, l'indépendance des recalculs et l'équivalence des deux versions sur 300 textes pseudo-aléatoires reproductibles.
