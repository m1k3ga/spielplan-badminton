## ToDo ##
 
= Generell =
  - Punktevergabe für Spieler mit weniger Spielen (10P pro Spiel weniger wie der Meistspielende)
  - Merken, wer mit wem gespielt hat in der TeamPairingsMatrix
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
20150509 TeamPairingMatrix as HashMap (concatenated ids as key)
         Team administration in CalculateGame switched to HashMap with key from ArrayList
20150501 5 players test
20150411 Tournament day added
         First Spock tests included
         Removed enum for players
20150315 first test class
20150321 first push to github
20150321 Move to JDK-8
 