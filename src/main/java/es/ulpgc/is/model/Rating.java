package es.ulpgc.is.model;

public class Rating {
    private int rating;
    private String content;


    public Rating(int rating, String content) {
        this.rating = rating;
        this.content = content;
    }

    public int rating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String content() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
