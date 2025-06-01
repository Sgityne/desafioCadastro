package model;

public enum PetSpecie {
    GATO("Gato"),
    CACHORRO("Cachorro");

    protected String petSpecie;

    PetSpecie(String petSpecie) {
        this.petSpecie = petSpecie;
    }
}
