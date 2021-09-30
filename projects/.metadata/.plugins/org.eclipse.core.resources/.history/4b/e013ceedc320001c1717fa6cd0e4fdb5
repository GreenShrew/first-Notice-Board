package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	
	private Connection conn; // Connection이란 데이터베이스에 접근하게 해 주는 하나의 객체를 의미
	private PreparedStatement pstmt;
	private ResultSet rs; // 어떠한 정보를 담을 수 있는 하나의 객체를 생성
// 여기까지 쓰고 ctrl + shift + o, 불필요한 import, 변수의 선언을 없애준다고 한다.
// 이 윗쪽은 외부 라이브러리.
	
	public UserDAO() {			// 하나의 객체를 만들었을 때, 자동으로 데이터베이스 커넥션이 이루어질 수 있도록 만든다.
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";	// BBS라는 데이터베이스에 접속하게 해준다
			String dbID = "root";
			String dbPassword = "dhfos1591";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);	// conn 객체 안에 접속된 정보가 담기게 된다
		} catch (Exception e) {		// try, catch 문구를 이용해서 예외처리
			e.printStackTrace();	// 오류 발생시 어떻게 오류가 생겼는지 출력하겠다
		}
	}	// 이 부분이 실제로 mysql에 접속하게 해주는 부분
	
	public int login(String userID, String userPassword) {		// 실제로 로그인하는 하나의 함수, userID와 userPassword를 입력 받아서 처리할 수 있도록 해줌
		String SQL = "Select userPassword FROM USER WHERE userID = ?";	// "" 내부는 실제로 mysql 내부에 입력할 명령어. user table에서 해당 사용자의 비밀번호를 가져올 수 있도록 만드는 명령어
		try {
			pstmt = conn.prepareStatement(SQL);	// pstmt = PreparedStatement에 어떠한 정해진 sql 문장을 데이터베이스에 삽입하는 형식으로 인스턴스를 가져오고
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery(); // rs =result set, 결과를 담을 수 있는 객체에 실행한 결과를 넣어주는 역할
			if (rs.next()) {	// 결과가 존재한다면 이게 실행됨
				if(rs.getString(1).equals(userPassword))	// 그리고 해당 ID와 비밀번호가 일치한다면 이게 실행됨
					return 1;	// 로그인 성공 결과
				else		// ID 비밀번호가 맞지 않다면 이게 실행됨
					return 0; // 비밀번호 불일치
			}
			return -1;		// 결과가 존재하지 않는다면 이게 실행됨. 아이디가 없다고 알려줌.
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // return -2는 데이터베이스 오류를 의미.
		
	}	// 매개변수 userID를 ?에 넣게된다. 실제로 접속 시도하는 사용자의 id를 ?로 입력 받아서, 그 id가 실제로 존재한다면 그 비밀번호는 무엇인지 데이터베이스에서 가져오도록 하는 것.
		// 이 부분이 로그인 시도를 해주는 함수
	
	public int join(User user) {
		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, ?)";		// mysql 내부에 입력할 명령어. id, password, name, gender, email이 차곡차곡 들어갈 예정
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());	// 위의 ?에 들어갈 내용이 뭐가될지 정해준다.
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate();	// 해당 statement를 실행한 결과를 나타내도록 한다.
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;	// 데이터베이스 오류
	}
	
}

// DAO class 는 실제로 데이터베이스에 접근해서 어떠한 데이터를 가져오거나 넣는 역할을 한다
