# Perudo  

Développement d'une version numérique du **Perudo**, le célèbre jeu de bluff et de probabilités où les joueurs parient sur la quantité de dés d’une certaine valeur présents sous les gobelets.

---

# Règles du Jeu

## 🎲 Initialisation

- Chaque joueur commence la partie avec **5 dés**.  
- Tous les joueurs secouent leurs dés et **regardent secrètement** leur résultat.  
- Personne ne connaît les dés des autres joueurs.  

Le premier joueur ouvre les enchères en annonçant une estimation du type :  
> « Il y a **au moins X dés** montrant la valeur **Y** parmi tous les dés en jeu. »

Exemple :  
Dans une partie à 4 joueurs (20 dés), le premier joueur peut annoncer :  
> « Il y a au moins **5** dés de valeur **3**. »

Le joueur suivant doit alors **soit enchérir**, soit **contester**.

---

## 🔼 Enchérir

Pour enchérir, un joueur doit **augmenter la mise**. Exemple : passer de « 5 dés de valeur 3 » à « 6 dés de valeur 3 ».

Il est interdit de **baisser** la quantité ou la valeur.

---

## ❗ Contester (Dudo)

Un joueur peut décider de contester l’annonce précédente en déclarant :  
> « Dudo » (ou « Je doute »).

À ce moment-là, **tous les joueurs révèlent leurs dés** et on compte :

- le nombre de dés correspondant à la valeur annoncée  
- **+ les Pacos (1)**, qui comptent comme des jokers (voir section suivante)

Si l’annonce était **vraie ou égale** au nombre réel, le joueur qui a contesté **perd un dé**.  
Si l’annonce était **fausse** (strictement supérieure au total réel), c’est le joueur qui a fait l’annonce qui **perd un dé**.

---

## 🦜 Spécificités des dés (Paco)

Les dés du Perudo ne sont pas des dés classiques.  
Chaque dé comporte les faces : **2, 3, 4, 5, 6 et Paco (1)**.

Le **Paco** représente une tête de toucan et agit comme un **joker**.

### Rôle du Paco :
- Lors du décompte, un Paco **compte comme n’importe quelle valeur**.  
- Exemple : si l’annonce est « 5 dés de valeur 4 » et qu’il y a 3 dés montrant 4 + 2 Pacos, alors l’annonce est considérée comme vraie.

### Exception : les annonces de Paco
Il est possible d’annoncer une enchère **sur les Pacos eux-mêmes**.  
Dans ce cas :
- Les Pacos **ne sont plus des jokers** : seuls les Pacos comptent.  
- Pour passer d’une enchère normale à une enchère Paco, on applique une règle spéciale :  
  - Une annonce Paco doit être **au moins la moitié (arrondie au supérieur)** de la quantité précédente.  
  - Exemple : si l’annonce est « 6 dés de valeur 4 », une annonce Paco doit être **au moins 3 Pacos**.

Et inversement, pour revenir d’une annonce Paco à une annonce normale, la nouvelle quantité doit être **au moins le double + 1**.

---

## 🧩 Fin de Manche

À chaque contestation :
- Le joueur perdant **retire un dé** de son jeu.
- Une nouvelle manche commence.
- Le joueur qui a perdu le dé **commence la prochaine enchère**.

---

## 🏆 Fin de Partie

Un joueur éliminé lorsqu’il n’a plus de dés.  
Le dernier joueur possédant au moins un dé remporte la partie.

---

# Règles supplémentaires

## Exact ! (Calza)

Le joueur devant reparler après une enchère peut annoncer « Calza » (ou « Exact »). La situation se règle alors de la manière suivante :

- **tous les joueurs révèlent leurs dés** et on compte
- le nombre de dés correspondant à la valeur annoncée 
- **+ les Pacos (1)**, qui comptent comme des jokers

Si l’annonce n'était pas **strictement exacte** (strictement supérieure ou strictement inférieure au total réel) au nombre réel, le joueur qui a dit « Calza » **perd un dé**.  
Si l’annonce était **strictment exacte**, le joueur qui a dit « Calza » **regagne un dé**.

- Note : Le joueur ne peut pas obtenir de dé supplémentaire si il n'en a pas encore perdu.

### Variante supplémentaire

Lorsqu'on dit « Calza » on peut miser un certain nombre de dés. Dans ce cas, le joueur perd/regagne autant de dés que ce qu'il a miser.

## La mise sur la valeur et la quantité

Avec cette variant, à chaque enchère le joueur à une option supplémentaire. Pour enchérir, un joueur doit **augmenter la mise** selon l’une des deux possibilités :

- **Augmenter la quantité** :  
  Exemple : passer de « 5 dés de valeur 3 » à « 6 dés de valeur 3 ».

- **Augmenter la valeur du dé** :  
  Exemple : passer de « 5 dés de valeur 3 » à « 5 dés de valeur 4 ».

Il est interdit de **baisser** la quantité ou la valeur.

Cette variante permet de créer une hiérarchie dans les chiffres des dés. Avoir un 6 est mieux qu'avoir un 2.