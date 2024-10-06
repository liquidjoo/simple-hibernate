package io.github.liquidjoo.simplehiberante.flow;

import io.github.liquidjoo.simplehiberante.model.Car;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.Table;
import org.hibernate.sql.Insert;
import org.hibernate.sql.Select;

import java.util.Iterator;

public class ServiceFlow {



    private void flowTest() {
        MetadataSources metadataSources = new MetadataSources(new StandardServiceRegistryBuilder().build());
        metadataSources.addAnnotatedClass(Car.class);
        Metadata metadata = metadataSources.buildMetadata();
        ;

        Insert insert = new Insert(metadata.getDatabase().getDialect());
        Select select = new Select(metadata.getDatabase().getDialect());

        Table carTable = metadata.getEntityBinding("car").getTable();
        Iterator<Column> columnIterator = carTable.getColumnIterator();
        while (columnIterator.hasNext()) {

        }

        carTable.getPrimaryKey();


    }
}
