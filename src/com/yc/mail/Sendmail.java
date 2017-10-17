package com.yc.mail;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;

import org.eclipse.jdt.internal.compiler.batch.Main;

import com.yc.bean.Resuser;  



public class Sendmail  {

	   //用于给用户发送邮件的邮箱
    private String from = "helloworld18@163.com";
        //邮箱的用户名
    private String username = "helloworld18@163.com";
        //邮箱的密码
    private String password = "s2048d";
        //发送邮件的服务器地址
    private String host = "smtp.163.com";
    
    private Resuser user;
    
    public Sendmail(Resuser user){
        this.user = user;
    }
    public Sendmail(){
        
    }
    
    Random r = new Random();
    String s = Integer.toString(  r.nextInt(1000000) );
    
    
    /* 重写run方法的实现，在run方法中发送邮件给指定的用户
     * @see java.lang.Thread#run()
     */
    
    public void run() {
        try{
        	
            Properties prop = new Properties();
            prop.setProperty("mail.host", host);
            prop.setProperty("mail.transport.protocol", "smtp");
            prop.setProperty("mail.smtp.auth", "true");
            Session session = Session.getInstance(prop);
            session.setDebug(true);
            Transport ts = session.getTransport();
            ts.connect(host, username, password);
            Message message = createEmail(session,user);
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
    * @Method: createEmail
    * @Description: 创建要发送的邮件
    * @param session
    * @param user
    * @return
    * @throws Exception
    */ 
    public Message createEmail(Session session,Resuser user) throws Exception{
        
//        String s = makema();
    	//生成一个6位数的验证码
    	
    	
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
        message.setSubject("用户注册邮件");
        
        String info = "你的验证码为" + s + "请不要告诉别人，有效期30分钟";
        message.setContent(info, "text/html;charset=UTF-8");
        message.saveChanges();
        return message;
    }
    
    /**
     * 生成验证码
     * @return
     */
    public String makema(){
//        Random r = new Random();
//        String s = Integer.toString(  r.nextInt(1000000) );
        
        return s;	
    }

    
    
    
}
