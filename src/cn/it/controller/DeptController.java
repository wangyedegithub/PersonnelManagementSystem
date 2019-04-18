package cn.it.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.it.commns.Page;
import cn.it.po.Dept;
import cn.it.service.DeptService;

@Controller
@RequestMapping("/dept")
public class DeptController {
	@Autowired
	private DeptService deptService;
	
	//跳转部门列表
	@RequestMapping("selectDept")
	public String selectDept(Dept dept,Model model,Page page){
		Page page2=deptService.totalRecords(dept,page);
		model.addAttribute("dept", dept);
		model.addAttribute("page", page2);
		return "dept/dept";
	}
	
	//修改方法
	@RequestMapping("/updateDept")
	public String updateDept( int flag,
			@ModelAttribute Dept dept ,Model model) {
		if (flag == 1) {
			dept=deptService.selectDeptById(dept.getId());
			model.addAttribute("dept", dept);
			return "dept/showUpdateDept";
		} else {
			List<Dept> depts = deptService.selectAlldept();
			for (Dept dept1 : depts) {
				if (!dept1.getId().equals(dept.getId())) {
					if (dept1.getName().equals(dept.getName())) {
						model.addAttribute("message", "用户名重复");
						model.addAttribute("dept", dept);
						return "dept/showUpdateDept";
					}
				}
			}
			deptService.updateWhitDeptParam(dept);
			return "redirect:selectDept.action";
		}
	}
	
	//添加方法
	@RequestMapping("/addDept")
	public String addDept(Dept dept,int flag, Model model
			 ){
		if (flag == 1) {
			return "dept/showAddDept";
		} else {
			List<Dept> depts = deptService.selectAlldept();
			for (Dept dept1 : depts) {
					if (dept1.getName().equals(dept.getName())) {
						model.addAttribute("message", "用户名重复");
						model.addAttribute("dept", dept);
						return "dept/showAddDept";
				}
			}
			deptService.insertWhitDeptParam(dept);
			return "redirect:selectDept.action";
		}
	}
	
	//删除方法
	@RequestMapping("/removeDept")
	public String removeDept(int[] ids){
		for (int id : ids) {
			deptService.deleteDeptById(id);
		}
		return "redirect:selectDept.action";
	}

}
