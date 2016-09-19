package cn.edu.syuct.note.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.syuct.note.entity.NoteResult;
import cn.edu.syuct.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class LoadShareController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/loadShare.do")
	@ResponseBody
	public NoteResult execute(String shareId){
		NoteResult result = 
			noteService.loadShare(shareId);
		return result;
	}
	
}
