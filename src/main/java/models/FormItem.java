package models;

public class FormItem {

    private String parameter;
    private String question;
    private String answerType;

    public FormItem(String parameter, String question, String answerType) {
        this.parameter = parameter;
        this.question = question;
        this.answerType = answerType;
    }

    public String getParameter() {
        return this.parameter;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswerType() {
        return this.answerType;
    }
}
