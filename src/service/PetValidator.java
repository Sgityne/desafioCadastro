package service;

public class PetValidator {

    private final String NAO_INFORMADO = "não informado";

    public String validatePetName (String name) {
        if (name == null || name.trim().isEmpty()) {
            return NAO_INFORMADO;
        }

        if (!name.contains(" ")) {
            throw new IllegalArgumentException("O pet deverá ter nome e sobrenome.");
        }

        if (!name.matches("([A-z ]+)")) {
            throw new IllegalArgumentException("O pet deverá conter SOMENTE letras.");
        }

        return name.trim();
    }

    public String validatePetWeight (String weight) {
        if (weight == null || weight.trim().isEmpty()) {
            return NAO_INFORMADO;
        }

        if (weight.contains(",")){
            weight = weight.replace(",", ".");
        }
        double weightD = Double.parseDouble(weight);
        if (weightD < 0.5 || weightD > 60) {
            throw new IllegalArgumentException("Peso inválido. Digite outro número.");
        }
        return String.valueOf(weightD);
    }

    /**
     * @param age years followed by months
     * @return {@link model.Pet#age age}
     */
    public String validatePetAge (String age) {
        if (age == null || age.trim().isEmpty()) {
            return NAO_INFORMADO;
        }

        double ageD = Double.parseDouble(age);
        if (ageD > 20 || ageD <= 0) {
            throw new IllegalArgumentException("Idade inválida. Digite outro número.");
        }
        return String.valueOf(ageD);
    }

    public String validatePetBreed (String breed) {
        if (breed == null || breed.trim().isEmpty()) {
            return NAO_INFORMADO;
        }

        if (!breed.matches("([A-z ]+)")) {
            throw new IllegalArgumentException("Raça inválida.");
        }
        return breed;
    }

    public String validateAddressNumber (String number) {
        if (number == null || number.trim().isEmpty()) {
            return NAO_INFORMADO;
        }
        return number;
    }
}
