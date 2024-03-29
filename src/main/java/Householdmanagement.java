import javax.naming.InvalidNameException;
import java.util.*;
import java.sql.*;

public class Householdmanagement {
    private final Connection mysqlConn;

    public Householdmanagement(Connection mysqlConn) {
        this.mysqlConn = mysqlConn;
    }

    static void startHousehold(boolean running, Scanner sc, Householdmanagement management) throws SQLException, InvalidNameException {
        while (running) {
            try {
                System.out.println("==================================");
                System.out.println("|| -- Wählen Sie eine Option -- ||");
                System.out.println("|| > 1. Neuen Haushalt anlegen  ||");
                System.out.println("|| > 2. Haushalt ändern         ||");
                System.out.println("|| > 3. Haushalt löschen        ||");
                System.out.println("|| > 4. Person anlegen          ||");
                System.out.println("|| > 5. Person ändern           ||");
                System.out.println("|| > 6. Person löschen          ||");
                System.out.println("|| > 7. Haustier anlegen        ||");
                System.out.println("|| > 8. Haustier ändern         ||");
                System.out.println("|| > 9. Haustier löschen        ||");
                System.out.println("|| > 0. Beenden                 ||");
                System.out.println("==================================");

                int userInput = sc.nextInt();
                sc.nextLine();

                switch (userInput) {
                    case 1:
                        management.printHousehold();
                        runCreateHousehold(sc, management);
                        break;
                    case 2:
                        management.printHousehold();
                        runUpdateHousehold(sc, management);
                        break;
                    case 3:
                        management.printHousehold();
                        runDeleteHousehold(sc, management);
                        break;
                    case 4:
                        management.printPersons();
                        runCreatePerson(sc, management);
                        break;
                    case 5:
                        management.printPersons();
                        runUpdatePerson(sc, management);
                        break;
                    case 6:
                        management.printPersons();
                        runDeletePerson(sc, management);
                        break;
                    case 7:
                        management.printPets();
                        runCreatePet(sc, management);
                        break;
                    case 8:
                        management.printPets();
                        runUpdatePet(sc, management);
                        break;
                    case 9:
                        management.printPets();
                        runDeletePet(sc, management);
                        break;
                    case 0:
                        running = false;
                        break;
                }
            } catch (InputMismatchException ie) {
                System.out.println("Eingabe ungültig. Bitte korrekte Eingabe tätigen!!");
                sc.nextLine();
            }
        }
    }

    private static void runDeletePet(Scanner sc, Householdmanagement management) {
        try {
            System.out.println("Sie möchten ein Haustier löschen. Geben Sie die ID des zu löschenden Haustiers an: ");
            while (!sc.hasNextInt()) {
                System.out.println("Bitte nur Zahlen eingeben. Buchstaben nicht erlaubt!!");
                sc.nextLine();
            }
            int petID = sc.nextInt();
            sc.nextLine();
            Pets deletePet = new Pets(petID, null, 0);
            management.deletePet(deletePet);
        } catch (SQLException e) {
            System.out.println("Eingabe ungültig");
        }
    }

    private static void runUpdatePet(Scanner sc, Householdmanagement management) {
        try {
            System.out.println("Sie wollen einen Haustier ändern. Geben Sie die ID des zu ändernden Haustiers an: ");
            while (!sc.hasNextInt()) {
                System.out.println("Bitte nur Zahlen eingeben. Buchstaben nicht erlaubt!!");
                sc.nextLine();
            }
            int petID = sc.nextInt();
            sc.nextLine();
            System.out.println("Geben Sie den neuen Namen des Haustieres ein: ");
            String petLabel = sc.nextLine();
            if (hasDigit(petLabel)) {
                throw new InvalidNameException("Nur Buchstaben erlaubt, keine Zahlen!!");
            }
            System.out.println("Welcher Person wollen Sie das Haustier zuordnen? Geben Sie die Personen ID ein: ");
            while (!sc.hasNextInt()) {
                System.out.println("Bitte nur Zahlen eingeben. Buchstaben nicht erlaubt!!");
                sc.nextLine();
            }
            int personID = sc.nextInt();
            sc.nextLine();
            Pets updatePet = new Pets(petID, petLabel, personID);
            management.updatePet(updatePet);
        } catch (InvalidNameException | SQLException e) {
            System.out.println("Eingabe ungültig");
        }
    }

