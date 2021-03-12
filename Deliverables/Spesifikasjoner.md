# Spesifikasjoner

Spesifikasjonen forteller oss om funksjonalitet spillet skal ha, og gir oss også informasjon om sentrale designvalg for eksempel 
hvordan vi regner ut posisjon (om 0,0 er oppe til venstre eller nede til venstre i griddet)

Struktur:\
Funksjon\
Arbeidsoppgaver: Kort beskrivelse

* Brukerhistorier - Skrives som en eksempelperson vil utføre et lite mål i spillet\
@ Akseptansekriterier - Skrives som å beskrive en test

For å kunne utføre alle funksjonene under er det gitt at programmet må kunne kjøre.

***

### 1. Vise et spillebrett
For å vise spillbrettet må man først starte et spill fra menyen ved å trykke på 1,1.

* Som en spiller ønsker jeg å se spillebrettet for å orientere meg.\
@ Når programmer starter vises et vindu med grafikk til spillebrettet.

***

### 2. Vise brikke på spillebrett
For å vise en brikke må man først starte et spill fra menyen ved å trykke på 1,1.

* Som en spiller ønsker jeg å se egen unik spiller-brikke på brettet for å vite hvor jeg er på spillebrettet.\
@ Alle skal kunne se brikken til spilleren på spillebrettet.

***

### 3. Flytte brikke (vha taster e.l. for testing)
Man må så trykke på en en piltast for å bevege seg.

* Som en utvikler ønsker jeg å kunne bevege spillebrikken i spillet med piltaster for å teste brett funksjoner lettere.\
@ Når en piltast trykkes beveges spillebrikken i en retning\
@ Hvis spilleren trykker høyre, vil robotens x-verdi øke med 1
@ Hvis spilleren trykker venstre, vil robotens x-verdi synke med 1
@ Hvis spilleren trykker opp, vil robotens y-verdi øke med 1
@ Hvis spilleren trykker ned, vil robotens y-verdi synke med 1

***

### 4. Robot besøker flagg 
Man må først starte et spill fra menyen. Robot kan så bevege seg ved hjelp av piltastene frem til flagget.

* Som robot vil jeg besøke flagg for å få poeng.\
@ Gitt at en robot passere et flaggs koordinater, skal roboten kåres som vinner.
@ Fremtidig kriterie: Gitt at robot lever og passerer et flagg(i riktig rekkefølge), skal en flagg-counter økes. (skal robot eller spill holde styr på counter?)

***

### 5. Robot vinner ved å besøke flagg
Etter at man starter et spill og beveger seg med piltastene til flagget, vil man vinne.

* Som robot vil jeg vinne ved å besøke alle flaggene i riktig rekkefølge for at spillet skal ha et mål.
* Som robot ønsker jeg å ha en oversikt over hvilke flagg jeg har besøkt i riktig rekkefølge for å kunne vinne.\
@ Gitt at en robot har passert flaggene i riktig rekkefølge (n … n-1) skal roboten vinne når den når flagg n\
@ Roboten har en liste over flagg den har besøkt i riktig rekkefølge

***

### 6. Spille fra flere maskiner (vise brikker for alle spillere, flytte brikker for alle spillere)
Serveren må sende over kartet til klienten(e), sånn at de har samme kart. Klienten må sende kort-valgene sine sånn at server-kartet oppdateres.

* Som spillere ønsker vi å kunne spille flere samtidig, for å kunne spille mot hverandre.\
@ Flere spillere ser samme brett på hver sin maskin\
@ Trekkene til enhver spiller vises på alle maskinene

***

### 7. Dele ut kort 
For å kunne dele ut kort må spillet først ha en kortstokk og minst en spiller som kan motta kort. Dette skjer automatisk som rundene går.

* Som et spill vil jeg at spillerne skal få utdelt tilfeldige kort for å bevege robotene sine.
* Som et spill vil jeg at kortstokken tømmes etter hvert som spillerne får kort for at spillerne ikke får alle de samme kortene\
@ Spillet har en kortstokk representert av en shufflet stack
@ Bruke Pop() for å tømme kortstokken mens man deler ut.

***

### 8. Velge 5 kort.
For å kunne velge kort må man først få utdelt kort. Man må ha en måte gi inputs til å velge riktige kort, og helst (men ikke nødvendigvis) kunne se hvilke kort man velger.

* Som et spill vil jeg ikke at spillerne skal ha mer enn 5 kort slik at en spiller ikke kan ha alle kortene samtidig.
@ Spillerne kan ikke holde mer enn 5 kort fra kortstokken representert av en ArrayList

***

### 9. Bevege robot ut fra valgte kort
For å kunne bevege en robot må man først kunne velge kort. Kortene må ha tilgang til funksjoner som flytter roboten på brettet.

* Som en spiller ønsker jeg å gi instrukser til roboten med kortene mine, for at den skal bevege seg.
@ Roboten flyttes etter kortets instrukser

***

### 10. Kommandolinje verktøy
Kommandlinjen vil ta imot input når programmet starter. Brukeren kan følge instruks på skjermen for å bytte settings.

* Som utvikler ønsker jeg et kommando-linje-verktøy som kan navigere ulike menyer for å sette opp og starte spill, for å ha mulighet til å organisere og starte spill.
@ Har et kommandolinjeverktøyet som kan velge kart, legge til spillere og eksterne (online) spillere, og starte spill.

***

### 11. Kommandolinje funksjonalitet
Spilleren vil kunne se en liste av utdelte kort, og få mulighet til å velge ved hjelp av nummer.

* Som utvikler ønsker jeg en kommandolinjefunksjonalitet som gjør at jeg kan spille roborally (få kort, velge kort osv.) frem til en bedre GUI er på plass.
@ Mens GUI viser brett kan man i kommandolinje kjøre runder, få kort, velge kort og kjøre sekvens, og 	se det skje i GUI.

***

### 12. Kjøre program i et testmodus

* Som utvikler ønsker jeg en mulighet til å kjøre programmet i testmodus (“test” som parameter i main feks) der jeg kan hoppe rett inn i spill, for at jeg ikke skal trenge å gå gjennom menyer hver gang jeg kjører spillet.
@ Ved å gi “test” som parameter i java Main “test” starter programmet en gui med demobrett med en gang.
@ I demoen kan brikker styres ved piltaster.


### 12.1 Rotere brikken (testmodus-extension)


* Som spiller vil jeg skifte orientation til roboten min ved hjelp av en knapp.
@ Når jeg trykker på knappen vil robotens retningvariabel og sprite bli oppdatert med ny retning.

***

https://design.entur.org/kom-i-gang/for-designere/brukerhistorier
https://www.reqview.com/papers/ReqView-Example_Software_Requirements_Specification_SRS_Document.pdf
