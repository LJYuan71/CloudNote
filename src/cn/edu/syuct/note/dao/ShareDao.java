package cn.edu.syuct.note.dao;

import java.util.List;
import java.util.Map;

import cn.edu.syuct.note.entity.Share;

public interface ShareDao {
	public Share findById(String id);
	public List<Map> findLikeTitle(String keyword);
	public Share findByNoteId(String noteId);
	public void save(Share share);
}
