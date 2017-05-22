package Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import DB.InputDB;
import Reader.ExcelReader;
import vo.BookVo;

public class Main {
	public static void main(String [] args) throws IOException, SQLException{
		System.out.println("시작");
		ExcelReader er = new ExcelReader("booklist.xlsx");
		List<BookVo> list = er.read();
		
//		for(BookVo b : list){
//			System.out.println(b);
//		}
		
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user = "scott", password = "tiger";
		
		InputDB batch = new InputDB();
		Connection conn =null;
		
		try{
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			System.out.println("DB 시작");
			batch.deleteBook(conn);
			batch.insertBook(conn, list);
			conn.commit();
			System.out.println("--------완료----------");
		}finally{
			if(conn != null){
				conn.setAutoCommit(true);
				conn.close();
			}
		}
		
		
		
	}
}
