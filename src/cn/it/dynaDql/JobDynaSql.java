package cn.it.dynaDql;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import cn.it.po.Job;

public class JobDynaSql {
	public String findWhitJobParam(final Map<String, Object> params){
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("job_inf");
				if (params.get("job") != null) {
					Job job = (Job) params.get("job");
					if (job.getName() != null && !job.getName().equals("")) {
						WHERE("name LIKE CONCAT ('%',#{job.name},'%')");
					}
				}
			}
		}.toString();

		if(params.get("page")!=null){
			sql += " limit #{page.startIndex},#{page.pageSize}";
		}
		return sql;
	}
	
	public String count(final Job job){
		return new SQL(){{
			SELECT ("count(*)");
			FROM ("job_inf");
			if(job.getName()!=null&&job.getName()!=""){
				WHERE ("name LIKE CONCAT ('%',#{name},'%')");
			}
		}
		}.toString();
	}
	
	
	public String updateWhitJobParam(final Job job){
		return new SQL(){{
			UPDATE ("job_inf");
			if(job.getName()!=""&&job.getName()!=null){
				SET ("name=#{name}");
			}
			if(job.getRemark()!=""&&job.getRemark()!=null){
				SET ("remark=#{remark}");
			}
			WHERE("id=#{id}");
		}
		}.toString();
	}
	
	public String insertWhitJobParam(final Job job){
		return new SQL()
		{
			{
			INSERT_INTO("job_inf");
			if(!job.getName().equals("")&&job.getName()!=null){
				VALUES("name", "#{name}");
			}
			if(!job.getRemark().equals("")&&job.getRemark()!=null){
				VALUES("remark","#{remark}");
			}
		}
		}.toString();
	}
}
