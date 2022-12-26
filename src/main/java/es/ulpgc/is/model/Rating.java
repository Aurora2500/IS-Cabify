package es.ulpgc.is.model;

public class Rating {
    private int rating;
    private String content;
    private Journey journey;


    public Rating(int rating, String content, Journey journey) {
        this.rating = rating;
        this.content = content;
        this.journey = journey;
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

    public Journey journey() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }
}
