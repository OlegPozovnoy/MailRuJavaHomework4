package serializer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

public class PositionalSerializer implements PositionalSerializerI {

    public final String serialize(Object o, PositionalSerializerDetailsI caller) {
        try {
            return caller.openHeader(o.getClass().getName())
                    + serializeFields(o, 1, caller).toString()
                    + "\n"
                    + caller.closeHeader(o.getClass().getName());
        } catch (IllegalAccessException e) {
            String message = "Conversion failed. IllegalAccessException: " + e.getMessage();
            System.err.println(message);
            throw new IllegalArgumentException(message);
        }
    }

    public final StringBuilder serializeFields(Object o, int tabs, PositionalSerializerDetailsI caller) throws IllegalAccessException {
        StringBuilder result = new StringBuilder();
        Field[] fields = o.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType().isPrimitive() || field.get(o).getClass().getName().equals(String.class.getName())) {//Primitive
                result.append(caller.printPrimitive(field.getName(), field.get(o), tabs));
            } else if (Collection.class.isAssignableFrom(field.getType())) {//Collectiom
                result.append(caller.printCollection(field.getName(), (Collection<?>) field.get(o), tabs));
            } else if (field.getType().isArray()) {//Array
                result.append(caller.printCollection(field.getName(), Arrays.asList((Object[]) field.get(o)), tabs));
            } else {//not primitive and not an array
                result.append(caller.printStructure(field.getName(), field.get(o), tabs));
            }
        }
        return caller.postProcess(result);
    }

    public boolean isPrimitive(Object o) {
        return o.getClass().isPrimitive() || o.getClass().getName().equals(String.class.getName());
    }
}
