### **Oblig 3 (26/03/21)**

# **Del 1: Team og prosjekt**

### **Hvordan fungerer rollene i teamet? Trenger dere å oppdatere hvem som er teamlead eller kundekontakt?**

Roller fungerer bra, noen roller viste seg å være mindre fast satt som f.eks møtestyrer, referat skriver. Rollene dekker et vidt spekter av arbeidsområder

Spesifikasjons mester: Hovedansvarlig for å skrive bruker historier, kravspesifikasjoner og arbeidsoppgaver, sier ifra dersom spesifikasjoner mangles til implementasjoner. (Terje)

Kartdesigner: Lager kartene som brukes i spillet og kart for testing av spill elementer (Emil)

Møtereferat og møteledelse: Skriver møtereferat for å holde styr på hva som ble gjort på gruppetimer, styrer diskusjonen når noe må diskuteres i gruppen og passer på at vi går videre dersom en diskusjon tar for lang tid. (Ansvar byttes på)

Prosess ansvarlig: En rolle som innebærer alle, sørger for at regler for gruppe adferd og kodeskikk blir fulgt.

Ansvarlig for kodekvalitet: Innebærer alle foreløpig, men etter hvert som koden har vokst bør rollen være for 2-3 personer i gruppen som har en god og forståelig oversikt over hele systemet og kan hjelpe dersom noen sitter seg fast i en implementasjon. Passer på at kommentarer er beskrivende og gjør koden mer forståelig.

Refactoring entusiast: Den som ser mest over kodekvalitet og refaktorerer koden ved behov. (Daniel)

Spillekspert: Har god kontroll over regler i RoboRally og sørger for at implementasjonen følger disse reglene. (Markus og Andreas)

### **Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er verdt å nevne? Synes teamet at de valgene dere har tatt er gode? Hvis ikke, hva kan dere gjøre annerledes for å forbedre måten teamet fungerer på?**

Scrum og kanban fungerer fortsatt bra, men at på server-siden kunne vi ha delt oppgavene opp litt mer i kort på prosjektbrettet istedenfor å ha en så stor generell oppgave.

Par-proggramering virker fortsatt til å være en super metode for å jobbe sammen.

Vi synes fordelingen av oppgaver har blitt mye bedre siden forrige gang, folk har alltids noe som kan gjøres.

Hatt mye fokus på GUI og server, noe som gjør automatiske tester vanskelig.

### **Gjør et kort retrospektiv hvor dere vurderer hva dere har klart til nå, og hva som kan forbedres. Dette skal handle om prosjektstruktur, ikke kode. Dere kan selvsagt diskutere kode, men dette handler ikke om feilretting, men om hvordan man jobber og kommuniserer.**

Synes vi har blitt mye bedre på å få alle med, føles som alle eier prosjektet.

GUI-delen av prosjektet har vært veldig flinke til å bruke project board, server ikke like flink. Server utviklerene har ikke tatt i bruk project board, men kan forklares med at server oppgaver til tider krever 2 personer uansett.

Føler at alle bidrar mer i møtene og at informasjonen som diskuteres er relevant til prosjektet.

GUI utviklerene føler at samarbeid har vært bra og gjort mye under utviklingen.

Vi er flinke til å si ifra til hverandre om hvordan ting går i prosjektet, og om det er problemer sendes meldinger.

Teamene er flinke til å oppdatere hvordan vi ligger an (Server og GUI)

### **Bli enige om maks tre forbedringspunkter fra retrospektivet, som skal følges opp under neste sprint.**

1. Server-utviklere kan bli bedre med å bruke project board
2. Kan bli flinkere å dele oppgaver i mindre deler slik at en oppgave på project board ikke tar 2 uker.

### **Forklar kort hvordan dere har prioritert oppgavene fremover. Legg ved skjermdump av project board ved innlevering**

Vi har valgt å prioritere GUI og Server delen av prosjektet og fortsette å utvikle disse delene.

### **Hvordan fungerer gruppedynamikken og kommunikasjonen?**

Det er nyttig at vi har delt oss i 2 mindre team for å fokusere på 2 store prosjektet (GUI &amp; Server), dette gjør at arbeid skjer raskere og at vi har en bedre oversikt over prosjektet sin helhet, enn hvis alle skulle hatt 100% kontroll over alt i prosjektet.

Synes gjennomgang av hvordan GUI og Server fungerte bra og hjalp begge lagene kjapt å skjønne/sette seg inn i hva begge lagene har gjort.

# **Del 2: krav**

### **Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang. Er dere kommet forbi MVP? Forklar hvordan dere prioriterer ny funksjonalitet.**

- Valgt å prioritere flerspiller kravet fra MvP, noe som tar litt lengre tid enn forventet. Informasjon kan sendes mellom klient og server, men må fortsatt implementeres i spillet.
- Spillet kan nesten spilles i runder, kort kan bli valgt og utføres i sekvens, men kan bare gjøres 1 gang foreløpig (ikke bug, har bare ikke kommet så langt enda).

### **Husk å skrive hvilke bugs som finnes i de kravene dere har utført (dersom det finnes bugs).**

Når kart skal velges, kan en velge flere spillere enn det kartet tillater. Dette fører til en out of bounds exception som kræsjer spillet.

### **Brukerhistorier og kravspesifikasjoner**

Finnes i Deliverables/Spesifikasjoner_SiD_RoboRally.pdf

# **Del 3: Produktleveranse og kodekvalitet**

### **Dere må dokumentere hvordan prosjektet bygger, testes og kjøres, slik at det er lett for gruppelderne å bygge, teste og kjøre koden deres. Under vurdering kommer koden også til å brukertestes.**

Står i README

### **Klassediagram**

Finnes i Deliverables/Class Diagram

### **Tester**

JUnit tester dekker logikken i systemet og finnes under src/test

Manuelle tester finnes i README.

### **Forskjeller i commits**

Blitt en forbedring i commits forskjeller, men de som jobber med server får ikke sine commits med siden det er fortsatt i en egen test gren. Code-with-me gjør også at noen mister commits for arbeidet de har gjort siden kun en person får commit fra code-with-me økter.

Gruppen har blitt flinkere til å gjøre flere commits på mindre deler av koden.
