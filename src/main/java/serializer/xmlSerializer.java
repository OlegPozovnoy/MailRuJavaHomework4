package serializer;

import helpers.StringHelper;

import java.util.Collection;

public class xmlSerializer extends PositionalSerializer {
    public StringBuilder postProcess(StringBuilder result) {
        return result;
    }

    private StringBuilder printArrayElement(Object obj, int level) throws IllegalAccessException {
        if (isPrimitive(obj))
            return new StringBuilder(obj.toString());
        else
            return new StringBuilder(serializeFields(obj, level + 2))
                    .append(printIndent(level + 1));
    }

    private StringBuilder printIndent(int size) {
        return (new StringBuilder("\n")).append(StringHelper.repeat("  ", size));
    }

    public StringBuilder printPrimitive(String name, Object value, int level) {
        return printIndent(level).append(openHeader(name))
                .append(value.toString())
                .append(closeHeader(name));
    }

    public StringBuilder printCollection(String name, Collection<?> col, int level) throws IllegalAccessException {
        StringBuilder result = printIndent(level).append(openHeader(name));
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

    public StringBuilder printStructure(String name, Object o, int level) throws IllegalAccessException {
        return printIndent(level).append(openHeader(name))
                .append(serializeFields(o, level + 1))
                .append(printIndent(level))
                .append(closeHeader(name));
    }

    public StringBuilder openHeader(String name) {
        return new StringBuilder("<").append(name).append(">");
    }

    public StringBuilder closeHeader(String name) {
        return new StringBuilder("</").append(name).append(">");
    }
}
