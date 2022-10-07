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
        fratilKontroll(antall,fra,til);                 //Sjekker at fra til og antall er gyldig intervall
        Liste <T> liste = new DobbeltLenketListe<>();
        for(int i = fra;i<til;i++){
            T verdi = hent(i);                          //henter verdien på riktig indeks
            liste.leggInn(verdi);                       // legger til i sublista
        }

        return liste;

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
        if (verdi==null){ //sjekker om verdi er null
            throw new NullPointerException();
        }
        if (indeks<0 || indeks>antall){ //sjekker om indeks er negativ eller større enn antall
            throw new IndexOutOfBoundsException("indeks er negativ eller større enn antall");
        }


        else{
            if (tom()){ //hvis tom så er verdien både hode og hale
                Node<T> nyNode= new Node<T>(verdi,null,null); //forrige og neste til verdien bli null fordi det kun er en verdi
                hode=nyNode; //oppdaterer hode
                hale=nyNode; //oppdaterer hale
                antall++; //øker
                endringer++; //øker
            }else if (indeks==0){ // hvis den skal legges inn først så blir dette det nye hode.
                Node<T> hodefør = hode; //tar vare på den som var hode før
                Node<T> nyNode= new Node<T>(verdi,null,hodefør); //dens forrige blir null sidne den er hode, og dens neste er den som var hode før
                hode=nyNode; //oppdaterer hode
                hodefør.forrige=hode; //må huske å sette at den som var hode før har det nye hode som sin forrige
                antall++; //øker
                endringer++; //øker
            }else if (indeks==antall){ //hvis den skal legges inn sist så blir det den nye halen. den som var hale før blir den ny halens forrige osv
                Node<T> halefør = hale; //tar vare på den som var hale før
                Node<T> nyNode= new Node<T>(verdi,halefør,null); // den som var hale før blir den ny verdien sin forrige, og verdien sin neste peker på null.
                hale=nyNode; //oppdaterer hale
                halefør.neste = nyNode; //den nye noden blir den gamle halen sin neste.
                antall++; //øker
                endringer++; //øker
            }else{ // om det ikke er noen av de så skal den legges mellom to noder.
                Node<T> p = finnNode(indeks);//den som allerde står på indeksen som den skal inn i blir nynodens neste. vi tar vare på denne
                Node<T> r = p.forrige;//den som står foran p blir nynodens forrige.
                Node<T> nyNode= new Node<T>(verdi,r,p);
                p.forrige=nyNode; //oppdaterer forrige og neste peker til p og r
                r.neste=nyNode;
                antall++;
                endringer++; //øker
            }



        }

    }

    @Override
    public boolean inneholder(T verdi) {
        if(indeksTil(verdi)!= -1){ //sjekker om indeksen til nodeverdien finnes, hvis den ikke er lik -1 så finnes den, så vi returnerer true
            return true;
        }
        return false; //hvis den returnerer -1 så vil den returnere false


    }

    private Node<T> finnNode(int indeks) {
        Node<T> node;

        if (indeks <= antall / 2) {     //begynner fra venstre ved hode
            node = hode;                //begynner fra hode
            for (int i = 0; i < indeks; i++) {      //Stopper når vi har kommet til indek
                node = node.neste;                  //når løkka stopper så er vi på riktig indeks. 
            }
        }
        else {                      //begynner fra høyre ved hale
            node = hale;            //definerer hale, begynner bakerst
            for (int i = antall - 1; i > indeks; i--) {     //holder på frem til vi har kommet til indeksen.
                node =  node.forrige;                       //når vi er ferdige med forløkka, peker noden på riktig indeks/node.
            }
        }
        return node;                //vi retunerer noden som er på riktig indeks
    }
    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks,false);           //sjekker indeksen
        // Node <T> node = finnNode(indeks);               //finner noden
        return finnNode(indeks).verdi;                     // returnerer verdien til noden vi fant
    }

    @Override
    public int indeksTil(T verdi) {
        if(verdi==null){ //sjekker om verdi er null, om den er så returnerer vi -1
            return -1;
        }

        for(int i= 0; i<antall;i++){ //starter fra 0 går gjennom alle
            if (hent(i).equals(verdi)){ // når verien til node på plass i er det samme som verdien vi fikk inn som parameter så retunerer vi i
                return i; // så denne vil returnere indeksen med en gang vi finner en verdi som er lik altså den første hvis det er flere av samme verdi

            }


            }return -1; //hvis vi ikke har funnet noen som er like så finnes ikke verdien så vi retunerer -1


    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        if (nyverdi==null){
            Objects.requireNonNull(nyverdi,"feil");         //hvis verdien er null, exepction

        }
        indeksKontroll(indeks,false);           //sjekker indeksen
        Node <T> peker = finnNode(indeks);           //henter node som skal erstattes
        T gammelverdi = peker.verdi;                   //setter noen som var der før til gammelverdi
        peker.verdi=nyverdi;                         //erstatter med nyverdi
        endringer++;
        return gammelverdi;                         // returnerer gammelverdi
    }

    @Override
    public boolean fjern(T verdi) {
        if(verdi==null){            //Hvis verdien er null, finnes den ikke i lista
            return false;           // Da returnerer vi null
        }
        Node <T> node=hode;         // definerer ny node lik hode
        while(node!=null){          // kjører igjennom while løkka frem til node er null, da hopper den ut fordi verdien ikke finnes og node = null;
            if (node.verdi.equals(verdi)) {     //hvis verdien finnes i noden
                break;                          // så har vi funnet noden vi skal slette og vi kan hoppe ut
            }
            node = node.neste;
        }

        if(node==null){                 //hvis noe er null så finnes den ikke i lista
            return false;               //fordi da har while løkka gått så mange runder at node har blitt null, uten å ha funnet noe som er like verdien
        }

        else if (antall==1){            //hvis antallet er 1, så er det kun et element igjen og vi sletter det.
            hode=null;                  // både hode og hale settes til null, slik at de ikke peker på noe
            hale=null;                  // og listen vår er da tom

        }
        else if(node==hode){            //hvis vi skal slette første element (hode)
            node=hode.neste;
            hode=node;
            node.forrige=null;

        }
        else if(node == hale){          //hvis vi skal slette siste element (hale)
            node = hale.forrige;
            hale=node;                  // den nye halen er det som var halen sin forrige før
            hale.neste=null;            // den skal peke på null og ikke gamle halen
        }
        else{                                   //hvis vi skal slette en node midt i listen
            node.forrige.neste=node.neste;      //legger pekerne riktig
            node.neste.forrige=node.forrige;
        }

        endringer++;
        antall--;
        return true;                //vi har klart å slette elementet
    }

    @Override
    public T fjern(int indeks) {

        if(hode==null){                 // hvis listen er tom
            throw new IndexOutOfBoundsException("Listen er tom!");
        }
        if(antall<=indeks || indeks<0){         //hvis indeksen ikke finnes
            throw new IndexOutOfBoundsException("Indeksen finnes ikke!");
        }
        Node <T> node;
        Node <T> gammelnode;        //for å huske og kunne retunere noden vi slettet
        if (antall==1){             //hvis det bare er et element igjen i lista
            gammelnode=hode;        //Da er det bare hodet igjen, lagrer det slik at vi kan retunere
            hode=null;              //sletter pekerne, slik at listen blir tom
            hale=null;

        }
        else if(indeks == 0){       //Hvis vi skal slette første node (hode)
            gammelnode=hode;
            node=hode.neste;
            hode=node;
            node.forrige=null;

        }
        else if(indeks == antall-1){        //hvis vi skal slette siste node (hale)
            gammelnode=hale;
            node = hale.forrige;
            hale=node;
            hale.neste=null;
        }
        else{                               //hvis vi skal slette en node midt i.
            node = finnNode(indeks);        // finner noden med finnNode
            gammelnode=node;
            node.forrige.neste=node.neste;
            node.neste.forrige=node.forrige;
        }
        endringer++;
        antall--;
        return gammelnode.verdi;            //returnerer noden vi slettet.
    }

    @Override
    public void nullstill() {
        //Metode 1          Brukte 6 ms og tregest
        /*                  //Kopierte ut kode 1 for å teste den andre koden, og dette var tregest så lot den bli sånn
        Node<T> node = hode;                    //finner hode, altså starten
        Node<T> neste;                          //og får definert en variabel neste
        for (int i = 0; i < antall; i++) {
            neste = node.neste;                 //lagrer neste så vi vet hvor vi skal hen neste
            node.neste = null;                  // Setter neste pekeren til null
            node = neste;                       // flytter oss videre i lista
            endringer++;
        }
        hode = null;                            //sørger for at hode blit null
        hale = null;                            // sørger for at halen blir null
        antall = 0;                             //listen er nå tom og antall må være 0

         */

        //Metode 2          Brukte 0 ms og raskest
        long tid = System.currentTimeMillis();          //makrerer tiden - tatt fra test filen hvor dere måler kode. (oppgave 3a)
        for (int i = 0; i < antall; i++) {              //forløkke så vi får gått igjennom hele listen
            fjern(0);                           //sletter den første noen hele
            endringer++;
        }
        hode=null;                                      //hode og hale settes til null
        hale=null;
        antall=0;
        tid = System.currentTimeMillis() - tid;         //slutter tiden
        System.out.println("tid: "+tid);                //skriver ut tiden for å se hva det ble

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