    private static void runCreatePet(Scanner sc, Householdmanagement management) {
        try {
            System.out.println("Sie möchten ein neues Haustier anlegen. Geben Sie die Bezeichnung / den Namen des Tiers an: ");
            String petLabel = sc.nextLine();
            if (hasDigit(petLabel)) {
                throw new InvalidNameException("Nur Buchstaben erlaubt, keine Zahlen!!");
            }
            management.printPersons();
            System.out.println("Zu wem gehört das Haustier? Geben Sie die Person ID an: ");
            while (!sc.hasNextInt()) {
                System.out.println("Bitte nur Zahlen (ID) eingeben!!");
                sc.nextLine();
            }
            int personID = sc.nextInt();
            sc.nextLine();
            Pets createPet = new Pets(0, petLabel, personID);
            management.createPet(createPet);
        } catch (InvalidNameException | SQLException e) {
            System.out.println("Eingabe ungültig");
        }
    }

    private static void runDeletePerson(Scanner sc, Householdmanagement management) {
        try {
            System.out.println("Sie wollen eine Person löschen. Geben Sie die ID der zu löschenden Person an: ");
            while (!sc.hasNextInt()) {
                throw new InvalidNameException("Bitte nur Zahlen eingeben. Buchstaben sind nicht erlaubt!!");
            }
            int personID = sc.nextInt();
            sc.nextLine();
            Persons deletePerson = new Persons(personID, null, null, 0);
            management.deletePerson(deletePerson);
        } catch (InvalidNameException | SQLException e) {
            System.out.println("Eingabe ungültig");
        }
    }

    private static void runUpdatePerson(Scanner sc, Householdmanagement management) {
        int householdID;
        try {
            System.out.println("Sie wollen eine Person ändern. Geben Sie die ID der Person ein, die Sie ändern möchten: ");
            while (!sc.hasNextInt()) {
                System.out.println("Bitte nur Zahlen eingeben. Buchstaben nicht erlaubt!!");
                sc.nextLine();
            }
            int personID = sc.nextInt();
            sc.nextLine();
            System.out.println("Geben Sie den neuen Vornamen der Person ein: ");
            String firstName = sc.nextLine();
            System.out.println("Nun geben Sie den Nachnamen ein: ");
            String lastName = sc.nextLine();
            if (hasDigit(firstName) || hasDigit(lastName)) {
                throw new InvalidNameException("Nur Buchstaben erlaubt, keine Zahlen!!");
            }
            System.out.println("Ordnen Sie die Person einem Haushalt zu: ");
            management.printHousehold();
            while (!sc.hasNextInt()) {
                System.out.println("Bitte nur Zahlen eingeben. Buchstaben nicht erlaubt!!");
                sc.nextLine();
            }
            householdID = sc.nextInt();
            Persons updatePerson = new Persons(personID, firstName, lastName, householdID);
            management.updatePerson(updatePerson);
        } catch (InvalidNameException | SQLException e) {
            System.out.println("Eingabe ungültig");
        }
    }

    private static void runCreatePerson(Scanner sc, Householdmanagement management) {
        int householdID;
        try {
            System.out.println("Person anlegen. Geben Sie den Vornamen der neuen Person ein: ");
            String firstName = sc.nextLine();
            System.out.println("Nun geben Sie den Nachnamen ein: ");
            String lastName = sc.nextLine();
            if (hasDigit(firstName) || hasDigit(lastName)) {
                throw new InvalidNameException("Nur Buchstaben erlaubt, keine Zahlen!!");
            }
            management.printHousehold();
            System.out.println("Zu welchem Haushalt gehört die Person? Bitte Haushalt ID angeben: ");
            householdID = sc.nextInt();
            sc.nextLine();
            Persons createPerson = new Persons(0, firstName, lastName, householdID);
            management.createPerson(createPerson);
        } catch (InvalidNameException | SQLException e) {
            System.out.println("Eingabe ungültig");
        }
    }

