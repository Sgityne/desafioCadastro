package model;

public class PetAddress {
    private String number;
    private String city;
    private String street;

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return this.street + ", " + this.number + " - " + this.city;
    }
}
