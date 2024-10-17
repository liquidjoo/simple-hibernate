package io.github.liquidjoo.simplehiberante.entity.column;


import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.Transient;
import java.lang.reflect.Field;

public class EntityField implements EntityColumn {

    private final String fieldName;
    private final ColumnType columnType;
    private final boolean isNullable;
    private final Field field;

    public EntityField(final Field field) {
        if (field.isAnnotationPresent(Transient.class)) {
            throw new IllegalArgumentException("Transient이 붙은 필드는 생성될 수 없습니다.");
        }
        this.fieldName = parseFieldName(field);
        this.columnType = ColumnType.valueOf(field.getType());
        this.isNullable = parseNullable(field);
        this.field = field;
    }

    private String parseFieldName(final Field field) {
        if (!field.isAnnotationPresent(Column.class)) {
            return field.getName();
        }
        String fieldName = field.getAnnotation(Column.class).name();
        if (fieldName.isEmpty()) {
            return field.getName();
        }
        return fieldName;
    }

    private boolean parseNullable(final Field field) {
        if (!field.isAnnotationPresent(Column.class)) {
            return true;
        }
        return field.getAnnotation(Column.class).nullable();
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public Object getFieldValue(final Object entity) {
        try {
            field.setAccessible(true);
            return field.get(entity);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("필드값에 접근할 수 없습니다.");
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Entity 객체에 일치하는 필드값이 없습니다.");
        } finally {
            field.setAccessible(false);
        }
    }

    @Override
    public void assignFieldValue(final Object entity, final Object value) {
        try {
            field.setAccessible(true);
            field.set(entity, value);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("필드값에 접근할 수 없습니다.");
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Entity 객체에 일치하는 필드값이 없습니다.");
        } finally {
            field.setAccessible(false);
        }
    }

    @Override
    public ColumnType getColumnType() {
        return columnType;
    }

    @Override
    public boolean isNullable() {
        return isNullable;
    }

    @Override
    public boolean isId() {
        return false;
    }

    @Override
    public GenerationType getGenerationType() {
        throw new IllegalStateException("일반 Field는 GenerationType을 호출할 수 없습니다.");
    }
}
