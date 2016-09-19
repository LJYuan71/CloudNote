package cn.edu.syuct.note.service;

import javax.servlet.http.HttpServletRequest;

import cn.edu.syuct.note.entity.NoteResult;

public interface UserService {
	public NoteResult checkLogin(HttpServletRequest request, String name,String pwd)throws Exception;
	public NoteResult regist(
		String name,String password,String nickname) throws Exception;
}
