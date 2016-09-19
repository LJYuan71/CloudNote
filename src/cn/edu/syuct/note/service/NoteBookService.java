package cn.edu.syuct.note.service;

import cn.edu.syuct.note.entity.NoteResult;

public interface NoteBookService {
	public NoteResult addBook(
		String bookName,String userId);
	public NoteResult loadBooks(String userId);
}
