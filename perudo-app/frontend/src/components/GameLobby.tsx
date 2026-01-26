import { useState } from 'react'
import { gameApi } from '../api/gameApi'
import './GameLobby.css'

interface GameLobbyProps {
  onGameCreated: (gameId: string) => void
  onPlayerJoined: (playerId: string, playerName: string) => void
}

export default function GameLobby({ onGameCreated, onPlayerJoined }: GameLobbyProps) {
  const [mode, setMode] = useState<'menu' | 'create' | 'join'>('menu')
  const [playerName, setPlayerName] = useState('')
  const [gameId, setGameId] = useState('')
  const [loading, setLoading] = useState(false)

  const handleCreateGame = async () => {
    if (!playerName.trim()) {
      alert('Please enter your name')
      return
    }
    setLoading(true)
    try {
      const game = await gameApi.createGame()
      const playerId = Math.random().toString(36).substr(2, 9)
      await gameApi.joinGame(game.gameId, playerId, playerName)
      onGameCreated(game.gameId)
      onPlayerJoined(playerId, playerName)
    } catch (error) {
      alert('Error creating game: ' + error)
    } finally {
      setLoading(false)
    }
  }

  const handleJoinGame = async () => {
    if (!playerName.trim() || !gameId.trim()) {
      alert('Please enter your name and game ID')
      return
    }
    setLoading(true)
    try {
      const playerId = Math.random().toString(36).substr(2, 9)
      await gameApi.joinGame(gameId, playerId, playerName)
      onGameCreated(gameId)
      onPlayerJoined(playerId, playerName)
    } catch (error) {
      alert('Error joining game: ' + error)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="lobby">
      <div className="lobby-container">
        <h1>🦜 PERUDO</h1>
        
        {mode === 'menu' && (
          <div className="menu">
            <p className="subtitle">The game of bluffing with dice</p>
            <button onClick={() => setMode('create')} className="btn-primary">
              Create New Game
            </button>
            <button onClick={() => setMode('join')} className="btn-secondary">
              Join Game
            </button>
          </div>
        )}

        {mode === 'create' && (
          <div className="form">
            <h2>Create Game</h2>
            <input
              type="text"
              placeholder="Enter your name"
              value={playerName}
              onChange={(e) => setPlayerName(e.target.value)}
              disabled={loading}
            />
            <button 
              onClick={handleCreateGame} 
              className="btn-primary"
              disabled={loading}
            >
              {loading ? 'Creating...' : 'Create Game'}
            </button>
            <button 
              onClick={() => setMode('menu')} 
              className="btn-cancel"
            >
              Back
            </button>
          </div>
        )}

        {mode === 'join' && (
          <div className="form">
            <h2>Join Game</h2>
            <input
              type="text"
              placeholder="Enter your name"
              value={playerName}
              onChange={(e) => setPlayerName(e.target.value)}
              disabled={loading}
            />
            <input
              type="text"
              placeholder="Enter game ID"
              value={gameId}
              onChange={(e) => setGameId(e.target.value)}
              disabled={loading}
            />
            <button 
              onClick={handleJoinGame} 
              className="btn-primary"
              disabled={loading}
            >
              {loading ? 'Joining...' : 'Join Game'}
            </button>
            <button 
              onClick={() => setMode('menu')} 
              className="btn-cancel"
            >
              Back
            </button>
          </div>
        )}
      </div>
    </div>
  )
}
