package cn.edu.syuct.note.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.edu.syuct.note.entity.NoteResult;
import cn.edu.syuct.note.service.UserService;

@Controller
@RequestMapping("/user")
public class RegistController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/regist.do")
	@ResponseBody
	public NoteResult execute(
		String name,String password,
		String nickname) throws Exception{
		NoteResult result = 
			userService.regist(
				name, password, nickname);
		return result;
	}
	
}
