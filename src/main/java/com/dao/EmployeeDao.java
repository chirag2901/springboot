package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bean.EmployeeBean;
import com.controller.EmployeeController;

@Repository
public class EmployeeDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private final static class EmployeeMapper implements RowMapper<EmployeeBean> {

		@Override
		public EmployeeBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.seteId(rs.getInt("eid"));
			employeeBean.seteName(rs.getString("ename"));
			employeeBean.seteAge(rs.getInt("eage"));
			employeeBean.seteEmail(rs.getString("eemail"));
			employeeBean.setePassword(rs.getString("epassword"));
			return employeeBean;
		}

	}

	public int addEmployee(EmployeeBean employeeBean) {
		return jdbcTemplate.update("insert into employee (ename,eage,eemail,epassword)values(?,?,?,?)",
				employeeBean.geteName(), employeeBean.geteAge(), employeeBean.geteEmail(), employeeBean.getePassword());
	}

	public int deleteEmployee(int eId) {
		return jdbcTemplate.update("delete from employee where eid = ?", eId);

	}
	public int updateEmployee(EmployeeBean employeeBean) {
//		EmployeeBean employeeBean = new EmployeeBean();
		
		return jdbcTemplate.update("update employee set ename = ? , eemail = ?,eage = ? ,epassword=? where eid = ?",employeeBean.geteName(),employeeBean.geteEmail(),employeeBean.geteAge(),employeeBean.getePassword(),employeeBean.geteId());
		
	}
	public EmployeeBean getEmployee(int eId) {
		return jdbcTemplate.queryForObject("select * from employee where eid = "+eId+"",new EmployeeMapper() );
	}
}
