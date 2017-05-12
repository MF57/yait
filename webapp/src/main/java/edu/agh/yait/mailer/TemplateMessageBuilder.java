package edu.agh.yait.mailer;

import java.util.List;
import java.util.Map;

public class TemplateMessageBuilder {

    /**
     * Constructs email messages to {@code mailAddresses} by dropping {@code dictionary} values to fields
     * in template {@code templateName}. All fields must be filled.
     * @param mailAddresses
     * @param templateName
     * @param subject
     * @param dictionary
     */
    void constructMessage(List mailAddresses, String templateName, String subject, Map dictionary) {
        // TODO: how is template directory known?
    }
}
