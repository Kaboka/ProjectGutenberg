# ProjectGutenberg
##### Philip West Christiansen, Kasper Karstensen & Emilie Hinsch Nielsen
##### Forår 2018, Til: Rolf-Helge Pfeiffer & Jens Egholm Pedersen, Afleveret: 28/05/2018

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
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/datamodel_post.png)

### PostgreSQL - Queries

##### 1. Given a city name your application returns all book titles with corresponding authors that mention this city.

SELECT book_title, author_name FROM "schemaGutenberg".book AS book 
INNER JOIN "schemaGutenberg"."book-author" AS book_author ON (book.id = book_author.book_id) INNER JOIN "schemaGutenberg".author AS author ON (book_author.author_id = author.id) INNER JOIN "schemaGutenberg"."book-city" AS book_city ON (book.id = book_city.book_id) INNER JOIN  "schemaGutenberg".city AS city ON (book_city.city_id = city.id) WHERE city.city_name = 'London';

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/P_1.png)
I alt 45.054 resultater.

##### 2. Given a book title, your application plots all cities mentioned in this book onto a map.

SELECT book_title, author_name FROM "schemaGutenberg".book AS book 
INNER JOIN "schemaGutenberg"."book-author" AS book_author ON (book.id = book_author.book_id) INNER JOIN "schemaGutenberg".author AS author ON (book_author.author_id = author.id) INNER JOIN "schemaGutenberg"."book-city" AS book_city ON (book.id = book_city.book_id) INNER JOIN  "schemaGutenberg".city AS city ON (book_city.city_id = city.id) WHERE city.city_name = 'London';

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/P_2.png)
I alt 29 resultater.

##### 3. Given an author name your application lists all books written by that author and plots all cities mentioned in any of the books onto a map.

SELECT book_title, author_name, city_name, city.longitude, city.latitude FROM "schemaGutenberg".book AS book INNER JOIN "schemaGutenberg"."book-author" AS book_author ON (book.id = book_author.book_id) INNER JOIN "schemaGutenberg".author AS author ON (book_author.author_id = author.id)	INNER JOIN "schemaGutenberg"."book-city" AS book_city ON (book.id = book_city.book_id) INNER JOIN  "schemaGutenberg".city AS city ON (book_city.city_id = city.id) WHERE author.author_name = 'United States. Central Intelligence Agency';

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/P_3.png)
I alt 17.613 resultater. 

##### 4. Given a geolocation, your application lists all books mentioning a city in vicinity of the given geolocation.

SELECT book_title, city_name FROM "schemaGutenberg".book AS book INNER JOIN "schemaGutenberg"."book-city" AS book_city ON (book.id = book_city.book_id) INNER JOIN  "schemaGutenberg".city AS city ON (book_city.city_id = city.id) WHERE Haversine('12.56553', '55.67594 ', city.longitude, city.latitude) < 10;

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/P_4.png)
I alt 1847 resultater.

### Neo4j - Queries

##### 1. Given a city name your application returns all book titles with corresponding authors that mention this city.

Match (c:CITY)<-[:MENTION]-(b:BOOK)<-[:WRITTEN]-(a:AUTHOR) 
where c.city_name = 'London' 

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/N_1.png)
I alt 45.054 resultater.

##### 2. Given a book title, your application plots all cities mentioned in this book onto a map.

Match (c:CITY)<-[:MENTION]-(b:BOOK) 
where b.book_name = 'An Attic Philosopher in Paris — Volume 2' 
return c, b;

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/N_2.png)
I alt 29 resultater.

##### 3. Given an author name your application lists all books written by that author and plots all cities mentioned in any of the books onto a map.

Match (c:CITY)<-[:MENTION]-(b:BOOK)<-[:WRITTEN]-(a:AUTHOR) 
where a.author_name = 'United States. Central Intelligence Agency' 
return b, c, a;

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/N_3.png)
I alt 17.613 resultater. 

##### 4. Given a geolocation, your application lists all books mentioning a city in vicinity of the given geolocation.

MATCH (c:CITY)<-[:MENTION]-(b:BOOK) 
WITH b, c, distance(point({ longitude: c.longitude, latitude: c.latitude }) , point({ longitude: 12.56553, latitude: 55.67594 })) as dist 
WHERE dist<=10000 
RETURN b, c;

##### Output:
![alt text](https://github.com/Kaboka/ProjectGutenberg/blob/master/Images/N_4.png)
I alt 1847 resultater.
