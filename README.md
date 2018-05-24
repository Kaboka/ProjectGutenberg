# ProjectGutenberg
##### Philip West Christiansen, Kasper Karstensen & Emilie Hinsch Nielsen
##### Forår 2018, Til: Rolf-Helge Pfeiffer & Jens Egholm Pedersen, Afleveret: 28/05/2018

# Indholdsfortegnelse  
[Introduktion](#introduktion)  
[Projektbeskrivelse](#projektbeskrivelse)  
[Valg af databaser](#valg-af-databaser)

[Relationel database](#relationel-database)  



# Introduktion 
Dette git-repository indeholder kode, rapport samt performance målinger for vores eksamensopgave i faget Database på PBA i Softwareudvikling ved CPHBusiness i foråret 2018. Opgaven er lavet af Emilie Nielsen, Philip West og Kasper Karstensen.

# Projektbeskrivelse
Projektet går ud på at lave en applikation med to typer af databaser der anvender data fra engelske bøger som er hentet fra Project Gutenberg(http://www.gutenberg.org/). Applikationen skal kunne lave forskellige forespørgsler og ved hjælp af performancemålinger skal vi finde ud af hvilken database der egner sig bedst i forskellige situationer. 
Forespørgslerne er som følgende: 

- Ud fra et bynavn skal man kunne finde alle bøger samt bogens forfatter hvor bogen nævner denne by.
- Ud fra en bog titel bliver alle byer den nævner plottet ind på et kort.
- Ud fra et forfatternavn bliver alle bøger listet som er skrevet af denne forfatter og de byer som bøgerne nævner bliver plottet ind på et kort.
- Ud fra en geolokation skal alle de bøger listes der nævner en by der ligger i nærheden af denne geolokation.

# Valg af databaser
I vores undervisning har vi arbejdet med fire forskellige paradigmer inden for databaser: Key-value stores, document-oriented, relational og graph database. Udfra disse paradigmer skal vi vælge 2 databaser som ikke hører under samme paradigme.

## Relationel database
Vi har valgt at arbejde med en relationel database i form af PostgreSQL. 
I gruppen mener vi at relationel database er det vi betegner som en “klassisk” database da det var den første type af database vi alle lærte at arbejde med da vi først begyndte at programmere. 
Vi vil gerne se hvordan denne performer i forhold til en nyere type af database.

## Graph database
Vi har valgt at arbejde med en graph database i form af Neo4J. Dette er den nyeste form for database som vi har arbejdet med. Ingen i gruppe havde hørt om graph databaser før vores undervisning og vil derfor gerne undersøge hvordan den performer i forhold til en “klassisk” database. Vi mener også at vi forholdsvis nemt vil kunne strukturere den data som vores applikation skal bruge i form af noder og edges.

# Datamodellering i databaser

## PostgreSQL
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/datamodel_post.png)

## Neo4j
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/datamodel_neo.png)

Author → Book,
Vi har valgt at forholdet mellem forfatter og bøger skal gå fra forfatter til bøger. Det har vi valgt da der er et krav om at man skal kunne finde de bøger en forfatter har skrevet.

Book → City,
Forholdet mellem bog og by skal gå fra bog til by. Man skal kunne finde ud af hvilken by en bog nævner.

## Datamodellering i applikation

Skrives her

## Importering af data

### Setup-guide

#### PostgreSQL

1. Download TAR fil.
2. Download pgAdmin 4.
3. Opret skema kaldet “schemaGutenberg”.
4. Kør følgende query:

```sql
Create function Haversine(lon1 float, lat1 float, lon2 float, lat2 float) returns float AS $$
BEGIN
	RETURN (2 * 3961 * asin(sqrt((sin(radians((lat2 - lat1) / 2))) ^ 2 + cos(radians(lat1)) * cos(radians(lat2)) * (sin(radians((lon2 - lon1) / 2))) ^ 2)));
	END;
$$ LANGUAGE plpgsql;
```

#### Neo4j

1. Download alle CSV filerne.
2. Download Neo4j Desktop.
3. Opret nyt projekt, anvend kode 1234.
4. Åben for projektet og klik “Import”. Flyt derefter alle CSV filerne til denne mappe. 
5. Åben projektet op i din browser.
6. Kør følgende queries: 

##### Import Authors

```
LOAD CSV WITH HEADERS FROM "file:///author.csv" AS csvLine
CREATE (:AUTHOR { id: toInt(csvLine.id), author_name: (csvLine.name)})
```

##### Import Books

```
LOAD CSV WITH HEADERS FROM "file:///book.csv" AS csvLine
CREATE (:BOOK { id: toInt(csvLine.id), book_name: (csvLine.title)});
```

##### Import Cities
```
LOAD CSV WITH HEADERS FROM "file:///cities.csv" AS csvLine
CREATE (:CITY { id: toInt(csvLine.id), city_name: csvLine.city_name, longitude: toFloat(csvLine.longitude), latitude: toFloat(csvLine.latitude)})
```

##### Indexer ID’erne
```
CREATE INDEX ON :AUTHOR (id)
```
```
CREATE INDEX ON :BOOK (id)
```
```
CREATE INDEX ON :CITY (id)
```

##### Book_Author Written (mellemtabel)
```
LOAD CSV WITH HEADERS FROM "file:///book_author.csv" AS csvLine
MATCH (a:AUTHOR { id: toInt(csvLine.author_id)}),
 (b:BOOK { id: toInt(csvLine.book_id)})
CREATE (a)-[:WRITTEN]->(b)
```

##### Book_City Mention (mellemtabel)
```
USING PERIODIC COMMIT 500
LOAD CSV WITH HEADERS FROM "file:///book_city.csv" AS csvLine
MATCH (a:BOOK { id: toInt(csvLine.book_id)}),
 (b:CITY { id: toInt(csvLine.city_id)})
CREATE (a)-[:MENTION]->(b)
```

## Queries

### PostgreSQL

##### 1. Given a city name your application returns all book titles with corresponding authors that mention this city.

```sql
SELECT book_title, author_name 
FROM "schemaGutenberg".book AS book 
INNER JOIN "schemaGutenberg"."book-author" AS book_author 
ON (book.id = book_author.book_id) 
INNER JOIN "schemaGutenberg".author AS author 
ON (book_author.author_id = author.id) 
INNER JOIN "schemaGutenberg"."book-city" AS book_city 
ON (book.id = book_city.book_id) 
INNER JOIN  "schemaGutenberg".city AS city 
ON (book_city.city_id = city.id) 
WHERE city.city_name = 'London';
```

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/P_1.png)
I alt 45.054 resultater.

##### 2. Given a book title, your application plots all cities mentioned in this book onto a map.

```sql
SELECT city_name, city.longitude, city.latitude 
FROM "schemaGutenberg".city AS city 
INNER JOIN "schemaGutenberg"."book-city" AS book_city 
ON (city.id = book_city.city_id) 
INNER JOIN "schemaGutenberg".book AS book 
ON (book_city.book_id = book.id) 
WHERE book_title = 'An Attic Philosopher in Paris — Volume 2';
```

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/P_2.png)
I alt 29 resultater.

##### 3. Given an author name your application lists all books written by that author and plots all cities mentioned in any of the books onto a map.

```sql
SELECT book_title, author_name, city_name, city.longitude, city.latitude 
FROM "schemaGutenberg".book AS book 
INNER JOIN "schemaGutenberg"."book-author" AS book_author 
ON (book.id = book_author.book_id) 
INNER JOIN "schemaGutenberg".author AS author 
ON (book_author.author_id = author.id)	
INNER JOIN "schemaGutenberg"."book-city" AS book_city 
ON (book.id = book_city.book_id) 
INNER JOIN  "schemaGutenberg".city AS city 
ON (book_city.city_id = city.id) 
WHERE author.author_name = 'United States. Central Intelligence Agency';
```

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/P_3.png)
I alt 17.613 resultater. 

