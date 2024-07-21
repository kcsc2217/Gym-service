package gymproject.gymProject.entity.exception;

public class GymNotFoundException extends RuntimeException{

    public GymNotFoundException(String message) {
        super(message);
    }
}
