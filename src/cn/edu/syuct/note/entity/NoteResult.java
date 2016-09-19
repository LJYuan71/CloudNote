package cn.edu.syuct.note.entity;

import java.io.Serializable;

public class NoteResult implements Serializable {
	private int status;//״̬
	private String msg;//��Ϣ
	private Object data;//���

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
