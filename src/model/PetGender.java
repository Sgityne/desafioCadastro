package model;

enum PetGender {
    FEMEA("Fêmea"),
    MACHO("Macho");

    protected String petGender;

    PetGender(String petGender) {
        this.petGender = petGender;
    }

    public String getPetGender() {
        return petGender;
    }
}
