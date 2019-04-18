package cn.it.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.it.commns.Page;
import cn.it.mapper.UserMapper;
import cn.it.po.Notice;
import cn.it.po.User;
import cn.it.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private UserMapper userMapper;
	// 添加方法
	@RequestMapping("/addNotice")
	public String addNotice(Notice notice, int flag, Model model,HttpSession session) {
		if (flag == 1) {
			return "notice/showAddNotice";
		}
		User user=(User) session.getAttribute("user");
		notice.setUser(user);
		noticeService.insertWhitNoticeParam(notice);
		return "redirect:selectNotice.action";
	}
	//用户列表，查询所有跳转列表页面
	@RequestMapping("selectNotice")
	public String selectUser(Notice notice,Model model,Page page){
		Page page2=noticeService.totalRecords(notice, page);
		model.addAttribute("page", page2);
		model.addAttribute("notice",notice);
		return "notice/notice";
	}
	//预览
	@RequestMapping("/previewNotice")
	public String previewNotice(Notice notice,Model model){
		notice=noticeService.selectNoticeById(notice.getId());
		model.addAttribute("notice", notice);
		return "notice/previewNotice";
	}
	//修改方法
	@RequestMapping("/updateNotice")
	public String updateNotice( int flag,
			@ModelAttribute Notice notice ,Model model,HttpSession session) {
		if (flag == 1) {
			notice=noticeService.selectNoticeById(notice.getId());
			model.addAttribute("notice", notice);
			return "notice/showUpdateNotice";
		}
			User user=(User) session.getAttribute("user");
			notice.setUser(user);
			noticeService.updateWhitNoticeParam(notice);
			return "redirect:selectNotice.action";
	}
	//删除方法
	@RequestMapping("/removeNotice")
	public String removeNotice(int[] ids){
		for (int id : ids) {
			noticeService.deleteNoticeById(id);
		}
		return "redirect:selectNotice.action";
	}
	
}
