package clazzes;

import clazzes.Address;

import java.util.List;

public class Person {
    private final String firstName;
    private final String lastName;
    private final Address address;
    private final List<String> phoneNumbers;
    private final String[] str = {"ras","ras2"};
    private final List<Address> addresses;

    public Person(String firstName, String lastName, Address address, List<String> phoneNumbers, List<Address> addresses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumbers = phoneNumbers;
        this.addresses = addresses;
    }
}
