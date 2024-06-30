package gymproject.gymProject.entity.exception;

public class DuplicatePasswordException extends RuntimeException{

    public DuplicatePasswordException(String message) {
        super(message);
    }
}
