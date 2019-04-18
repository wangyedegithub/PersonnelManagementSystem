package cn.it.po;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Employee {
	private Integer id;		//id
	private Dept dept;		//Ա���������Ŷ���
	private Job job;		//Ա������ְλ����
	private String name; 	//����
	private String cardId;	//���֤
	private String address;	//��ַ
	private String postCode;	//�ʱ�
	private String tel;		//�绰��
	private String phone;	//�ֻ���
	private String qqNum;	//qq
	private String email;	//����
	private Integer sex;		//�Ա�
	private String party;	//������ò
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;	//����
	private String education; //ѧ��
	private String race;	//����
	private String speciality;	//רҵ
	private String hobby;	//����
	private String remark;	//��ע
	private Date createDate;	//����ʱ�� 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQqNum() {
		return qqNum;
	}
	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", cardId=" + cardId
				+ ", address=" + address + ", postCode=" + postCode + ", tel="
				+ tel + ", phone=" + phone + ", qqNum=" + qqNum + ", email="
				+ email + ", sex=" + sex + ", party=" + party + ", birthday="
				+ birthday + ", education=" + education + ", race=" + race
				+ ", speciality=" + speciality + ", hobby=" + hobby
				+ ", remark=" + remark + ", createDate=" + createDate + "]";
	}
	
}
