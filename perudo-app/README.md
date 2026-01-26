# Perudo - Multiplayer Dice Game

Une application web pour jouer au Perudo avec tes amis, peu importe où ils sont. Interface claire et jeu fonctionnel.

## Comment ça marche? 🎯

### Pour le créateur de la partie:
1. **Ouvrez l'application**
2. Entrez votre pseudo
3. Cliquez **"Create New Game"**
4. ✅ Une partie est créée et vous êtes dedans
5. 📋 **Copiez le Game ID** (bouton 📋 dans le header)
6. Partagez cet ID avec vos amis

### Pour les autres joueurs:
1. **Ouvrez l'application** (même URL que le créateur)
2. Entrez votre pseudo
3. Cliquez **"Join Game"**
4. Collez le **Game ID** reçu du créateur
5. ✅ Vous rejoignez la partie!

### Pour commencer le jeu:
- Une fois que **minimum 2 joueurs** sont présents
- Le créateur clique **"Start Game"**
- ✅ Le jeu commence!

---

## Architecture

```
perudo-app/
├── backend/          # Spring Boot API (Port 8080)
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/perudo/
│       │   ├── PerudoApplication.java
│       │   ├── game/           # Logique du jeu
│       │   │   ├── Dice.java
│       │   │   ├── Player.java
│       │   │   ├── Bid.java
│       │   │   └── GameState.java
│       │   └── api/            # Endpoints REST
│       │       └── GameController.java
│       └── resources/
│           └── application.yml
└── frontend/         # React + TypeScript (Port 3000)
    ├── package.json
    ├── vite.config.ts
    ├── index.html
    └── src/
        ├── App.tsx
        ├── api/
        │   └── gameApi.ts
        └── components/
            ├── GameLobby.tsx
            ├── GameBoard.tsx
            ├── BidArea.tsx
            └── PlayerHand.tsx
```

## Prérequis

- Java 17+ avec Maven
- Node.js 18+ avec npm
- Un navigateur moderne
- Une connexion internet (pour jouer en ligne)

## Installation & Démarrage

### Backend (Spring Boot)

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

L'API sera disponible sur `http://localhost:8080/api`

### Frontend (React)

**Dans un autre terminal:**

```bash
cd frontend
npm install
npm run dev
```

L'interface sera disponible sur `http://localhost:3000`

### Accès local (sur le même réseau)

Pour jouer avec d'autres sur votre réseau local:

1. Trouvez votre adresse IP:
```bash
ipconfig  # Windows
# ou
ifconfig  # Mac/Linux
```

2. Partagez cette URL avec vos amis:
```
http://[VOTRE_IP]:3000
```

**Exemple:** Si votre IP est `192.168.1.100`, partagez:
```
http://192.168.1.100:3000
```

## Fonctionnalités

✅ Créer une partie  
✅ Rejoindre une partie existante (avec Game ID)  
✅ Système d'enchères complet  
✅ Challenge (Dudo)  
✅ Affichage des dés de chaque joueur  
✅ Interface claire et intuitive  
✅ Copie facile du Game ID  

## Gameplay

**Tour d'un joueur:**
1. Il peut faire une **enchère** (quantité + valeur)
2. L'enchère suivante doit être **plus haute**
3. Le prochain joueur peut **accepter** ou **contester (Dudo)**

**Si quelqu'un crie Dudo:**
- On révèle les dés
- Si l'enchère était vraie → le contestataire perd un dé
- Si l'enchère était fausse → le pariant perd un dé
- Les dés sont relancés, nouveau tour

**Fin du jeu:**
- Qui perd tous ses dés est éliminé
- Le dernier avec des dés gagne!

## Développement

Le code est simple et sans sur-complication:
- Backend: REST API avec Spring Boot
- Frontend: React avec TypeScript pour la clarté
- Pas de WebSocket pour l'instant (polling simple)
- CSS vanilla pour l'interface

## Déploiement (Jouer avec des amis en ligne)

### Option 1: Railway (Recommandé - Gratuit ⭐)

Déployer sur Internet pour que vos amis puissent jouer de n'importe où:

1. Créez un compte gratuit sur [railway.app](https://railway.app)
2. Connectez votre repo GitHub (fork ce projet)
3. Railway détectera automatiquement les fichiers Docker
4. Cliquez **"Deploy"**
5. ✅ Votre app est en ligne avec une URL publique!
6. Partagez l'URL avec vos amis

**Avantages:**
- Gratuit
- Automatique
- Pas de configuration complexe
- Accessible de partout

### Option 2: Docker local

Pour tester avec docker-compose:

```bash
docker-compose up
```

L'app sera disponible sur `http://localhost:3000`

### Option 3: ngrok (Tunnel temporaire)

Pour un test rapide avec un tunnel:

```bash
# Terminal 1 - Backend
cd backend
mvn spring-boot:run

# Terminal 2 - Frontend
cd frontend
npm run dev

# Terminal 3 - Tunnel ngrok
ngrok http 3000
```

Partagez l'URL ngrok générée avec vos amis!
