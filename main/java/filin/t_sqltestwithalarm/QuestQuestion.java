package filin.t_sqltestwithalarm;

import java.util.ArrayList;

public class QuestQuestion {

    private String Question;
    private ArrayList<String> Answer;
    private String TrueAnswer;

    public QuestQuestion(String question, ArrayList<String> answer, String trueAnswer) {
        this.Question = question;
        this.Answer = answer;
        this.TrueAnswer = trueAnswer;
    }


    public String getQuestion() {
        return this.Question;
    }

    public void setQuestion(String question) {
        this.Question = question;
    }

    public ArrayList<String> getAnswer() {
        return this.Answer;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.Answer = answer;
    }

    public String getTrueAnswer() {
        return this.TrueAnswer;
    }

    public void setTrueAnswer(String trueAnswer) {
        this.TrueAnswer = trueAnswer;
    }

    @Override
    public String toString() {
        return "{'Question = " + Question + '\'' +
                ",\n Answer=" + Answer + "\n" +
                ", TrueAnswer='" + TrueAnswer + '\'' +
                '}';
    }
}