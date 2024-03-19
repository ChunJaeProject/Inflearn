package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletContext;

public class JDBConnect {

	public Connection conn;
	public Statement stmt; //쿼리문
	public PreparedStatement psmt;
	public ResultSet rs;
	
	// DB연결은 프로젝트 내의 web.xml에서 변경하고, 커넥션풀은 severs폴더의 server.xml과 context.xml파일을 수정해야함
	
	public JDBConnect() { //기본 생성자로 고정해서 연결
		try {
			Class.forName("org.mariadb.jdbc.Driver"); //드라이버를 로딩
			String url = "jdbc:mariadb://localhost:3306/maria";
			String dbId ="root";
			String dbPwd ="1234";
			conn = DriverManager.getConnection(url,dbId,dbPwd); // DB에 커넥션맺을때
			System.out.println(" DB 연결 성공 : 기본 생성자");
		}
		catch(Exception e ) {
			e.printStackTrace();
			
		}
	}
	public JDBConnect(String driver, String url, String dbId, String dbPwd) {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,dbId,dbPwd);
			System.out.println("DB연결 성공 : 사용자 정의 생성자 1 ");
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	public JDBConnect (ServletContext application) {
		try {
			String driver = application.getInitParameter("Driver");
			Class.forName(driver);
			
			String url = application.getInitParameter("URL");
			String id = application.getInitParameter("dbId");
			String pwd = application.getInitParameter("dbPwd");
		
			conn = DriverManager.getConnection(url,id,pwd);
			
			System.out.println("DB연결 성공 : 사용자 정의 생성자 2 ");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(stmt !=null) stmt.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
			System.out.println("JDBC 연결 해지 성공");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
// DB연결 : 드라이버선택(jdbc odbc ) -> url 설정 -> 아이디 ,패스워드설정
//드라이버로딩 class.forName(driver이름) 
	
}
