package danielrrapi.model;

import danielrrapi.model.abstracts.Articolo;

public class Rivista extends Articolo {

    public Periodicita periodicita;

    public Rivista(String codiceIsbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(codiceIsbn, titolo, annoPubblicazione, numeroPagine);

        this.periodicita = periodicita;
    }
    @Override
    public String toString() {
        return "Rivista{" +
                "codiceIsbn='" + codiceIsbn + '\'' +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                ", periodicita='" + periodicita + '\'' +
                '}';
    }




}
