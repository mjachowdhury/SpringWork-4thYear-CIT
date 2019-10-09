/**
 * 
 */
package ie.mohammed.model.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ie.mohammed.model.Customer;

/**
 * @author Mohammed
 *
 */
public class CustomerRowMapper implements RowMapper<Customer>{

	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		 
		return null;
	}

}
