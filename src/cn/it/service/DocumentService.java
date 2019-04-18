package cn.it.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.it.commns.Page;
import cn.it.mapper.DocumentMapper;
import cn.it.po.Document;

@Service("documentService")
public class DocumentService {
	@Autowired
	private DocumentMapper documentMapper;
	
	public	Page totalRecords(Document document,Page page){
		//1.准备当前页信息
		int currentPageNum=1;
		if (page.getCurrentPageNum()>0) {
			currentPageNum=page.getCurrentPageNum();
		}
		//2.获取总记录条数
		int totalRecords=documentMapper.count(document);
		if(documentMapper.count(document)==0){
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
		//dept.setName("技");
		params.put("page", page2);
		params.put("document", document);
		List<Document> records=documentMapper.findWhitDocumentParam(params);
		//5把查询的结果集封装到page对象中
		page2.setRecords(records);
		return page2;
	}
	
	public void updateWhitDocumentParam(Document document){
		documentMapper.updateWhitDocumentParam(document);
	}
	
	public void insertWhitDocumentParam(Document document){
		documentMapper.insertWhitDocumentParam(document);
	}
	
	public void deleteDocumentById(int id){
		documentMapper.deleteDocumentById(id);
	}
	
	public List<Document> selectAllDocument(){
		return documentMapper.selectAllDocument();
	}
	
	public Document selectDocumentById(int id){
		return documentMapper.selectDocumentById(id);
	}
}
