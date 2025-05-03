package com.example.SpringbootMongodbCurdwithExcel.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.SpringbootMongodbCurdwithExcel.model.Employee;
import com.example.SpringbootMongodbCurdwithExcel.repository.OriginRepository;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class EmployeeExcelController {
	
	@Autowired
	private OriginRepository originRepository;
	
	@GetMapping("/employees/template")
	public void downloadTemplate(HttpServletResponse response) throws IOException {
	    XSSFWorkbook workbook = new XSSFWorkbook();
	    XSSFSheet sheet = workbook.createSheet("Employees");

	    Row header = sheet.createRow(0);
	    header.createCell(0).setCellValue("EID");
	    header.createCell(1).setCellValue("Name");
	    header.createCell(2).setCellValue("Sal");
	    header.createCell(3).setCellValue("Address");
	    header.createCell(4).setCellValue("Designation");

	    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	    response.setHeader("Content-Disposition", "attachment; filename=employee_template.xlsx");

	    workbook.write(response.getOutputStream());
	    workbook.close();
	}
	
	
	@PostMapping("/employees/upload")
	public ResponseEntity<String> uploadEmployees(@RequestParam("file") MultipartFile file) {
	    try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
	        XSSFSheet sheet = workbook.getSheetAt(0);
	        List<Employee> employees = new ArrayList<>();

	        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	            Row row = sheet.getRow(i);
	            if (row != null) {
	            	 String eid =  getCellValueAsString(row.getCell(0));
	                String name =  getCellValueAsString(row.getCell(1));
	                String sal =  getCellValueAsString(row.getCell(2));
	                String address =  getCellValueAsString(row.getCell(3));
	                String department =  getCellValueAsString(row.getCell(4));
	                
	                Employee emp = new Employee();
	                emp.setEid(eid);
	                emp.setName(name);
	                emp.setSal(sal);
	                emp.setAddress(address);
	                emp.setDesignation(department);
	                employees.add(emp);
	            }
	        }
	        originRepository.saveAll(employees);
	        return ResponseEntity.ok("Uploaded " + employees.size() + " employees");
	    } catch (IOException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file");
	    }
	}
	
	private String getCellValueAsString(Cell cell) {
	    if (cell == null) return "";

	    switch (cell.getCellType()) {
	        case STRING:
	            return cell.getStringCellValue();
	        case NUMERIC:
	            if (DateUtil.isCellDateFormatted(cell)) {
	                return cell.getDateCellValue().toString(); // or format as needed
	            } else {
	                return String.valueOf(cell.getNumericCellValue());
	            }
	        case BOOLEAN:
	            return String.valueOf(cell.getBooleanCellValue());
	        case FORMULA:
	            return cell.getCellFormula();
	        case BLANK:
	            return "";
	        default:
	            return "";
	    }
	}

	
	
	

}
