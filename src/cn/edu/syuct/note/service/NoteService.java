package cn.edu.syuct.note.service;

import cn.edu.syuct.note.entity.NoteResult;

public interface NoteService {
	public NoteResult hightSearch(
		String title,String status,String begin,String end);
	public NoteResult replayNote(String noteId,String bookId);
	public NoteResult loadDelete(String userId);
	public NoteResult loadShare(String shareId);
	public NoteResult searchNote(String keyword);
	public NoteResult shareNote(String noteId);
	public NoteResult recycleNote(String noteId);
	public NoteResult updateNote(
		String noteId,String noteTitle,String noteBody);
	public NoteResult loadNote(String noteId);
	public NoteResult addNote(
		String noteTitle,String bookId,String userId);
	public NoteResult loadNotes(String bookId);
}
