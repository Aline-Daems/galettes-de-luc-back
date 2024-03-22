package be.technobel.dal.models.entities;

import java.util.Map;

public class Email {

    String to;
    String from;
    String subject;

    HtmlTemplate HtmlTemplate;




    public Email(String to, String from, String subject, Email.HtmlTemplate htmlTemplate) {
        this.to = to;
        this.from = from;
        this.subject = subject;
        HtmlTemplate = htmlTemplate;



    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public static class HtmlTemplate {

        private String template;
        private Map<String, Object> props;



        public HtmlTemplate(String template, Map<String, Object> props){
            this.template = template;
            this.props = props;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public Map<String, Object> getProps() {
            return props;
        }

        public void setProps(Map<String, Object> props) {
            this.props = props;
        }


    }

    public HtmlTemplate getHtmlTemplate() {
        return HtmlTemplate;
    }

    public void setHtmlTemplate(Email.HtmlTemplate htmlTemplate) {
        HtmlTemplate = htmlTemplate;
    }
}
