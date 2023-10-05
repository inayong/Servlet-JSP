package model2.mvcboard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;

public class MVCBoardDAO extends JDBConnect {
	public MVCBoardDAO() {
		super();
	}
	
	// 검색 조건에 맞는 게시물의 개수를 반환
	public int selectCount(Map<String, Object> map) {
		Statement stmt = null;
		ResultSet rs = null;
		
		int totalCount = 0;
		
		String query = "select count(*) from mvcboard";
		
		if (map.get("searchWord") != null) {
			query += "where " + map.get("searchField") + " " + " like '%" + map.get("searchWord") + "%'";
		}
		
		try {
			stmt = getConnection().createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		}
		catch (Exception e) {
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}
		return totalCount;
	}
	
	// 검색 조건에 맞는게시물 목록을 반환(페이징 기능 지원)
	public List<MVCBoardDTO> selectListPage(Map<String, Object> map) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();
		
		String query = "select * from mvcboard";
		
		if (map.get("searchWord") != null) {
			query += "where " + map.get("searchField") + " like '%" + map.get("searchWord") + "%' ";
		}
		query += " order by idx desc limit ?,?";
		
		try {
			psmt = getConnection().prepareStatement(query);
			psmt.setInt(1, (Integer)map.get("start"));
			psmt.setInt(2, (Integer)map.get("pageSize"));
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
				
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
				
				board.add(dto);
			}
		}
		catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		
		return board;
	}
	
	// 게시글 데이터를 받아 DB에 추가(파일 업로드 지원)
	public int insertWrite(MVCBoardDTO dto) {
		PreparedStatement psmt = null;
		int result = 0;
		try {
			String query = "insert into mvcboard (" + " name, title, content, ofile, sfile, pass) " + " values ( " + "?, ?, ?, ?, ?, ?)";
			
			psmt = getConnection().prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getPass());
			
			result = psmt.executeUpdate();
			
		}
		catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	//주어진 일련번호에 해당하는 게시물을 dto에 담아 반환합니다.
	public MVCBoardDTO selectView(String idx) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		MVCBoardDTO dto = new MVCBoardDTO();
		String query = "select * from mvcboard where idx=?";
		try {
			psmt = getConnection().prepareStatement(query);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
			}
		}
		catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		return dto;
	}
	
	public void updateVisitCount(String idx) {
		PreparedStatement psmt = null;
		
		String query = "update mvcboard set " + " visitcount=visitcount+1 " + " where idx=?";
		
		try {
			psmt = getConnection().prepareStatement(query);
			psmt.setString(1, idx);
			psmt.executeQuery();
		}
		catch (Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
		
	}
}
