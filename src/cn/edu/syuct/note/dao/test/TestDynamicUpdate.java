package cn.edu.syuct.note.dao.test;

import cn.edu.syuct.note.entity.Note;

import cn.edu.syuct.note.dao.NoteDao;

public class TestDynamicUpdate 
	extends TestBase{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NoteDao noteDao = 
			getContext().getBean(
				"noteDao",NoteDao.class);
		//���Զ�̬����,����վ�ָ��ʼ�
		Note note = new Note();
		note.setCn_note_status_id("2");
		//note.setCn_notebook_id("10001");
		note.setCn_note_id("0c8b7f7e-0336-4220-9774-fd97d2cd0c40");
		noteDao.dynamicUpdate(note);
		
	}

}
