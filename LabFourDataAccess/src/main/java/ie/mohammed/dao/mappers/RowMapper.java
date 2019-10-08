package ie.mohammed.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
	T MapRow (ResultSet rs, int rowNumber) throws SQLException;
}
