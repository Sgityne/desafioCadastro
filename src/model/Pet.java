package model;

public class Pet {
    private String name;
    private PetSpecie specie;
    private PetGender gender;
    private PetAddress address;
    private String age;
    private String weight;
    private String breed;

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecie(PetSpecie specie) {
        this.specie = specie;
    }

    public void setGender(PetGender gender) {
        this.gender = gender;
    }

    public void setAddress(PetAddress address) {
        this.address = address;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
