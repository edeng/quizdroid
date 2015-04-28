package edu.washington.ghirme.quizdroid;

import java.util.List;

// This class allows you to easily access questions based on the topic and question number
public class Question {
    private static String[][][] questions = {{{"What is 1 + 1?", "2", "11", "13", "4", "2"}, // math questions
            {"What is 1 * 1?", "1", "3", "11", "111", "1"}},
            {{"Which law states the need to wear seatbelts?", "Newton's First Law", "Newton's Second Law", "Newton's Third Law", "none of the above", "Newton's Third Law"},  // physics questions
                    {"What is force?", "mass", "mass * acceleration", "speed + time", "acceleration * speed", "mass * acceleration"}},
            {{"The Fantastic Four have the headquarters in what building?", "Stark Tower", "Fantastic Headquarters", "Baxter Building", "Xavier Institute", "Baxter Building"}, // marvel questions
                    {"Peter Parker works as a photographer for:", "The Daily Planet", "The Daily Bugle", "The New York Times", "The Rolling Stone", "The Daily Bugle"}}};


    public static String[][] getQuestionsForPosition(int position) {
        return questions[position];
    }

    public static String[] getQuestionForPositionAndQuestion(int position, int questionNumber) {
        return questions[position][questionNumber];
    }
}