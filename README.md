# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Serina Erzengin, S364561, s364561@oslomet.no
* ...

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Per har hatt hovedansvar for oppgave 1, 3, og 5. 
* Else har hatt hovedansvar for oppgave 2, 4, og 6. 
* Fatima har hatt hovedansvar for oppgave 7 og 8. 
* Vi har i fellesskap løst oppgave 10. 

# Oppgavebeskrivelse

I oppgave 1 så fant vi antallet ved å sette første noden vi har til å være hode, og så lenge noden vi er på (for hver runde så tar vi neste node) ikke er null så øker vi antallet med 1. (men da vi oppdaterer antall underveis så er det lettere å returnere antall da det allerde er oppdatert)
fant tom ved å sjekke om hode er null, for om denne er null så er helte listen tom.
når vi skulle lage den dobbeltlinket listen så er det først en test for om liste a er null. videre er det for løkke som sjekker hvert element, om den ikke er null så sjekker vi om hode er null. hvis hode er null så opretter vi ny node ( eller om det kun en verdi), denne vil da være både hode og hale. Hvis det allerde er et hode i listen så legger vi inn videre. Tar vare på den som var hale før vi legger inn ny node. Opretter ny node hvor den verdien som var hale før vil være den nye noden sin neste, og den nye noden sin neste vil være null. Oppdaterer hale til å være noden vi la inn, og  må også huske å sette at den som var hale før  den nye noden kom inn, vil ha den nye noden som sin neste.  

I oppgave 2 så brukte vi koden fra kompiendiet 3.2.2 oppgave 3. Append er en innebygd metode i StringBuilder som legger til mer i strengen. Vi sjekker først om listen er tom, om den ikke er det så legger vi først inn hode noden, deretter lagde vi en forløkke, og så lenge noden ikke er null så legger vi inn nodenes verdi. For hver runde går vi til neste. legger inn komma og mellomrom mellom hver node. Gjør det samme med omventstring bare at vi endrer for-løkken til å gå bakover isteden.
I legg inn metoden sjekker vi først om verdi er null, om den er null kaster vi en NullPointerExeption. Sjekker også om tabellen er tom, om den er tom så setter vi første verdi til å være både hode og hale (kun en verdi). Hvis den allerde har hode legger vi inn videre. tar vare på den gamle halen.... samme forklaring som i oppgave 1.

I oppgave 3 lagde vi først en metode FinnNode som skulle finne og returnere en Node på en bestemt indeks. Indeksen bestemte om vi skulle starte på hode eller hale, og brukte en for-løkke som hoppet fra node til node i lista, og som stoppet når man nådde indeksen, som vi returnerer.
Så kodet vi metoden hent ved å bruke finnNode, hvor vi først sjekker om indeksen er gyldig og ikke outofbounds, for også la finnNode finne og returnere riktig node, som vi returnerer videre. 
Så lagde vi oppdater, hvor vi sjekket at nyverdi ikke var null, for å så sjekke at indeksen var gyldig. Så mådde vi lagre den gamle verdien slik at vi kunne få returnert den etter vi hadde oppdatert noden. Oppdaterer noden med den nye verdien og returnerer gammel.
Den siste metoden var subliste, hvor vi sjekket at intervallet vi skulle lage subliste av var gydlig, opprettet en ny liste, og hentet ut nodene i intervallet og la det i sublista, og returnerte denne.

I oppgave 4 så laget vi indeksTil metoden ved å først sjekke om verdi er null, om den er null så returnerer vi -1. Deretter lagde vi for-løkke som startet fra 0 og går gjennom alle elementer. Når verdien til node på plass i er det samme som verdien fikk inn som parameter, så returnerer vi i. Så den vil returnere ideksen med en gang den finner verdien som er lik, altså den første hvis det er flere av samme verdi. hvis den ikke har funnet noe lik verdi når den har gått gjennom hele løkken så  finnes ikke verdien og vi returnerer -1.
I inneholder metoden så sjekker vi først om indeksen til nodeverdien finnes, dette gjør vi ved hjelp indeksTil metoden. hvis indeksen til verdien vi får inn. ikke er lik -1 så finnes den, så vi returnerer true. Hvis den returnerer -1 derimot så vil den returnere false, fordi da finnes ikke verdien.

I oppgave 5 så laget vi først sjekk om verdi er null, og om indeks er negativ eller om indeks er større enn antall, om de er det sp kaster vi Exeption. Om det er godkjente verdier sjekker vi først om tabellen er tom, om den er tom så er verdien vi skal sette inn både hode og hale. setter riktige forrige og neste pekere. hvis den ikke er tom så sjekker vi om den vil sette inn verdien på starten (indeks==0)- Da blir verdien det nye hode. Vi oppdaterer hode og hva det som var hode før sin forrigepeker er. Hvis den ikke vil sette inn på starten, sjekker vi om den vil sette inn på slutten, ved å sjekke om indeksen er det samme som antallet. Om den vil det så blir verdien vår nye hale. oppdaterer forrige og neste pekere. hvis den ikke vil sette inn verdien på noen av testene over så skal den legges mellom to noder. Vi starter med å finne den noden som egentlig står på indeksen som den nye verdien skal settes inn på. denne noden vil være den nye nodens neste. Finner deretter den som ligger på deres forrige, dette blir den nye nodens forrige. til slutt oppdaterer vi neste og forrige peker til de nodene som verdien plasseres mellom.
vi øker antall og endringer underveis hele tiden.

I oppgave 6 lagde vi først fjern-metoden som fjernet på indeks. Her startet vi med å sjekke to excpetions, om listen er tom og hvis indeksen ikke finnes. Derreter lagde vi koden som fjernet noden på 4 måter for 4 ulike situajsoner. 
Første situasjon var hvis det bare er et element i lista, og da trengs kun hode og hale lik null. Andre er hvis vi skal slette hodet, så blir hodet satt lik den andre noden i listen. Tredje hvis vi skal slette halen, så blir det nest siste elementet gjort om til hale.
Og den siste måten gjaldt for å slette alle noder som var midt i en liste med flere noder, hvor vi finner noden med finn node, for også endre pekerne til å ikke peke på denne noden. 
I alle måtene lagret vi hva noden vi slettet var for å kunne returnere denne. 
Når vi kodet fjern-metoden som fjernet på verdi brukte vi de samme situasjonene og kodet basert på de, men vi fant noden ved å lete igjennom lista til vi fant riktig verdi. 

I oppgave 7 lagde vi to metoder som vi skulle. Brukte en forløkke i metode 1 for å gå igjennom listen og slette verdi og pekere på hver node. 
I metode 2 brukte jeg også en for-løkke for å gå igjennom hver node, men brukte metoden fjern for å fjerne noden. 
På min Pc var metode 2 klart mye raskere enn metode 1 da jeg målte tiden. 

I oppgave 8 fulgte vi stegene til oppgavebeskrivelsen for det meste. I metoden next sjekka vi først etter to exceptions for om endringene ikke var ritkig og om listen var tom. Derreter lagrer vi verdien til nåverende verdi, for flytte oss over til ny verdi også returnere verdien vi lagra.
I metoden iterator opprettet vi en instans/objekt av iteratorklassen hvor vi brukte konsturktøren uten paramteter, og i metoden iterator med indeks, sjekket vi at indkes var gyldig også opprettet et objekt av iterator klassen for vi brukte konstruktøren med indeks parameter.
Kontstruktørene i iterator lagde vi ganske like, eneste forskjellen er at i den uten indeks begynner vi på hode, og i den med finner vi noden med finn indkes og da er det den noden vi begynner på. 