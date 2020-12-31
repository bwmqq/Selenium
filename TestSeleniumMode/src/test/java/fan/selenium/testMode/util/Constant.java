package fan.selenium.testMode.util;
//定义常量
public class Constant {
	//定义Excel测试数据文件的路径
	public static final String TestDataExcelFilePath = "E:/Projects/TestSeleniumMode/Excel/SeleniumCase.xlsx";
	//定义Cookie文件位置
	public static final String TestDataCookieFilePath = "../TestSeleniumMode/src/test/resources/cookie.properties";
	public static final String TestDataSessionFilePath = "../TestSeleniumMode/src/test/resources/session.properties";
	//定义浏览器启动驱动名称
	public static final String TestDatachromedriverPath = "../TestSeleniumMode/driver/chromedriver.exe";
	public static final String TestDatageckodiverPath = "../TestSeleniumMode/driver/geckodiver.exe";
	public static final String TestDataoperadriverPath = "../TestSeleniumMode/driver/operadriver.exe";
	
	
	
	
	
	//修改第二张图片为展示，找style="display: none;
	public static final String TestDisplayed = "document.getElementsByClassName(\"geetest_canvas_fullbg\")[0].setAttribute('style', 'display: block')\n";
	//修改第二个图片为隐藏
	public static final String TestHidden = "document.getElementsByClassName(\"geetest_canvas_fullbg\")[0].setAttribute('style', 'display: none')\n";
	//第一张图片
	public static final String TestPhoto1 = "canvas[class='geetest_canvas_bg geetest_absolute']";
	//第二张图片
	public static final String TestPhoto2 = "canvas[class='geetest_canvas_fullbg geetest_fade geetest_absolute']";
	//整个界面的大小
	public static final String Testdiv = "div[class = 'geetest_holder geetest_mobile geetest_ant geetest_popup']";
}
