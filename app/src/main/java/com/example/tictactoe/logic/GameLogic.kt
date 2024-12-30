package com.example.tictactoe.logic

class GameLogic {

    private val board = Array(3) { Array(3) { CellState.EMPTY } }
    private var currentPlayer = CellState.PLAYER_X

    fun getCurrentPlayer(): CellState = currentPlayer

    fun makeMove(row: Int, col: Int): Boolean {
        if (board[row][col] == CellState.EMPTY) {
            board[row][col] = currentPlayer
            return true
        }
        return false
    }

    fun checkGameState(): GameResult {
        for (row in 0..2) {
            if (board[row][0] == currentPlayer &&
                board[row][1] == currentPlayer &&
                board[row][2] == currentPlayer
            ) {
                return if (currentPlayer == CellState.PLAYER_X) GameResult.PLAYER_X_WINS else GameResult.PLAYER_O_WINS
            }
        }

        for (col in 0..2) {
            if (board[0][col] == currentPlayer &&
                board[1][col] == currentPlayer &&
                board[2][col] == currentPlayer
            ) {
                return if (currentPlayer == CellState.PLAYER_X) GameResult.PLAYER_X_WINS else GameResult.PLAYER_O_WINS
            }
        }

        if (board[0][0] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][2] == currentPlayer
        ) {
            return if (currentPlayer == CellState.PLAYER_X) GameResult.PLAYER_X_WINS else GameResult.PLAYER_O_WINS
        }
        if (board[0][2] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][0] == currentPlayer
        ) {
            return if (currentPlayer == CellState.PLAYER_X) GameResult.PLAYER_X_WINS else GameResult.PLAYER_O_WINS
        }

        if (board.all { row -> row.all { it != CellState.EMPTY } }) {
            return GameResult.DRAW
        }

        return GameResult.ONGOING
    }

    fun switchPlayer() {
        currentPlayer = if (currentPlayer == CellState.PLAYER_X) CellState.PLAYER_O else CellState.PLAYER_X
    }

    fun resetGame() {
        for (row in 0..2) {
            for (col in 0..2) {
                board[row][col] = CellState.EMPTY
            }
        }
        currentPlayer = CellState.PLAYER_X
    }

    fun getBoard(): Array<Array<CellState>> = board
}
