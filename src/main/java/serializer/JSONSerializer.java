package serializer;

import helpers.StringHelper;

import java.util.Collection;

public class JSONSerializer extends PositionalSerializer {
    //удаляем последнюю запятую в последовательности элементов
    public StringBuilder postProcess(StringBuilder result) {
        return result.deleteCharAt(result.length() - 1);
    }

    private StringBuilder printArrayElement(Object obj, int level) throws IllegalAccessException {
        if (isPrimitive(obj))
            return new StringBuilder(quoted(obj.toString()));
        else
            return new StringBuilder("{")
                    .append(serializeFields(obj, level + 2))
                    .append(printIndent(level + 1))
                    .append("}");
    }

    public StringBuilder printPrimitive(String name, Object value, int level) {
        return printIndent(level)
                .append(quoted(name))
                .append(": ")
                .append(quoted(value.toString()))
                .append(",");
    }

    public StringBuilder printCollection(String name, Collection<?> col, int level) throws IllegalAccessException {
        StringBuilder result = printIndent(level).append(quoted(name)).append(": [");
        for (Object obj : col) {
            result.append(printIndent(level + 1)).append(printArrayElement(obj, level)).append(",");
        }
        // удаляем последнюю запятую
        result.deleteCharAt(result.length() - 1);
        result.append(printIndent(level)).append("],");
        return result;
    }

    public StringBuilder printStructure(String name, Object o, int level) throws IllegalAccessException {
        return printIndent(level).append(quoted(name))
                .append(": {")
                .append(serializeFields(o, level + 1))
                .append(printIndent(level)
                        .append("},"));
    }

    public StringBuilder openHeader(String name) {
        return new StringBuilder("{");
    }

    public StringBuilder closeHeader(String name) {
        return new StringBuilder("}");
    }

    // задаем отступ для данного формата
    private StringBuilder printIndent(int level) {
        return (new StringBuilder("\n")).append(StringHelper.repeat("  ", level));
    }

    private StringBuilder quoted(String str) {
        return new StringBuilder("\"").append(str).append("\"");
    }
}
