package hibernate.exceptions;

/**
 * Created by Андрей on 18.04.2018.
 */
public class NoSuchProductException extends RuntimeException {
   public NoSuchProductException(String exception) {
       super(exception);
   }
}
