package com.example.myguessapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Round implements Serializable {

    private int correctAnswer;
    private List<String> currentCategories;
    private List<String> allCategories;

    public List<String> getCurrentCategories() {
        return currentCategories;
    }

    public void setCurrentCategories(List<String> currentCategories) {
        this.currentCategories = currentCategories;
    }

    Round() {
        this.currentCategories = new ArrayList<String>();
        currentCategories.add("unknown");
        currentCategories.add("unknown");
        this.correctAnswer = 0;
    }

    Round(List<String> categoriesList, String cat1, String cat2) {
        this.currentCategories = new ArrayList<String>();
        currentCategories.add(cat1);
        currentCategories.add(cat2);
        this.correctAnswer = pickCorrectAnswer();
        this.allCategories = categoriesList;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int pickCorrectAnswer() {
        Random r = new Random();
        int result = r.nextInt(100) + 1;
        return result;
    }

    private void drawCorrectAnswer() {
        Random r = new Random();
        int result = r.nextInt(100) + 1;
        setCorrectAnswer(result);
    }

    public String toString() {
        String category1 = this.currentCategories.get(0).toString();
        String category2 = this.currentCategories.get(1).toString();
        String answer = Integer.toString(this.correctAnswer);
        return category1 + " " + category2 + " " + answer;
    }

    public void drawNewCategories() {
        List categories = this.allCategories;
        int roof = categories.size() / 2;
        Random r = new Random();
        int index = r.nextInt(roof) * 2;
        ArrayList<String> newCategories = new ArrayList<>();
        newCategories.add(categories.get(index).toString());
        newCategories.add(categories.get(index + 1).toString());
        this.setCurrentCategories(newCategories);
    }

    public void drawNewCorrectAnswer() {
        Random r = new Random();
        int newAnswer = r.nextInt(100) + 1;
        this.correctAnswer = newAnswer;
    }
}
