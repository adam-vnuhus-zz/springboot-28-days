// package com.onemount.onecinema.mail;

// import java.util.Map;

// import javax.mail.MessagingException;
// import javax.mail.internet.MimeMessage;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.core.io.Resource;
// import org.springframework.mail.SimpleMailMessage;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.mail.javamail.MimeMessageHelper;
// import org.springframework.stereotype.Service;
// import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
// import org.thymeleaf.context.Context;
// import org.thymeleaf.spring5.SpringTemplateEngine;

// @Service("EmailService")
// public class EmailServiceImpl implements EmailService{

//     private static final String NOREPLY_ADDRESS = "anhtunanh@gmail.com";
//     @Autowired
//     private JavaMailSender emailSender;
//     @Autowired
//     private SimpleMailMessage template;
//     @Autowired
//     private SpringTemplateEngine thymeleafTemplateEngine;
//     @Autowired
//     private FreeMarkerConfigurer freemarkerConfigurer;
//     @Value("classpath:/mail-logo.png")
//     private Resource resourceFile;

//     @Override
//     public void sendMessageUsingThymeleafTemplate(String to, String subject, Map<String, Object> templateModel)
//             throws MessagingException {

//         Context thymeleafContext = new Context();
//         thymeleafContext.setVariables(templateModel);

//         String htmlBody = thymeleafTemplateEngine.process("template-thymeleaf.html", thymeleafContext);

//         sendHtmlMessage(to, subject, htmlBody);
//     }

//     private void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
//         MimeMessage message = emailSender.createMimeMessage();
//         MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
//         helper.setFrom(NOREPLY_ADDRESS);
//         helper.setTo(to);
//         helper.setSubject(subject);
//         helper.setText(htmlBody, true);
//         helper.addInline("attachment.png", resourceFile);
//         emailSender.send(message);
//     }
// }