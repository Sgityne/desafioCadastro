package model;

enum PetSpecie {
    GATO("Gato"),
    CACHORRO("Cachorro");

    protected String petSpecie;

    PetSpecie(String petSpecie) {
        this.petSpecie = petSpecie;
    }

    public String getPetSpecie() {
        return petSpecie;
    }
}
