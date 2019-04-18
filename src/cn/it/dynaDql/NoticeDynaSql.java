package cn.it.dynaDql;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import cn.it.po.Notice;


public class NoticeDynaSql {
	public String findWhitNoticeParam(final Map<String, Object> params){
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("notice_inf");
				if (params.get("notice") != null) {
					Notice notice = (Notice) params.get("notice");
					if (notice.getTitle() != null && !notice.getTitle().equals("")) {
						WHERE("title LIKE CONCAT ('%',#{notice.title},'%')");
					}
					if (notice.getContent() != null && !notice.getContent().equals("")) {
						WHERE("content LIKE CONCAT ('%',#{notice.content},'%')");
					}
				}
			}
		}.toString();

		if(params.get("page")!=null){
			sql += " limit #{page.startIndex},#{page.pageSize}";
		}
		return sql;
	}
	
	public String count(final Notice notice){
		return new SQL(){{
			SELECT ("count(*)");
			FROM ("notice_inf");
			if (notice.getTitle() != null && !notice.getTitle().equals("")) {
				WHERE("title LIKE CONCAT ('%',#{title},'%')");
			}
			if (notice.getContent() != null && !notice.getContent().equals("")) {
				WHERE("content LIKE CONCAT ('%',#{content},'%')");
			}
		}
		}.toString();
	}
	
	
	public String updateWhitNoticeParam(final Notice notice){
		return new SQL(){{
			UPDATE ("notice_inf");
			if (notice.getTitle() != null && !notice.getTitle().equals("")){
				SET ("title=#{title}");
			}
			if (notice.getContent() != null && !notice.getContent().equals("")){
				SET ("content=#{content}");
			}
			if(notice.getUser()!=null&&notice.getUser().getId()!=null){
				SET ("user_id=#{user.id}");
			}
			WHERE("id=#{id}");
		}
		}.toString();
	}
	
	public String insertWhitNoticeParam(final Notice notice){
		return new SQL()
		{
			{
			INSERT_INTO("notice_inf");
			if (notice.getTitle() != null && !notice.getTitle().equals("")){
				VALUES("title","#{title}");
			}
			if (notice.getContent() != null && !notice.getContent().equals("")){
				VALUES("content","#{content}");
			}
			if(notice.getUser()!=null&&notice.getUser().getId()!=null){
				VALUES("user_id","#{user.id}");
			}
		}
		}.toString();
	}
}
