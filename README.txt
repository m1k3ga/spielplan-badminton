## ToDo ##
 
= Generell =
  - team pick points basierend auf counter matrix (anzahl zusammen gespielter spiele)
    und nicht auf summe der pick points der spieler
  - CalculateGame -> pickTeamA wählt nach pickPoint für einzelne Spieler aus.
    Der zweite Spieler sollte nach den Pickpoints für die Teams gewählt werden
    sonst spielen vielleiht öfter teams zusammen von spielern die nicht oft gespielt haben
    aber vielleicht oft zusammen gespielt haben
    ToDo: Wähle auch nach PickPoints für die Teams (punkte für spieler 1 + 2 )

  - FivePlayerTournamentTest is auf @Ignore gestellt
  - Spock tests gehen nicht mehr! (Werden auf ignore gesettz und ignoriert wenn über test/java->run alltests)

  - DerbyDB für Persistenz
  - Die Player_Id wird aktuell immer neu gesetzt (counter++)
    In DB durch DB_id ersetzen
  - log4j durch logback ersetzen???

  - Given-When-Then Tests mit parametrisierten Unit-Tests bzw. Spock :
    Given "Four arbitrary games in a row"
    When "update metrics???"
    Then "each player has played at least two games"

 
 = Metrics =
   - daysPlayedSeason noch nicht berechnet
 
 
 
#  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
=== LOG ===
20150905 Refactor classes
         Pick team B by pick points (calculated on sum of pick points of the team players)
20150517 Added Unit test for CalculateGame
         pickTeamA now picks the two players of the team based on the pickPoints for each player (highest)
20150515 Added Mockito. Not yet used (implicit class instantiation)
20150510 Introduced pickPoints as basis for picking players and teams
         Assure that each team pairing is saved in the TeamPairingMatrix
20150509 TeamPairingMatrix as HashMap (concatenated ids as key)
         Team administration in CalculateGame switched to HashMap with key from ArrayList
20150501 5 players test
20150411 Tournament day added
         First Spock tests included
         Removed enum for players
20150315 first test class
20150321 first push to github
20150321 Move to JDK-8
 