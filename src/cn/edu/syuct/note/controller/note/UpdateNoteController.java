package cn.edu.syuct.note.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.edu.syuct.note.entity.NoteResult;
import cn.edu.syuct.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class UpdateNoteController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/update.do")
	@ResponseBody
	public NoteResult execute(
		String noteId,String noteTitle,String noteBody){
		NoteResult result = 
			noteService.updateNote(
				noteId, noteTitle, noteBody);
		return result;
	}
	
}
