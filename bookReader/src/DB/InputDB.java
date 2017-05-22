package DB;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import vo.BookVo;

public class InputDB {
	
	/*
	 * employee_id, employee_name, email, job_position, salary, hire_date
	 */
	public void insertBook(Connection conn, List<BookVo> list) throws SQLException{
		String sql = "insert into book values (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = null;		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			for(int i = 0; i < list.size(); i++){

				pstmt.setString(1, list.get(i).getBookId());
				pstmt.setString(2, list.get(i).getTitle());
				pstmt.setString(3, list.get(i).getAuthor());
				pstmt.setString(4, list.get(i).getPublisher());
				pstmt.setString(5, list.get(i).getPublishDate());
				pstmt.setString(6, "N");
				pstmt.addBatch();
				pstmt.clearParameters();
			}
			pstmt.executeBatch();
		} finally {
			if(pstmt != null) pstmt.close();
			
		}
	}
	public void deleteBook(Connection conn) throws SQLException{
		String sql = "delete from book";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} finally {
			if(pstmt != null) pstmt.close();
		}
	}
}
