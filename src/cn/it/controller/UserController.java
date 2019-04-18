package cn.it.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.it.commns.Page;
import cn.it.po.User;
import cn.it.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	//用户登录
	@RequestMapping("/login")
	public String Login(User user,HttpSession session,Model model){
	
		User user1=userService.selectEqUser(user);
		//如果登录成功把用户名保存下来,跳转主页面
		if(user1!=null){
			session.setAttribute("user", user1);
			model.addAttribute("user", user1);
			return "redirect:/jsp/main.jsp";
		}
		else{
			//返回错误消息，跳转登录页面
			session.setAttribute("message1", "用户名或密码错误请重新登录");
			return "redirect:/loginForm.jsp";
		}
	}
	//注销清除session跳转登录
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/loginForm.jsp";
	}
	//用户列表，查询所有跳转列表页面
	@RequestMapping("selectUser")
	public String selectUser(User user,Model model,Page page){
		Page page2=userService.totalRecords(user, page);
		model.addAttribute("page", page2);
		model.addAttribute("user",user);
		return "user/user";
	}
	//多项根据id删除
	@RequestMapping("removeUser")
	public String removeUser(int[] ids){
		for (int id : ids) {
			userService.deleteUserById(id);
		}
		return "redirect:selectUser.action";
	}
	//添加用户falg用来标识跳转那个
	@RequestMapping("addUser")
	public String addUser(User user,int flag,Model model)
	{
		if(flag==1)
		{
		
		return "user/showAddUser";
		}
		else {
			List<User> users=userService.selectAllUser();
			for (User user2 : users) {
				if (user2.getLoginname().equals(user.getLoginname())) {
					model.addAttribute("message", "用户名重复");
					model.addAttribute("user",user );
					return "user/showAddUser";
				}
			}
			userService.insertWhitUserParam(user);
			return "redirect:selectUser.action";
	}
	}
	//修改用户falg用来标识跳转那个
	@RequestMapping("updateUser")
	public String updateUser(int flag,@ModelAttribute User user,Model model){
		if(flag==1){
			user=userService.selectUserById(user.getId());
			model.addAttribute("user",user );
			return "user/showUpdateUser";
		}
		else {
			List<User> users=userService.selectAllUser();
			for (User user2 : users) {
				if(!user2.getId().equals(user.getId())){
				if (user2.getLoginname().equals(user.getLoginname())) {
					model.addAttribute("message", "用户名重复");
					//model.addAttribute("user",user );
					return "user/showUpdateUser";
				}
				}
			}
			userService.updateWhitUserParam(user);
			return "redirect:selectUser.action";
		}
	
	}
	@RequestMapping("/pageTest")
	public String pageTest(User user,Page page){
		 userService.totalRecords(user, page);
		 return null;
	}
}
