package cheikh.tall.biblio.exceptions;

public class CustomException extends RuntimeException{
    public String message;
    public CustomException(String message1) {
        this.message = message1;
    }


}
