package edu.agh.yait.mailer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TemplateMessageBuilder {

    private String templateDirectoryPath;

    public TemplateMessageBuilder(String templateDirectoryPath) {
        this.templateDirectoryPath = templateDirectoryPath;
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

}
