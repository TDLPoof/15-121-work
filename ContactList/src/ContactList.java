import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ContactList {
    private final int MAX_CONTACTS = 256;
    private Person[] contacts;

    public ContactList(String fileName) {
        try {
            Scanner s = new Scanner(new File(fileName));
            String name = "";
            String number = "";
            while (s.hasNext()) {
                name = s.next();
                if (s.hasNext()) { // increments by every name-number pair
                    number = s.next();
                }
                addContact(new Person(name, number));
            }
        }
        catch (FileNotFoundException fnfe) {
            System.err.println("Error: File not found.");

        }
    }

    public void addContact(Person contact) {
        for (int i = 0; i < MAX_CONTACTS; i++) {
            if (contacts[i] == null) {
                contacts[i] = contact;
                break;
            }
        }
    }
    public void removeContact() {
        for (int i = MAX_CONTACTS - 1; i > 0; i--) {
            if (contacts[i] != null) {
                contacts[i] = null;
                break;
            }
        }
    }

    public Person lookUpContact(String name) {
        for (int i = 0; i < MAX_CONTACTS; i++) {
            if (contacts[i].getName().equals(name)) {
                return contacts[i]);
            }
        }
        return null;
    }
    public void editContact(String name, String newNumber) {
        lookUpContact(name).setNumber(newNumber);
    }
    public void editContact(int index, String newNumber) {
        if (index < MAX_CONTACTS) {
            contacts[index].setNumber(newNumber);
        }

    }
}

