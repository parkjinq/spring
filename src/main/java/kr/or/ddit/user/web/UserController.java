package kr.or.ddit.user.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.model.PageVO;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Resource(name = "userService")
	IUserService userService;
	
	@RequestMapping("/loginView")
	public String loginView() {
		
		return "login/login";
	}
	
	@RequestMapping(value="/loginProcess", method=RequestMethod.POST)
	public String loginProcess(HttpServletRequest request) {
		
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		
		if("brown".equals(userId) && "brownpass".equals(pass)) {
			return "main";
		} else {
			return "login/login";
		}
	}
	
	/**
	 * 
	* Method : userAllList
	* 작성자 : jin
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 조회
	 */
	@RequestMapping("/userAllList")
	public String userAllList(Model model) {
		List<UserVO> userList = userService.selectUserAll();
		model.addAttribute("userList", userList);
		return "user/userAllList";
	}
	
	/**
	 * 
	* Method : userPageList
	* 작성자 : jin
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : 페이징, 사용자 조회
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/userPageList", method = RequestMethod.GET)
	public String userPageList(HttpServletRequest request, Model model) {
		PageVO pageVO = new PageVO(Integer.parseInt(request.getParameter("page")), Integer.parseInt(request.getParameter("pageSize")));
		Map<String, Object> resultMap = userService.selectUserPageList(pageVO);
		List<UserVO> userList = (List<UserVO>) resultMap.get("userList");
		int pageCnt = (int) resultMap.get("pageCnt");
		model.addAttribute("userList", userList);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("pageCnt", pageCnt);
		
		return "user/userPageList";
	}
	
	@RequestMapping("/userPageListAjax")
	public String userPageListAjax(Model model, PageVO pageVO) {
			
		Map<String, Object> resultMap = userService.selectUserPageList(pageVO);
		model.addAllAttributes(resultMap);
		return "jsonView";
	}
	
	@RequestMapping("/userPageListAjaxHtml")
	public String userPageListAjaxHtml(Model model, PageVO pageVO) {
			
		Map<String, Object> resultMap = userService.selectUserPageList(pageVO);
		model.addAllAttributes(resultMap);
		return "user/pageListHtml";
	}
	
	@RequestMapping("/userPagenationAjaxHtml")
	public String userPagenationAjaxHtml(Model model, PageVO pageVO) {
		
		Map<String, Object> resultMap = userService.selectUserPageList(pageVO);
		model.addAllAttributes(resultMap);
		return "user/pagenationHtml";
	}
	
	@RequestMapping("/userDetail")
	public String userDetail(@RequestParam("userId") String userId, Model model) {
		UserVO userVO = userService.selectUser(userId);
		model.addAttribute(userVO);
		return "user/userDetail";
	}
	
	@RequestMapping(value = "/userForm", method = RequestMethod.GET)
	public String userForm() {
		return "user/userForm";
	}
	
	@RequestMapping(value = "/userForm", method = RequestMethod.POST)
	public String userForm(@RequestPart("profilePic") MultipartFile part, HttpServletRequest request, UserVO userVO) {
		try {
			if(part.getSize() > 0) {
				String path = request.getServletContext().getRealPath("/profile");
				String fileName = part.getOriginalFilename();
				part.transferTo(new File(path + File.separator + fileName));
				userVO.setProfile("/profile/" + fileName);
			} else {
				userVO.setProfile("");
			}
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int insertCnt = userService.insertUser(userVO);
		
		return "redirect:/user/userPageList?page=1&pageSize=10";
	}
	
	@RequestMapping(value = "/userUpdate", method = RequestMethod.GET)
	public String userUpdate(@RequestParam("userId") String userId, Model model) {
		UserVO userVO = userService.selectUser(userId);
		model.addAttribute("userVO", userVO);
		return "user/userUpdate";
	}
	
	@RequestMapping(value = "/userUpdate", method = RequestMethod.POST)
	public String userUpdate(@RequestPart("profilePic") MultipartFile part, HttpServletRequest request, UserVO userVO) {
		String preProfile = request.getParameter("existingProfile");
		try {
			if(part.getSize() > 0) {
				String path = request.getServletContext().getRealPath("/profile");
				String fileName = part.getOriginalFilename();
				part.transferTo(new File(path + File.separator + fileName));
				userVO.setProfile("/profile/" + fileName);
			} else {
				userVO.setProfile(preProfile);
			}
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int updateCnt = userService.updateUser(userVO);
		
		return "redirect:/user/userDetail?userId="+userVO.getUserId();
	}
}
