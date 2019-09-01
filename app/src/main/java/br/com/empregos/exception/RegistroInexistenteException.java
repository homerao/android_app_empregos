package br.com.empregos.exception;

public class RegistroInexistenteException extends Exception {


    public RegistroInexistenteException(String message) {
        super(message);
    }

    public RegistroInexistenteException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistroInexistenteException(Throwable cause) {
        super(cause);
    }



    public RegistroInexistenteException() {
    }
}
