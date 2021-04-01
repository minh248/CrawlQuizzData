package com.company.crawl;

import com.company.entity.Answer;
import com.company.entity.config.ConfigValue;
import com.company.entity.Item;
import com.company.entity.Question;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class Crawl {
    private static final ConfigValue config = new ConfigValue();
    private WebDriver driver;

    public Crawl() {
        System.setProperty(config.getDriver(), config.getDriverPath());
        this.driver = new ChromeDriver();
    }

    public ArrayList<Item> itemsFrom(String url) {
        // create temporary list
        ArrayList<Item> items = new ArrayList<>();

        // get into url
        driver.get(url);

        // find by xpath
        List<WebElement> elements = driver.findElements(By.xpath("/html/body/div[@typeof='ItemList']/div/div/div/div[2]/a"));

        // add into temporary items
        for (WebElement element : elements) {
            items.add(new Item(element.getText(), element.getAttribute("href")));
        }

        return items;
    }

    public List<Question> questionsFrom(String url, int itemId) {
        List<Question> questions = new ArrayList<>();

        // get into url from item
        driver.get(url);

        // check url consist "Take Trivia Quiz: Single Page"
        List<WebElement> elementListForChecking = driver.findElements(By.xpath("/html/body/div[5]/div/div[2]/b/a"));
        boolean existElement = false;
        for (WebElement element : elementListForChecking) {
            if (element.getText().equals("Take Trivia Quiz: Single Page")) {
                // get inside if it exists
                existElement = true;
                driver.get(element.getAttribute("href"));
                break;
            }
        }

        if (!existElement) {
            return null;
        }

        // get total question
        final int TOTAL_QUESTION = driver.findElements(By.xpath("/html/body/form/div")).size() - 1;
        System.out.println("total questions: " + TOTAL_QUESTION);

        // load 10 question
        for (int i = 0; i < TOTAL_QUESTION; i++) {
            // initialize question, question content, answer temporary list and multiple choice check
            Question questionTem;
            String questionContent;
            List<Answer> answersTem = new ArrayList<>();
            boolean isMultipleChoiceQuestion;

            // set questionId
            String questionId = "question" + (i + 1);

            // get question element
            WebElement questionElement = driver.findElement(By.xpath("//*[@id='" + questionId + "']"));

            // check multiple choice then click an crawl
            if (questionElement.findElement(By.xpath("div/div[1]/div[2]/div[1]/input")).getAttribute("type").equals("radio")) {
                // is multiple choice
                isMultipleChoiceQuestion = true;

                // click in radio button
                questionElement.findElement(By.xpath("div/div[1]/div[2]/div[1]/input")).click();

                // crawl
                // crawl answer and add into answersTem
                List<WebElement> answersElement = questionElement.findElements(By.xpath("div/div[1]/div[2]/div"));
                for (WebElement element : answersElement) {
                    answersTem.add(new Answer(element.getText()));
                }
                // crawl question content
                questionContent = questionElement.findElement(By.xpath("div/div[1]/div[1]/b")).getText();
                questionTem = new Question(questionContent, isMultipleChoiceQuestion, answersTem);

                // add to global questions variable
                questions.add(questionTem);
            } else {
                // isn't multiple choice
                isMultipleChoiceQuestion = false;
                // crawl question content
                questionContent = questionElement.findElement(By.xpath("div/div[1]/div[1]/b")).getText();
                questionTem = new Question(questionContent, isMultipleChoiceQuestion, answersTem);

                // add to global questions variable
                questions.add(questionTem);
            }
        }

        // submit to check answer in next page
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        // checking correct answers and crawl explaination
        List<WebElement> resultElementXpathList = driver.findElements(By.xpath("/html/body/div[@class='container answercontain']"));

        for (int i = 0; i < TOTAL_QUESTION; i++) {
            // initialize ansersTem, isCorrect, correctAnswer, explanation
            List<Answer> answersTem = questions.get(i).getAnswers();
            ;
            boolean isCorrect = true;
            String correctAnswer;
            String explanation;

            WebElement resultElementXpath = resultElementXpathList.get(i);

            // set isCorrect by result
            if (resultElementXpath.findElement(By.xpath("div/div/table/tbody/tr[2]/td[2]/font")).getAttribute("color").equals("red")) {
                isCorrect = false;
            }

            // set correct answer
            if (isCorrect) {
                correctAnswer = resultElementXpath.findElement(By.xpath("div/div/table/tbody/tr[2]/td[2]/font")).getText();
                for (Answer answerTem : answersTem) {
                    if (answerTem.getAnswer().equals(correctAnswer)) {
                        answerTem.changeToCorrectAnswer();
                    }
                }
            } else {
                correctAnswer = resultElementXpath.findElement(By.xpath("div/div/table/tbody/tr[2]/td[2]/p[1]/font")).getText();

                //this code came from mistake in database of this web
                if (correctAnswer.equals("f")) {
                    correctAnswer = "false";
                }

                for (Answer answerTem : answersTem) {
                    if (answerTem.getAnswer().equals(correctAnswer)) {
                        answerTem.changeToCorrectAnswer();
                    }
                }
            }

            // add correct answer in question which isn't multiple choice question
            if (answersTem.size() == 0) {
                Answer answer = new Answer(correctAnswer);
                answer.changeToCorrectAnswer();
                answersTem.add(answer);
            }


            // crawl explanation
            try {
                explanation = resultElementXpath.findElement(By.xpath("div/div/table/tbody/tr[2]/td[2]/p/font[@color='darkblue']")).getText();
                questions.get(i).setExplanation(explanation);
            } catch (Exception e) {
                System.out.println("no explanation here");
                explanation = "";
            }

            // set itemId for question
            questions.get(i).setItemId(itemId);
        }

        return questions;
    }

    public void closeDriver() {
        try {
            driver.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Driver is closed");
        }
    }
}