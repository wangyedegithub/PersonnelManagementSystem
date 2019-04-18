package cn.it.dynaDql;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import cn.it.po.Dept;

public class DeptDynaSql {
	public String findWhitDeptParam(final Map<String, Object> params){
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("dept_inf");
				if (params.get("dept") != null) {
					Dept dept = (Dept) params.get("dept");
					if (dept.getName() != null && !dept.getName().equals("")) {
						WHERE("name LIKE CONCAT ('%',#{dept.name},'%')");
					}
				}
			}
		}.toString();

		if(params.get("page")!=null){
			sql += " limit #{page.startIndex},#{page.pageSize}";
		}
		return sql;
	}
	
	public String count(final Dept dept){
		return new SQL(){{
			SELECT ("count(*)");
			FROM ("dept_inf");
			if(dept.getName()!=null&&dept.getName()!=""){
				WHERE ("name LIKE CONCAT ('%',#{name},'%')");
			}
		}
		}.toString();
	}
	
	
	public String updateWhitDeptParam(final Dept dept){
		return new SQL(){{
			UPDATE ("dept_inf");
			if(dept.getName()!=""&&dept.getName()!=null){
				SET ("name=#{name}");
			}
			if(dept.getRemark()!=""&&dept.getRemark()!=null){
				SET ("remark=#{remark}");
			}
			WHERE("id=#{id}");
		}
		}.toString();
	}
	
	public String insertWhitDeptParam(final Dept dept){
		return new SQL()
		{
			{
			INSERT_INTO("dept_inf");
			if(!dept.getName().equals("")&&dept.getName()!=null){
				VALUES("name", "#{name}");
			}
			if(!dept.getRemark().equals("")&&dept.getRemark()!=null){
				VALUES("remark","#{remark}");
			}
		}
		}.toString();
	}
}