    private static void runDeleteHousehold(Scanner sc, Householdmanagement management) throws SQLException {
        System.out.println("Sie wollen einen Haushalt löschen. Geben Sie die ID des zu löschenden Haushalts an: ");
        while (!sc.hasNextInt()) {
            System.out.println("Bitte nur Zahlen eingeben. Buchstaben nicht erlaubt!!");
            sc.nextLine();
        }
        int householdID = sc.nextInt();
        sc.nextLine();
        Household deleteHousehold = new Household(householdID, null);
        management.deleteHousehold(deleteHousehold);
    }

    private static void runUpdateHousehold(Scanner sc, Householdmanagement management) {
        try {
            System.out.println("Sie wollen einen Haushalt ändern. Geben Sie die ID des zu ändernden Haushalts an: ");
            while (!sc.hasNextInt()) {
                System.out.println("Bitte nur Zahlen eingeben. Buchstaben nicht erlaubt!!");
                sc.nextLine();
            }
            int householdID = sc.nextInt();
            sc.nextLine();
            System.out.println("Geben Sie den neuen Namen des Haushaltes ein: ");
            String householdName = sc.nextLine();
            if (hasDigit(householdName)) {
                throw new InvalidNameException("Nur Buchstaben erlaubt, keine Zahlen!!");
            }
            Household updateHousehold = new Household(householdID, householdName);
            management.updateHousehold(updateHousehold);
        } catch (InvalidNameException | SQLException e) {
            System.out.println("Eingabe ungültig");
        }
    }

    private static void runCreateHousehold(Scanner sc, Householdmanagement management) {
        try {
            System.out.println("Geben Sie die ID für den neuen Haushalt an: ");
            while (!sc.hasNextInt()) {
                System.out.println("Bitte nur Zahlen eingeben. Buchstaben nicht erlaubt!!");
                sc.nextLine();
            }
            int householdID = sc.nextInt();
            sc.nextLine();
            System.out.println("Geben Sie den Namen für den neuen Haushalt an: ");
            String householdName = sc.nextLine();
            if (hasDigit(householdName)) {
                throw new InvalidNameException("Nur Buchstaben erlaubt, keine Zahlen!!");
            }
            Household newHousehold = new Household(householdID, householdName);
            management.createHousehold(newHousehold);
        } catch (InvalidNameException | SQLException e) {
            System.out.println("Eingabe ungültig");
            e.printStackTrace();
        }
    }

    public void createHousehold(Household household) throws SQLException {
        String sqlPrompt = "INSERT INTO household (household_id, household_name) VALUES (?,?)";
        PreparedStatement pS = mysqlConn.prepareStatement(sqlPrompt);
        pS.setInt(1, household.getHouseholdID());
        pS.setString(2, household.getHouseholdName());
        pS.executeUpdate();
    }

    public void deleteHousehold(Household household) throws SQLException {
        String sqlPrompt = "DELETE FROM household WHERE household_id = ?";
        PreparedStatement pS = mysqlConn.prepareStatement(sqlPrompt);
        pS.setInt(1, household.getHouseholdID());
        pS.executeUpdate();
    }

    public void createPerson(Persons person) throws SQLException {
        String sqlPrompt = "INSERT INTO persons (person_firstName, person_lastName, household_id) VALUES (?,?,?)";
        PreparedStatement pS = mysqlConn.prepareStatement(sqlPrompt);
        pS.setString(1, person.getFirstName());
        pS.setString(2, person.getLastName());
        pS.setInt(3, person.getHouseholdID());
        pS.executeUpdate();
    }

    public void deletePerson(Persons person) throws SQLException {
        String sqlPrompt = "DELETE FROM persons WHERE person_firstName = ? AND person_lastName = ?";
        PreparedStatement pS = mysqlConn.prepareStatement(sqlPrompt);
        pS.setString(1, person.getFirstName());
        pS.setString(2, person.getLastName());
        pS.executeUpdate();
    }

    public void createPet(Pets pets) throws SQLException {
        String sqlPrompt = "INSERT INTO pets (pet_label, person_id) VALUES (?,?)";
        PreparedStatement pS = mysqlConn.prepareStatement(sqlPrompt);
        pS.setString(1, pets.getPetLabel());
        pS.setInt(2, pets.getPersonID());
        pS.executeUpdate();
    }

