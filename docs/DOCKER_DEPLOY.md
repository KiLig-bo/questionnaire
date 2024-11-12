# Docker Compose Deployment Guide

> **Note**: All following operations assume you are in the project’s root directory. If not, navigate to it first:
   ```shell
   cd questionnaire
   ```

## Start the Project in One Step
> First, ensure that Docker Engine and Docker Compose are installed and that your system has internet access.

1. Build the project using Maven:
   ```shell
   ./mvnw clean package -Dmaven.test.skip=true
   ```

2. Start the project and its dependencies using Docker Compose:
   ```shell
   docker-compose up --build
   ```

   - If the `mysql:8.0.22` or `elasticsearch:7.6.2` images are not present locally, Docker will pull them, which may take some time.
   - During the startup, the Elasticsearch container will access GitHub to download and install the IK analysis plugin.
   - The MySQL container will execute initial SQL scripts to set up table structures and import initial data.

3. Once completed, run `docker ps` to confirm the following three containers are running:
   - `questionnaire_web`: the main questionnaire application
   - `questionnaire_db`: the MySQL database
   - `questionnaire_es`: Elasticsearch

## Sync Data to Elasticsearch
Wait until Elasticsearch is fully started before executing the following steps.  
With port 9200 exposed, you can run the commands below to interact with Elasticsearch directly from your local machine.

### Create the `template` Index
```shell
curl -XPUT localhost:9200/template?pretty
```

### Set Field Mappings
```shell
curl -XPOST localhost:9200/template/_mapping?pretty -H 'content-type:application/json' -d'
{
    "properties":{
       "id":{"type":"long"},
       "name":{
         "type":"text",
         "analyzer":"ik_max_word",
         "search_analyzer":"ik_smart"
       },
       "createDate":{
          "type":"date",
          "format":"yyyy/MM/dd HH:mm:ss"
       },
       "publishDate":{
          "type":"date",
          "format":"yyyy/MM/dd HH:mm:ss"
       },
       "authorId":{"type":"long"},
       "authorName":{"type":"text"},
       "typeId":{"type":"integer"},
       "questionCount":{"type":"integer"},
       "status":{"type":"integer"},
       "questions":{
          "type":"nested",
          "properties":{
             "id":{"type":"long"},
             "templateId":{"type":"long"},
             "questionTitle":{
                "type":"text",
                "analyzer":"ik_max_word",
                "search_analyzer":"ik_smart"
             },
             "questionOrder":{"type":"integer"},
             "questionNum":{"type":"integer"},
             "inputPlaceholder":{"type":"text"}
          }
       }
    }
}'
```

### Verify Mappings
```shell
curl -XGET localhost:9200/template/_mapping?pretty
```

### Index Data into Elasticsearch
Data located in `data.json` can be indexed with:
```shell
curl -XPOST localhost:9200/template/_bulk?pretty \
-H 'content-type:application/json' \
--data-binary "@./docker/inites/data.json"
```

### Test a Search Query
```shell
curl -XPOST localhost:9200/template/_search?pretty -H 'content-type:application/json' -d'
{
    "query" : { "match" : { "name" : "大学生" }},
    "highlight" : {
        "pre_tags" : ["<tag1>", "<tag2>"],
        "post_tags" : ["</tag1>", "</tag2>"],
        "fields" : {
            "name" : {}
        }
    }
}
'
```

## FAQ

### 1. Why is startup so slow?
- If the `mysql:8.0.22` or `elasticsearch:7.6.2` images are not present, Docker will need to pull them first. Using a local mirror or Docker Hub in your region can help speed this up.
- Additionally, the Elasticsearch container accesses GitHub to download and install the IK analysis plugin.
- The MySQL container also takes some time to initialize tables and import initial data.

### 2. Why did startup fail?
- Ensure your system is connected to the internet since pulling images and downloading plugins requires connectivity.
- Confirm you are running `docker-compose` from the project’s root directory.

### 3. Why did Elasticsearch fail to start?
- Elasticsearch accesses GitHub to download and install the IK analysis plugin. A failure to access GitHub could cause Elasticsearch to fail.  
  If access to GitHub is restricted, download the plugin locally and install it manually after starting Elasticsearch.
