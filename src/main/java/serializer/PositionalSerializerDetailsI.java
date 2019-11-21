package serializer;

import java.util.Collection;

public interface PositionalSerializerDetailsI {
    // различные преобразования строки на шаге вроде убирания последней запятой
    StringBuilder postProcess(StringBuilder result);

    // как выводим primitives
    String printPrimitive(String name, Object value, int level);

    // как выводим коллекции
    StringBuilder printCollection(String name, Collection<?> col, int level) throws IllegalAccessException;

    // как выводим вложенные обьекты
    String printStructure(String name, Object o, int level) throws IllegalAccessException;

    // префикс всего обьекта
    String openHeader(String name);

    // закрытие всего обьекта
    String closeHeader(String name);
}
