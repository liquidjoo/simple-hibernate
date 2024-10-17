package io.github.liquidjoo.simplehiberante.db2;


import java.sql.Types;

public class MysqlDialect extends Dialect{

    public MysqlDialect() {
        registerColumnType(Types.DATE, "datetime");
    }


    @Override
    public String getColumnDefine(int typeCode) {
        return super.getColumnDefine(typeCode);
    }
}
