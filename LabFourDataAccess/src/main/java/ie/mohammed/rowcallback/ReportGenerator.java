package ie.mohammed.rowcallback;

import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportGenerator implements RowCallbackHandler{
	Writer writer;
	
	public ReportGenerator(Writer writer) {
		this.writer = writer;
	}
	
	public void processRow(ResultSet rs) throws SQLException{
		try {
			writer.write(rs.getString("powerName") + "\r\n");
		}catch (IOException e) {
			 e.printStackTrace();
		}
	}
}
