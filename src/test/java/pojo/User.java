package pojo;

public class User {

    private int id;
    private final String title;
    private final String body;
    private final int userId;

    public User(int id, String title, String body, int userId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.userId = userId;
    }
}

