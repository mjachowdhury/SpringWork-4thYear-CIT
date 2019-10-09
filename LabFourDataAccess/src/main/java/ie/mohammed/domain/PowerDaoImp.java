package ie.mohammed.domain;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ie.mohammed.dao.mappers.PowerRowMapper;
import ie.mohammed.rowcallback.ReportGenerator;

@Repository
public class PowerDaoImp implements PowerDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int getPowerCount() {
		String sql = "SELECT  COUNT (*) FROM power";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public int getStartingWithLetterCount(char letter) {
		String pattern = letter + "%";
		String sql = "SELECT  COUNT (*) FROM power WHERE power.powerName LIKE ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, pattern);
	}

	public int getPowerByName(String powerName) {
		String sql = "SELECT  COUNT (*) FROM power WHERE power.powerName LIKE ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, powerName);
	}

	public Map findByIdMap(int powerId) {
		String sql = "SELECT * FROM county WHERE powerId=?";
		return jdbcTemplate.queryForMap(sql, powerId);
	}

	public boolean isIdInTable(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	// Insert a power
	public void insertPower(String powerName) {
		String sql = "INSERT INTO power(powerName) VALUES (?)";
		jdbcTemplate.update(sql, powerName);

	}

	public List getAListOfPowers() {
		String sql = "SELECT * FROM power";
		return jdbcTemplate.queryForList(sql);
	}

	// TO get single row

	public Power findById(int powerId) {
		String sql = "SELECT * FROM power WHERE powerId=?";
		Power power = jdbcTemplate.queryForObject(sql, new PowerRowMapper(), powerId);
		return power;
	}

	// To get several rows

	public List<Power> findAll() {
		String sql = "SELECT * FROM power";
		List<Power> powers = jdbcTemplate.query(sql, new PowerRowMapper());
		//return jdbcTemplate.query(sql, new PowerRowMapper());
		
		return powers;
	}

	/*
	 * public void generateReportOfPowers(String fileName) { String sql =
	 * "SELECT * FROM power"; Writer writer;
	 * 
	 * try { writer = new BufferedWriter(new FileWriter(fileName));
	 * jdbcTemplate.query(sql, new ReportGenerator(writer)); writer.close(); } catch
	 * (IOException e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	public int changePowerName(String oldName, String newName) {
		return jdbcTemplate.update("UPDATE power SET powerName=? where powerName=?", new Object[] { newName, oldName });
	}

	// Add a power method
	public void addAPower(String powerName, String powerDescription) {
		String sql = "INSERT INTO power(powerName, powerDescription) VALUES (?,?)";
		jdbcTemplate.update(sql, new Object[] { powerName, powerDescription });

	}

}
