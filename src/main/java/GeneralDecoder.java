import serializer.Serializer;

public class GeneralDecoder {
    private Serializer serializer;

    void setSerializer(Serializer serializer) {
        this.serializer = serializer;
    }

    String serialize(Object o) throws IllegalAccessException {
        return serializer.serialize(o);
    }
}
