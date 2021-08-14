package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bean.EmployeeBean;
import com.dao.EmployeeDao;

@RestController
public class EmployeeController {
	
//	@RequestMapping(value = "/home",method = RequestMethod.POST)
//	public String home() {
//		System.out.println("Home Called");
//		System.out.println("Home Called1");
//		return "api called";
//	}
//	
//	@PostMapping(value = "/addEmployee")
//	public String addEmployee(String eName,int eAge,String eEmail,String ePassword) {
//		System.out.println("eName : "+eName +" - "+eAge+"-mail "+eEmail+"-Pass"+ePassword);
//		return eName+'-'+eAge+'-'+eEmail+'-'+ePassword;
//	}
	@Autowired
	EmployeeDao employeeDao;

	@PostMapping(value = "/addEmployee")
	public ResponseEntity<String> addEmployee(EmployeeBean employeeBean) {
		 employeeDao.addEmployee(employeeBean);
		 return new ResponseEntity<String>("Record Inserted",HttpStatus.CREATED);
	}
	@PostMapping(value = "/addEmployee1",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addEmployee1(@RequestBody EmployeeBean employeeBean) {
		System.out.println("empname =================== "+employeeBean.geteName());
		System.out.println("empname =================== "+employeeBean.geteEmail());
			employeeDao.addEmployee(employeeBean);
		 return new ResponseEntity<String>("Record Inserted",HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/deleteEmployee/{eId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("eId") int eId){
		int res = employeeDao.deleteEmployee(eId);
		if(res>0) {
			return new ResponseEntity<String>("Dat Deleted",HttpStatus.OK);
		}
		return new ResponseEntity<String>("Data not Deleted",HttpStatus.CONFLICT);
		
	}
	@GetMapping(value = "/getEmployee/{eId}")
	public ResponseEntity<EmployeeBean> getEmployee(@PathVariable("eId") int eId){
		EmployeeBean employeeBean = employeeDao.getEmployee(eId);
		return new ResponseEntity<EmployeeBean>(employeeBean,HttpStatus.OK);
		
	}
	@PostMapping(value = "/updateEmployee")
	public ResponseEntity<String> updateEmployee(EmployeeBean employeeBean){
//		EmployeeBean employeeBean = new EmployeeBean();
		int res = employeeDao.updateEmployee(employeeBean);
		if(res>0) {
			return new ResponseEntity<String>("Data Updated",HttpStatus.ACCEPTED);

		}
		return new ResponseEntity<String>("Not Updated",HttpStatus.CONFLICT);

	}
}
