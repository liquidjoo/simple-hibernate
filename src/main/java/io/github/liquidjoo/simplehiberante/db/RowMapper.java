package io.github.liquidjoo.simplehiberante.db;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowMapper<T> {
    T mapRow(final ResultSet resultSet) throws SQLException;
}
