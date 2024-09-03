package tictactoe;

public class User {
    private int userId;
    private String name;
    private String username;
    private int wins;

    public User(int userId, String name, String username, int wins) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.wins = wins;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public int getWins() {
        return wins;
    }

}
