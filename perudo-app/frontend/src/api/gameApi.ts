import axios from 'axios'

const API_BASE = '/api'

export interface GameState {
  gameId: string
  players: Player[]
  currentPlayerIndex: number
  currentBid: Bid | null
  phase: string
  message?: string
}

export interface Player {
  id: string
  name: string
  dices: Dice[]
  diceCount: number
  active: boolean
}

export interface Dice {
  value: string
}

export interface Bid {
  quantity: number
  faceValue: number
  playerId: string
}

export const gameApi = {
  createGame: async (): Promise<GameState> => {
    const response = await axios.post(`${API_BASE}/games/create`)
    return response.data
  },

  joinGame: async (gameId: string, playerId: string, playerName: string): Promise<GameState> => {
    const response = await axios.post(`${API_BASE}/games/${gameId}/join`, {
      playerId,
      playerName
    })
    return response.data
  },

  getGame: async (gameId: string): Promise<GameState> => {
    const response = await axios.get(`${API_BASE}/games/${gameId}`)
    return response.data
  },

  startGame: async (gameId: string): Promise<GameState> => {
    const response = await axios.post(`${API_BASE}/games/${gameId}/start`)
    return response.data
  },

  makeBid: async (gameId: string, quantity: number, faceValue: number, playerId: string): Promise<GameState> => {
    const response = await axios.post(`${API_BASE}/games/${gameId}/bid`, {
      quantity,
      faceValue,
      playerId
    })
    return response.data
  },

  callDudo: async (gameId: string): Promise<{ actualCount: number; bidWon: boolean; loserName: string }> => {
    const response = await axios.post(`${API_BASE}/games/${gameId}/dudo`)
    return response.data
  }
}
