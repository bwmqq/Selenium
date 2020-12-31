package fan.selenium.testMode.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.SessionStorage;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class HandleSession {
	public WebDriver driver;
	public ProSessionUtil pro;
	public HandleSession(WebDriver driver){
		this.driver = driver;
		pro = new ProSessionUtil(Constant.TestDataSessionFilePath);
	}
	public void setSession(String session){
		ChromeDriver driver1 = (ChromeDriver) this.driver;
		String[] split = session.split(",");
		SessionStorage sessionStorage = driver1.getSessionStorage();
		for (int i = 0; i < split.length; i++) {
			String value = pro.getPro(split[i]);
			sessionStorage.setItem(split[i], value);
		}
		this.driver = (WebDriver) driver1;
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().refresh();
	}
	//获取session
	public void writeSession() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ChromeDriver driver1 = (ChromeDriver) this.driver;
		SessionStorage sessionStorage = driver1.getSessionStorage();
		Set<String> strings = sessionStorage.keySet();
		List<String> strings1 = new ArrayList<String>(strings);
		Properties pro = new Properties();
		FileOutputStream file = new FileOutputStream(Constant.TestDataSessionFilePath);
		for (String key : strings1) {
			pro.setProperty(key, sessionStorage.getItem(key));
		}
		pro.store(file, "Session");
		this.driver = (WebDriver) driver1;
	}
}
