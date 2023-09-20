package membership;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.JDBConnect;


public class MemberDAO  extends JDBConnect {
	public MemberDAO(String drv, String url, String id, String pw) {
		super(drv, url, id, pw);
	}
	
	//테스트용 java application
//	public MemberDAO() {
//		super();
//	}
//	
//	public static void main(String[] args) {
//		MemberDAO md = new MemberDAO();
//		MemberDTO m = md.getMemberDTO("musthave", "1234");
//		System.out.println("member:" + m);
//	}
	
	public MemberDTO getMemberDTO(String uid, String upass) {
		MemberDTO dto = new MemberDTO();
		Connection con = getConnection();
		
		String query = "select * from member where id=? and pass=?";
		PreparedStatement psmt;
		ResultSet rs;
		
		try {
			psmt = con.prepareStatement(query); //여기서 적었으니깐 위에선 변수선언만 해주면됨
			psmt.setString(1, uid);
			psmt.setString(2, upass);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
}
