package commands;

import data.exercises.InvalidExerciseException;
import data.workouts.InvalidWorkoutException;
import data.workouts.Workout;
import data.workouts.WorkoutList;
import werkIt.UI;

/**
 * A class that will handle
 */
public class WorkoutCommand extends Command {
    public static final String BASE_KEYWORD = "workout";
    public static final String CREATE_ACTION_KEYWORD = "/new";
    public static final String LIST_ACTION_KEYWORD = "/list";
    public static final String DELETE_ACTION_KEYWORD = "/delete";
    public static final String UPDATE_ACTION_KEYWORD = "/update";

    private UI ui;
    private WorkoutList workoutList;

    private String userAction;
    private String userArguments;

    public WorkoutCommand(String userInput, UI ui, WorkoutList workoutList,
                          String userAction, String userArguments) throws InvalidCommandException {
        super(userInput);
        this.ui = ui;
        this.workoutList = workoutList;
        setUserAction(userAction);
        this.userArguments = userArguments;
    }

    public String getUserAction() {
        return this.userAction;
    }

    public void setUserAction(String userAction) throws InvalidCommandException {
        switch (userAction) {
        case CREATE_ACTION_KEYWORD:
            // Fallthrough
        case LIST_ACTION_KEYWORD:
            // Fallthrough
        case DELETE_ACTION_KEYWORD:
            // Fallthrough
        case UPDATE_ACTION_KEYWORD:
            this.userAction = userAction;
            break;
        default:
            String className = this.getClass().getSimpleName();
            throw new InvalidCommandException(className, InvalidCommandException.INVALID_ACTION_ERROR_MSG);
        }
    }

    public String getUserArguments() {
        return this.userArguments;
    }

    public void setUserArguments(String userArguments) {
        this.userArguments = userArguments;
    }

    /**
     * (WIP) Note: need to catch and handle exceptions in this method, not the calling method.
     *
     */
    public void execute() {
        try {
            switch (getUserAction()) {
            case CREATE_ACTION_KEYWORD:
                Workout newWorkout = workoutList.createWorkout(getUserArguments());
                ui.printNewWorkoutCreatedMessage(newWorkout);
            }
        } catch (InvalidExerciseException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again.");

        } catch (InvalidWorkoutException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again.");
        }
    }
}
