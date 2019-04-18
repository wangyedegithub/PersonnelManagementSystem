package cn.it.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.it.commns.Page;
import cn.it.mapper.EmployeeMapper;
import cn.it.po.Employee;

@Service("employeeService")
public class EmployeeService {
	@Autowired
	private EmployeeMapper employeeMapper;
	
	public	Page totalRecords(Employee employee,Page page){
		//1.׼����ǰҳ��Ϣ
		int currentPageNum=1;
		if (page.getCurrentPageNum()>0) {
			currentPageNum=page.getCurrentPageNum();
		}
		//2.��ȡ�ܼ�¼����
		int totalRecords=employeeMapper.count(employee);
		if(employeeMapper.count(employee)==0){
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
		//Employee.setName("��");
		params.put("page", page2);
		params.put("employee", employee);
		List<Employee> records=employeeMapper.findWhitEmployeeParam(params);
		//5�Ѳ�ѯ�Ľ������װ��page������
		page2.setRecords(records);
		return page2;
	}
	
	public void updateWhitEmployeeParam(Employee employee){
		employeeMapper.updateWhitEmployeeParam(employee);
	}
	
	public void insertWhitEmployeeParam(Employee employee){
		employeeMapper.insertWhitEmployeeParam(employee);
	}
	
	public void deleteEmployeeById(int id){
		employeeMapper.deleteEmployeeById(id);
	}
	
	public List<Employee> selectAllEmployee(){
		return employeeMapper.selectAllEmployee();
	}
	
	public Employee selectEmployeeById(int id){
		return employeeMapper.selectEmployeeById(id);
	}
}
