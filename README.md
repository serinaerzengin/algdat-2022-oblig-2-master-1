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

I oppgave 1 så fant vi antallet ved å sette første noden vi har til å være hode, og så lenge noden vi er på (for hver runde så tar vi neste node) ikke er null så øker vi antallet med 1.
fant tom ved å sjekke om hode er null, for om denne er null så er helte listen tom.
når vi skulle lage den dobbeltlinket listen så er det først en test for om liste a er null. videre er det for løkke som sjekker hvert element, om den ikke er null så sjekker vi om hode er null. hvis hode er null så opretter vi ny node ( eller om det kun en verdi), denne vil da være både hode og hale. Hvis det allerde er et hode i listen så legger vi inn videre. Tar vare på den som var hale før vi legger inn ny node. Opretter ny node hvor den verdien som var hale før vil være den nye noden sin neste, og den nye noden sin neste vil være null. Oppdaterer hale til å være noden vi la inn, og  må også huske å sette at den som var hale før  den nye noden kom inn, vil ha den nye noden som sin neste. 

I oppgave 2 så brukte vi koden fra kompiendiet 3.2.2 oppgave 3. Append er en innebygd metode i StringBuilder som legger til mer i strengen. Vi sjekker først om listen er tom, om den ikke er det så legger vi først inn hode noden, deretter lagde vi en forløkke, og så lenge noden ikke er null så legger vi inn nodenes verdi. For hver runde går vi til neste. legger inn komma og mellomrom mellom hver node. Gjør det samme med omventstring bare at vi endrer for-løkken til å gå bakover isteden.
I legg inn metoden sjekker vi først om verdi er null, om den er null kaster vi en NullPointerExeption. Sjekker også om tabellen er tom, om den er tom så setter vi første verdi til å være både hode og hale (kun en verdi). Hvis den allerde har hode legger vi inn videre. tar vare på den gamle halen.... samme forklaring som i oppgave 1.
