package edu.agh.yait.mailer;

/**
 * Created by marcinsendera on 24.05.2017.
 */

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

class ConfigurationUtil {
    private static Configuration configuration;

    static {
        configuration = new Configuration(Configuration.VERSION_2_3_23);
        ClassTemplateLoader loader = new ClassTemplateLoader(
                ConfigurationUtil.class, "/fmtemplates/");
        configuration.setTemplateLoader(loader);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    static Configuration getConfiguration() {
        return configuration;
    }
}
