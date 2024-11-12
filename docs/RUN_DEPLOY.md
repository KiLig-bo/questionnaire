# Project Setup and Deployment

## 1. Prepare the Database
1. Ensure that MySQL or MariaDB database service is installed and running.
2. Create the `questionnaire` database:
   ```mysql
   CREATE DATABASE IF NOT EXISTS `questionnaire` DEFAULT CHARACTER SET utf8mb4;
   ```
3. Import `questionnaire-schema.sql` (table structure definition).
   + Using the `mysqlimport` command:
     ```bash
     mysqlimport.exe -uroot -p questionnaire your_path/questionnaire-schema.sql
     ```
   + Alternatively, from within the MySQL command line:
     ```mysql
     USE questionnaire;
     SOURCE your_absolute_path/questionnaire-schema.sql;
     ```
4. Import `questionnaire-data.sql` (test data).
   + Using the `mysqlimport` command:
     ```bash
     mysqlimport.exe -uroot -p questionnaire your_path/questionnaire-data.sql
     ```
   + Or from within the MySQL command line:
     ```mysql
     USE questionnaire;
     SOURCE your_absolute_path/questionnaire-data.sql;
     ```

## 2. Prepare ElasticSearch
> Required ElasticSearch version: 7.6.2 

1. Install ElasticSearch  
   + **Local installation**:  
     Choose the appropriate package (e.g., zip or msi for Windows, tar.gz for Linux).  
     [ElasticSearch Download](https://repo.huaweicloud.com/elasticsearch/7.6.2/)
   + **Docker installation**:
     ```bash
     docker pull elasticsearch:7.6.2
     ```

2. Install the IK Analyzer Plugin for ElasticSearch  
   In the `bin` directory of ElasticSearch, install the plugin directly by running:
   ```bash
   ./elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.6.2/elasticsearch-analysis-ik-7.6.2.zip
   ```
   Or, download it locally first:
   [IK Download](https://github.com/medcl/elasticsearch-analysis-ik/releases) (choose version 7.6.2). Then install with:
   ```bash
   ./elasticsearch-plugin install file:///{your_path}/elasticsearch-analysis-ik-7.6.2.zip
   ```

3. Start ElasticSearch  
   In the `bin` directory, run:
   ```bash
   ./elasticsearch
   ```
   Verify if ElasticSearch started successfully:
   ```bash
   curl "http://{your_ip}:9200/_cat"
   ```

## 3. Project Configuration
1. Import the project into your IDE (recommended: IntelliJ IDEA).
2. Update the configuration:  
   Adjust settings in `application-<profile>.properties` to configure database connections, ElasticSearch connection, email settings, and other necessary configurations based on your environment.
3. Import data into ElasticSearch:  
   Run the `indexTemplateToES` test method in `src/test/java/com/gyb/questionnaire/ESTemplateSearchTest.java` to load data from the database into ElasticSearch.

## 4. Start the Project
In the project root directory, execute:
```bash
./mvnw spring-boot:run
```
Alternatively, in IntelliJ IDEA, right-click and run `QuestionnaireApplication`.  
In your browser, go to [http://localhost:8080](http://localhost:8080) to access the main page.
