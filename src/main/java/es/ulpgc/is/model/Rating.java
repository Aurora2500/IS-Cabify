package es.ulpgc.is.model;

public class Rating {
    private final int rating;
    private final String content;


    public Rating(int rating, String content) {
        this.rating = rating;
        this.content = content;
    }

    public int rating() {
        return rating;
    }

    public String content() {
        return content;
    }
}
