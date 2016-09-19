package cn.edu.syuct.note.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.syuct.note.entity.NoteResult;
import cn.edu.syuct.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class HightSearchController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/hightSearch.do")
	@ResponseBody
	public NoteResult execute(
		String title,String status,
		String begin,String end){
		NoteResult result = 
			noteService.hightSearch(
			 title, status, begin, end);
		return result;
	}
	
	
	
}
