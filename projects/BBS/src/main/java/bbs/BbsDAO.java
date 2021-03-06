// DAO class 는 데이터 접근 객체의 약자.
// 실제로 DB에 접속해서 어떤 데이터를 빼올 수 있도록 해주는 역할.
package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BbsDAO {

	private Connection conn; // Connection이란 데이터베이스에 접근하게 해 주는 하나의 객체를 의미
	private ResultSet rs; // 어떠한 정보를 담을 수 있는 하나의 객체를 생성
// 여기까지 쓰고 ctrl + shift + o, 불필요한 import, 변수의 선언을 없애준다고 한다.
// 이 윗쪽은 외부 라이브러리.
	
	public BbsDAO() {			// 하나의 객체를 만들었을 때, 자동으로 데이터베이스 커넥션이 이루어질 수 있도록 만든다.
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
	// 아랫부분. 게시판 글 쓰기를 위해서는 총 3개의 함수가 필요.
	public String getDate() {		// 현재의 시간을 가져오는 함수, 게시판에 글을 작성 할 때 서버의 시간을 넣어주는 역할.
		String SQL = "SELECT NOW()";	// mysql 내부에서 현재의 시간을 가져오는 문장
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);	// SELECT NOW()를 실행 준비 단계로 만들어줌
			rs = pstmt.executeQuery();	// 실제 실행 시 나오는 결과를 가져올 수 있도록 만듦
			if (rs.next()) {	// 만약 rs에 결과가 있다면 아래를 실행
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();	// 오류 발생시 오류 출력
		}
		return ""; // DB 오류 발생시 빈칸 안내문 출력
	}	// 이렇게 getDate라는 함수 하나를 만들었다.
	
	public int getNext() {
		String SQL = "SELECT bbsID FROM BBS ORDER BY bbsID DESC";	// 내림차순 해서 가장 마지막에 쓰인 게시글 번호를 가져옴
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) + 1;	// 나온 결과에 1을 더해 게시글 번호를 증가시킨다
			}
			return 1;	// 첫 게시물은 결과가 안 나오므로 이 문장을 통해 위치를 잡아준다
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int write(String bbsTitle, String userID, String bbsContent) {
		String SQL = "INSERT INTO BBS VALUES (?, ?, ?, ?, ?, ?)";	// 실제로 DB 안에 하나의 게시글을 작성해서 넣어줄 필요가 있다
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());	// 게시글 번호
			pstmt.setString(2, bbsTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, getDate());
			pstmt.setString(5, bbsContent);
			pstmt.setInt(6, 1);			// 첫 글 작성시 글이 보여지는 형태가 되어야 다음 글에 번호가 증가할 수 있다
			return pstmt.executeUpdate();	// INSERT는 성공적으로 수행 시 0 이상의 결과값 반환
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<Bbs> getList(int pageNumber) {
		String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10";
		// BBS에서 bbsID가 특정한 숫자 ?보다 작을때로 잡고, 삭제가 되지 않아서 bbsAvailable가 1인 글들만 가져오도록 하고, ORDER BY로 내림차수 정렬, LIMIT로 위에서 10개까지만 노출하도록 지시
		ArrayList<Bbs> list = new ArrayList<Bbs>();		//Bbs라는 class에서 나오는 인스턴스를 보관 할 수 있는 리스트를 생성
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);		// 위 ?에 들어갈 숫자
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				list.add(bbs);	// 결과로 나온 모든 게시글 목록을 담아서 list에 해당 인스턴트를 담아서 반환할 수 있도록 만듦
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;	// 그 결과 뽑아온 10개의 게시글 리스트를 출력
	}

// 아래는 페이징 처리 방법
	public boolean nextPage(int pageNumber) {	// 게시글이 10, 20... 단위르 끊긴다면 글 다음 화면이 존재하지 않으므로 nextPage가 없다는것을 알려주는 퍼리
		String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);		// 위 ?에 들어갈 숫자
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;	// 결과가 없으면 다음 페이지로 넘어갈 수 없다
	}
	
	public Bbs getBbs(int bbsID) {
		String SQL = "SELECT * FROM BBS WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				return bbs;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int update(int bbsID, String bbsTitle, String bbsContent) {
		String SQL = "UPDATE BBS SET bbsTitle = ?, bbsContent = ? WHERE bbsID = ?";	// 특정한 ID에 해당하는 제목과 내용을 바꾸겠다는 의미
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, bbsTitle);
			pstmt.setString(2, bbsContent);
			pstmt.setInt(3, bbsID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
    public int delete(int bbsID) {
        String SQL = "UPDATE BBS SET bbsAvailable = 0  WHERE bbsID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, bbsID);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
	}
}
