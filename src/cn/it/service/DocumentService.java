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
		//1.׼����ǰҳ��Ϣ
		int currentPageNum=1;
		if (page.getCurrentPageNum()>0) {
			currentPageNum=page.getCurrentPageNum();
		}
		//2.��ȡ�ܼ�¼����
		int totalRecords=documentMapper.count(document);
		if(documentMapper.count(document)==0){
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
		params.put("document", document);
		List<Document> records=documentMapper.findWhitDocumentParam(params);
		//5�Ѳ�ѯ�Ľ������װ��page������
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
