package cn.edu.syuct.note.service.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import cn.edu.syuct.note.entity.Note;
import cn.edu.syuct.note.entity.NoteResult;
import cn.edu.syuct.note.service.NoteService;
import cn.edu.syuct.note.service.NoteServiceImpl;


public class TestHightSearch {
	//private static final Log log1 = LogFactory.getLog(TestHightSearch.class);
	private static final Logger log = Logger.getLogger(TestHightSearch.class);
	public static void main(String[] args){
		log.debug("测试输出日志");
		/*String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		NoteService service = 
			ac.getBean("noteServiceImpl",
				NoteService.class);
		NoteResult result =	service.hightSearch(
			null, "0", "2015-10-01", "2015-11-15");
		List<Note> list = (List)result.getData();
		for(Note n : list){
			System.out.println(n.getCn_note_title());
		}
		System.out.println("锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�"+list.size());*/
		
		
	}
	
}
