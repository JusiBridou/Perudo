import { useState } from 'react'
import { GameState } from '../api/gameApi'
import './BidArea.css'

interface BidAreaProps {
  gameState: GameState
  onMakeBid: (quantity: number, faceValue: number) => void
  onDudo: () => void
}

export default function BidArea({ gameState, onMakeBid, onDudo }: BidAreaProps) {
  const [quantity, setQuantity] = useState(1)
  const [faceValue, setFaceValue] = useState(2)

  const handleBid = () => {
    onMakeBid(quantity, faceValue)
  }

  const faces = [
    { value: 1, label: 'Paco' },
    { value: 2, label: '2' },
    { value: 3, label: '3' },
    { value: 4, label: '4' },
    { value: 5, label: '5' },
    { value: 6, label: '6' }
  ]

  return (
    <div className="bid-area">
      <h3>Make Your Bid</h3>
      
      <div className="bid-controls">
        <div className="control-group">
          <label>Quantity</label>
          <input
            type="number"
            min="1"
            max="30"
            value={quantity}
            onChange={(e) => setQuantity(Math.max(1, parseInt(e.target.value) || 1))}
          />
        </div>

        <div className="control-group">
          <label>Face Value</label>
          <select value={faceValue} onChange={(e) => setFaceValue(parseInt(e.target.value))}>
            {faces.map(face => (
              <option key={face.value} value={face.value}>
                {face.label}
              </option>
            ))}
          </select>
        </div>
      </div>

      <div className="bid-preview">
        <p>Betting on: <strong>{quantity} × {faceValue === 1 ? 'Paco' : faceValue}</strong></p>
      </div>

      <div className="bid-actions">
        <button onClick={handleBid} className="btn-bid">
          Bid
        </button>
        <button onClick={onDudo} className="btn-dudo" disabled={!gameState.currentBid}>
          Dudo!
        </button>
      </div>
    </div>
  )
}
