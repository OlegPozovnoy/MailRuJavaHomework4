package serializer.serializer;

import org.junit.jupiter.api.Test;
import serializer.Serializer;
import serializer.XMLSerializerI;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XMLSerializerTest {
    Serializer xml = new XMLSerializerI();

    @Test
    void testSimple() {
        String result = "<serializer.serializer.Test1>\n" +
                "  <field1>1</field1>\n" +
                "  <field2>2.0</field2>\n" +
                "</serializer.serializer.Test1>";
        assertEquals(result.trim(),xml.serialize(new Test1()).trim());
    }

    @Test
    void testSimple2() {
        String result ="<serializer.serializer.Test2>\n" +
                "  <fields>\n" +
                "    <1>1</1>\n" +
                "    <2>2</2>\n" +
                "    <3>3</3>\n" +
                "  </fields>\n" +
                "</serializer.serializer.Test2>\n";
        assertEquals(result.trim(),xml.serialize(new Test2()).trim());
    }

    @Test
    void testSimple3() {
        String result = "<serializer.serializer.Test3>\n" +
                "  <fields>\n" +
                "    <1>1</1>\n" +
                "    <2>2</2>\n" +
                "    <3>3</3>\n" +
                "  </fields>\n" +
                "</serializer.serializer.Test3>";
        assertEquals(result.trim(),xml.serialize(new Test3()).trim());
    }

    @Test
    void testSimple4() {
        String result = "<serializer.serializer.Test4>\n" +
                "  <test1>\n" +
                "    <field1>1</field1>\n" +
                "    <field2>2.0</field2>\n" +
                "  </test1>\n" +
                "</serializer.serializer.Test4>";
        assertEquals(result.trim(),xml.serialize(new Test4()).trim());
    }

    @Test
    void testSimple5() {
        String result = "<serializer.serializer.Test5>\n" +
                "  <fields>\n" +
                "    <1>\n" +
                "      <fields>\n" +
                "        <1>1</1>\n" +
                "        <2>2</2>\n" +
                "        <3>3</3>\n" +
                "      </fields>\n" +
                "    </1>\n" +
                "  </fields>\n" +
                "</serializer.serializer.Test5>";
        assertEquals(result.trim(),xml.serialize(new Test5()).trim());
    }
}
