package edu.agh.yait.mailer.service;

/**
 * Created by marcinsendera on 16.05.2017.
 */
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import com.sun.deploy.util.StringUtils;
import edu.agh.yait.mailer.model.EmailType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;

@Service("mailService")
public class MailServiceImpl implements MailService{

    @Autowired
    JavaMailSender mailSender;


    @Autowired
    @Qualifier("freemarkerAppConfig")
    Configuration freemarkerConfiguration;


    @Override
    public void sendEmail(Object object) {

        EmailType emailType = (EmailType) object;

        MimeMessagePreparator preparator = getMessagePreparator(emailType);

        try {
            mailSender.send(preparator);
            System.out.println("Message has been sent.............................");
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private MimeMessagePreparator getMessagePreparator(final EmailType emailType){

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                helper.setSubject("[Kompo] Test mail");
                helper.setFrom("kompoyait@gmail.com");
                helper.setTo(emailType.getRecipientInfo().getEmailAddresses());

                Map<String, Object> model = new HashMap<String, Object>();
                model.put("emailType", emailType);

                String text = getFreeMarkerTemplateContent(model);//Use Freemarker or Velocity
                System.out.println("Template content : "+text);

                // use the true flag to indicate you need a multipart message
                helper.setText(text, true);

                //Additionally, let's add a resource as an attachment as well.

            }
        };
        return preparator;
    }



    public String getFreeMarkerTemplateContent(Map<String, Object> model){
        StringBuffer content = new StringBuffer();
        try{
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfiguration.getTemplate("example_template.txt"),model));
            return content.toString();
        }catch(Exception e){
            System.out.println("Exception occured while processing fmtemplate:"+e.getMessage());
        }
        return "";
    }
}
