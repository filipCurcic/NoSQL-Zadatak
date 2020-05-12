package com.example.zadatak.services;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.zadatak.repositories.CustomExcelRepository;
import com.example.zadatak.repositories.KorisnikRepository;

@Service
public class ReadFileService {

	@Autowired
	private KorisnikRepository studentRepository;
	
	@Autowired
	private CustomExcelRepository excelRepository;
	
	public boolean saveReadFile(MultipartFile multipartFile) throws JSONException {
		boolean isFlag = false;
		if(multipartFile == null) {
			System.out.println("fajl ne postoji");
		} else {
			String exstension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
			
			if(exstension.equalsIgnoreCase("xlsx")) {
				
				isFlag = readDataFromExcel(multipartFile);
			}
			
		}
		return isFlag;
		
	}
	
	@SuppressWarnings("deprecation")
	public boolean readDataFromExcel(MultipartFile multipartFile) throws JSONException {
		Workbook workbook = getWorkBook(multipartFile);
		Sheet sheet =  workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		ArrayList<String> naslovi = new ArrayList<String>();
		while(rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			int brojac = 0;
			JSONObject obj = new JSONObject();
			while(cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				switch(cell.getCellType()) 
				{
					case Cell.CELL_TYPE_NUMERIC:
						if(row.getRowNum() == 0) {
							naslovi.add(cell.getStringCellValue());
						} else {
							break;
						}
					case Cell.CELL_TYPE_STRING:
						if(row.getRowNum() == 0) {
							naslovi.add(cell.getStringCellValue());
						} else {
							String sadrzaj = cell.getStringCellValue();
							if(naslovi.get(brojac).toLowerCase() == "id") {
							} else {
								obj.put(naslovi.get(brojac).toLowerCase(), sadrzaj);
							}
							break;
						}
				}
				brojac++;
			}
			if(row.getRowNum() > 0) {
				obj.remove("id");
				excelRepository.save(obj);
			}
			
		}
		return true;
	}

	private Workbook getWorkBook(MultipartFile multipartFile) {
		Workbook workbook = null;
		
		String exstension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		
		try {
			if(exstension.equalsIgnoreCase("xlsx")){
				workbook = new XSSFWorkbook(multipartFile.getInputStream());
			} else if(exstension.equalsIgnoreCase("xls")) {
				workbook = new HSSFWorkbook(multipartFile.getInputStream());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return workbook;
	}
}
