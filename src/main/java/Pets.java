public class Pets {

    private final int petID;
    private final String petLabel;
    private final int personID;

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
