package fan.selenium.testMode.handle;

import fan.selenium.testMode.base.DriverBase;
import fan.selenium.testMode.util.log;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class DriverHandle {

    public static void execute (WebDriver driver, String CaseName, String Preconditions, String elementName, String mode,
                                String objects, String actions, String parameters) throws Exception {
        DriverBase driverBase = new DriverBase(driver);
        if (objects.equals("element")){
            if (actions.equals("click")){
                //点击
                driverBase.click(mode, elementName);
            }else if (actions.equals("getText")){
                driverBase.getText(mode, elementName, parameters, CaseName);
            }else if (actions.equals("moveToElement")){
                //切换iframa
                driverBase.moveToElement(mode, elementName);
            }else if (actions.equals("frame")){
                //切换iframa
                driverBase.frame(mode, elementName);
            }else if (actions.equals("sendKeys")){
                //输入
                driverBase.sendKeys(mode, elementName, parameters);
            }else if (actions.equals("clear")){
                //清除
                driverBase.clear(mode, elementName);
            }else {
                log.info("所需要的操作不支持，请完善！！！");
            }
        }else {
            if (actions.equals("get")){
                //打开网页
                driverBase.get(parameters, Preconditions);
            }else if (actions.equals("movePhoto")){
                //获取Cookies
                log.info(elementName);
                driverBase.movePhoto(elementName);
            }else if (actions.equals("Thead")){
                //等待3秒
                Thread.sleep(Long.valueOf(parameters));
            }else if (actions.equals("getCookie")){
                //获取Cookies
                driverBase.getCookie();
            }else if (actions.equals("setCookie")){
                //写入Cookies
                driverBase.setCookies(parameters);
            }else if (actions.equals("getSession")){
                //获取Cookies
                driverBase.getSeesion();
            }else if (actions.equals("setSession")){
                //写入Cookies
                driverBase.setSeesion(parameters);
            }else if (actions.equals("refresh")){
                //刷新
                driverBase.refresh();
            }else if (actions.equals("getTitle")){
                //获取网页名称
                String title = driverBase.getTitle();
                log.info("当前网页的名称为：" + title);
            }else if (actions.equals("getNameTitle")){
                //获取指定网页名称
                String nameTitle = driverBase.getNameTitle(parameters);
                log.info("选择网页的名称为：" + nameTitle);
            }else if (actions.equals("getWindowHandle")){
                //获取当前窗口名称
                String windowHandle = driverBase.getWindowHandle();
                log.info("当前窗口名称为：" + windowHandle);
            }else if (actions.equals("getWindowsHandles")){
                //获取当前所有窗口名称
                List<String> windowsHandles = driverBase.getWindowsHandles();
                for (String handle : windowsHandles) {
                    log.info("当前窗口名称分别为：" + handle);
                }
            }else if (actions.equals("defaultWindouws")){
                //切换至默认窗口
                driverBase.defaultWindouws();
            }else if (actions.equals("switchWindows")){
                //切换至指定窗口
                driverBase.switchWindows(parameters);
            }else if (actions.equals("alert")){
                //切换至alert弹窗
                driverBase.switchAlert(parameters);
            }else if (actions.equals("max")){
                //最大化浏览器
                driverBase.maxmize(Preconditions);
            }else if (actions.equals("back")){
                //返回
                driverBase.back();
            }else if (actions.equals("close")){
                //关闭
                driverBase.close();
            }else if (actions.equals("getUrl")){
                //获取当前地址
                String url = driverBase.getUrl();
                log.info("当前地址为：" + url);
                System.out.println("当前地址为：" + url);
            }else {
                log.info("所需要的操作不支持，请完善！！！");
            }
        }
    }
}
