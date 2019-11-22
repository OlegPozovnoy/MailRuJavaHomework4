import clazzes.Address;
import clazzes.Person;
import serializer.JSONSerializer;
import serializer.PositionalSerializer;
import serializer.PositionalSerializerImpl;
import serializer.XMLSerializer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        Address address = new Address("Moscow", "112233");
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("aaaa");
        phoneNumbers.add("ssss");

        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("One", "1"));
        addresses.add(new Address("Tow", "2"));
        Person person = new Person("First", "Last", address, phoneNumbers, addresses);

        PositionalSerializer decoder = new PositionalSerializerImpl(new JSONSerializer());
        System.out.println(decoder.serialize(person));
        decoder.setDecoder(new XMLSerializer());
        System.out.println(decoder.serialize(person));
    }
}
