package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	
	private Connection conn; // Connection�̶� �����ͺ��̽��� �����ϰ� �� �ִ� �ϳ��� ��ü�� �ǹ�
	private PreparedStatement pstmt;
	private ResultSet rs; // ��� ������ ���� �� �ִ� �ϳ��� ��ü�� ����
// ������� ���� ctrl + shift + o, ���ʿ��� import, ������ ������ �����شٰ� �Ѵ�.
// �� ������ �ܺ� ���̺귯��.
	
	public UserDAO() {			// �ϳ��� ��ü�� ������� ��, �ڵ����� �����ͺ��̽� Ŀ�ؼ��� �̷���� �� �ֵ��� �����.
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";	// BBS��� �����ͺ��̽��� �����ϰ� ���ش�
			String dbID = "root";
			String dbPassword = "dhfos1591";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);	// conn ��ü �ȿ� ���ӵ� ������ ���� �ȴ�
		} catch (Exception e) {		// try, catch ������ �̿��ؼ� ����ó��
			e.printStackTrace();	// ���� �߻��� ��� ������ ������� ����ϰڴ�
		}
	}	// �� �κ��� ������ mysql�� �����ϰ� ���ִ� �κ�
	
	public int login(String userID, String userPassword) {		// ������ �α����ϴ� �ϳ��� �Լ�, userID�� userPassword�� �Է� �޾Ƽ� ó���� �� �ֵ��� ����
		String SQL = "Select userPassword FROM USER WHERE userID = ?";	// "" ���δ� ������ mysql ���ο� �Է��� ��ɾ�. user table���� �ش� ������� ��й�ȣ�� ������ �� �ֵ��� ����� ��ɾ�
		try {
			pstmt = conn.prepareStatement(SQL);	// pstmt = PreparedStatement�� ��� ������ sql ������ �����ͺ��̽��� �����ϴ� �������� �ν��Ͻ��� ��������
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery(); // rs =result set, ����� ���� �� �ִ� ��ü�� ������ ����� �־��ִ� ����
			if (rs.next()) {	// ����� �����Ѵٸ� �̰� �����
				if(rs.getString(1).equals(userPassword))	// �׸��� �ش� ID�� ��й�ȣ�� ��ġ�Ѵٸ� �̰� �����
					return 1;	// �α��� ���� ���
				else		// ID ��й�ȣ�� ���� �ʴٸ� �̰� �����
					return 0; // ��й�ȣ ����ġ
			}
			return -1;		// ����� �������� �ʴ´ٸ� �̰� �����. ���̵� ���ٰ� �˷���.
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // return -2�� �����ͺ��̽� ������ �ǹ�.
		
	}	// �Ű����� userID�� ?�� �ְԵȴ�. ������ ���� �õ��ϴ� ������� id�� ?�� �Է� �޾Ƽ�, �� id�� ������ �����Ѵٸ� �� ��й�ȣ�� �������� �����ͺ��̽����� ���������� �ϴ� ��.
		// �� �κ��� �α��� �õ��� ���ִ� �Լ�
	
	public int join(User user) {
		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, ?)";		// mysql ���ο� �Է��� ��ɾ�. id, password, name, gender, email�� �������� �� ����
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());	// ���� ?�� �� ������ �������� �����ش�.
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate();	// �ش� statement�� ������ ����� ��Ÿ������ �Ѵ�.
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;	// �����ͺ��̽� ����
	}
	
}

// DAO class �� ������ �����ͺ��̽��� �����ؼ� ��� �����͸� �������ų� �ִ� ������ �Ѵ�
