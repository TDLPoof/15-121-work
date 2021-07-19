import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ContactList {
    private int MAX_CONTACTS = 4;
    private int lastIndex = 0;
    private Person[] contacts = new Person[MAX_CONTACTS];

    public static void main(String[] args) throws FileNotFoundException {
        ContactList contactList = new ContactList("data.txt");
        System.out.println(contactList);
        contactList.addContact("New", "456-789-0123");
        System.out.println(contactList);
        contactList.addContact("Errorboi", "18");
        System.out.println(contactList);
        contactList.moveToEnd("Bob");
        System.out.println(contactList);
        contactList.editContact("Bob", "yyy-yyy-yyyy");
        System.out.println(contactList);
    }

    public ContactList(String fileName) {
        try {
            load(fileName);
        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found, please try again.");
            e.printStackTrace();
        }
    }

    private void load(String fileName) throws FileNotFoundException {
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

    private void nullContacts() {
        for (int i = 0; i < MAX_CONTACTS; i++) {
            if (i > lastIndex)
                contacts[i] = null;
        }
    }

    private void copy(Person[] template, Person[] into) {
        for (int i = 0; i < template.length; i++) {
            into[i] = template[i];
        }
    }

    public void addContact(Person contact) {
        boolean inserted = false;
        for (int i = 0; i < MAX_CONTACTS; i++) {
            if (contacts[i] == null) {
                contacts[i] = contact;
                this.lastIndex += 1;
                inserted = true;
                break;
            }
        }
        if (!inserted) {
            MAX_CONTACTS *= 2;
            Person[] newContacts = new Person[MAX_CONTACTS];
            copy(contacts, newContacts);
            newContacts[this.lastIndex + 1] = contact;
            this.lastIndex += 1;
            contacts = newContacts;
        }
        nullContacts();
    }

    public void addContact(String name, String number) {
        Person contact = new Person(name, number);
        addContact(contact);
    }

    private void removeLastContact() {
        for (int i = MAX_CONTACTS - 1; i > 0; i--) {
            if (contacts[i] != null) {
                contacts[i] = null;
                break;
            }
        }
        nullContacts();
    }
    public void removeContact(String name) {
        Person removedPerson = lookUpContact(name);
        int index = Arrays.asList(contacts).indexOf(removedPerson);
        for (int i = index; i < MAX_CONTACTS; i++) {
            if (i > this.lastIndex) {
                break;
            }
            if (i < MAX_CONTACTS - 1)
                contacts[i] = contacts[i + 1];
            else
                contacts[i] = null;
        }
        this.lastIndex -= 1;
        nullContacts();
    }

    public Person lookUpContact(String name) {
        for (int i = 0; i < MAX_CONTACTS; i++) {
            if (contacts[i] != null && contacts[i].getName().equals(name)) {
                return contacts[i];
            }
        }
        System.err.println("Error: Contact not found.");
        return new Person("", "");
    }
    public void editContact(String name, String newNumber) {
        lookUpContact(name).setNumber(newNumber);
    }
    public void editContact(int index, String newNumber) {
        if (index < MAX_CONTACTS) {
            contacts[index].setNumber(newNumber);
        }
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < MAX_CONTACTS; i++) {
            if (contacts[i] == null) {
                continue;
            }
            s = s + contacts[i].toString() + "\n";
        }
        return s;
    }

    /* homework 4 */

    public void moveToEnd(int index) {
        for (int i = index; i < MAX_CONTACTS - 1; i++) {
            if (contacts[i + 1] == null) {
                break;
            }
            Person swapIndex = contacts[i];
            contacts[i] = contacts[i + 1];
            contacts[i + 1] = swapIndex;
        }
    }
    public void moveToEnd(String name) {
        Person contact = lookUpContact(name);
        removeContact(name);
        this.lastIndex += 1;
        contacts[this.lastIndex] = contact;
    }
}

