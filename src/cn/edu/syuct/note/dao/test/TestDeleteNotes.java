package cn.edu.syuct.note.dao.test;

import cn.edu.syuct.note.dao.NoteDao;

public class TestDeleteNotes 
extends TestBase{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NoteDao noteDao = 
			getContext().getBean("noteDao",NoteDao.class);
		String[] ids = {"fsaf-as-df-asdf-as-df-dsa",
					"ss19055-30e8-4cdc-bfac-97c6bad9518f"};
		int rows = noteDao.deleteNotes(ids);
		System.out.println("ɾ��ļ�¼��:"+rows);
	}

}
