package be.technobel.pl.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("smtp.gmail.com")
    private  String host;
    @Value("587")
    private int port;

    private JavaMailSender javaMailSender(){

        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();

        javaMailSenderImpl.setHost(host);
        javaMailSenderImpl.setPort(port);
        javaMailSenderImpl.setUsername("daems.aline90@gmail.com");
        javaMailSenderImpl.setPassword("eilzjibzzxqxigua");
        javaMailSenderImpl.setJavaMailProperties(javaMailProperties());
        return javaMailSenderImpl;



    }

    private Properties javaMailProperties() {

        Properties properties = new Properties();

        properties.setProperty("mail.transport.protocol", "smtp");

        properties.setProperty("mail.smtp.auth", "true");

        properties.setProperty("mail.smtp.starttls.enable", "true");

        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SocketFactory");

        properties.setProperty("mail.debug", "true");

        return  properties;


    }

    @Primary
    @Bean
    public FreeMarkerConfigurationFactoryBean factoryBean(){

        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();

        bean.setTemplateLoaderPath("classpath:/templates");

        return bean;
    }


}
