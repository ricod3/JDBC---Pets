# JDBC---Pets

## Aufgabe: Haustiere

Erstelle ein Programm, welches einen Haushalt mit Personen und Haustieren modelliert und in einer Datenbank persistent speichert.
Hierfür sollen beliebig viele Haushalte angelegt werden können. Jeder Haushalt besteht aus 1 bis n Personen und jeder Person sind 0 bis m Haustiere zugeordnet.

### Es sollen die üblichen Management Methoden einer Datenbank nutzbar sein.
Create(Haushalt)
Create(Person, haushalt_id)
Create(Haustier, person_id)
Read(haushalt_id)
Read(person_id)
Read(haustier_id)
Update(haushalt_id, values)
Update(person_id, values)
Update(haustier_id, values)
Delete(haushalt_id)
Delete(person_id)
Delete(haustier_id)

Zusätzlich noch eine Methode, welche sämtliche Haushalte in der Datenbank ausgibt:
List<Household> getAllHouseHolds();

Löse diese Aufgabe mittels einer MySQL Datenbank.

Achte darauf, dass das Object Relation Mapping richtig umgesetzt wird, d.h. die Verbindungen zwischen Haushalt und Person, respektive Person und Haustier sowohl im Java Code als auch in der Datenbank existieren. 

![petsERM](https://github.com/ricod3/JDBC---Pets/assets/113040547/b76d83f8-b03e-40ce-83fe-f81120ad2f6b)
