package io.github.liquidjoo.simplehiberante.infra;

import io.github.liquidjoo.simplehiberante.annotation.SimpleColumn;
import io.github.liquidjoo.simplehiberante.annotation.SimpleId;
import io.github.liquidjoo.simplehiberante.product.Product;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class SimpleQueryBuilderTest {

    @Test
    void name() {
        Class<Product> clazz = Product.class;

        String tableName = clazz.getSimpleName();

        String query = selectAllQuery(tableName);

        for (Field declaredField : clazz.getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(SimpleId.class)) {
                // TODO: 2021/11/04 id 가져옴 (프라이머리 키)
            }
            // TODO: 2021/11/04 컬럼값들

        }

        String query1 = createQuery(Product.class);
        System.out.println(query1);

    }

    @Test
    void insertQueryTest() throws InvocationTargetException, InstantiationException, IllegalAccessException {

        String query = insertQuery(Product.class, new Product(1L, "seongju", "cup", BigDecimal.valueOf(1000)));
        System.out.println(query);
    }

    private String insertQuery(Class type, Product product) throws IllegalAccessException {

        String tableName = type.getSimpleName();

        List<String> fields = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (Field declaredField : type.getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(SimpleId.class) || declaredField.isAnnotationPresent(SimpleColumn.class)) {
                fields.add(declaredField.getName());
                declaredField.setAccessible(true);
                if (declaredField.getType() == String.class) {
                    values.add("'" + declaredField.get(product).toString() + "'");
                } else {
                    values.add(declaredField.get(product).toString());
                }

            }
        }

        return  "insert into "
                + tableName
                + " (" + String.join(", ", fields) + ") "
                + "values "
                + "("
                + String.join(", ", values)
                + ");";
    }

    private String selectAllQuery(String tableName) { // create or generate
        return "select * from " + tableName;
    }

    private String createQuery(Class type) { // create or generate
        String tableName = type.getSimpleName();

        String primaryKey = "PRIMARY KEY (%s)";
        List<String> fields = new ArrayList<>();
        for (Field declaredField : type.getDeclaredFields()) {
            // TODO: 2021/11/04 컬럼값들
            fields.add(declaredField.getName() + " " + SimpleDialect.convertType(declaredField.getType()));
            if (declaredField.isAnnotationPresent(SimpleId.class)) {
                primaryKey = String.format(primaryKey, declaredField.getName());
            }
        }
        String query = "create table  "
                + tableName
                + " (\n "
                + String.join(", \n", fields) + ", \n"
                + primaryKey
                + ");";


        return query;
    }

    /*
    CREATE TABLE tutorials_tbl (
        id INT NOT NULL,
        title VARCHAR(50) NOT NULL,
        author VARCHAR(20) NOT NULL,
        submission_date DATE
   );


CREATE TABLE PUBLIC.TEST_TABLE

(COL1 INTEGER NOT NULL,

COL2 CHAR(25),

COL3 VARCHAR(25) NOT NULL,

COL4 DATE,

PRIMARY KEY (COL1))
     */
    @Test
    public void name2() throws Exception {
        final Product product = new Product();
        final Field[] fields = product.getClass().getDeclaredFields();
        for (final Field field : fields) {
            field.setAccessible(true);
            field.get(product);
        }
    }
}
