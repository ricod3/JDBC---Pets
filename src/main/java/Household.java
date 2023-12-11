public class Household {
    private int householdID;
    private String householdName;

    public Household(int householdID, String householdName) {
        this.householdID = householdID;
        this.householdName = householdName;
    }

    public int getHouseholdID() {
        return householdID;
    }

    public String getHouseholdName() {
        return householdName;
    }
}
