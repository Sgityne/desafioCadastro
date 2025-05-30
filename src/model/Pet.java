package model;

public class Pet {
    private String name;
    private PetSpecie specie;
    private PetGender gender;
    private PetAddress address;
    private String age;
    private String weight;
    private String breed;

    public String getName() {
        return name;
    }

    public String getSpecie() {
        return specie.getPetSpecie();
    }

    public String getGender() {
        return gender.getPetGender();
    }

    public PetAddress getAddress() {
        return address;
    }

    public String getAge() {
        return age;
    }

    public String getWeight() {
        return weight;
    }

    public String getBreed() {
        return breed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecie(int num) {
        if (num == 1) {
            this.specie = PetSpecie.CACHORRO;
        } else if (num == 2) {
            this.specie = PetSpecie.GATO;
        }
    }

    public void setSpecie(String specie) {
        this.specie = PetSpecie.valueOf(specie);
    }

    public void setGender(char input) {
        if (input == 'F') {
            this.gender = PetGender.FEMEA;
        } else if (input == 'M') {
            this.gender = PetGender.MACHO;
        }
    }

    public void setGender(String gender) {
        this.gender = PetGender.valueOf(gender);
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
