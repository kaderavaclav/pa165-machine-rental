# Machine rental REST API

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

The project was built and tested with these versions of frameworks and SDKs. If you do have an older version you may run into unexpected troubles, if so, please let us know.
```
Maven: apache-maven-3.3.9
Java: 1.8.0_74
```

### Installing

Clone the `master` branch of this project to your directory. To get ready, you need to build whole project. Run command prompt in project directory root and call

```
mvn clean install
```
After build success, change directory to `rest` root and run the REST API application:
```
> cd rest
> ...\rest\mvn tomcat7:run
```

## Usage & commands
You can access application on address `http://localhost:8080/pa165/rest/`. All its features are available through commands below.

### Testing
For easy testing purpose in GUI we reccomend you to use *Advanced REST client* for Chrome browser. <https://advancedrestclient.com/>

Example of `POST` request
```
POST http://localhost:8080/pa165/rest/machines/create

conten-type: application/json

{ 
  "name": "new Machine name",
  "description": "Machine description"
}
```

### Machine entity commands

**Find all machines**
```
GET ~/machines
```

**Find machine by ID**
```
GET ~/machines/{id}
```

**Create new machine**
```
POST ~/machines/create
```

**Update existing machine**
```
PUT ~/machines/update
```

**Remove existing machine** - (Application won't let you delete machine that has relations.)
```
DELETE ~/machines/{id}
```



