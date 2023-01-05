package gui.aussenanlage;

public class PlausiException extends Exception {

    private static final long serialVersionUID = 1L;

    public static final String FORMAL = "formal";
    public static final String INHALTLICH = "inhaltlich";

    private String plausiTyp;
    private String feldname;

    public PlausiException(String plausiTyp, String feldname){
        this.plausiTyp = plausiTyp;
        this.feldname = feldname;
    }

    public String getPlausiTyp() {
        return this.plausiTyp;
    }

    @Override
    public String getMessage(){
        return "Bitte korrigieren Sie den " + plausiTyp + "en "
                + "Eingabefehler im Feld " + this.feldname + ".";
    }
}