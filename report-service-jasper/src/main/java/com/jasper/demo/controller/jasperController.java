package com.jasper.demo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.jasper.demo.entity.CustomerVO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
public class jasperController {
	  private RestTemplate restTemplate = new RestTemplate();
	  @RequestMapping("/report/{reportFormat}")
	  public String getcustomer(@PathVariable String reportFormat,HttpServletRequest request, HttpServletResponse response) throws JRException, FileNotFoundException
	  {
		  String path="D:/airoli/BIRT_Stuff/Report";
		   	 String URL2= "http://localhost:8185/customer/id/92";
		   List<CustomerVO> Custhistory = new ArrayList<CustomerVO>();
		   ResponseEntity<CustomerVO>  responseFromMaster = restTemplate.exchange(URL2,HttpMethod.GET, null,CustomerVO.class);
	        CustomerVO cust = responseFromMaster.getBody();
	   	 List<CustomerVO> customerlist = new ArrayList<CustomerVO>(); 
	   	customerlist.add(cust);
	        File file=ResourceUtils.getFile("classpath:customer.jrxml");
	  	  JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
	  	   JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(customerlist);
	  	   
	  	  Map<String,Object>parameters=new  HashMap<>();
	  	parameters.put("created by", "Shadma");
	  	  JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, parameters,dataSource);
	  	  
		   if(reportFormat.equalsIgnoreCase("html"))
		   {
	 JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"./customer.html");  
			 
//			   JasperViewer jasperViewer = new JasperViewer(jasperPrint);
//			   jasperViewer.setVisible(true);
		   }
		   
		return "Report generated";
		  
	  }

	
	  
	  
	

}
