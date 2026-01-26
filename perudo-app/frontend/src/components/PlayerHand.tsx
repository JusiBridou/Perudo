import { Player } from '../api/gameApi'
import './PlayerHand.css'

interface PlayerHandProps {
  player: Player
}

export default function PlayerHand({ player }: PlayerHandProps) {
  if (!player || !player.dices) {
    return null
  }

  return (
    <div className="player-hand">
      <h3>Your Dices</h3>
      <div className="hand-dices">
        {player.dices.map((dice, idx) => (
          <div key={idx} className="hand-dice">
            <span>{dice.value}</span>
          </div>
        ))}
      </div>
    </div>
  )
}
