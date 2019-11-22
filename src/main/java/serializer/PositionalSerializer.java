package serializer;

public interface PositionalSerializer extends Serializer {
    boolean isPrimitive(Object o);

    StringBuilder serializeFields(Object o, int tabs) throws IllegalAccessException;

    void setDecoder(PositionalSerializerDetails serializerDetails);
}
