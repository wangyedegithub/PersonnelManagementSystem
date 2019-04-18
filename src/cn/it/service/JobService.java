package cn.it.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.it.commns.Page;
import cn.it.mapper.JobMapper;
import cn.it.po.Job;


@Service("jobService")
public class JobService {
	@Autowired
	private JobMapper jobMapper;
	
	public 	Page totalRecords(Job job,Page page){
		//1.׼����ǰҳ��Ϣ
		int currentPageNum=1;
		if (page.getCurrentPageNum()>0) {
			currentPageNum=page.getCurrentPageNum();
		}
		//2.��ȡ�ܼ�¼����
		int totalRecords=jobMapper.count(job);
		if(jobMapper.count(job)==0){
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
		//job.setName("��");
		params.put("page", page2);
		params.put("job", job);
		List<Job> records=jobMapper.findWhitJobParam(params);
		//5�Ѳ�ѯ�Ľ������װ��page������
		page2.setRecords(records);
		return page2;
	}
	
	public void updateWhitJobParam(Job job){
		jobMapper.updateWhitJobParam(job);
	}
	
	public void insertWhitJobParam(Job job){
		jobMapper.insertWhitJobParam(job);
	}
	
	public void deleteJobById(int id){
		jobMapper.deleteJobById(id);
	}
	
	public List<Job> selectAllJob(){
		return jobMapper.selectAllJob();
	}
	
	public Job selectJobById(int id){
		return jobMapper.selectJobById(id);
	}
}
