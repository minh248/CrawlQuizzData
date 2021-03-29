package com.company.entity;

public class Answer {
    private int id;
    private String answer;
    private boolean isCorrect;
    private static int count = 0;

    public Answer(String answer) {
        this.id = ++count;
        this.answer = answer;
        this.isCorrect = false;
    }

    public void changeToCorrectAnswer() {
        this.isCorrect = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answer='" + answer + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
