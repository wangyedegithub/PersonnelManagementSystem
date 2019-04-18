package cn.it.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.it.commns.Page;
import cn.it.po.Job;
import cn.it.service.JobService;


@Controller
@RequestMapping("/job")
public class JobController {
	@Autowired
	private JobService jobService;
	
	//跳转部门列表
	@RequestMapping("/selectJob")
	public String selectJob(Job job,Model model,Page page){
		Page page2=jobService.totalRecords(job,page);
		model.addAttribute("page", page2);
		model.addAttribute("job", job);
		return "job/job";
	}
	
	//修改方法
	@RequestMapping("/updateJob")
	public String updateJob( int flag,
			@ModelAttribute Job job ,Model model) {
		if (flag == 1) {
			job=jobService.selectJobById(job.getId());
			model.addAttribute("job", job);
			return "job/showUpdateJob";
		} else {
			List<Job> jobs = jobService.selectAllJob();
			for (Job job1 : jobs) {
				if (!job1.getId().equals(job.getId())) {
					if (job1.getName().equals(job.getName())) {
						model.addAttribute("message", "用户名重复");
						model.addAttribute("Job", job);
						return "job/showUpdateJob";
					}
				}
			}
			jobService.updateWhitJobParam(job);
			return "redirect:selectJob.action";
		}
	}
	
	//添加方法
	@RequestMapping("/addJob")
	public String addJob(Job job,int flag, Model model
			 ){
		if (flag == 1) {
			return "job/showAddJob";
		} else {
			List<Job> jobs = jobService.selectAllJob();
			for (Job job1 : jobs) {
					if (job1.getName().equals(job.getName())) {
						model.addAttribute("message", "用户名重复");
						model.addAttribute("job", job);
						return "job/showAddJob";
				}
			}
			jobService.insertWhitJobParam(job);
			return "redirect:selectJob.action";
		}
	}
	
	//删除方法
	@RequestMapping("/removeJob")
	public String removeJob(int[] ids){
		for (int id : ids) {
			jobService.deleteJobById(id);
		}
		return "redirect:selectJob.action";
	}
}
