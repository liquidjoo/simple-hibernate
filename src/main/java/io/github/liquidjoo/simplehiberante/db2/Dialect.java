package io.github.liquidjoo.simplehiberante.db2;

import java.sql.Types;
import java.util.HashMap;

public abstract class Dialect {

    private HashMap<Integer, String> map = new HashMap<>();

    public Dialect() {

        registerColumnType(Types.VARCHAR, "varchar(255)");
        registerColumnType(Types.BIGINT, "bigint");
        registerColumnType(Types.BLOB, "TEXT");
        registerColumnType(Types.DATE, "date");

    }

    protected void registerColumnType(int code, String name) {
        map.put(code, name);
    }

    public String getColumnDefine(int typeCode) {
        return map.get(typeCode);
    }
}
