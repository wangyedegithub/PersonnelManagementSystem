package cn.it.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.it.commns.Page;
import cn.it.mapper.DeptMapper;
import cn.it.mapper.JobMapper;
import cn.it.po.Dept;
import cn.it.po.Employee;
import cn.it.po.Job;
import cn.it.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private JobMapper jobMapper;
	@Autowired
	private  DeptMapper deptMapper;
	//跳转员工列表
		@RequestMapping("selectEmployee")
		public String selectEmployee(Employee employee,Model model,Page page){
			//判断关联查询对象
			Page page2= employeeService.totalRecords(employee,page);
			List<Dept> depts=deptMapper.selectAllDept();
			List<Job> jobs=jobMapper.selectAllJob();
			model.addAttribute("employee", employee);
			model.addAttribute("page", page2);
			model.addAttribute("jobs", jobs);
			model.addAttribute("depts", depts);
			return "employee/employee";
		}
	//修改方法
		@RequestMapping("/updateEmployee")
		public String updateEmployee( int flag,
				@ModelAttribute Employee employee ,Model model) {
			if (flag == 1) {
				employee=employeeService.selectEmployeeById(employee.getId());
				model.addAttribute("employee", employee);
				List<Dept> depts=deptMapper.selectAllDept();
				List<Job> jobs=jobMapper.selectAllJob();
				model.addAttribute("depts", depts);;
				model.addAttribute("jobs", jobs);
				return "employee/showUpdateEmployee";
			}
				employeeService.updateWhitEmployeeParam(employee);
				return "redirect:selectEmployee.action";
			
		}
		//添加方法
		@RequestMapping("/addEmployee")
		public String addEmployee(Employee employee,int flag, Model model
				 ){
			if (flag == 1) {
				List<Dept> depts=deptMapper.selectAllDept();
				List<Job> jobs=jobMapper.selectAllJob();
				model.addAttribute("depts", depts);;
				model.addAttribute("jobs", jobs);
				return "employee/showAddEmployee";
			} 
				employeeService.insertWhitEmployeeParam(employee);
				return "redirect:selectEmployee.action";
		}
		//删除方法
		@RequestMapping("/removeEmployee")
		public String removeEmployee(int[] ids){
			for (int id : ids) {
				employeeService.deleteEmployeeById(id);
			}
			return "redirect:selectEmployee.action";
		}

}
