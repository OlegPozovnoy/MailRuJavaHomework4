package serializer;

public interface PositionalSerializerI {
    String serialize(Object o, PositionalSerializerDetailsI caller);

    StringBuilder serializeFields(Object o, int tabs, PositionalSerializerDetailsI caller) throws IllegalAccessException;

    boolean isPrimitive(Object o);
}
