package cn.it.dynaDql;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import cn.it.po.Document;

public class DocumentDynaSql {
	public String findWhitDocumentParam(final Map<String, Object> params){
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("Document_inf");
				if (params.get("document") != null) {
					Document document = (Document) params.get("document");
					if (document.getTitle() != null && !document.getTitle().equals("")) {
						WHERE("title LIKE CONCAT ('%',#{document.title},'%')");
					}
				}
			}
		}.toString();

		if(params.get("page")!=null){
			sql += " limit #{page.startIndex},#{page.pageSize}";
		}
		return sql;
	}
	
	public String count(final Document document){
		return new SQL(){{
			SELECT ("count(*)");
			FROM ("document_inf");
			if (document.getTitle() != null && !document.getTitle().equals("")) {
				WHERE("title LIKE CONCAT ('%',#{title},'%')");
			}
		}
		}.toString();
	}
	
	
	public String updateWhitDocumentParam(final Document document){
		return new SQL(){{
			UPDATE ("document_inf");
			if (document.getTitle() != null && !document.getTitle().equals("")){
				SET ("title=#{title}");
			}
			if (document.getFileName() != null && !document.getFileName().equals("")){
				SET ("filename=#{fileName}");
			}
			if(document.getUser()!=null&&document.getUser().getId()!=null){
				SET ("user_id=#{user.id}");
			}
			if (document.getRemark()!=null&&!document.getRemark().equals("")) {
				SET("remark=#{remark}");
			}
			WHERE("id=#{id}");
		}
		}.toString();
	}
	
	public String insertWhitDocumentParam(final Document document){
		return new SQL()
		{
			{
			INSERT_INTO("document_inf");
			if (document.getTitle() != null && !document.getTitle().equals("")){
				VALUES("title","#{title}");
			}
			
			if(document.getUser()!=null&&document.getUser().getId()!=null){
				VALUES("user_id","#{user.id}");
			}
			if (document.getRemark()!=null&&!document.getRemark().equals("")) {
				VALUES("remark","#{remark}");
			}
			if (document.getFileName() != null && !document.getFileName().equals("")){
				VALUES("filename","#{fileName}");
			}
		}
		}.toString();
	}
}
