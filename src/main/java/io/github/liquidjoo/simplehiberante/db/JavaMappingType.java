package io.github.liquidjoo.simplehiberante.db;

import java.sql.Types;
import java.util.HashMap;

public class JavaMappingType {

    private HashMap<Integer, Class> jdbcTypeCodeToJavaClassMap;
    private HashMap<Class, Integer> jdbcClassToJdbcTypeCodeMap;

    public JavaMappingType() {
        this.jdbcClassToJdbcTypeCodeMap = jdbcClassToJdbcTypeCodeMappings();
        this.jdbcTypeCodeToJavaClassMap = jdbcTypeCodeToJavaClassMappings();

    }

    private HashMap<Class, Integer> jdbcClassToJdbcTypeCodeMappings() {
        HashMap<Class, Integer> map = new HashMap<>();
        map.put(String.class, Types.VARCHAR);
        map.put(Long.class, Types.BIGINT);
        map.put(Boolean.class, Types.BIT);
        return map;
    }

    private HashMap<Integer, Class> jdbcTypeCodeToJavaClassMappings() {
        HashMap<Integer, Class> map = new HashMap<>();
        map.put(Types.VARCHAR, String.class);
        map.put(Types.BIGINT, Long.class);
        return map;
    }

    public Class getJavaClassTypeByCode(Integer typeCode) {
        return jdbcTypeCodeToJavaClassMap.get(typeCode);
    }

    public Integer getTypeCodeByClass(Class clazz) {
        return jdbcClassToJdbcTypeCodeMap.get(clazz);
    }

}
