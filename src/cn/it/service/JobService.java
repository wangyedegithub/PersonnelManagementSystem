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
		//1.准备当前页信息
		int currentPageNum=1;
		if (page.getCurrentPageNum()>0) {
			currentPageNum=page.getCurrentPageNum();
		}
		//2.获取总记录条数
		int totalRecords=jobMapper.count(job);
		if(jobMapper.count(job)==0){
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
		//job.setName("技");
		params.put("page", page2);
		params.put("job", job);
		List<Job> records=jobMapper.findWhitJobParam(params);
		//5把查询的结果集封装到page对象中
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
