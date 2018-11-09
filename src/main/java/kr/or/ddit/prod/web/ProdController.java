package kr.or.ddit.prod.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.util.model.PageVO;

@RequestMapping("/prod")
@Controller
public class ProdController {

	@Resource(name ="prodService")
	private IProdService prodService;

	@SuppressWarnings("unchecked")
	@RequestMapping("/prodPageList")
	public String prodPageList(HttpServletRequest request, Model model) {
		
		PageVO pageVO = new PageVO(Integer.parseInt(request.getParameter("page")), Integer.parseInt(request.getParameter("pageSize")));
		Map<String, Object> resultMap = prodService.selectProdPageList(pageVO);
		
		model.addAttribute("pdList", (List<ProdVO>)resultMap.get("pdList"));
		model.addAttribute("pdCnt", (int)resultMap.get("pdCnt"));
		model.addAttribute("pageVO", pageVO);
		
		return "prod/prodPageList";
	}
	
	@RequestMapping("/prodDetail")
	public String prodDetail(HttpServletRequest request, Model model) {
		String prodId = request.getParameter("prod_id");
		ProdVO prodVO = prodService.selectProd(prodId);
		
		model.addAttribute(prodVO);
		
		return "prod/prodDetail";
	}

}
