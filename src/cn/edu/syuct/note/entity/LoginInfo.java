package cn.edu.syuct.note.entity;

import java.io.Serializable;

public class LoginInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String login_ip;
	private String login_host;
	private String web_ip;
	private String user_name;
	public String getLogin_ip() {
		return login_ip;
	}
	public void setLogin_ip(String longin_ip) {
		this.login_ip = longin_ip;
	}
	public String getLogin_host() {
		return login_host;
	}
	public void setLogin_host(String login_host) {
		this.login_host = login_host;
	}
	public String getWeb_ip() {
		return web_ip;
	}
	public void setWeb_ip(String web_ip) {
		this.web_ip = web_ip;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	

}
