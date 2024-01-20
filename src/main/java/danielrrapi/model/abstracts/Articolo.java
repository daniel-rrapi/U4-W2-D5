package danielrrapi.model.abstracts;

public abstract class Articolo {
    protected String codiceIsbn;
    protected String  titolo;
    protected int annoPubblicazione;
    protected int numeroPagine;

    public Articolo(String codiceIsbn, String titolo, int annoPubblicazione, int numeroPagine) {
        this.codiceIsbn = codiceIsbn;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public String getCodiceIsbn() {
        return codiceIsbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }
}
