## ToDo ##
 
= Generell =
  - TeamPairingsMatrix aufbauen. Aktuell (size, size) als Matrix. Geht das nicht besser?
    In der matrix soll einfaches navigieren der spieler möglich sein,
    nicht nur über indizes oder spieler ids
  - Punktevergabe für ausgesetzte Spiele
  - Merken, wer mit wem gespielt hat in der TeamPairingsMatrix
  - log4j durch logback ersetzen

  - Given-When-Then Tests mit parametrisierten Unit-Tests bzw. Spock :
    Given "Four arbitrary games in a row"
    When "update metrics???"
    Then "each player has played at least two games"
 
 
 = Metrics =
   - daysPlayedSeason noch nicht berechnet
 
 
 
 #  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
 === LOG ===
 20150501 5 players test
 20150411 Tournament day added
          First Spock tests included
          Removed enum for players
 20150315 first test class
 20150321 first push to github
 20150321 Move to JDK-8
 