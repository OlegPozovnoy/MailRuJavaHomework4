package serializer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

public abstract class PositionalSerializer implements Serializer {
    @Override
    public final String serialize(Object o) {
        try {
            return openHeader(o.getClass().getName())
                    + serializeFields(o, 1).toString()
                    + "\n"
                    + closeHeader(o.getClass().getName());
        } catch (IllegalAccessException e) {
            String message = "Conversion failed. IllegalAccessException: " + e.getMessage();
            System.err.println(message);
            throw new IllegalArgumentException(message);
        }
    }

    protected final StringBuilder serializeFields(Object o, int tabs) throws IllegalAccessException {
        StringBuilder result = new StringBuilder();
        Field[] fields = o.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType().isPrimitive() || field.get(o).getClass().getName().equals(String.class.getName())) {//Primitive
                result.append(printPrimitive(field.getName(), field.get(o), tabs));
            } else if (Collection.class.isAssignableFrom(field.getType())) {//Collectiom
                result.append(printCollection(field.getName(), (Collection<?>) field.get(o), tabs));
            } else if (field.getType().isArray()) {//Array
                result.append(printCollection(field.getName(), Arrays.asList((Object[]) field.get(o)), tabs));
            } else {//not primitive and not an array
                result.append(printStructure(field.getName(), field.get(o), tabs));
            }
        }
        return postProcess(result);
    }

    // различные преобразования строки на шаге вроде убирания последней запятой
    protected abstract StringBuilder postProcess(StringBuilder result);

    // как выводим primitives
    protected abstract String printPrimitive(String name, Object value, int level);

    // как выводим коллекции
    protected abstract StringBuilder printCollection(String name, Collection<?> col, int level) throws IllegalAccessException;

    // как выводим вложенные обьекты
    protected abstract String printStructure(String name, Object o, int level) throws IllegalAccessException;

    // префикс всего обьекта
    protected abstract String openHeader(String name);

    // закрытие всего обьекта
    protected abstract String closeHeader(String name);

    protected final boolean isPrimitive(Object o) {
        return o.getClass().isPrimitive() || o.getClass().getName().equals(String.class.getName());
    }
}
