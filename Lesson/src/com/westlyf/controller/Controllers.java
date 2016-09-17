package com.westlyf.controller;

import com.westlyf.domain.exercise.practical.PracticalExercise;
import com.westlyf.domain.exercise.practical.PracticalPrintExercise;
import com.westlyf.domain.exercise.practical.PracticalReturnExercise;
import com.westlyf.domain.exercise.quiz.QuizExercise;
import com.westlyf.domain.lesson.Lesson;
import com.westlyf.domain.lesson.TextLesson;
import com.westlyf.domain.lesson.VideoLesson;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * Created by robertoguazon on 04/09/2016.
 */
public class Controllers {

    //viewers
    private static TextLessonViewerController textLessonViewerController;
    private static Node textLessonViewerNode;

    private static VideoLessonViewerController videoLessonViewerController;
    private static Node videoLessonViewerNode;

    private static QuizExerciseViewerController quizExerciseViewerController;
    private static Node quizExerciseViewerNode;

    private static PracticalPrintExerciseViewerController practicalPrintExerciseViewerController;
    private static Node practicalPrintExerciseViewerNode;

    private static PracticalReturnExerciseViewerController practicalReturnExerciseViewerController;
    private static Node practicalReturnExerciseViewerNode;

    private static LessonMakerController lessonMakerController;
    private static Node lessonMakerNode;

    private static QuizExerciseMakerController quizExerciseMakerController;
    private static Node quizExerciseMakerNode;

    private static PracticalExerciseMakerController practicalExerciseMakerController;
    private static Node practicalExerciseMakerNode;

    //makers


    //TODO static methods for calling different controllers

    public static void loadAll() {
        try {

            //viewers
            load(ControllerType.TEXT_LESSON_VIEWER, "../view/TextLessonViewer.fxml");
            System.out.println(ControllerType.TEXT_LESSON_VIEWER + " loaded");

            load(ControllerType.VIDEO_LESSON_VIEWER, "../view/VideoLessonViewer.fxml");
            System.out.println(ControllerType.VIDEO_LESSON_VIEWER + " loaded");

            load(ControllerType.QUIZ_EXERCISE_VIEWER, "../view/QuizExerciseViewer.fxml");
            System.out.println(ControllerType.QUIZ_EXERCISE_VIEWER + " loaded");

            load(ControllerType.PRACTICAL_PRINT_EXERCISE_VIEWER, "../view/PracticalPrintExerciseViewer.fxml");
            System.out.println(ControllerType.PRACTICAL_PRINT_EXERCISE_VIEWER + " loaded");

            load(ControllerType.PRACTICAL_RETURN_EXERCISE_VIEWER, "../view/PracticalReturnExerciseViewer.fxml");
            System.out.println(ControllerType.PRACTICAL_RETURN_EXERCISE_VIEWER + " loaded");

            //makers
            load(ControllerType.LESSON_MAKER, "../view/LessonMaker.fxml");
            System.out.println(ControllerType.LESSON_MAKER + " loaded");

            load(ControllerType.QUIZ_EXERCISE_MAKER, "../view/QuizExerciseMaker.fxml");
            System.out.println(ControllerType.QUIZ_EXERCISE_MAKER + " loaded");

            load(ControllerType.PRACTICAL_EXERCISE_MAKER, "../view/PracticalExerciseMaker.fxml");
            System.out.println(ControllerType.PRACTICAL_EXERCISE_MAKER + " loaded");

        } catch (Exception e) {
            e.printStackTrace();
            //System.err.println("Error: " + e);
        }
    }

    public static void load(ControllerType controllerType, String viewLocation) throws IOException {
        FXMLLoader loader = new FXMLLoader(Controllers.class.getResource(viewLocation));

        switch (controllerType) {
            //viewers
            case TEXT_LESSON_VIEWER:
                textLessonViewerNode = loader.load();
                textLessonViewerController = loader.getController();
                break;
            case VIDEO_LESSON_VIEWER:
                videoLessonViewerNode = loader.load();
                videoLessonViewerController = loader.getController();
                break;
            case QUIZ_EXERCISE_VIEWER:
                quizExerciseViewerNode = loader.load();
                quizExerciseViewerController = loader.getController();
                break;
            case PRACTICAL_PRINT_EXERCISE_VIEWER:
                practicalPrintExerciseViewerNode = loader.load();
                practicalPrintExerciseViewerController = loader.getController();
                break;
            case PRACTICAL_RETURN_EXERCISE_VIEWER:
                practicalReturnExerciseViewerNode = loader.load();
                practicalReturnExerciseViewerController = loader.getController();
                break;

            //makers
            case LESSON_MAKER:
                lessonMakerNode = loader.load();
                lessonMakerController = loader.getController();
                break;
            case QUIZ_EXERCISE_MAKER:
                quizExerciseMakerNode = loader.load();
                quizExerciseMakerController = loader.getController();
                break;
            case PRACTICAL_EXERCISE_MAKER:
                practicalExerciseMakerNode = loader.load();
                practicalExerciseMakerController = loader.getController();
                break;

            default:
                break;
        }
    }

