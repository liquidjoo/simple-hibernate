package io.github.liquidjoo.simplehiberante.entity.column;


import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.lang.reflect.Field;

public interface EntityColumn {

    String getFieldName();

    Object getFieldValue(Object entity);

    void assignFieldValue(Object instance, Object value);

    ColumnType getColumnType();

    boolean isNullable();

    boolean isId();

    GenerationType getGenerationType();

    static boolean isAvailableCreateEntityColumn(final Field field) {
        return !field.isAnnotationPresent(Transient.class);
    }

    static EntityColumn create(final Field field) {
        if (field.isAnnotationPresent(Id.class)) {
            return new EntityId(field);
        }
        return new EntityField(field);
    }
}
