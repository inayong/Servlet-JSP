package fileupload;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/13FileUpload/UploadProcess.do")
@MultipartConfig(
	maxFileSize = 1024 * 1024 * 1,
	maxRequestSize = 1024 * 1024 * 10
		)
public class UploadProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//드라이브 명으로 절대경로 저장
			//Uploads 디렉토리의 물리적 경로 가져오기
			String saveDirectory = getServletContext().getRealPath("Uploads");
			//파일 업로드 하기
			String originalFileName = FileUtil.uploadFile(req, saveDirectory);
			//저장된 파일명 변경하기
			String saveFileName = FileUtil.renameFile(saveDirectory, originalFileName);
			
			//db에 저장
			insertMyFile(req, originalFileName, saveFileName);
			resp.sendRedirect("FileList.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMessage", "파일 업로드 오류");
			req.getRequestDispatcher("FileUploadMain.jsp").forward(req, resp);
		}
	}
	
	private void insertMyFile(HttpServletRequest req, String oFileName, String sFileName) {
		// 파일 외 폼값 받기
		String title = req.getParameter("title");
		String[] cateArray = req.getParameterValues("cate");
		StringBuffer cateBuf = new StringBuffer();
		if (cateArray == null) {
			cateBuf.append("선택한 항목 없음");
		}
		else {
			for (String s : cateArray) {
				cateBuf.append(s + ", ");
			}
		}
		
		MyFileDTO dto = new MyFileDTO();
		dto.setTitle(title);
		dto.setCate(cateBuf.toString());
		dto.setOfile(oFileName);
		dto.setSfile(sFileName);
		
		MyFileDAO dao = new MyFileDAO();
		dao.insertFile(dto);
		dao.close();
	}
}
