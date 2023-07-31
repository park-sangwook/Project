package kr.human.boot.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.human.boot.service.BoardService;
import kr.human.boot.service.FileService;
import kr.human.boot.vo.BoardVO;
import kr.human.boot.vo.CommVO;
import kr.human.boot.vo.FileVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	private final FileService fileService;
	private final ResourceLoader loader;
	@GetMapping(value = "/")
	public String index(Model model,@ModelAttribute CommVO cv) {
		model.addAttribute("pv",boardService.select(cv.getCurrentPage(),cv.getPageSize(),cv.getBottomLength()));
		return "index";
	}
	@GetMapping(value = "/insert")
	public String insert() {
		return "insert";
	}
	
	@PostMapping(value = "/insertOk")
	public String insertOk(@ModelAttribute BoardVO vo,@RequestParam(required = false) MultipartFile uploadFile) {
		FileVO  fv = new FileVO(UUID.randomUUID().toString(),uploadFile.getOriginalFilename());
		boardService.insert(vo);
		try {
			String filePath = loader.getResource("/").getURI().toString()+"upload/";
			filePath = filePath.substring(6);
			File file = new File(filePath);
			if(!file.exists())file.mkdirs();
			FileCopyUtils.copy(uploadFile.getBytes(), new File(filePath+fv.getUuid()+"_"+fv.getName()));
			log.info("파일 경로 : {}",filePath);
			fileService.insert(fv);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
	@PostMapping(value = "/view/updateOk")
	public String updateOk(@ModelAttribute BoardVO vo) {
		boardService.update(vo);
		return "redirect:/";
	}
	@GetMapping(value = "/view/{idx}")
	public String view(@PathVariable int idx,Model model) {
		BoardVO vo = boardService.selectByIdx(idx);
		FileVO fv = fileService.selectByIdx(vo.getIdx());
		model.addAttribute("vo",vo);
		model.addAttribute("image",fv.getUuid()+"_"+fv.getName());
		log.info("결과 : {}",fv);
		return "view";
	}
}
