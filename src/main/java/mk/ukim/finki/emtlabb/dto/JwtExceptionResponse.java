package mk.ukim.finki.emtlabb.dto;
import java.util.Date;

public record JwtExceptionResponse(
        Date timestamp,
        int status,
        String error,
        String message,
        String path
) {

    public JwtExceptionResponse(int status, String error, String message, String path) {
        this(new Date(), status, error, message, path);
    }

    @Override
    public Date timestamp() {
        return timestamp;
    }

    @Override
    public int status() {
        return status;
    }

    @Override
    public String error() {
        return error;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public String path() {
        return path;
    }
}
