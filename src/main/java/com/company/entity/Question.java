package com.company.entity;

import java.util.List;

public class Question {
    private int id;
    private String question;
    private boolean isMultipleChoiceQuestion;
    private String explanation;
    private int itemId;
    private List<Answer> answers;
    private static int count = 0;

    public Question(String question, boolean isMultipleChoiceQuestion, List<Answer> answers) {
        this.id = ++count;
        this.question = question;
        this.isMultipleChoiceQuestion = isMultipleChoiceQuestion;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isMultipleChoiceQuestion() {
        return isMultipleChoiceQuestion;
    }

    public void setMultipleChoiceQuestion(boolean multipleChoiceQuestion) {
        isMultipleChoiceQuestion = multipleChoiceQuestion;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explaination) {
        this.explanation = explaination;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", isMultipleChoiceQuestion=" + isMultipleChoiceQuestion +
                ", explaination='" + explanation + '\'' +
                ", answers=" + answers +
                '}';
    }
}
