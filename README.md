# ChallengeMe
ChallengeMe - Application to manage and share challenges


### Modules

| Module | Packages | Description |
| --- | --- | --- |
| cm-app | com.dj.cm.app | Application runner |
| cm-ui | com.dj.cm.ui.*, resources | Front-End stuff |
| cm-rest | com.dj.cm.rest.controller.* | RESTfulAPI controllers |
| cm-biz | com.dj.cm.biz.service.* | Business services api (Declaration of interfaces and types) |
| cm-biz-local | com.dj.cm.biz.service.*.impl | Local implementation of business services |
| cm-persistence | com.dj.cm.persistence.repo.* | Persistence/Data Access classes |
| cm-common | com.dj.cm.common.util.* | Commonly used utils and tools |
| cm-model | com.dj.cm.model.entity.* | Data model (Entities and Other basic types) |

### Deploy for Development

1\. Start RabbitMQ Messaging Server
```
application.properties/event.enabled=true
MacOS $ brew services start rabbitmq
```

2\. Start Application
```
Run com.dj.cm.Application from IDE or command-line
```

3\. Start Webpack Dev Server

3.1 To apply any incoming package.json updates for node_modules, run:
```
cm-ui/src/main/app $ npm install
```

3.2 To start Webpack Dev Server, run:
```
cm-ui/src/main/app $ npm start
```


### Deploy for Production

1\. Build
```
cm $ mvn clean install
```

2\. Start Application
```
cm $ java -Dspring.profiles.active=production -jar cm-app/target/cm-app-1.0.0-SNAPSHOT.jar
```


