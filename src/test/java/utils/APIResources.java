package utils;

public enum APIResources {

    makepost("/posts"),
    makecomment("/comments"),
    getUser("/users");

    private String resource;

    APIResources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }


}

