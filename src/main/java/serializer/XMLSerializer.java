package serializer;

import java.util.Collection;

public final class XMLSerializer extends PositionalSerializer {
    public StringBuilder postProcess(StringBuilder result) {
        return result;
    }

    private StringBuilder printArrayElement(Object obj, int level) throws IllegalAccessException {
        return isPrimitive(obj) ?
                new StringBuilder(obj.toString())
                : serializeFields(obj, level + 2).append(printIndent(level + 1));
    }

    private String printIndent(int size) {
        return "\n" + "  ".repeat(size);
    }

    protected String printPrimitive(String name, Object value, int level) {
        return printIndent(level)
                + openHeader(name)
                + value.toString()
                + closeHeader(name);
    }

    protected StringBuilder printCollection(String name, Collection<?> col, int level) throws IllegalAccessException {
        StringBuilder result = new StringBuilder(printIndent(level) + openHeader(name));
        int i = 1;
        for (Object obj : col) {
            result.append(printIndent(level + 1))
                    .append(openHeader(String.valueOf(i)))
                    .append(printArrayElement(obj, level))
                    .append(closeHeader(String.valueOf(i)));
            i++;
        }
        result.append(printIndent(level))
                .append(closeHeader(name));
        return result;
    }

    protected String printStructure(String name, Object o, int level) throws IllegalAccessException {
        return printIndent(level)
                + openHeader(name)
                + serializeFields(o, level + 1)
                + printIndent(level)
                + closeHeader(name);
    }

    protected String openHeader(String name) {
        return "<" + name + ">";
    }

    protected String closeHeader(String name) {
        return "</" + name + ">";
    }
}