    public static void view(ControllerType controllerType, Pane parent) {
        view(controllerType,parent,null);
    }

    public static void view(ControllerType controllerType, Pane parent, Lesson lesson) {
        //clear all children nodes before adding
        parent.getChildren().clear();

        //set lessons on viewers
        checkSetLesson(controllerType, lesson);

        switch (controllerType) {

            //viewers
            case TEXT_LESSON_VIEWER:
                addToPane(parent,textLessonViewerNode);
                break;
            case VIDEO_LESSON_VIEWER:
                addToPane(parent,videoLessonViewerNode);
                break;
            case QUIZ_EXERCISE_VIEWER:
                addToPane(parent,quizExerciseViewerNode);
                break;
            case PRACTICAL_PRINT_EXERCISE_VIEWER:
                addToPane(parent,practicalPrintExerciseViewerNode);
                break;
            case PRACTICAL_RETURN_EXERCISE_VIEWER:
                addToPane(parent,practicalReturnExerciseViewerNode);
                break;

            //makers
            case LESSON_MAKER:
                addToPane(parent,lessonMakerNode);
                break;
            case QUIZ_EXERCISE_MAKER:
                addToPane(parent,quizExerciseMakerNode);
                break;
            case PRACTICAL_EXERCISE_MAKER:
                addToPane(parent,practicalExerciseMakerNode);
                break;

            default:
                break;
        }
    }

    public static Node getNode(ControllerType controllerType, Lesson lesson) {

        //set lessons on viewers
        checkSetLesson(controllerType, lesson);

        switch (controllerType) {

            //viewers
            case TEXT_LESSON_VIEWER:
                return textLessonViewerNode;
            case VIDEO_LESSON_VIEWER:
                return videoLessonViewerNode;
            case QUIZ_EXERCISE_VIEWER:
                return quizExerciseViewerNode;
            case PRACTICAL_PRINT_EXERCISE_VIEWER:
                return practicalPrintExerciseViewerNode;
            case PRACTICAL_RETURN_EXERCISE_VIEWER:
                return practicalReturnExerciseViewerNode;

            //makers
            case LESSON_MAKER:
                return lessonMakerNode;
            case QUIZ_EXERCISE_MAKER:
                return quizExerciseMakerNode;
            case PRACTICAL_EXERCISE_MAKER:
                return practicalExerciseMakerNode;

            default:
                return null;
        }
    }

    public static Node getNode(ControllerType controllerType) {
        return getNode(controllerType, null);
    }

    private static void setLesson(ControllerType controllerType, Lesson lesson) {
        if (lesson != null) {
            switch (controllerType) {
                case TEXT_LESSON_VIEWER:
                    textLessonViewerController.setTextLesson((TextLesson)lesson);
                    break;
                case VIDEO_LESSON_VIEWER:
                    videoLessonViewerController.setVideoLesson((VideoLesson)lesson);
                    break;
                case QUIZ_EXERCISE_VIEWER:
                    quizExerciseViewerController.setQuizExercise((QuizExercise)lesson);
                    break;
                case PRACTICAL_PRINT_EXERCISE_VIEWER:
                    practicalPrintExerciseViewerController.setPracticalPrintExercise((PracticalPrintExercise)lesson);
                    break;
                case PRACTICAL_RETURN_EXERCISE_VIEWER:
                    practicalReturnExerciseViewerController.setPracticalReturnExercise((PracticalReturnExercise)lesson);
                    break;
                default:
                    break;
            }
        }
    }
    private static void checkSetLesson(ControllerType controllerType, Lesson lesson) {
        switch(controllerType) {
            case TEXT_LESSON_VIEWER:
            case VIDEO_LESSON_VIEWER:
            case QUIZ_EXERCISE_VIEWER:
            case PRACTICAL_PRINT_EXERCISE_VIEWER:
            case PRACTICAL_RETURN_EXERCISE_VIEWER:
                setLesson(controllerType, lesson);
                break;
            default:
                break;
        }
    }

    private static void addToPane(Pane parent, Node node) {
        Region region = (Region)node;
        parent.getChildren().add(region);

        region.prefWidthProperty().bind(parent.widthProperty());
        region.prefHeightProperty().bind(parent.heightProperty());
    }
}