    public void deletePet(Pets pets) throws SQLException {
        String sqlPrompt = "DELETE FROM pets WHERE pet_id = ?";
        PreparedStatement pS = mysqlConn.prepareStatement(sqlPrompt);
        pS.setInt(1, pets.getPetID());
        pS.executeUpdate();
    }

    /**
     * LEFT JOIN for persons, to show all persons regardless whether a pet has been assigned or not
     * JOIN to show all persons who have been assigned to a household
     */
    public void printPersons() throws SQLException {
        System.out.println("Diese Personen existieren bereits in der Datenbank");
        String sqlPrompt = "SELECT p.person_id, p.person_firstName, p.person_lastName, h.household_name, pet.pet_label FROM persons p JOIN household h ON p.household_id = h.household_id LEFT JOIN pets pet on p.person_id = pet.person_id";
        PreparedStatement pS = mysqlConn.prepareStatement(sqlPrompt);
        ResultSet rS = pS.executeQuery();

        while (rS.next()) {
            int personID = rS.getInt("person_id");
            String firstName = rS.getString("person_firstName");
            String lastName = rS.getString("person_lastName");
            String householdName = rS.getString("household_name");
            String petLabel = rS.getString("pet_label");

            System.out.println(
                    ">>> Person ID: " + personID +
                            " | Vorname: " + firstName +
                            " | Nachname: " + lastName +
                            " | Haushalt: " + householdName +
                            " | Haustier: " + petLabel
            );
        }
    }

    public void printHousehold() throws SQLException {
        System.out.println("Diese Haushalte existieren bereits in der Datenbank");
        String sqlPrompt = "SELECT * FROM household";
        PreparedStatement pS = mysqlConn.prepareStatement(sqlPrompt);
        ResultSet rS = pS.executeQuery();

        while (rS.next()) {
            int householdID = rS.getInt("household_id");
            String householdName = rS.getString("household_name");
            System.out.println(">>> Haushalt ID: " + householdID + " | Haushalt: " + householdName);
        }
    }

    public void printPets() throws SQLException {
        System.out.println("Diese Haustiere existieren bereits in der Datenbank");
        String sqlPrompt = "SELECT * FROM pets";
        PreparedStatement pS = mysqlConn.prepareStatement(sqlPrompt);
        ResultSet rS = pS.executeQuery();

        while (rS.next()) {
            int petID = rS.getInt("pet_id");
            String petLabel = rS.getString("pet_label");
            System.out.println(">>> Haustier ID: " + petID + " | Haustier: " + petLabel);
        }
    }

    public static boolean hasDigit(String s) {
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                return true;
            }
        }
        return false;
    }

    public void updateHousehold(Household household) throws SQLException {
        String sqlPrompt = "UPDATE household SET household_name = ? WHERE household_id = ?";
        PreparedStatement pS = mysqlConn.prepareStatement(sqlPrompt);
        pS.setString(1, household.getHouseholdName());
        pS.setInt(2, household.getHouseholdID());
        pS.executeUpdate();
    }

    public void updatePerson(Persons person) throws SQLException {
        String sqlPrompt = "UPDATE persons SET person_firstName = ?, person_lastName = ?, household_id = ? WHERE person_id = ?";
        PreparedStatement pS = mysqlConn.prepareStatement(sqlPrompt);
        pS.setString(1, person.getFirstName());
        pS.setString(2, person.getLastName());
        pS.setInt(3, person.getHouseholdID());
        pS.setInt(4, person.getPersonID());
        pS.executeUpdate();
    }

    public void updatePet(Pets pet) throws SQLException {
        String sqlPrompt = "UPDATE pets SET pet_label = ?, person_id = ? WHERE pet_id = ?";
        PreparedStatement pS = mysqlConn.prepareStatement(sqlPrompt);
        pS.setString(1, pet.getPetLabel());
        pS.setInt(2, pet.getPersonID());
        pS.setInt(3, pet.getPetID());
        pS.executeUpdate();
    }
}

