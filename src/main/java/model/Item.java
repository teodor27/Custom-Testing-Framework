package model;

public class Item {
    private String name;
    private int numberOfReviews;
    private double rating;
    private double price;
    private int selectionPoints;

    public Item() {
        this.selectionPoints = 0;
    }


    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public Item setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
        return this;
    }

    public double getRating() {
        return rating;
    }

    public Item setRating(double rating) {
        this.rating = rating;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Item setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getSelectionPoints() {
        return selectionPoints;
    }

    public Item setSelectionPoints(int selectionPoints) {
        this.selectionPoints = selectionPoints;
        return this;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", numberOfReviews=" + numberOfReviews +
                ", rating=" + rating +
                ", price=" + price +
                ", selectionPoints=" + selectionPoints +
                '}';
    }
}
