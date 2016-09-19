package cn.edu.syuct.note.dao;

import cn.edu.syuct.note.entity.LoginInfo;
import cn.edu.syuct.note.entity.User;

public interface UserDao {
	public User findByName(String name);
	public void save(User user);
	public void saveLoginInfo(LoginInfo loginInfo);
}
