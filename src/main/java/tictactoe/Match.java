package tictactoe;

public class Match {
    private int matchId;
    private String playerOne;
    private String playerTwo;
    private String winner;

    public Match(int matchId, String playerOne, String playerTwo, String winner) {
        this.matchId = matchId;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.winner = winner;
    }

    public int getMatchId() {
        return matchId;
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public String getWinner() {
        return winner;
    }
}
