package edu.agh.yait.mailer;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TemplateMessageBuilder {

    private String templateDirectoryPath;


    Configuration freemarkerConfiguration = ConfigurationUtil.getConfiguration();

    public TemplateMessageBuilder(String templateDirectoryPath){
        this.templateDirectoryPath = templateDirectoryPath;
    }


    public MimeMessagePreparator constructMessagePreparator(String[] mailAddresses, String templateName, String tokenUrl){

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setTo(mailAddresses);

                Map<String, Object> dictionary = new HashMap<String, Object>();
                RecipientInfo recipientInfo = new RecipientInfo(mailAddresses);
                //recipientInfo.setContactName("Student");
                MailType mailType = new MailType(recipientInfo, tokenUrl);
                dictionary.put("mailType", mailType);

                String text = getFreeMarkerTemplateContent(dictionary, templateName, mailType);
                helper.setSubject(mailType.getSubject());
                helper.setFrom(mailType.getSenderName());

                System.out.println("Template content : "+text);
                helper.setText(text, true);
            }
        };

    return preparator;
    }

    /**
     * Constructs email messages to {@code mailAddress} by dropping {@code dictionary} values to fields
     * in template {@code templateName}. All fields must be filled.
     * @param mailAddress
     * @param templateName
     * @param subject
     * @param dictionary
     */
    public void constructMessage(String mailAddress, String templateName, String subject, Map<String, String> dictionary) {
        System.out.println("Constructing message to " + mailAddress + " from template " + this.getTemplatePath(templateName));
        System.out.println("Message subject: " + subject);
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }


    private String getTemplatePath(String templateName) {
        return this.templateDirectoryPath + "/" + templateName; // TODO: actual file path separator here
    }


    public String getFreeMarkerTemplateContent(Map<String, Object> dictionary, String templateName, MailType mailType){
        StringBuffer content = new StringBuffer();
        try{
            System.out.println(freemarkerConfiguration.toString());

            Template template = freemarkerConfiguration.getTemplate(templateName);
            String subject = (String) template.getCustomAttribute("subject");
            String senderName = (String) template.getCustomAttribute("senderName");
            mailType.setSubject(subject);
            mailType.setSenderName(senderName);
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(template, dictionary));
            return content.toString();
        }catch(Exception e){
            System.out.println("Exception occured while processing fmtemplate:"+e.getMessage());
        }
        return "";
    }

}
