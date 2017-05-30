package edu.agh.yait.mailer;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.Map;


public class TemplateMessageBuilder {
    private static final Logger logger = LoggerFactory.getLogger(TemplateMessageBuilder.class);
    private final Configuration freemarkerConfiguration = ConfigurationUtil.getConfiguration();
    private String templateDirectoryPath;

    public TemplateMessageBuilder(String templateDirectoryPath) {
        this.templateDirectoryPath = templateDirectoryPath;
    }

    public MimeMessagePreparator constructMessagePreparator(String[] mailAddresses, String templateName, String tokenUrl) {

        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(mailAddresses);

            Map<String, Object> dictionary = new HashMap<>();
            RecipientInfo recipientInfo = new RecipientInfo(mailAddresses);
            //recipientInfo.setContactName("Student");
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

    /**
     * Constructs email messages to {@code mailAddress} by dropping {@code dictionary} values to fields
     * in template {@code templateName}. All fields must be filled.
     *
     * @param mailAddress
     * @param templateName
     * @param subject
     * @param dictionary
     */
    public void constructMessage(String mailAddress, String templateName, String subject, Map<String, String> dictionary) {
        logger.debug("Constructing message to " + mailAddress + " from template " + this.getTemplatePath(templateName));
        logger.debug("Message subject: " + subject);
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            logger.debug(entry.getKey() + " -> " + entry.getValue());
        }
    }


    private String getTemplatePath(String templateName) {
        return this.templateDirectoryPath + "/" + templateName; // TODO: actual file path separator here
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
