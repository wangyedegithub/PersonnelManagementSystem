package cn.it.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;




import cn.it.commns.Page;
import cn.it.mapper.UserMapper;
import cn.it.po.Document;
import cn.it.po.User;
import cn.it.service.DocumentService;

@Controller
@RequestMapping("/document")
public class DocumentController {
	@Autowired
	private DocumentService documentService;
	@Autowired
	private UserMapper userMapper;
	
	// 添加方法
		@RequestMapping("/addDocument")
		public String addDocument(Document document, int flag, Model model,HttpSession session) throws Exception {
			if (flag == 1) {
				return "document/showAddDocument";
			}
			// 获取文件名
			String fileName = document.getFile().getOriginalFilename();
			//获取上传路径
			String path = session.getServletContext().getRealPath(
	                "/upload/");
			System.out.println(path);
			//将上传文件保留在目标文件中
			//将内存写入磁盘
			document.getFile().transferTo(new File(path +File.separator+fileName));
			//把文件名放入document中
			document.setFileName(fileName);
			User user=(User) session.getAttribute("user");
			document.setUser(user);
			documentService.insertWhitDocumentParam(document);
			return "redirect:selectDocument.action";
		}
		//用户列表，查询所有跳转列表页面
		@RequestMapping("selectDocument")
		public String selectUser(Document document,Model model,Page page){
			Page page2=documentService.totalRecords(document, page);
			model.addAttribute("page", page2);
			model.addAttribute("document",document);
			return "document/document";
		}
		//下载
		@RequestMapping("/downLoad")
		public ResponseEntity<byte[]> downLoadDocument(Document document,Model model,HttpSession session) throws Exception{
			document=documentService.selectDocumentById(document.getId());
			String fileName=document.getFileName();
			String path = session.getServletContext().getRealPath(
	                "/upload/");
			//获得file对象
			File file=new File(path +File.separator+fileName);
			//创建HttpHeaders
			HttpHeaders headers=new HttpHeaders();
			//下载限时任务名，解决中文乱码
			String downLoadFile=new String(fileName.getBytes("utf-8"),"iso-8859-1");
			//通知浏览器以attachment的下载方式打开图片
			headers.setContentDispositionFormData("attachment", downLoadFile);
			//二进制下载
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			//201 H
			
			model.addAttribute("document", document);
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
		}
		//修改方法
		@RequestMapping("/updateDocument")
		public String updateDocument( int flag,
				@ModelAttribute Document document ,Model model,HttpSession session) throws Exception {
			if (flag == 1) {
				document=documentService.selectDocumentById(document.getId());
				model.addAttribute("document", document);
				return "document/showUpdateDocument";
			}
			// 获取文件名
			String fileName = document.getFile().getOriginalFilename();
						//获取上传路径
			String path = session.getServletContext().getRealPath(
				                "/upload/");
			System.out.println(path);
						//将上传文件保留在目标文件中
						//将内存写入磁盘
			document.getFile().transferTo(new File(path +File.separator+fileName));
						//把文件名放入document中
			document.setFileName(fileName);
			User user=(User) session.getAttribute("user");
			document.setUser(user);
			documentService.updateWhitDocumentParam(document);
			return "redirect:selectDocument.action";
		}
		//删除方法
		@RequestMapping("/removeDocument")
		public String removeDocument(int[] ids){
			for (int id : ids) {
				documentService.deleteDocumentById(id);
			}
			return "redirect:selectDocument.action";
		}
}
