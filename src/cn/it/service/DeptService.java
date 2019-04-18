package cn.it.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.it.commns.Page;
import cn.it.mapper.DeptMapper;
import cn.it.po.Dept;

@Service("deptService")
public class DeptService {
	@Autowired
	private DeptMapper deptMapper;
	
	public	Page totalRecords(Dept dept,Page page){
		//1.׼����ǰҳ��Ϣ
		int currentPageNum=1;
		if (page.getCurrentPageNum()>0) {
			currentPageNum=page.getCurrentPageNum();
		}
		//2.��ȡ�ܼ�¼����
		int totalRecords=deptMapper.count(dept);
		if(deptMapper.count(dept)==0){
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
		params.put("dept", dept);
		List<Dept> records=deptMapper.findWhitDeptParam(params);
		//5�Ѳ�ѯ�Ľ������װ��page������
		page2.setRecords(records);
		return page2;
	}
	
	public void updateWhitDeptParam(Dept dept){
		deptMapper.updateWhitDeptParam(dept);
	}
	
	public void insertWhitDeptParam(Dept dept){
		deptMapper.insertWhitDeptParam(dept);
	}
	
	public void deleteDeptById(int id){
		deptMapper.deleteDeptById(id);
	}
	
	public List<Dept> selectAlldept(){
		return deptMapper.selectAllDept();
	}
	
	public Dept selectDeptById(int id){
		return deptMapper.selectDeptById(id);
	}
	
}
