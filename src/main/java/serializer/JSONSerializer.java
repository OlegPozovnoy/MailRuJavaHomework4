package serializer;

import java.util.Collection;

public final class JSONSerializer extends PositionalSerializer {
    //удаляем последнюю запятую в последовательности элементов
    protected StringBuilder postProcess(StringBuilder result) {
        return result.deleteCharAt(result.length() - 1);
    }

    private StringBuilder printArrayElement(Object obj, int level) throws IllegalAccessException {
        return isPrimitive(obj) ?
                new StringBuilder(quoted(obj.toString()))
                : new StringBuilder("{")
                .append(serializeFields(obj, level + 2))
                .append(printIndent(level + 1))
                .append("}");
    }

    protected String printPrimitive(String name, Object value, int level) {
        return printIndent(level) + formatKey(name) + quoted(value.toString()) + ",";
    }

    protected StringBuilder printCollection(String name, Collection<?> col, int level) throws IllegalAccessException {
        StringBuilder result = new StringBuilder(printIndent(level) + formatKey(name) + "[");
        for (Object obj : col) {
            result.append(printIndent(level + 1)).append(printArrayElement(obj, level)).append(",");
        }
        // удаляем последнюю запятую
        result.deleteCharAt(result.length() - 1);
        result.append(printIndent(level)).append("],");
        return result;
    }

    protected String printStructure(String name, Object o, int level) throws IllegalAccessException {
        return printIndent(level) + formatKey(name) + "{" + serializeFields(o, level + 1) + printIndent(level) + "},";
    }

    protected String openHeader(String name) {
        return "{";
    }

    protected String closeHeader(String name) {
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
