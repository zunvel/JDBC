package ojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//JDBC 라이브러리 사용 방법
//	1.	JDBC 드라이버 로드
//	2.	DB 접속
//	3.	SQL쿼리 수행
//	4.	결과값 처리
//	5. 	연결 종료


public class OjdbcEx_01 {
	public static void main(String[] args) {
		
		try {
//			1.	JDBC 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		//--- OJDBC 사용에 필요한 변수들 ---
		
		Connection conn = null;	//DB연결 객체(접속 객체)
		
		Statement st = null;	//SQL구문 저장 및 수행 객체
		ResultSet rs = null;	//조회 결과 반환 객체
		
		//-------------------------------------

		
//		2.	DB 접속
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@devkh00_high?TNS_ADMIN=/Users/jmac/Desktop/Dev/Wallet_devkh00",
					"ADMIN",
					"Tjdwns123456!");
		
//		3.	SQL쿼리 수행
			//3-1. 쿼리 작성
			st = conn.createStatement();
			
			//3-2. SQL쿼리 수행 
			rs = st.executeQuery("SELECT * FROM emp ORDER BY empno");
			
//		4.	결과값 처리
			// 조회된 결과행을 처음부터 한 행씩 마지막까지 반복적으로 확인할 수 있도록 작성한다
			
			//rs.next()
			//	-> 조회 결과를 순차적으로 한 행씩 참조한다
			//	-> 실행할 때마다 다음 행을 참조한다
			//	-> 다음 next()로 조회할 행이 있으면 true 반환
			//	-> 마지막 행을 조회할 경우 false 반환
			
			while ( rs.next() ) {				
//				System.out.print( rs.getInt("empno") + ",");
//				System.out.print( rs.getString("ename") + ",");
//				System.out.print( rs.getString("job") + ",");
//				System.out.print( rs.getInt("mgr") + ",");
//				System.out.print( rs.getDate("hiredate") + ",");
//				System.out.print( rs.getDouble("sal") + ",");
//				System.out.print( rs.getDouble("comm") + ",");
//				System.out.println( rs.getInt("deptno") );
				
				System.out.print( rs.getString("empno") + ",");
				System.out.print( rs.getString("ename") + ",");
				System.out.print( rs.getString("job") + ",");
				System.out.print( rs.getString("mgr") + ",");
				System.out.print( rs.getString("hiredate") + ",");
				System.out.print( rs.getString("sal") + ",");
				System.out.print( rs.getString("comm") + ",");
				System.out.println( rs.getString("deptno") );
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
//		5. 연결 종료
			try {
				//자원 해제
				if (rs!=null && !rs.isClosed()) rs.close(); 
				if (st!=null && !st.isClosed()) st.close(); 
				if (conn!=null && !conn.isClosed()) conn.close(); 			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		

	}

}
