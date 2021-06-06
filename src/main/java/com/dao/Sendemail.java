package com.dao;


import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.util.Date;

public class Sendemail
{
    public void sendmail(String to)throws IOException
    {
        String To=to;
        String title = "结算成功";
        String subject = MimeUtility.encodeWord(title, "UTF-8", "Q");
        String texts = "您已成功结算购物车里的商品";

        String from = "majiayu5422@163.com";  
        String host = "smtp.163.com";   
 
        Properties properties = System.getProperties();
        // 设置邮件服务器
        properties.setProperty("mail.transport.protocol", "smtp");//使用协议
        properties.setProperty("mail.smtp.host", host);//发件人邮箱服务地址
        properties.setProperty("mail.smtp.auth", "true");//需要请求认证
        properties.setProperty("mail.smtp.port", "465");//ssl端口
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.ssl.enable", true);
        final String username = "majiayu5422@163.com";   
        final String password = "BBNSJQSULQSUPGOP";     
        properties.setProperty("mail.user", username);
        properties.setProperty("mail.password", password);
        Session session = Session.getDefaultInstance(properties);
        try{
            MimeMessage mime = new MimeMessage(session);
            mime.setFrom(new InternetAddress(from));
            mime.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(To));
            mime.setSubject(subject);
            mime.setContent(texts, "text/html;charset=UTF-8");
            mime.setSentDate(new Date());
            mime.saveChanges();
            Transport transport = session.getTransport();
            transport.connect(username,password);
            transport.sendMessage(mime, mime.getAllRecipients());
            transport.close();
 
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
