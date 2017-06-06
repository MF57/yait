package edu.agh.yait.mailer;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.Map;

@PropertySource("classpath:application.properties")
public class TemplateMessageBuilder {
    private static final Logger logger = LoggerFactory.getLogger(TemplateMessageBuilder.class);
    private final Configuration freemarkerConfiguration = ConfigurationUtil.getConfiguration();
    //@Value("${mailer.template.name}")
    private String templateName = "ticket_template.txt";

    public MimeMessagePreparator constructMessagePreparator(String mailAddress, String tokenUrl) {

        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(mailAddress);

            Map<String, Object> dictionary = new HashMap<>();
            RecipientInfo recipientInfo = new RecipientInfo(mailAddress);
            MailType mailType = new MailType(recipientInfo, tokenUrl);
            dictionary.put("mailType", mailType);

            String text = getFreeMarkerTemplateContent(dictionary, templateName, mailType);
            helper.setSubject(mailType.getSubject());
            helper.setFrom(mailType.getSenderName());

            logger.debug("Template content : " + text);
            helper.setText(text, true);
        };
        return preparator;
    }


    public String getFreeMarkerTemplateContent(Map<String, Object> dictionary, String templateName, MailType mailType) {
        StringBuilder content = new StringBuilder();
        try {
            logger.debug(freemarkerConfiguration.toString());

            Template template = freemarkerConfiguration.getTemplate(templateName);
            String subject = (String) template.getCustomAttribute("subject");
            String senderName = (String) template.getCustomAttribute("senderName");
            mailType.setSubject(subject);
            mailType.setSenderName(senderName);
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(template, dictionary));
            return content.toString();
        } catch (Exception e) {
            logger.error("Exception occured while processing fmtemplate:" + e.getMessage());
        }
        return null;
    }

}
