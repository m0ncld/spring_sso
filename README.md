# spring_sso

## Description

Simple test project for integration test between Keycloak system, mysql and eureka. The main purpose of this application is a Single Sign-On (**SSO**)
for standalone application and two web application.

# Run project
## Docker setup

### Start system on dockers

The Docker configuration is located in the  __tools/docker_ directory. To run docker with MySQL and Keycloak execute the
command:

```
$ sudo docker compose up
```

when you would like to run docker compose in deamon use command

```
$ sudo docker compose up -d
```

First time the system is initializing and liquibase for keycloak can take a while so be patient!

### Configure Keycloak system on docker

Default Key Cloak admin panel is available on [http://localhost:8081](http://localhost:8081).
Choose **Administration Console** to login to Administarion Panel. Default developer credentials are: user: **admin** with password: **password**.

On the left side is a panel with selected *realm* (**master** is seleted as default).
For adding new realm for develop, click on the **Create realm** button. You can upload a JSON file located in *_tools/config/java-application-realm-export.json*.

After configuration realm, you can add users (for example):

* username: **customUser**, password: **userPass** temporary: **false**, roles: **user**
* username: **customGuest**, password: **guestPass** temporary: **false**, roles: **guest**
* username: **customUserGuest**, password: **userGuestPass** temporary: **false**, roles: **user**, **guest**

After defining user data, you can access the token using request:

```
Method: POST
Url: http://localhost:8081/realms/JavaApplication/protocol/openid-connect/token
Content-Type: application/x-www-form-urlencoded
Body:
  username=customUser
  password=userPass
  grant_type=password
  client_id=java-application
```