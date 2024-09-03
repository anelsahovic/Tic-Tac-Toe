package tictactoe;

public class Match {
    private int matchId;
    private int playerOneId;
    private int playerTwoId;
    private int winnerId;

    public Match(int matchId, int playerOneId, int playerTwoId, int winnerId) {
        this.matchId = matchId;
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
        this.winnerId = winnerId;
    }

    public int getMatchId() {
        return matchId;
    }

    public int getPlayerOneId() {
        return playerOneId;
    }

    public int getPlayerTwoId() {
        return playerTwoId;
    }

    public int getWinnerId() {
        return winnerId;
    }
}
