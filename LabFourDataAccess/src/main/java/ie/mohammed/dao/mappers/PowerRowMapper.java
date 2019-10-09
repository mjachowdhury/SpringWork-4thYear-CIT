package ie.mohammed.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ie.mohammed.domain.Power;

public class PowerRowMapper implements RowMapper<Power> {


	public Power mapRow(ResultSet rs, int rowNum) throws SQLException {
		 
		Power power = new Power();
		power.setPowerId(rs.getInt("powerId"));
		power.setPowerName(rs.getString("powerName"));
		power.setPowerDescription(rs.getString("powerDescription"));
		return power;
	}
	
	

}
