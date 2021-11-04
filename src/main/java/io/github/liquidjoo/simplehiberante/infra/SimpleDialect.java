package io.github.liquidjoo.simplehiberante.infra;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class SimpleDialect {

    private static Map<Class, String> dialect = new HashMap<>();
    static {
        dialect.put(String.class, "varchar(20)");
        dialect.put(Long.class, "bigint");
        dialect.put(BigDecimal .class, "bigint");
    }

    public static String convertType(Class clazz) {
        return dialect.get(clazz);
    }
}
