package cn.edu.syuct.note.controller.notebook;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.syuct.note.entity.NoteResult;
import cn.edu.syuct.note.service.NoteBookService;

@Controller
@RequestMapping("/notebook")
public class AddBookController {
	@Resource
	private NoteBookService bookService;
	
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult execute(
		String bookName,String userId){
		NoteResult result = 
			bookService.addBook(bookName, userId);
		return result;
	}
	
}
