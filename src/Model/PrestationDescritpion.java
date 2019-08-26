package Model;

public class PrestationDescritpion {
    Sexe sexe;
    Longueur longueur;
    float price;

    public PrestationDescritpion(Sexe sexe, float price) {
        this.sexe = sexe;
        this.price = price;

        longueur = null;
    }

    public void setLongueur(Longueur longueur) {
        this.longueur = longueur;
    }

    // Possible descriptions
    public enum Sexe {
        Homme ("Homme"),
        Femme ("Femme");

        private String name = "";

        Sexe(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public enum Longueur {
        Long("Long"),
        MiLong("Mi-long"),
        Court("Court");

        private String name = "";

        Longueur(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public enum Type {

    }
}