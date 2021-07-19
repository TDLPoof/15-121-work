import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ContactArrayList {
    private ArrayList<Person> contacts = new ArrayList<Person>();

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

    public ContactArrayList(String fileName) {
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
            addContact(name, number);
        }
    }

    public void addContact(Person contact) {
        contacts.add(contact);
    }

    public void addContact(String name, String number) {
        Person contact = new Person(name, number);
        addContact(contact);
    }
    public void removeContact(String name) {
        contacts.remove(lookUpContact(name));
    }

    public Person lookUpContact(String name) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equals(name)) {
                return contacts.get(i);
            }
        }
        return new Person("", "");
    }
    public void editContact(String name, String newNumber) {
        lookUpContact(name).setNumber(newNumber);
    }
    public void editContact(int index, String newNumber) {
        if (index < contacts.size()) {
            contacts.get(index).setNumber(newNumber);
        }
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i) == null) {
                continue;
            }
            s = s + contacts.get(i).toString() + "\n";
        }
        return s;
    }
}

