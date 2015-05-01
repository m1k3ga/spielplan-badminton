#  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
# Vorhandene Teams auflisten
SELECT 
	team.id_team,
	s1.name, 
	s2.name
FROM 
	team, spieler s1, spieler s2
WHERE 
	team.id_spieler_1 = s1.id_spieler
	AND team.id_spieler_2 = s2.id_spieler;


#  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
# Team Statistiken
SELECT id_team_1 AS id_team, COUNT(id_team_1) AS t1_won, 0 AS t2_lost, s1.name, s2.name
FROM spiel, team, spieler AS s1, spieler AS s2
where
	id_team_1 = team.id_team AND
	team.id_spieler_1 = s1.id_spieler AND
	team.id_spieler_2 = s2.id_spieler
GROUP BY id_team

#  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
# Alle Spielpaarungen mit Ergebnissen von einem Spieltag (16)
SELECT
	id_spieltag, 
	s1.name, s2.name,
	s3.name, s4.name,
	team_1_punkte, team_2_punkte
FROM 
	spiel, team AS t1, team AS t2, spieler s1, spieler s2, spieler s3, spieler s4
WHERE
	spiel.id_spieltag = 16 AND
	spiel.id_team_1 = t1.id_team    AND spiel.id_team_2 = t2.id_team
	AND t1.id_spieler_1 = s1.id_spieler	AND t1.id_spieler_2 = s2.id_spieler
	AND t2.id_spieler_1 = s3.id_spieler	AND t2.id_spieler_2 = s4.id_spieler


#  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
# Spielstatistiken der einzelnen Spieler für eine Saison
SELECT 
	s1_name, s2_name, SUM(x.t1_won) + SUM(x.t2_lost) AS games, SUM(x.t1_won) AS won, SUM(x.t2_lost) as lost
FROM (

SELECT 
	id_team_1 AS id_team, COUNT(id_team_1) AS t1_won, 0 AS t2_lost, s1.name AS s1_name, s2.name AS s2_name
FROM 
	spiel, team, spieler AS s1, spieler AS s2
WHERE
	id_team_1 = team.id_team AND
	team.id_spieler_1 = s1.id_spieler AND
	team.id_spieler_2 = s2.id_spieler
GROUP BY id_team

UNION ALL

SELECT 
	id_team_2 AS id_team, 0 AS t1_won, COUNT(id_team_2) AS t2_lost, s1.name AS s1_name, s2.name AS s2_name
FROM 
	spiel, team, spieler AS s1, spieler AS s2
WHERE
	id_team_2 = team.id_team AND
	team.id_spieler_1 = s1.id_spieler AND
	team.id_spieler_2 = s2.id_spieler
GROUP BY id_team_2

) x
GROUP BY x.id_team
ORDER BY games DESC, x.id_team

#  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
