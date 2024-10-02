package utilities;

	import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;
	import com.aventstack.extentreports.markuputils.ExtentColor;
	import com.aventstack.extentreports.markuputils.MarkupHelper;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
	import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;
	

public class ExtentReportManager implements ITestListener{
		
	public static ExtentReports extent	;
	public static ExtentSparkReporter sparkReporter;
	public static ExtentTest test;
	
	String repName;
		
public void onStart(ITestContext testContext) {
	
	  SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); 
	  Date dt = new Date(); String currentdatetimeStamp = df.format(dt);
	 
	 
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//

	repName = "Test-Report-"+timeStamp + ".html";
	sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
	
	sparkReporter.config().setDocumentTitle("openCart Automation Report");
	sparkReporter.config().setReportName("OpenCart Functional Testing");
	sparkReporter.config().setTheme(Theme.DARK);	
	
	extent = new ExtentReports();
	extent.attachReporter(sparkReporter);
	extent.setSystemInfo("Application", "OpenCart");
	extent.setSystemInfo("Module", "Admin");
	extent.setSystemInfo("SubModule","Customers");
	extent.setSystemInfo("UserName",System.getProperty("user.name"));
	extent.setSystemInfo("Enviornment","QA");
	
	
	
	String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
	
		String BrowserName = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", BrowserName);
	
	List<String>includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
}	
		
	public void onTestSuccess(ITestResult result)	{
		
		test =extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+ "got Successfully Excecuted");
	}
	
	
	
	public void onTestFailure(ITestResult result) {
		test =extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+ "got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());	
	//try {	
	String imgPath = new BaseClass().captureScreen(result.getName());
	test.addScreenCaptureFromPath(imgPath);
	

	//catch(IOException e1) {
	//	e1.printStackTrace();
	
	}

public void onTestSkipped(ITestResult result)	{
	test = extent.createTest(result.getTestClass().getName());
	test.assignCategory(result.getMethod().getGroups());
	test.log(Status.SKIP ,result.getName()+ "got Skipped");
	test.log(Status.INFO, result.getThrowable().getMessage());	
}

public void onFinish(ITestResult result)	{	
	extent.flush();
	String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
	File extentReport = new File(pathOfExtentReport );
	try {
		Desktop.getDesktop().browse(extentReport.toURI());
	}
	catch(IOException e) {
		e.printStackTrace();
	}
	/*
	 * try { URL url = new URL
	 * ("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
	 * 
	 * ImageHtmlEmail email = new ImageHtmlEmail(); email.setDataSourceResolver(new
	 * DataSourceUrlResolver(url)); email.setHostName("smpt.goole.com");
	 * email.setSmtpPort(465); email.setAuthenticator(new
	 * DefaultAuthenticator("shruthi.344@gmail.com","password"));
	 * email.setSSLOnConnect(true); email.setFrom("Shruthi.344@gmail.com");
	 * email.setSubject("Test Results");
	 * email.setMsg("Please Find the Attatched Report");
	 * email.addTo("shruthi.344@gamil.com");
	 * email.attach(url,"extent report","please Check Report"); email.send();
	 * 
	 * 
	 * } catch (Exception e ) { e.printStackTrace(); }
	 */
}
}


			
			
			
			
			
			
	
	
	
	
	
	
	
	
	
	
		
	

