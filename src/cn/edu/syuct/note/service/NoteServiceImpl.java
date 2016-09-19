package cn.edu.syuct.note.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.syuct.note.entity.Note;
import cn.edu.syuct.note.entity.NoteResult;
import cn.edu.syuct.note.entity.Share;

import cn.edu.syuct.note.dao.NoteDao;
import cn.edu.syuct.note.dao.ShareDao;
import cn.edu.syuct.note.util.NoteUtil;

@Service("noteService")//括号及括号内容可以去掉，默认就是
@Transactional//该类具有事务性
public class NoteServiceImpl implements NoteService{
	@Resource
	private NoteDao noteDao;
	@Resource
	private ShareDao shareDao;
	private static final Log log = LogFactory.getLog(NoteServiceImpl.class);
	private static final Logger LOGGER = Logger.getLogger(NoteBookServiceImpl.class);
	
	public NoteResult loadNotes(String bookId) {
		List<Map> list = 
			noteDao.findByBookId(bookId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("查询笔记成功");
		result.setData(list);
		return result;
	}
	@Transactional
	public NoteResult addNote(
		String noteTitle, String bookId, String userId) {
		NoteResult result = new NoteResult();
		//添加笔记
		Note note = new Note();
		note.setCn_note_title(noteTitle);
		note.setCn_notebook_id(bookId);
		note.setCn_user_id(userId);
		note.setCn_note_status_id("1");//normal
		note.setCn_note_type_id("1");//normal
		note.setCn_note_body("");
		note.setCn_note_create_time(
			System.currentTimeMillis());
		String noteId = NoteUtil.createId();
		note.setCn_note_id(noteId);
		
		try {
			noteDao.save(note);
			result.setStatus(0);
			result.setMsg("创建笔记成功");
			result.setData(noteId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage()+"创建笔记失败！");
		}
		result.setMsg("创建笔记失败");
		return result;
	}

	public NoteResult loadNote(String noteId) {
		NoteResult result = new NoteResult();
		Note note = noteDao.findById(noteId);
		result.setStatus(0);
		result.setMsg("查询笔记成功");
		result.setData(note);
		return result;
	}

	public NoteResult updateNote(
			String noteId, 
			String noteTitle,
			String noteBody) {
		NoteResult result = new NoteResult();
		//更新
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_title(noteTitle);
		note.setCn_note_body(noteBody);
		note.setCn_note_last_modify_time(
			System.currentTimeMillis());
		noteDao.dynamicUpdate(note);//更新
		//TODO如果笔记分享，更新分享表信息
		result.setStatus(0);
		result.setMsg("更新笔记成功");
		return result;
	}

	public NoteResult recycleNote(String noteId) {
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_status_id("2");
		noteDao.dynamicUpdate(note);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("删除笔记成功");
		LOGGER.debug("删除"+noteId+"笔记成功！");
		return result;
	}

	public NoteResult shareNote(String noteId) {
		NoteResult result = new NoteResult();
		//检查该笔记是否分享过
		Share has_share = 
			shareDao.findByNoteId(noteId);
		if(has_share != null){
			result.setStatus(1);
			result.setMsg("该笔记已被分享过");
			return result;
		}
		//未被分享过，分享处理
		Note note = 
			noteDao.findById(noteId);
		Share share = new Share();
		share.setCn_share_title(
			note.getCn_note_title());
		share.setCn_share_body(
			note.getCn_note_body());
		share.setCn_note_id(noteId);
		String shareId = NoteUtil.createId();
		share.setCn_share_id(shareId);
		shareDao.save(share);
		
		result.setStatus(0);
		result.setMsg("分享笔记成功");
		return result;
	}

	public NoteResult searchNote(String keyword) {
		NoteResult result = new NoteResult();
		if(keyword != null 
			&& !"".equals(keyword)){
			keyword = "%"+keyword+"%";
		}else{
			keyword = "%";
		}
		List<Map> list = 
			shareDao.findLikeTitle(keyword);
		result.setData(list);
		result.setStatus(0);
		result.setMsg("检索分享笔记成功");
		return result;
	}

	public NoteResult loadShare(String shareId) {
		Share share = 
			shareDao.findById(shareId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("查询分享笔记成功");
		result.setData(share);
		return result;
	}

	public NoteResult loadDelete(String userId) {
		NoteResult result = 
			new NoteResult();
		List<Map> list = 
			noteDao.findDelete(userId);
		result.setStatus(0);
		result.setData(list);
		result.setMsg("查询回收站信息成功");
		return result;
	}

	public NoteResult replayNote(
		String noteId, String bookId) {
//		Map<String, Object> params = 
//			new HashMap<String, Object>();
//		params.put("noteId", noteId);
//		params.put("bookId", bookId);
//		noteDao.replayNote(params);
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_notebook_id(bookId);
		note.setCn_note_status_id("1");
		noteDao.dynamicUpdate(note);
		
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("恢复笔记成功");
		return result;
	}

	public NoteResult hightSearch(
			String title,String status, 
			String begin,String end) {
		Map params = new HashMap();
		//如果标题不为空,就添加map参数
		if(title != null && !"".equals(title)){
			title = "%"+title+"%";
			params.put("title", title);
		}
		//如果状态没有选择全部"0",就添加map参数
		if(status != null && !"0".equals(status)){
			params.put("status", status);
		}
		//如果开始日期不为空,就添加map参数
		if(begin != null && !"".equals(begin)){
			//将字符串转成日期（Long表示）
			Date begindate = 
				java.sql.Date.valueOf(begin);
			params.put("beginDate", 
				begindate.getTime());
		}
		//如果结束日期不为空,就添加map参数
		if(end != null && !"".equals(end)){
			//将字符串转成日期（Long表示）
			Date enddate = 
				java.sql.Date.valueOf(end);
			//将当前日期+1
			Calendar c = Calendar.getInstance();
			c.setTime(enddate);
			c.add(Calendar.DATE, 1);
			params.put("endDate", 
				c.getTimeInMillis());
		}
		//调用dao
		List<Note> list = 
			noteDao.hightSearch(params);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setData(list);
		result.setMsg("检索成功");
		return result;
	}

}
