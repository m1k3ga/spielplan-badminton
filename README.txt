## ToDo ##
 
= Generell =
  - Unit Test für CalculateGame fehlt!
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
 