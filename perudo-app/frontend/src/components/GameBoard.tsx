import { useState, useEffect } from 'react'
import { gameApi, GameState } from '../api/gameApi'
import PlayerHand from './PlayerHand'
import BidArea from './BidArea'
import './GameBoard.css'

interface GameBoardProps {
  gameId: string
  playerId: string
  playerName: string
  onGameEnd: () => void
}

export default function GameBoard({ gameId, playerId, playerName, onGameEnd }: GameBoardProps) {
  const [gameState, setGameState] = useState<GameState | null>(null)
  const [gameStarted, setGameStarted] = useState(false)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)
  const [copied, setCopied] = useState(false)

  useEffect(() => {
    loadGame()
  }, [])

  const loadGame = async () => {
    try {
      setLoading(true)
      const game = await gameApi.getGame(gameId)
      setGameState(game)
      setGameStarted(game.phase === 'PLAYING')
      setError(null)
    } catch (err) {
      setError('Failed to load game: ' + err)
    } finally {
      setLoading(false)
    }
  }

  const copyGameId = () => {
    navigator.clipboard.writeText(gameId)
    setCopied(true)
    setTimeout(() => setCopied(false), 2000)
  }

  const handleStartGame = async () => {
    try {
      const game = await gameApi.startGame(gameId)
      setGameState(game)
      setGameStarted(true)
    } catch (err) {
      setError('Failed to start game: ' + err)
    }
  }

  const handleMakeBid = async (quantity: number, faceValue: number) => {
    try {
      const game = await gameApi.makeBid(gameId, quantity, faceValue, playerId)
      setGameState(game)
    } catch (err) {
      setError('Failed to make bid: ' + err)
    }
  }

  const handleDudo = async () => {
    try {
      const result = await gameApi.callDudo(gameId)
      await loadGame()
      setError(result.bidWon ? `Bid was correct! ${result.loserName} loses a die` : `Bid was wrong! ${result.loserName} loses a die`)
    } catch (err) {
      setError('Failed to call dudo: ' + err)
    }
  }

  if (loading) {
    return <div className="gameboard"><p>Loading game...</p></div>
  }

  if (!gameState) {
    return <div className="gameboard"><p>Game not found</p></div>
  }

  const currentPlayer = gameState.players[gameState.currentPlayerIndex]
  const currentPlayerObj = gameState.players.find(p => p.id === playerId)

  return (
    <div className="gameboard">
      <div className="header">
        <h1>🦜 PERUDO</h1>
        <div className="game-info">
          <div className="game-id-section">
            <span>Game ID:</span>
            <div className="id-copy-group">
              <code>{gameId}</code>
              <button onClick={copyGameId} className="btn-copy" title="Copy Game ID">
                {copied ? '✓ Copied!' : '📋'}
              </button>
            </div>
          </div>
          <span>You: {playerName}</span>
        </div>
      </div>

      {error && (
        <div className="error-banner">
          {error}
          <button onClick={() => setError(null)}>×</button>
        </div>
      )}

      <div className="game-container">
        {!gameStarted ? (
          <div className="lobby-game">
            <h2>Waiting for game to start...</h2>
            <div className="players-list">
              <h3>Players ({gameState.players.length})</h3>
              {gameState.players.map(p => (
                <div key={p.id} className="player-item">
                  ✓ {p.name}
                </div>
              ))}
            </div>
            {gameState.players.length >= 2 && (
              <button onClick={handleStartGame} className="btn-start">
                Start Game
              </button>
            )}
            <button onClick={onGameEnd} className="btn-back">
              Back to Lobby
            </button>
          </div>
        ) : (
          <div className="game-active">
            <div className="current-turn">
              <h2>
                {currentPlayer.id === playerId ? '🎯 Your Turn!' : `${currentPlayer.name}'s Turn`}
              </h2>
            </div>

            <div className="players-board">
              {gameState.players.map(player => (
                <div 
                  key={player.id} 
                  className={`player-card ${player.id === playerId ? 'own-player' : ''} ${currentPlayer.id === player.id ? 'active-player' : ''}`}
                >
                  <h3>{player.name}</h3>
                  <div className="dices-display">
                    {player.dices.map((dice, idx) => (
                      <span key={idx} className="dice">{dice.value}</span>
                    ))}
                  </div>
                  <p className="dice-count">{player.diceCount} dices</p>
                </div>
              ))}
            </div>

            {currentPlayer.id === playerId && (
              <>
                <BidArea 
                  gameState={gameState}
                  onMakeBid={handleMakeBid}
                  onDudo={handleDudo}
                />
                <PlayerHand player={currentPlayerObj!} />
              </>
            )}

            {gameState.currentBid && (
              <div className="current-bid">
                <h3>Current Bid</h3>
                <p>{gameState.currentBid.quantity} × {gameState.currentBid.faceValue === 1 ? 'Paco' : gameState.currentBid.faceValue}</p>
              </div>
            )}

            <button onClick={onGameEnd} className="btn-back">
              Exit Game
            </button>
          </div>
        )}
      </div>
    </div>
  )
}
