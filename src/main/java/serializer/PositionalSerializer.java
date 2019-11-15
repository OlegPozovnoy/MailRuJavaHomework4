package serializer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

public abstract class PositionalSerializer implements  Serializer {
    @Override
    public String serialize(Object o) throws IllegalAccessException {
        return openHeader(o.getClass().getName()).append(serializeFields(o, 1)).append("\n").append(closeHeader(o.getClass().getName())).toString();
    }

    public StringBuilder serializeFields(Object o, int tabs) throws IllegalAccessException {
        StringBuilder result = new StringBuilder();
        Field[] fields = o.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType().isPrimitive() || field.get(o).getClass().getName().equals("".getClass().getName())) {//Primitive
                result.append(printPrimitive(field.getName(), field.get(o), tabs));
            } else if (Collection.class.isAssignableFrom(field.getType())) {//Collectiom
                result.append(printCollection(field.getName(), (Collection<?>) field.get(o), tabs));
            } else if (field.getType().isArray()) {//Array
                result.append(printCollection(field.getName(), Arrays.asList((Object[]) field.get(o)), tabs));
            } else//not primitive and not array
            {
                result.append(printStructure(field.getName(), field.get(o), tabs));
            }
        }
        return postProcess(result);
    }
    // различные преобразования строки на шаге вроде убирания последней запятой
    public abstract StringBuilder postProcess(StringBuilder result);
    // как выводим primitives
    public abstract StringBuilder printPrimitive(String name, Object value, int level);
    // как выводим коллекции
    public abstract StringBuilder printCollection(String name, Collection<?> col, int level) throws IllegalAccessException;
    // как выводим вложенные обьекты
    public abstract StringBuilder printStructure(String name, Object o, int level)throws IllegalAccessException;
    // префикс всего обьекта
    public abstract StringBuilder openHeader(String name);
    // закрытие всего обьекта
    public abstract StringBuilder closeHeader(String name);

    public boolean isPrimitive(Object o) {
        return o.getClass().isPrimitive() || o.getClass().getName().equals("".getClass().getName());
    }
}
