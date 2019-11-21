package serializer;

import java.util.Collection;

public final class JSONSerializer implements Serializer, PositionalSerializerDetailsI {

    private PositionalSerializer serializer = new PositionalSerializer();

    public String serialize(Object o) {
        return serializer.serialize(o, this);
    }

    //удаляем последнюю запятую в последовательности элементов
    public StringBuilder postProcess(StringBuilder result) {
        return result.deleteCharAt(result.length() - 1);
    }

    private StringBuilder printArrayElement(Object obj, int level) throws IllegalAccessException {
        return serializer.isPrimitive(obj) ?
                new StringBuilder(quoted(obj.toString()))
                : new StringBuilder("{")
                .append(serializer.serializeFields(obj, level + 2, this))
                .append(printIndent(level + 1))
                .append("}");
    }

    public String printPrimitive(String name, Object value, int level) {
        return printIndent(level) + formatKey(name) + quoted(value.toString()) + ",";
    }

    public StringBuilder printCollection(String name, Collection<?> col, int level) throws IllegalAccessException {
        StringBuilder result = new StringBuilder(printIndent(level) + formatKey(name) + "[");
        for (Object obj : col) {
            result.append(printIndent(level + 1)).append(printArrayElement(obj, level)).append(",");
        }
        // удаляем последнюю запятую
        result.deleteCharAt(result.length() - 1);
        result.append(printIndent(level)).append("],");
        return result;
    }

    public String printStructure(String name, Object o, int level) throws IllegalAccessException {
        return printIndent(level) + formatKey(name) + "{" + serializer.serializeFields(o, level + 1, this) + printIndent(level) + "},";
    }

    public String openHeader(String name) {
        return "{";
    }

    public String closeHeader(String name) {
        return "}";
    }

    // задаем отступ для данного формата
    private String printIndent(int level) {
        return "\n" + "  ".repeat(level);
    }

    private String quoted(String str) {
        return "\"" + str + "\"";
    }

    private String formatKey(String name) {
        return quoted(name) + ": ";
    }
}
