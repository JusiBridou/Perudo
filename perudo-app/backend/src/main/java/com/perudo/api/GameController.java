package com.perudo.api;

import com.perudo.game.GameState;
import com.perudo.game.Player;
import com.perudo.game.Bid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "http://localhost:3000")
public class GameController {
    private static final Logger log = LoggerFactory.getLogger(GameController.class);
    private Map<String, GameState> games = new HashMap<>();

    @PostMapping("/create")
    public ResponseEntity<GameState> createGame() {
        GameState game = new GameState();
        games.put(game.getGameId(), game);
        log.info("Game created: {}", game.getGameId());
        return ResponseEntity.ok(game);
    }

    @PostMapping("/{gameId}/join")
    public ResponseEntity<GameState> joinGame(
            @PathVariable String gameId,
            @RequestBody PlayerRequest request) {
        GameState game = games.get(gameId);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        Player player = new Player(request.getPlayerId(), request.getPlayerName());
        game.addPlayer(player);
        log.info("Player {} joined game {}", request.getPlayerName(), gameId);
        return ResponseEntity.ok(game);
    }

    @PostMapping("/{gameId}/start")
    public ResponseEntity<GameState> startGame(@PathVariable String gameId) {
        GameState game = games.get(gameId);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        game.startGame();
        log.info("Game {} started with {} players", gameId, game.getPlayers().size());
        return ResponseEntity.ok(game);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<GameState> getGame(@PathVariable String gameId) {
        GameState game = games.get(gameId);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game);
    }

    @PostMapping("/{gameId}/bid")
    public ResponseEntity<GameState> makeBid(
            @PathVariable String gameId,
            @RequestBody BidRequest request) {
        GameState game = games.get(gameId);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        Bid bid = new Bid(request.getQuantity(), request.getFaceValue(), request.getPlayerId());
        game.setCurrentBid(bid);
        game.nextPlayer();
        return ResponseEntity.ok(game);
    }

    @PostMapping("/{gameId}/dudo")
    public ResponseEntity<DudoResult> dudo(@PathVariable String gameId) {
        GameState game = games.get(gameId);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }

        Bid bid = game.getCurrentBid();
        int actualCount = game.countDices(bid.getFaceValue(), true);
        
        boolean bidWon = actualCount >= bid.getQuantity();
        Player loser = bidWon ? game.getCurrentPlayer() : 
                       game.getPlayers().stream()
                           .filter(p -> p.getId().equals(bid.getPlayerId()))
                           .findFirst().orElse(null);

        if (loser != null) {
            loser.loseDice();
        }

        game.rollAllDices();
        game.setCurrentBid(null);
        
        return ResponseEntity.ok(new DudoResult(actualCount, bidWon, loser.getName()));
    }
}
