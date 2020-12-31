package fan.selenium.testMode.util;

import org.openqa.selenium.Cookie;

import java.io.*;
import java.util.List;
import java.util.Properties;

public class ProSessionUtil {
    private Properties prop;
    private String filePath;
    //构造方法
    public ProSessionUtil(String filePath){
        //传进来的filePath = 当前的filePath
        this.filePath = filePath;
        this.prop = readProperties();
    }
    //读取配置文件
    private Properties readProperties() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(filePath);
            BufferedInputStream in = new BufferedInputStream(inputStream);
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
    //读取配置文件
    public String getPro(String key) {
        if (prop.containsKey(key)){
            String sy = prop.getProperty(key);
            return sy;
        }else {
            System.out.println("你获取的key不存在");
            return "";
        }
    }
}
