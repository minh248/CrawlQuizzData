package com.company.main;

import com.company.crawl.get.GetItem;
import com.company.crawl.get.GetQuestion;
import com.company.crawl.get.GetUrl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Crawl all urls");
        System.out.println("2. Crawl all items");
        System.out.println("3. Crawl all questions");
        System.out.println("4. Crawl all");
        System.out.println("Type something to exit \n");

        System.out.print("Choose your option: ");
        int option = scanner.nextInt();
        System.out.println("");

        switch (option){
            case 1:
                System.out.println("Crawl all urls");
                GetUrl.all();
                break;
            case 2:
                System.out.println("Crawl all items");
                GetItem.all();
                break;
            case 3:
                System.out.println("Crawl all questions");
                GetQuestion.all();
                break;
            case 4:
                System.out.println("Crawl all");
                GetUrl.all();
                GetItem.all();
                GetQuestion.all();
                break;
            default:
                System.out.println("Exiting...");
                break;
        }
    }
}