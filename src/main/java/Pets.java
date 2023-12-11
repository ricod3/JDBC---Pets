public class Pets {

    private int petID;
    private String petLabel;
    private int personID;

    public int getPetID() {
        return petID;
    }

    public Pets(int petID, String petLabel, int personID) {
        this.petID = petID;
        this.petLabel = petLabel;
        this.personID = personID;
    }

    public String getPetLabel() {
        return petLabel;
    }

    public int getPersonID() {
        return personID;
    }
}
