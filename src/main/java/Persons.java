public class Persons {
    private final int personID;
    private final String firstName;
    private final String lastName;
    private final int householdID;

    public Persons(int personID, String firstName, String lastName, int householdID) {
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.householdID = householdID;
    }
    public int getPersonID() {
        return personID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getHouseholdID() {
        return householdID;
    }
}
