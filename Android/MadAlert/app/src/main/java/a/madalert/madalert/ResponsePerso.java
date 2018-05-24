package a.madalert.madalert;

public class ResponsePerso {

    private int numeroAlertas;
    private String distrito;
    private String message;
    private String token;

    public String getDistritoP(){
        return distrito;
    }

    public int getNumeroAlertas(){
        return numeroAlertas;
    }
    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}