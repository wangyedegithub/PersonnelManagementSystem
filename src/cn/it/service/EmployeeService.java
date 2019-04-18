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
		//1.准备当前页信息
		int currentPageNum=1;
		if (page.getCurrentPageNum()>0) {
			currentPageNum=page.getCurrentPageNum();
		}
		//2.获取总记录条数
		int totalRecords=employeeMapper.count(employee);
		if(employeeMapper.count(employee)==0){
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
		//Employee.setName("技");
		params.put("page", page2);
		params.put("employee", employee);
		List<Employee> records=employeeMapper.findWhitEmployeeParam(params);
		//5把查询的结果集封装到page对象中
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
