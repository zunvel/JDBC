package ojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class OjdbcEx_04 {
	
	//OJDBC 드라이버
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
		
	//DB 연결 정보
	private static final String URL = "jdbc:oracle:thin:@devkh00_high?TNS_ADMIN=/Users/jmac/Desktop/Dev/Wallet_devkh00";
	private static final String USERNAME = "ADMIN";		
	private static final String PASSWORD = "Tjdwns123456!";
		
	//OJDBC 객체
	private static Connection conn = null;	//DB연결 객체
	
	private static Statement st = null;	//SQL 수행 객체
	private static PreparedStatement ps = null;	//SQL 수행 객체
	
	private static ResultSet rs = null;	//조회 결과 객체
	
	
	public static void main(String[] args) {
		
		//드라이버 로드
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//조회할 job 입력받기
		Scanner sc = new Scanner(System.in);
		System.out.print("조회할 job 입력 : ");
		String job = sc.nextLine();
		
		//SQL작성
		String sql ="";
		sql += "SELECT * FROM emp";
		sql += " WHERE upper(job) = upper( ? )";
		sql += " ORDER BY empno";
		
		try {
			//DB연결
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			//SQL수행 객체
//			st = conn.createStatement();	//수행 객체 생성
//			rs = st.executeQuery(sql);		//sql수행 및 결과 저장
			
			ps = conn.prepareStatement(sql);	//수행 객체 생성
			
			//SQL구문의 첫 번째 "?" 를 job 변수의 값으로 치환한다
			//	** 문자 데이터일 경우 .setString()은 ''작은 따옴표를 자동으로 씌워준다
			ps.setString(1, job);
			
			rs = ps.executeQuery();

			//SQL 조회 결과 처리
			while (rs.next()) {
				System.out.print( rs.getString("empno") + "\t" );
				System.out.print( rs.getString("ename") + "\t" );
				System.out.println( rs.getString("job") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
			//자원 해제
				if( rs!=null && !rs.isClosed()) rs.close();
				if( ps!=null && !ps.isClosed()) ps.close();
				if( conn!=null && !conn.isClosed()) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
		
		

	}

}
