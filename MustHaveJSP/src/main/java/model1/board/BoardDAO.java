package model1.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;
import jakarta.servlet.ServletContext;

public class BoardDAO extends JDBConnect {
	Statement stmt;
	PreparedStatement psmt;
	ResultSet rs;
	Connection con = getConnection();
	
	public BoardDAO(ServletContext application) {
		super(application);
	}
	
	// 검색 조건에 맞는 게시물의 개수를 반환
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0; // 결과(게시물 수)를 담을 변수
		
		String query = "select count(*) from board";
		
		if (map.get("searchWord") != null) {
			query += "where " + map.get("searchField") + " "
					+ " like '%" + map.get("searchWord") + "%'";
		}
		
		try {
			stmt = con.createStatement(); //쿼리문 생성
			rs = stmt.executeQuery(query); // 쿼리 실행
			rs.next(); // 커서를 첫 번째 행으로 이동
			totalCount = rs.getInt(1); //첫 번째 컬럼 값을 가져옴
		}
		catch (Exception e) {
			System.out.println("게시물 수를 구하는 중 예외 발생");
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	//검색 조건에 맞는 게시물 목록을 반환
	public List<BoardDTO> selectList(Map<String, Object> map) {
		List<BoardDTO> bbs = new Vector<BoardDTO>(); // 결과(게시물 목록)를 담을 변수
		
		String query = "select * from board";
		if (map.get("searchWord") != null) {
			query += " where " + map.get("searchField") + " "
					+ " like '%" + map.get("searchWord") + "%' ";
		}
		query += " order by num desc ";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				BoardDTO dto = new BoardDTO(); // 한 행(게시물 하나)의 내용을 dto에 저장
				
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				
				bbs.add(dto); // 결과 목록에 저장
			}
		}
		catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		
		return bbs;
	}
	
	// 검색 조건에 맞는 게시물 목록을 반환(페이징 기능 지원)
	public List<BoardDTO> selectListPage(Map<String, Object> map) {
		List<BoardDTO> bbs = new Vector<BoardDTO>(); // 결과(게시물 목록)을 담을 변수
		
		String query = " SELECT * FROM board ";
		
		if (map.get("searchWord") != null) {
			query += (" where " + map.get("searchField"))
				   + " like '%" + map.get("searchWord") + "%' ";
		}
		
		query += ("	order by num desc "	+ " limit ?, ? "); //start count , 검색하고자하는 갯수
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, (Integer)map.get("start"));
			psmt.setInt(2, (Integer)map.get("pageSize"));
			
			rs = psmt.executeQuery();
		
			
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				
				bbs.add(dto);
			}
		}
		catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		
		
		return bbs;
	}
	
	// 게시글 데이터를 받아 DB에 추가
	public int insertWrite(BoardDTO dto) {
		int result = 0;
		
		
		try {
			String query = "insert into board ("
						 + " title,content,id,visitcount) "
						 + " values ( ?, ?, ?, 0)";
			
			psmt = con.prepareStatement(query);
			
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 지정한 게시물을 찾아 내용을 반환
	public BoardDTO selectView(String num) {
		BoardDTO dto = new BoardDTO();
		
		String query = "select B.*, M.name "
					 + " from member M inner join board B "
					 + " on M.id=B.id "
					 + " where num=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				dto.setNum(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString(6));
				dto.setName(rs.getString("name"));
			}
		}
		catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		
		return dto;
	}
	
	// 지정한 게시물의 조회수를 1 증가
	public void updateVisitCount(String num) {
		String query = "update board set "
					 + " visitcount=visitcount+1 "
					 + " where num=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	// 지정한 게시물을 수정
	public int updateEdit(BoardDTO dto) {
		int result = 0;
		
		try {
			String query = "update board set "
						 + " title=?, content=? "
						 + " where num=?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getNum());
			
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 지정한 게시물을 삭제
	public int deletePost(BoardDTO dto) {
		int result = 0;
		
		try {
			String query = "delete from board where num=?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getNum());
			
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}
			
		return result;
		}
	}
