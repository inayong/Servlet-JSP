package fileupload;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import common.JDBConnect;

public class MyFileDAO extends JDBConnect {
	
	//테스트
//	public static void main(String[] args) {
//		MyFileDAO dao = new MyFileDAO();
//		
//		MyFileDTO dto = new MyFileDTO();
//		dto.setTitle("title");
//		dto.setCate("cate");
//		dto.setOfile("ofile");
//		dto.setSfile("sfile");
//		
//		System.out.println("result:" +dao.insertFile(dto));
//	}

	public int insertFile(MyFileDTO dto) {
		int applyResult = 0 ;
		
		PreparedStatement psmt = null;
		try {
			String query = "insert into myfile ( "
					+ " title, cate, ofile, sfile)"
					+ " values ( "
					+ " ?, ?, ?, ?)";
			
			psmt = getConnection().prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getCate());
			psmt.setString(3, dto.getOfile());
			psmt.setString(4, dto.getSfile());
			
			applyResult = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("insert 중 예외 발생");
			e.printStackTrace();
		}
		finally {
			if (psmt != null)
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return applyResult;
	}
	
	//파일 목록을 반환
	public List<MyFileDTO> myFileList() {
		List<MyFileDTO> fileList = new Vector<MyFileDTO>();
		Statement stmt = null;
		ResultSet rs = null;
		
		String query = "select * from myfile order by idx desc";
		try {
			stmt = getConnection().createStatement();
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {//목록 안의 파일 수만큼 반복
				MyFileDTO dto = new MyFileDTO();
				dto.setIdx(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setCate(rs.getString(3));
				dto.setOfile(rs.getString(4));
				dto.setSfile(rs.getString(5));
				dto.setPostdate(rs.getString(6));
				
				fileList.add(dto);
			}
		} catch (Exception e) {
			System.out.println("select 시 예외 발생");
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return fileList;
	}
}
