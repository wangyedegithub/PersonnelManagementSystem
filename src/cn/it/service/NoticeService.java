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
		//1.׼����ǰҳ��Ϣ
		int currentPageNum=1;
		if (page.getCurrentPageNum()>0) {
			currentPageNum=page.getCurrentPageNum();
		}
		//2.��ȡ�ܼ�¼����
		int totalRecords=noticeMapper.count(notice);
		if(noticeMapper.count(notice)==0){
			currentPageNum=1;
			totalRecords=1;
		}
		
		// ҳ���¼��
		int pageSize=4;
		if (page.getPageSize()>0) {
			pageSize=page.getPageSize();
		}
		//3.����page
		Page page2=new Page(currentPageNum, totalRecords, pageSize);
		//4.ʹ��page��ѯ���з�ҳ�Ľ����
		//List<User> records=userMapper.selectUserParam(user, page2);
		Map<String, Object> params=new HashMap<String, Object>();
		//dept.setName("��");
		params.put("page", page2);
		params.put("notice", notice);
		List<Notice> records=noticeMapper.findWhitNoticeParam(params);
		//5�Ѳ�ѯ�Ľ������װ��page������
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
