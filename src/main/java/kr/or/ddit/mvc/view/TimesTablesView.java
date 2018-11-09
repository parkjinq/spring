package kr.or.ddit.mvc.view;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

//구구단 출력하는 View
//spring cutomView를 만들기 위해서 View interface를 구현할 필요가 있음
public class TimesTablesView implements View {

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		int tables = (int) model.get("table");
		
		pw.println("<!DOCTYPE html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset=\"UTF-8\">");
		pw.println("<title>timesTables.html</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("	<table border=\"1\">");
		int value = 0;
		if(request.getParameter("table") == null || request.getParameter("table").equals("")) {
			for(int i = 2; i <= 9; i++){
				pw.println("		<tr>");
				for(int j = 1; j <= 9; j++){
					value = i*j;
					pw.println("			<td>" + i + " X " + j + " = " + value + "</td>");
				}
				pw.println("		</tr>");
			}
			
		} else {
			int table = Integer.parseInt(request.getParameter("table"));
			
			pw.println("		<tr>");
			for(int j = 1; j <= 9; j++){
				value = table*j;
				pw.println("			<td>" + table + " X " + j + " = " + value + "</td>");
			}
			pw.println("		</tr>");
		}
		pw.println("</table>");			
		pw.println("</body>");			
		pw.println("</html>");
	}

}
