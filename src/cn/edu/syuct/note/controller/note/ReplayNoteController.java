package cn.edu.syuct.note.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.edu.syuct.note.entity.NoteResult;
import cn.edu.syuct.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class ReplayNoteController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/replay.do")
	@ResponseBody
	public NoteResult execute(
		String noteId,String bookId){
		NoteResult result = 
			noteService.replayNote(noteId, bookId);
		return result;
	}
	
	
	
}
