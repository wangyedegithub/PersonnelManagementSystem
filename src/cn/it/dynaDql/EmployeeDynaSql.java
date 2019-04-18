package cn.it.dynaDql;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import cn.it.po.Employee;

public class EmployeeDynaSql {
	public String findWhitEmployeeParam(final Map<String, Object> params){
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("employee_inf");
				if(params.get("employee")!=null){
					Employee employee =(Employee) params.get("employee");
					if(employee.getDept()!=null&&employee.getDept().getId()!=null&&employee.getDept().getId()!=0){
						WHERE("DEPT_ID = #{employee.dept.id}");
					}
					if(employee.getJob()!=null&&employee.getJob().getId()!=null&&employee.getJob().getId()!=0){
						WHERE("JOB_ID = #{employee.job.id}");
					}
					if(employee.getName()!=null&&!employee.getName().equals("")){
						WHERE("name LIKE CONCAT ('%',#{employee.name},'%')");
					}
					if (employee.getPhone()!=null&&!employee.getPhone().equals("")) {
						WHERE("phone LIKE CONCAT ('%',#{employee.phone},'%')");
					}
					if(employee.getCardId()!=null&&!employee.getCardId().equals("")){
						WHERE("card_id LIKE CONCAT ('%',#{employee.cardId},'%')");
					}
					if (employee.getSex()!=null&&employee.getSex()!=0) {
						WHERE("sex=#{employee.sex}");
					}
				}
			}
		}.toString();

		if(params.get("page")!=null){
			sql += " limit #{page.startIndex},#{page.pageSize}";
		}
		return sql;
	}
	
	public String count(final Employee employee){
		return new SQL(){{
			SELECT ("count(*)");
			FROM ("employee_inf");
			if(employee.getDept()!=null&&employee.getDept().getId()!=null&&employee.getDept().getId()!=0){
				WHERE("DEPT_ID = #{dept.id}");
			}
			if(employee.getJob()!=null&&employee.getJob().getId()!=null&&employee.getJob().getId()!=0){
				WHERE("JOB_ID = #{job.id}");
			}
			if(employee.getName()!=null&&!employee.getName().equals("")){
				WHERE("name LIKE CONCAT ('%',#{name},'%')");
			}
			if (employee.getPhone()!=null&&!employee.getPhone().equals("")) {
				WHERE("phone LIKE CONCAT ('%',#{phone},'%')");
			}
			if(employee.getCardId()!=null&&!employee.getCardId().equals("")){
				WHERE("card_id LIKE CONCAT ('%',#{cardId},'%')");
			}
			if (employee.getSex()!=null&&employee.getSex()!=0) {
				WHERE("sex=#{sex}");
			}
		}
		}.toString();
	}
	
	
	public String updateWhitEmployeeParam(final Employee employee){
		return new SQL(){{
			UPDATE ("employee_inf");
			if(employee.getDept()!=null){
				SET("DEPT_ID = #{dept.id}");
			}
			if(employee.getJob()!=null){
				SET("JOB_ID = #{job.id}");
			}
			if(employee.getName()!=null){
				SET("name = #{name}");
			}
			if (employee.getPhone()!=null) {
				SET("phone = #{phone}");
			}
			if(employee.getCardId()!=null){
				SET("card_id = #{cardId}");
			}
			if (employee.getSex()!=null) {
				SET("sex = #{sex}");
			}
			if (employee.getTel()!=null) {
				SET("tel = #{tel}");
			}
			if (employee.getEmail()!=null) {
				SET("email = #{email}");
			}
			if(employee.getParty()!=null){
				SET("party = #{party}");
			}
			if (employee.getBirthday()!=null) {
				SET("birthday = #{birthday}");
			}
			if (employee.getRace()!=null) {
				SET("race = #{race}");
			}
			if (employee.getEducation()!=null) {
				SET("education = #{education}");
			}
			if (employee.getSpeciality()!=null) {
				SET("speciality = #{speciality}");
			}
			if (employee.getHobby()!=null) {
				SET("hobby = #{hobby}");
			}
			if (employee.getRemark()!=null) {
				SET("remark = #{remark}");
			}
			if(employee.getCreateDate()!=null){
				SET("create_Date = #{create_Date}");
			}
			if (employee.getQqNum()!=null) {
				SET("qq_num = #{qqNum}");
			}
			if (employee.getPostCode()!=null) {
				SET("post_Code = #{postCode}");
			}
			if(employee.getAddress()!=null){
				SET("address = #{address}");
			}
			WHERE("id = #{id}");
		}
		}.toString();
	}
	
	public String insertWhitEmployeeParam(final Employee employee){
		return new SQL()
		{
			{
			INSERT_INTO("employee_inf");
			if(employee.getDept()!=null){
				VALUES("DEPT_ID","#{dept.id}");
			}
			if(employee.getJob()!=null){
				VALUES("JOB_ID"," #{job.id}");
			}
			if(employee.getName()!=null){
				VALUES("name"," #{name}");
			}
			if (employee.getPhone()!=null) {
				VALUES("phone"," #{phone}");
			}
			if(employee.getCardId()!=null){
				VALUES("card_id"," #{cardId}");
			}
			if (employee.getSex()!=null) {
				VALUES("sex","#{sex}");
			}
			if (employee.getTel()!=null) {
				VALUES("tel","#{tel}");
			}
			if (employee.getEmail()!=null) {
				VALUES("email","#{email}");
			}
			if(employee.getParty()!=null){
				VALUES("party"," #{party}");
			}
			if (employee.getBirthday()!=null) {
				VALUES("birthday"," #{birthday}");
			}
			if (employee.getRace()!=null) {
				VALUES("race"," #{race}");
			}
			if (employee.getEducation()!=null) {
				VALUES("education"," #{education}");
			}
			if (employee.getSpeciality()!=null) {
				VALUES("speciality"," #{speciality}");
			}
			if (employee.getHobby()!=null) {
				VALUES("hobby"," #{hobby}");
			}
			if (employee.getRemark()!=null) {
				VALUES("remark"," #{remark}");
			}
			if(employee.getCreateDate()!=null){
				VALUES("create_Date"," #{create_Date}");
			}
			if (employee.getQqNum()!=null) {
				VALUES("qq_num"," #{qqNum}");
			}
			if (employee.getPostCode()!=null) {
				VALUES("post_Code"," #{postCode}");
			}
			if(employee.getAddress()!=null){
				VALUES("address"," #{address}");
			}
		}
		}.toString();
	}
}
