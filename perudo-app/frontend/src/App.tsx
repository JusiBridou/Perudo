import { useState } from 'react'
import GameLobby from './components/GameLobby'
import GameBoard from './components/GameBoard'
import './App.css'

function App() {
  const [gameId, setGameId] = useState<string | null>(null)
  const [playerId, setPlayerId] = useState<string>('')
  const [playerName, setPlayerName] = useState<string>('')

  const handleGameCreated = (id: string) => {
    setGameId(id)
  }

  const handlePlayerJoined = (id: string, name: string) => {
    setPlayerId(id)
    setPlayerName(name)
  }

  const handleGameEnd = () => {
    setGameId(null)
    setPlayerId('')
    setPlayerName('')
  }

  return (
    <div className="app">
      {!gameId ? (
        <GameLobby onGameCreated={handleGameCreated} onPlayerJoined={handlePlayerJoined} />
      ) : (
        <GameBoard 
          gameId={gameId} 
          playerId={playerId} 
          playerName={playerName}
          onGameEnd={handleGameEnd}
        />
      )}
    </div>
  )
}

export default App
