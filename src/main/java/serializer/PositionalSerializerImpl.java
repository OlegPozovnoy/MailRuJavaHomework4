package serializer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

public class PositionalSerializerImpl implements PositionalSerializer {
    private PositionalSerializerDetails serializerDetails;

    public PositionalSerializerImpl(PositionalSerializerDetails serializerDetails) {
        this.serializerDetails = serializerDetails;
    }

    public void setDecoder(PositionalSerializerDetails serializerDetails) {
        this.serializerDetails = serializerDetails;
    }

    public final String serialize(Object o) {
        try {
            return serializerDetails.openHeader(o.getClass().getName())
                    + serializeFields(o, 1).toString()
                    + "\n"
                    + serializerDetails.closeHeader(o.getClass().getName());
        } catch (IllegalAccessException e) {
            String message = "Conversion failed. IllegalAccessException: " + e.getMessage();
            System.err.println(message);
            throw new IllegalArgumentException(message, e);
        }
    }

    public StringBuilder serializeFields(Object o, int tabs) throws IllegalAccessException {
        StringBuilder result = new StringBuilder();
        Field[] fields = o.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType().isPrimitive() || field.get(o).getClass().getName().equals(String.class.getName())) {//Primitive
                result.append(serializerDetails.printPrimitive(field.getName(), field.get(o), tabs));
            } else if (Collection.class.isAssignableFrom(field.getType())) {//Collectiom
                result.append(serializerDetails.printCollection(field.getName(), (Collection<?>) field.get(o), tabs, this));
            } else if (field.getType().isArray()) {//Array
                result.append(serializerDetails.printCollection(field.getName(), Arrays.asList((Object[]) field.get(o)), tabs, this));
            } else {//not primitive and not an array
                result.append(serializerDetails.printStructure(field.getName(), field.get(o), tabs, this));
            }
        }
        return serializerDetails.postProcess(result);
    }

    public boolean isPrimitive(Object o) {
        return o.getClass().isPrimitive() || o.getClass().equals(String.class);
    }
}
