package cn.it.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.it.commns.Page;
import cn.it.mapper.NoticeMapper;
import cn.it.po.Notice;

@Service("noticeService")
public class NoticeService {
	@Autowired
	private NoticeMapper noticeMapper;
	
	public	Page totalRecords(Notice notice,Page page){
		//1.准备当前页信息
		int currentPageNum=1;
		if (page.getCurrentPageNum()>0) {
			currentPageNum=page.getCurrentPageNum();
		}
		//2.获取总记录条数
		int totalRecords=noticeMapper.count(notice);
		if(noticeMapper.count(notice)==0){
			currentPageNum=1;
			totalRecords=1;
		}
		
		// 页面记录数
		int pageSize=4;
		if (page.getPageSize()>0) {
			pageSize=page.getPageSize();
		}
		//3.创建page
		Page page2=new Page(currentPageNum, totalRecords, pageSize);
		//4.使用page查询带有分页的结果集
		//List<User> records=userMapper.selectUserParam(user, page2);
		Map<String, Object> params=new HashMap<String, Object>();
		//dept.setName("技");
		params.put("page", page2);
		params.put("notice", notice);
		List<Notice> records=noticeMapper.findWhitNoticeParam(params);
		//5把查询的结果集封装到page对象中
		page2.setRecords(records);
		return page2;
	}
	
	public void updateWhitNoticeParam(Notice notice){
		noticeMapper.updateWhitNoticeParam(notice);
	}
	
	public void insertWhitNoticeParam(Notice notice){
		noticeMapper.insertWhitNoticeParam(notice);
	}
	
	public void deleteNoticeById(int id){
		noticeMapper.deleteNoticeById(id);
	}
	
	public List<Notice> selectAlldept(){
		return noticeMapper.selectAllNotice();
	}
	
	public Notice selectNoticeById(int id){
		return noticeMapper.selectNoticeById(id);
	}
}
