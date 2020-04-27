package model;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getNumberOfReviews() == item.getNumberOfReviews() &&
                Double.compare(item.getRating(), getRating()) == 0 &&
                Double.compare(item.getPrice(), getPrice()) == 0 &&
                getName().equals(item.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getNumberOfReviews(), getRating(), getPrice());
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
