package io.github.liquidjoo.simplehiberante.query;

import io.github.liquidjoo.simplehiberante.db.H2Dialect;

public class QueryGenerator {

    private static final String CREATE_TABLE_TEMPLATE = "CREATE TABLE %s (%s)";
    private static final String DROP_TABLE_TEMPLATE = "DROP TABLE IF EXISTS %s";
    private static final String ALTER_TABLE_TEMPLATE = "ALTER TABLE IF EXISTS %s %s %s";
    private static final String TRUNCATE_TABLE_TEMPLATE = "TRUNCATE TABLE %s";

    public static String getCreateQuery(String tableName, String fields) {
        return String.format(CREATE_TABLE_TEMPLATE, tableName, fields);
    }

    public static String getDropTable(String tableName) {
        return String.format(DROP_TABLE_TEMPLATE, tableName);
    }

    public static String getAlterTableByColumnAdd(String tableName, String columnName, Class<?> columnType) {
        String columnInfo = columnName + " " + H2Dialect.getTypeValue(H2Dialect.convertType(columnType));

        return String.format(ALTER_TABLE_TEMPLATE, tableName, "ADD", columnInfo);
    }

    public static String getTruncateTable(String tableName) {
        return String.format(TRUNCATE_TABLE_TEMPLATE, tableName);
    }
}
