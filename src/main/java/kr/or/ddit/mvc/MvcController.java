package kr.or.ddit.mvc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.classic.Logger;
import kr.or.ddit.file.model.FileVO;
import kr.or.ddit.file.service.IFileService;
import kr.or.ddit.util.FileUtil;

//@ModelAttribute에 의해 속성이 있을 경우
//재요청시 @ModelAttribute가 적용된 메서드를 재실행하지 않고
//세션에 저장하여 값을 재활용
//(잘 변경되지 않는 값을 저장할 경우 메서드가 매번 호출되는 과부하를 줄일 수 있다.)
@SessionAttributes("rangers")
@Controller
public class MvcController {

	@Resource(name="fileService")
	IFileService fileService;
	private Logger logger = (Logger) LoggerFactory.getLogger(MvcController.class);
	
	//@RequestMapping이 붙은 메서드가 실행되기 전에 먼저 실행됨
	//해당 컨트롤러에서 공통적으로 사용될 속성이 있을 경우
	//중복을 피하기위해 @ModelAttribute를 적용한
	//메서드를 통해 코드 중복을 막을 수 있음
	@ModelAttribute(value="rangers")
	public List<String> setup() {
		logger.debug("{}", "setup");
		
		List<String> rangers = new ArrayList<>();
		rangers.add("cony");
		rangers.add("brown");
		rangers.add("sally");
		
		return rangers;
	}
	
	@RequestMapping("/mvc/view")
	public String mvcView(@ModelAttribute("rangers")List<String> rangers) {
		for(String ranger : rangers) {
			logger.debug("ranger : {}", ranger);
		}
		rangers.add("james");
		
		return "mvc/view";
	}
	
	@RequestMapping("/mvc/view2")
	public String mvcView2() {
		return "mvc/view";
	}
	
	@RequestMapping("/mvc/{libcd}")
	public String pathvarialbe(@PathVariable("libcd") String libcd) {
		logger.debug("libcd : {}", libcd);
		return "mvc/view";
	}
	
	//fileupload 테스트를 위한 view(get)
	@RequestMapping(value = "/mvc/fileuploadView", method = {RequestMethod.GET})
	public String fileuploadView() {
		return "mvc/fileupload";
	}
	
	//fileupload(파일전송)울 처리 하기위한 controller method(post)
	@RequestMapping(value = "/mvc/fileupload", method = {RequestMethod.POST})
	public String fileupload(@RequestPart("file") MultipartFile part,
							@RequestParam("userId") String userId) {
		logger.debug("userId : {}", userId);
		logger.debug("partSize : {}", part.getSize());
		logger.debug("originalFileName : {}", part.getOriginalFilename());
		
		//파일 write
		//1. File객체 생성(경로 + 이름 > 파일명 충돌방지를 위해 유니크한 임의의 파일명을 생성)
		String path = "D:\\A_TeachingMaterial\\6.JspSrpgin\\upload";
		String fileName = UUID.randomUUID().toString();					//충동방지를 위한 임의의 파일명
		String originalFileName = part.getOriginalFilename();			//사용자가 업로드한 실제 파일명
		
		File file = new File(path + File.separator + fileName);
		
		FileVO fileVO = new FileVO();
		fileVO.setFile_name(fileName + FileUtil.getFileExt(originalFileName));
		fileVO.setFile_path(path);
		fileVO.setOrg_file_name(originalFileName);
		
		//FileUtil.getFileExt(String fileName)
		fileVO.setFile_ext(FileUtil.getFileExt(originalFileName));
		
		try {
			if(part.getSize() > 0) {
				//정해진 path에 업로드 파일 작성
				part.transferTo(file);
				
				//데이터베이스에 첨부파일 정보저장
				//1. fileService
				//2. 로직호출
				int inserCount = fileService.insertFile(fileVO);
			}
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "mvc/fileupload";
	}

}
