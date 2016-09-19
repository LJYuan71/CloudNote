package cn.edu.syuct.note.controller.user;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.edu.syuct.note.entity.NoteResult;
import cn.edu.syuct.note.service.UserService;

@Controller//扫锟斤拷锟斤拷锟斤拷锟斤拷
@RequestMapping("/user")
public class LoginController {
	
	@Resource//注锟斤拷
	private UserService userService;
	
	@RequestMapping("/login.do")
	@ResponseBody//锟斤拷锟斤拷锟斤拷值NoteResult转锟斤拷json锟斤拷锟�
	public NoteResult execute(
		HttpServletRequest request, String name,String pwd) throws Exception{
		NoteResult result = 
			userService.checkLogin(request,name, pwd);
		return result;
	}
	
}
