package serializer.serializer;

import org.junit.jupiter.api.Test;
import serializer.JSONSerializerI;
import serializer.Serializer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JSONSerializerTest {
    private Serializer json = new JSONSerializerI();

    @Test
    void testSimple() {
        String result = "{\n" +
                "  \"field1\": \"1\",\n" +
                "  \"field2\": \"2.0\"\n" +
                "}\n";
        assertEquals(result.trim(), json.serialize(new Test1()).trim());
    }

    @Test
    void testSimple2() {
        String result = "{\n" +
                "  \"fields\": [\n" +
                "    \"1\",\n" +
                "    \"2\",\n" +
                "    \"3\"\n" +
                "  ]\n" +
                "}";
        assertEquals(result.trim(), json.serialize(new Test2()).trim());
    }

    @Test
    void testSimple3() {
        String result = "{\n" +
                "  \"fields\": [\n" +
                "    \"1\",\n" +
                "    \"2\",\n" +
                "    \"3\"\n" +
                "  ]\n" +
                "}\n";
        assertEquals(result.trim(), json.serialize(new Test3()).trim());
    }

    @Test
    void testSimple4() {
        String serializer = json.serialize(new Test4());
        String result = "{\n" +
                "  \"test1\": {\n" +
                "    \"field1\": \"1\",\n" +
                "    \"field2\": \"2.0\"\n" +
                "  }\n" +
                "}";
        System.out.println(serializer);
        assertEquals(result.trim(), json.serialize(new Test4()).trim());
    }

    @Test
    void testSimple5() {
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
        assertEquals(result.trim(), json.serialize(new Test5()).trim());
    }
}