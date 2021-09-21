package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Utility extends base {
	
	static ExtentReports ext;
	int k=0;
	int column;
	ArrayList<String>al = new ArrayList();

	public static void takess(String tcname) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String dest= System.getProperty("user.dir")+"\\reports\\"+tcname+".png";
		FileUtils.copyFile(src, new File  (dest));
		
	}
	
	
	public static ExtentReports getextreport() {
		
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		//reporter.config().setDocumentTitle("Test");
		
		ext = new ExtentReports();
		ext.attachReporter(reporter);
		
		return ext;
		
	}
	
	public ArrayList<String> getExcel(String tcname) throws IOException {
		
		FileInputStream fis= new FileInputStream("C:\\java selenium excel\\courseinput.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		int sheets=wb.getNumberOfSheets();
		
		for (int i=0; i<sheets; i++) {
			XSSFSheet sheet=wb.getSheetAt(i);
			if (sheet.getSheetName().equalsIgnoreCase("SheetName")) {
				Iterator<Row>rows=sheet.rowIterator();
				Row fr=rows.next();
				Iterator<Cell>cl=fr.cellIterator();
				while(cl.hasNext()) {
					if (cl.next().getStringCellValue().equalsIgnoreCase("Test Case")) {
						column=k;
					}
					k++;
				}
				
				while (rows.hasNext()) {
					Row ft= rows.next();
					if (ft.getCell(column).getStringCellValue().equalsIgnoreCase(tcname)) {
						Iterator<Cell> cls = ft.cellIterator();
						
						if (cls.next().getCellType()==CellType.STRING) {
							al.add(cls.next().getStringCellValue());
						}else {
							String h = NumberToTextConverter.toText(cls.next().getNumericCellValue());
							al.add(h);
						}
					}
				}break;
			}
			}return al;
		}
	
}
