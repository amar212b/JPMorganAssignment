package pojo;

import lombok.Getter;

public class Comment {

    @Getter
    private int postId;
    @Getter
    private int id;
    @Getter
    private String name;
    @Getter
    private String email;
    @Getter
    private String body;

    public void setpostId(int postId) {
        this.postId = postId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "{" +
                "postId='" + postId + '\'' +
                ",id='" + id  + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
