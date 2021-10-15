package io.github.liquidjoo.simplehiberante.db;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class H2Dialect {

    private static final Integer LONG = 1;
    private static final Integer STRING = 2;
    private static final Integer BIG_DECIMAL = 3;
    private static final Integer LOCAL_DATE = 4;

    private static Map<Integer, String> mapper;

    static {
        mapper = new HashMap<>();
        mapper.put(LONG, "bigint");
        mapper.put(STRING, "varchar");
        mapper.put(BIG_DECIMAL, "decimal");
        mapper.put(LOCAL_DATE, "date");

    }

    public static String getTypeValue(Integer type) {
        return mapper.get(type);
    }

    public static Integer convertType(Class<?> aClass) {
        if (aClass.equals(String.class)) {
            return STRING;
        }

        if (aClass.equals(Long.class)) {
            return LONG;
        }

        if (aClass.equals(BigDecimal.class)) {
            return BIG_DECIMAL;
        }

        if (aClass.equals(LocalDate.class)) {
            return LOCAL_DATE;
        }

        throw new IllegalArgumentException();
    }

}
