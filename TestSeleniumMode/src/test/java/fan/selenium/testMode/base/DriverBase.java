package fan.selenium.testMode.base;

import fan.selenium.testMode.page.DriverElementPage;
import fan.selenium.testMode.util.HandleCookie;
import fan.selenium.testMode.util.HandleSession;
import fan.selenium.testMode.util.PhotoMove;
import fan.selenium.testMode.util.log;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.*;

public class DriverBase {
    public WebDriver driver;
    public DriverElementPage driverElement;
    //获取driver
    public DriverBase(WebDriver driver){
        this.driver = driver;
        driverElement = new DriverElementPage(driver);
    }




    //获取文案
    public void getText(String mode, String elementName, String parameters, String CaseName) throws Exception {
        String text = driverElement.DrElement(mode, elementName).getText();
        if (text.equals(parameters)) {
            log.error(CaseName + "验证通过，获取结果为：" + text + "；要求结果为：" + parameters);
        }else {
            log.error(CaseName + "验证失败，获取结果为：" + text + "；要求结果为：" + parameters);
            throw new Exception("验证失败");
        }
    }
    //获取Cookie
    public void getCookie(){
        HandleCookie handleCookie = new HandleCookie(driver);
        handleCookie.writeCookie();
    }
    //获取Session
    public void getSeesion() throws Exception {
        HandleSession handleSession = new HandleSession(driver);
        handleSession.writeSession();
    }
    //设置Session
    public void setSeesion(String parameters) {
        HandleSession handleSession = new HandleSession(driver);
        handleSession.setSession(parameters);
    }
    //设置Cookie
    public void setCookies(String parameters){
        HandleCookie handleCookie = new HandleCookie(driver);
        handleCookie.setCookie(parameters);
    }
    //删除Cookie
    public void deleteCookies(Cookie cookie){
        driver.manage().deleteCookie(cookie);
    }
    //刷新界面
    public void refresh(){
        driver.navigate().refresh();
    }
    //获取title
    public String getTitle(){
        return driver.getTitle();
    }
    //根据指定名称获取title
    public String getNameTitle(String win){
        return driver.switchTo().window(win).getTitle();
    }
    //获取当前窗口
    public String getWindowHandle(){
        return driver.getWindowHandle();
    }
    //切换windows窗口
    public void switchWindows(String name){
        List<String> windowsHandles = this.getWindowsHandles();
        Iterator<String> iterator = windowsHandles.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            String title = driver.switchTo().window(next).getTitle();
            if (title.equals(name)){
                driver.switchTo().window(next);
                break;
            }else {
                continue;
            }
        }
    }
    //切换默认窗口
    public void defaultWindouws(){
        List<String> windowsHandles = this.getWindowsHandles();
        driver.switchTo().window(windowsHandles.get(0));
    }
    //获取当前系统窗口list
    public List<String> getWindowsHandles(){
        Set<String> winHandels = driver.getWindowHandles();
        List<String> handles = new ArrayList<String>(winHandels);
        return handles;
    }
    //移动鼠标
    public void moveToElement(String mode, String elementName) throws Exception {
        Actions actions = new Actions(driver);
        actions.moveToElement(driverElement.DrElement(mode, elementName)).perform();
    }
    //切换alert窗口
    public void switchAlert(String parameters){
        if (parameters.equals("确定")){
            driver.switchTo().alert().accept();
        }else {
            driver.switchTo().alert().dismiss();
        }
    }
    //切换frame窗口
    public void frame(String mode, String elementName) throws Exception {
        driver.switchTo().frame(driverElement.DrElement(mode, elementName));
    }
    //封装输入
    public void sendKeys(String mode, String elementName, String value) throws Exception {
        driverElement.DrElement(mode, elementName).sendKeys(value);
    }
    //封装点击
    public void click(String mode, String elementName) throws Exception {
        driverElement.DrElement(mode, elementName).click();
    }
    //清除当前文本框
    public void clear(String mode, String elementName) throws Exception {
        driverElement.DrElement(mode, elementName).clear();
    }
    //关闭驱动
    public void close(){
        System.out.println("close webdriver");
        driver.close();
    }
    //获取当前url
    public String getUrl(){
        return driver.getCurrentUrl();
    }
    //点击返回
    public void back(){
        driver.navigate().back();
    }
    //滑动验证码
    public void movePhoto(String elementName) throws InterruptedException {
        ChromeDriver driver1 = (ChromeDriver) this.driver;
        PhotoMove photoMove = new PhotoMove(driver1);
        photoMove.loginNext(elementName);
        this.driver = (WebDriver) driver1;
    }
    //网页最大化
    public void maxmize(String Preconditions){
        if (null != Preconditions){
            String[] split = Preconditions.split(",");
            driver.manage().window().setSize(new Dimension(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }else {
            driver.manage().window().maximize();
        }
    }
    //访问地址
    public void get(String url, String preconditions){
        log.info("访问网页" + url);
        driver.get(url);
        if ("null".equals(preconditions)){
            driver.manage().window().maximize();
        }else {
            String[] split = preconditions.split(",");
            Dimension dimension = new Dimension(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            driver.manage().window().setSize(dimension);
        }
    }
}