##### 4. Given a geolocation, your application lists all books mentioning a city in vicinity of the given geolocation.

```sql
SELECT book_title, city_name FROM "schemaGutenberg".book AS book 
INNER JOIN "schemaGutenberg"."book-city" AS book_city 
ON (book.id = book_city.book_id) 
INNER JOIN  "schemaGutenberg".city AS city 
ON (book_city.city_id = city.id) 
WHERE Haversine('12.56553', '55.67594 ', city.longitude, city.latitude) < 10;
```

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/P_4.png)
I alt 1.847 resultater.

### Neo4j

##### 1. Given a city name your application returns all book titles with corresponding authors that mention this city.

```
Match (c:CITY)<-[:MENTION]-(b:BOOK)<-[:WRITTEN]-(a:AUTHOR) 
where c.city_name = 'London' 
return b, a;
```

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/N_1.png)
I alt 45.054 resultater.

##### 2. Given a book title, your application plots all cities mentioned in this book onto a map.

```
Match (c:CITY)<-[:MENTION]-(b:BOOK) 
where b.book_name = 'An Attic Philosopher in Paris — Volume 2' 
return c, b;
```

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/N_2.png)
I alt 29 resultater.

##### 3. Given an author name your application lists all books written by that author and plots all cities mentioned in any of the books onto a map.

```
Match (c:CITY)<-[:MENTION]-(b:BOOK)<-[:WRITTEN]-(a:AUTHOR) 
where a.author_name = 'United States. Central Intelligence Agency' 
return b, c, a;
```

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/N_3.png)
I alt 17.613 resultater. 

##### 4. Given a geolocation, your application lists all books mentioning a city in vicinity of the given geolocation.

```
MATCH (c:CITY)<-[:MENTION]-(b:BOOK) 
WITH b, c, distance(point({ longitude: c.longitude, latitude: c.latitude }) , point({ longitude: 12.56553, latitude: 55.67594 })) as dist 
WHERE dist<=10000 
RETURN b, c;
```

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/N_4.png)
I alt 1.847 resultater.
