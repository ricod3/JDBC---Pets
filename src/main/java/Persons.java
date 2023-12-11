public class Persons {
    private int personID;
    private String firstName;
    private String lastName;
    private int householdID;

    public Persons(String firstName, String lastName, int householdID) {
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
