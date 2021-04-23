Oblig 4 SiD

Del 1	Team og projsket

•	Hvordan fungerer rollene i teamet? Trenger dere å oppdatere hvem som er teamlead eller kundekontakt?

Roller fungerer bra, noen roller viste seg å være mindre fast satt som f.eks møtestyrer, referat skriver. Rollene dekker et vidt spekter av arbeidsområder
Spesifikasjons mester: Hovedansvarlig for å skrive bruker historier, kravspesifikasjoner og arbeidsoppgaver, sier ifra dersom spesifikasjoner mangles til implementasjoner. (Terje)
Kartdesigner: Lager kartene som brukes i spillet og kart for testing av spill elementer (Emil)
Møtereferat og møteledelse: Skriver møtereferat for å holde styr på hva som ble gjort på gruppetimer, styrer diskusjonen når noe må diskuteres i gruppen og passer på at vi går videre dersom en diskusjon tar for lang tid. (Ansvar byttes på)
Prosess ansvarlig: En rolle som innebærer alle, sørger for at regler for gruppe adferd og kodeskikk blir fulgt.
Ansvarlig for kodekvalitet: Innebærer alle foreløpig, men etter hvert som koden har vokst bør rollen være for 2-3 personer i gruppen som har en god og forståelig oversikt over hele systemet og kan hjelpe dersom noen sitter seg fast i en implementasjon. Passer på at kommentarer er beskrivende og gjør koden mer forståelig.
Refactoring entusiast: Den som ser mest over kodekvalitet og refaktorerer koden ved behov. (Daniel)
Spillekspert: Har god kontroll over regler i RoboRally og sørger for at implementasjonen følger disse reglene. (Markus og Andreas)

•	Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er verdt å nevne? Synes teamet at de valgene dere har tatt er gode? Hvis ikke, hva kan dere gjøre annerledes for å forbedre måten teamet fungerer på?

Scrum og kanban fortsetter å fungere.
Par-programmering gir god feedback og raskere løsninger.
Valgte denne uken å fokusere på å gjøre programmet bedre istedenfor å legge til ny funksjonalitet med unntak av server.
Server har ikke fått implementert full flerspiller, men kan få 2 eller flere spillere til å lage samme brett, det som mangler er å sende over de valgte kortene og utføre logikken, noe som vi ikke hadde tid til å få gjort.

•	Gjør et retrospektiv hvor dere vurderer prosjektet har gått. Hva har dere gjort bra, hva hadde dere gjort annerledes hvis dere begynte på nytt?

Prosjektet har vært lærerikt, gått bra å jobbe sammen. 
En del krasj med andre fag.
Ble ikke ferdig med alt, men lærte fortsatt mye om å jobbe sammen og hvor vanskelig det kan være å fordele oppgaver likt og hvor lett en kan henge etter på tekniske krav.
Samarbeid har vært bra. 

Ting som kunne gjort annerledes

Multiplayer tok over veldig, og vi burde byttet fokus fra multiplayer til at prosjektet skulle funger ordentlig for enkeltspillere. En av grunnene til at vi ikke byttet fokus fra multiplayer var bl.a sunk cost fallacy, og en tankegang om at det å lage server og client i etterkant ville ført til en større teknisk gjeld.
Testing av server og klient var vanskelig, vi kunne ikke komme oss på samme nett og eneste testingen vi fikk gjort var lokalt på 2 forskjellige instanser av spillet.
Burde gjort screens mer abstract, siden screens bruker mye samme metoder. Ville gjort koden mer leselig.
Passe på at alle skjønner hvordan programmet fungerer før ny funksjonalitet implementeres.
Burde hatt en bedre løsning på par-programmering. Førte til ganske ujevn commits siden kun en person fikk commitebe for det arbeidet.
En utpekt teamlead hadde kanskje vært en god ide for å holde prosjektet i en bestemt retning.





Legg ved skjermdump av project board ved innlevering. Sørg for at det er oppdatert med siste status ved innlevering.
 


Hvordan fungerer gruppedynamikken og kommunikasjonen nå i forhold til starten?
Gruppedynamikken har blitt mye bedre. Alle bidrar med innspill og er trygg på å jobbe sammen og ta tilbakemeldinger fra hverandre. Vi gjorde noen forsøk på å arrangere sosiale. 
Mange var stille i starten, men vi har blitt flinkere og tryggere på å snakke med hverandre.
Synes vi har jobbet bra sammen, delingen av teamet inn i server/GUI  var god hjelp.


Del 2: krav
•	Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang. Er dere kommet forbi MVP? Forklar hvordan dere prioriterer ny funksjonalitet.
1.	Valgt å prioritere flerspiller kravet fra MvP, noe som tar litt lengre tid enn forventet. Vi kan sende og motta informasjon på server og klient siden. De tegner opp samme kart, men rundene og valgte kort kan ikke spilles via nett.

2.	Flere spiller sprites kan nå velges før spillet startes.



Husk å skrive hvilke bugs som finnes i de kravene dere har utført (dersom det finnes bugs).
Bugs:

Bilder i GUI av sprites og kart skaleres ikke riktig når vi går i vindu modus og kan blokkere GUI elementer. Fikses ved å justere størrelsen på skjermen etter den er satt til vindu.
Når server ser etter spillere, eller klient venter på server fryses spillet. Dette kan føre til at når en kobler seg til et spill, men ikke har nok spillere, at programmet må avsluttes manuelt siden det forblir i en evig while-løkke.
Kort kan ikke kommites i vindu-modus.

