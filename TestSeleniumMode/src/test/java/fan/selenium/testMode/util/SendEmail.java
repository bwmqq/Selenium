package fan.selenium.testMode.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class SendEmail {
    public static void sendToEmail(String content){
        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.163.com");
        email.setAuthentication("17301047637@163.com", "fanxiangyang520");
        try {
            email.setFrom("17301047637@163.com");
            email.addTo("2978148291@qq.com");
            email.setSubject("selenium自动化测试邮件");
            email.setContent(content, "text/html; charset = UTF-8");
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
