package com.bl.lms.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bl.lms.services.MailServices;

@Component
public class MessageListnerImpl implements MessageListener{


@Autowired
private MailServices  mailsender;
	
	
 @SuppressWarnings("static-access")
public void onMessage(Email email) throws NoSuchFieldException, SecurityException, ClassNotFoundException {
	//email = email.replaceAll("Email ", "Email=");

	
	System.out.println(email);
	System.out.println("to : "+email.getMailTo());
	System.out.println("from : "+email.getMailFrom());
	System.out.println("body:"+email.getMailBody());
	System.out.println("subject:"+email.getMailSubject());
	//SENDING MESSAGE FROM JMS GETTING FROM RABBITMQ ONE BY ONE
	mailsender.send(email.getMailTo(), email.getMailSubject(), email.getMailBody());
	

  
  System.out.println("Message Received:" +email.toString());

  System.out.println(new Date());
  
 }

}
