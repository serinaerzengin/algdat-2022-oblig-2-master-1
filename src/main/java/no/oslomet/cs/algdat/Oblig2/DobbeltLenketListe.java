package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() { //konstruktør
        hode = null;
        hale = null;
        endringer = 0;
        antall = 0;
    }

    public DobbeltLenketListe(T[] a) {
        if (a==null){
            throw new NullPointerException("Tabellen a er null!!");
        }

        for(int i = 0; i<a.length; i++){

            if (a[i]!=null){

                if (hode==null){ //hvis hode er null så setter vi første verdi. når det kun er en verdi så er det både hode og hale.
                    Node<T> node= new Node(a[i], null, null);
                    hode=node; //node er både hode og hale
                    hale=node;

                }else{ //hvis ikke hode er null så har vi et hode allerde, og da legger vi inn videre
                   Node<T> haleFørNyNode= hale;
                    Node<T> node= new Node(a[i], haleFørNyNode, null); /*lager ny node, den verdien som var hale før vil være den ny noden sin forrige
                    , og den nye node sin neste vil være null*/
                    hale= node; //setter noden til å være hale
                    haleFørNyNode.neste=node;//må si at noden som var hale sist har den nye noden som sin neste
                }
                antall++; //øker antall
                endringer++; //øker endringer
            }
        }
    }

    private static void fratilKontroll(int antall, int fra, int til) //kopiert fra kompendiet programkode 1.2.3 a) endret tabelllengde til antall.
    {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int antall() { //
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }



    @Override
    public boolean leggInn(T verdi) {
        if (verdi==null){ //sjekker om verdi er null
            throw new NullPointerException();
        }
        if (tom()){ //hvis den er tom så setter vi første verdi til å vøre hode og hale (kun en verdi).
            Node<T> nyNode= new Node<T>(verdi,null,null);
            hode=nyNode;
            hale=nyNode;
            antall++; //øker
            endringer++; //øker
            return true;
        }else { //legger inn resten
            Node<T> halefør = hale; //tar vare på gammel hale slik at vi kan fikse pekeren etterpå
            Node<T> nyNode = new Node<T>(verdi,halefør,null); //samme forklaring som over
            antall++; //øker
            endringer++; //øker
            halefør.neste = nyNode;
            hale = nyNode;
            return true;
        }

    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    private Node<T> finnNode(int indeks) {
        Node<T> node;

        if (indeks <= antall / 2) {
            node = hode;
            for (int i = 0; i < indeks; i++) {
                node = node.neste;
            }
        }
        else {
            node = hale;
            for (int i = antall - 1; i > indeks; i--) {
                node =  node.forrige;
            }
        }
        return node;
    }
    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks,false);           //sjekker indeksen
        // Node <T> node = finnNode(indeks);               //finner noden
        return finnNode(indeks).verdi;                              // returnerer verdien til noden vi fant
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        StringBuilder s= new StringBuilder(); //tatt fra kompendiet 3.2.2 oppgave 3
        s.append('['); //append innebygd metode i StringBuilder som legger til mer i strengen
        if (!tom()){ //hvis den ikke er tom så legger vi inn videre
            s.append(hode.verdi); // legger inn hode først

            for (Node<T> node = hode.neste; node!=null; node=node.neste){ // så lenge noden ikke er null så legger vi inn nodenes verdier. for hver runde så går vi til neste
                s.append(',').append(' ').append(node.verdi); //legger inn komma og mellomrom mellom hver node.
            }

            /*kan også skrive slik isteden for for-løkke
             p = p.neste;
             while (p != null){  // tar med resten hvis det er noe mer
                s.append(',').append(' ').append(p.verdi);
             p = p.neste;
             */

        }

        s.append(']');
        return s.toString();
    }

    public String omvendtString() {
        StringBuilder s = new StringBuilder(); // lik som den over, bare vi endrer for-løkken til å gå bakover isteden.
        s.append('[');
        if (!tom()){
            s.append(hale.verdi);

            for (Node<T> node= hale.forrige; node!=null; node= node.forrige){
                s.append(',').append(' ').append(node.verdi);
            }
        }

        s.append(']');
        return s.toString();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


