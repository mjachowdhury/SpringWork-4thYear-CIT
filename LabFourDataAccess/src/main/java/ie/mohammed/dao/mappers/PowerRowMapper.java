package ie.mohammed.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import ie.mohammed.domain.Power;

public class PowerRowMapper implements RowMapper<Power> {

	public Power MapRow(ResultSet rs, int rowNumber) throws SQLException {
		 
		Power power = new Power();
		power.setPowerId(rs.getInt("powerId"));
		power.setPowerName(rs.getString("powerName"));
		power.setPowerDescription(rs.getString("powerDescription"));
		return power;
	}
	
	

}
