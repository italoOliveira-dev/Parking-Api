package br.com.projeto.parking_api.exception;

public class UsernameValueUniqueException extends RuntimeException{
    
    public UsernameValueUniqueException(String message) {
        super(message);
    }
}
