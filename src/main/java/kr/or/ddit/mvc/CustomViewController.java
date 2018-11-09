package kr.or.ddit.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

import kr.or.ddit.mvc.view.TimesTablesView;

@Controller
public class CustomViewController {
	
	@RequestMapping("/customView")
	public View customView() {
		
		return new TimesTablesView();
	}

	@RequestMapping("/customViewBeanName")
	public String customViewBeanName(Model model, @RequestParam(name = "table") int table) {
		model.addAttribute("table", table);
		return "TimesTablesView";
	}
	
}
