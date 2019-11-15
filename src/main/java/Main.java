import clazzes.Address;
import clazzes.Person;
import serializer.jsonSerializer;
import serializer.xmlSerializer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) throws IllegalAccessException {
        Address address = new Address("Moscow", "112233");
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("aaaa");
        phoneNumbers.add("ssss");

        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("One", "1"));
        addresses.add(new Address("Tow", "2"));
        Person person = new Person("First", "Last", address, phoneNumbers, addresses);

        GeneralDecoder decoder = new GeneralDecoder();
        decoder.setSerializer(new jsonSerializer());
        System.out.println(decoder.serialize(person));
        decoder.setSerializer(new xmlSerializer());
        System.out.println(decoder.serialize(person));
    }
}
