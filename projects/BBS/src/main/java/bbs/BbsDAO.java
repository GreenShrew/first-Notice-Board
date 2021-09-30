// DAO class �� ������ ���� ��ü�� ����.
// ������ DB�� �����ؼ� � �����͸� ���� �� �ֵ��� ���ִ� ����.
package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BbsDAO {

	private Connection conn; // Connection�̶� �����ͺ��̽��� �����ϰ� �� �ִ� �ϳ��� ��ü�� �ǹ�
	private ResultSet rs; // ��� ������ ���� �� �ִ� �ϳ��� ��ü�� ����
// ������� ���� ctrl + shift + o, ���ʿ��� import, ������ ������ �����شٰ� �Ѵ�.
// �� ������ �ܺ� ���̺귯��.
	
	public BbsDAO() {			// �ϳ��� ��ü�� ������� ��, �ڵ����� �����ͺ��̽� Ŀ�ؼ��� �̷���� �� �ֵ��� �����.
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
	// �Ʒ��κ�. �Խ��� �� ���⸦ ���ؼ��� �� 3���� �Լ��� �ʿ�.
	public String getDate() {		// ������ �ð��� �������� �Լ�, �Խ��ǿ� ���� �ۼ� �� �� ������ �ð��� �־��ִ� ����.
		String SQL = "SELECT NOW()";	// mysql ���ο��� ������ �ð��� �������� ����
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);	// SELECT NOW()�� ���� �غ� �ܰ�� �������
			rs = pstmt.executeQuery();	// ���� ���� �� ������ ����� ������ �� �ֵ��� ����
			if (rs.next()) {	// ���� rs�� ����� �ִٸ� �Ʒ��� ����
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();	// ���� �߻��� ���� ���
		}
		return ""; // DB ���� �߻��� ��ĭ �ȳ��� ���
	}	// �̷��� getDate��� �Լ� �ϳ��� �������.
	
	public int getNext() {
		String SQL = "SELECT bbsID FROM BBS ORDER BY bbsID DESC";	// �������� �ؼ� ���� �������� ���� �Խñ� ��ȣ�� ������
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) + 1;	// ���� ����� 1�� ���� �Խñ� ��ȣ�� ������Ų��
			}
			return 1;	// ù �Խù��� ����� �� �����Ƿ� �� ������ ���� ��ġ�� ����ش�
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int write(String bbsTitle, String userID, String bbsContent) {
		String SQL = "INSERT INTO BBS VALUES (?, ?, ?, ?, ?, ?)";	// ������ DB �ȿ� �ϳ��� �Խñ��� �ۼ��ؼ� �־��� �ʿ䰡 �ִ�
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());	// �Խñ� ��ȣ
			pstmt.setString(2, bbsTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, getDate());
			pstmt.setString(5, bbsContent);
			pstmt.setInt(6, 1);			// ù �� �ۼ��� ���� �������� ���°� �Ǿ�� ���� �ۿ� ��ȣ�� ������ �� �ִ�
			return pstmt.executeUpdate();	// INSERT�� ���������� ���� �� 0 �̻��� ����� ��ȯ
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<Bbs> getList(int pageNumber) {
		String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10";
		// BBS���� bbsID�� Ư���� ���� ?���� �������� ���, ������ ���� �ʾƼ� bbsAvailable�� 1�� �۵鸸 ���������� �ϰ�, ORDER BY�� �������� ����, LIMIT�� ������ 10�������� �����ϵ��� ����
		ArrayList<Bbs> list = new ArrayList<Bbs>();		//Bbs��� class���� ������ �ν��Ͻ��� ���� �� �� �ִ� ����Ʈ�� ����
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);		// �� ?�� �� ����
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				list.add(bbs);	// ����� ���� ��� �Խñ� ����� ��Ƽ� list�� �ش� �ν���Ʈ�� ��Ƽ� ��ȯ�� �� �ֵ��� ����
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;	// �� ��� �̾ƿ� 10���� �Խñ� ����Ʈ�� ���
	}

// �Ʒ��� ����¡ ó�� ���
	public boolean nextPage(int pageNumber) {	// �Խñ��� 10, 20... ������ ����ٸ� �� ���� ȭ���� �������� �����Ƿ� nextPage�� ���ٴ°��� �˷��ִ� �۸�
		String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);		// �� ?�� �� ����
			rs = pstmt.executeQuery();
			if (rs.next()) {	// ����� �ϳ��� �����Ѵٸ�
				return true;	// ���� �������� �Ѿ �� �ִ�
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;	// ����� ������ ���� �������� �Ѿ �� ����
	}
}