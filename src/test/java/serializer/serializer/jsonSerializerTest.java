package serializer.serializer;

import org.junit.jupiter.api.Test;
import serializer.Serializer;
import serializer.jsonSerializer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class jsonSerializerTest {
    Serializer json = new jsonSerializer();

    @Test
    public void testSimple() throws IllegalAccessException{
        String result = "{\n" +
                "  \"field1\": \"1\",\n" +
                "  \"field2\": \"2.0\"\n" +
                "}\n";
        assertEquals(result.trim(),json.serialize(new Test1()).trim());
    }

    @Test
    public void testSimple2() throws IllegalAccessException{
        String result ="{\n" +
                "  \"fields\": [\n" +
                "    \"1\",\n" +
                "    \"2\",\n" +
                "    \"3\"\n" +
                "  ]\n" +
                "}";
        assertEquals(result.trim(),json.serialize(new Test2()).trim());
    }

    @Test
    public void testSimple3() throws IllegalAccessException{
        String result = "{\n" +
                "  \"fields\": [\n" +
                "    \"1\",\n" +
                "    \"2\",\n" +
                "    \"3\"\n" +
                "  ]\n" +
                "}\n";
        assertEquals(result.trim(),json.serialize(new Test3()).trim());
    }

    @Test
    public void testSimple4() throws IllegalAccessException{
        String serializer =  json.serialize(new Test4());
        String result = "{\n" +
                "  \"test1\": {\n" +
                "    \"field1\": \"1\",\n" +
                "    \"field2\": \"2.0\"\n" +
                "  }\n" +
                "}";
        System.out.println(serializer);
        assertEquals(result.trim(),json.serialize(new Test4()).trim());
    }

    @Test
    public void testSimple5() throws IllegalAccessException{
        String result = "{\n" +
                "  \"fields\": [\n" +
                "    {\n" +
                "      \"fields\": [\n" +
                "        \"1\",\n" +
                "        \"2\",\n" +
                "        \"3\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";
        assertEquals(result.trim(),json.serialize(new Test5()).trim());
    }
}