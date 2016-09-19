package cn.edu.syuct.note.dao;

import java.util.List;

import cn.edu.syuct.note.entity.NoteBook;

public interface NoteBookDao {
	public void save(NoteBook book);
	public List<NoteBook> findByUser(String userId);
}
