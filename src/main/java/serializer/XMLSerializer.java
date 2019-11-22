package serializer;

import java.util.Collection;

public final class XMLSerializer implements PositionalSerializerDetails {
    public StringBuilder postProcess(StringBuilder result) {
        return result;
    }

    private StringBuilder printArrayElement(Object obj, int level, PositionalSerializer serializer) throws IllegalAccessException {
        return serializer.isPrimitive(obj) ?
                new StringBuilder(obj.toString())
                : serializer.serializeFields(obj, level + 2).append(printIndent(level + 1));
    }

    private String printIndent(int size) {
        return "\n" + "  ".repeat(size);
    }

    public String printPrimitive(String name, Object value, int level) {
        return printIndent(level)
                + openHeader(name)
                + value.toString()
                + closeHeader(name);
    }

    public StringBuilder printCollection(String name, Collection<?> col, int level, PositionalSerializer serializer) throws IllegalAccessException {
        StringBuilder result = new StringBuilder(printIndent(level) + openHeader(name));
        int i = 1;
        for (Object obj : col) {
            result.append(printIndent(level + 1))
                    .append(openHeader(String.valueOf(i)))
                    .append(printArrayElement(obj, level, serializer))
                    .append(closeHeader(String.valueOf(i)));
            i++;
        }
        result.append(printIndent(level))
                .append(closeHeader(name));
        return result;
    }

    public String printStructure(String name, Object o, int level, PositionalSerializer serializer) throws IllegalAccessException {
        return printIndent(level)
                + openHeader(name)
                + serializer.serializeFields(o, level + 1)
                + printIndent(level)
                + closeHeader(name);
    }

    public String openHeader(String name) {
        return "<" + name + ">";
    }

    public String closeHeader(String name) {
        return "</" + name + ">";
    }
}
