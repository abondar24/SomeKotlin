# Phone Book Server

Backend of phone book application

## Build and run

```
mvn clean install

java -jar <jar-locaiton>/phServer.jar
```

## Endpoints

### User

- Create User
```
POST localhost:8080/user 
{
  "username":some-username,
  "password":some-password
}

Authentication: not required

Response: 201
{ 
  "id": some-id, 
  "username":some-username,
  "password":password-hash
}

```

- Update User
```
PUT localhost:8080/user 
{ 
  "id": some-id, 
  "username":some-username,
  "password":password-hash
}

Authentication: required

Response: 200 - User updated
{ 
  "id": some-id, 
  "username":some-username,
  "password":password-hash
}


Response 401 - No jwt token provided

Response: 404 - User not found

```


- Find User
```
GET localhost:8080/user/{id} 

Authentication: required

Response: 302
{ 
  "id": {id}, 
  "username":some-username,
  "password":password-hash
}

Response 400 - Wrong id

Response 401 - No jwt token provided

Response: 404 - User not found
```

- Delete User
```
DELETE localhost:8080/user/{id} 

Authentication: required

Response: 200 - User deleted

Response 400 - Wrong id

Response 401 - No jwt token provided

Response: 404 - User not found
```

### Login

- Login User
```
POST localhost:8080/login 
{ 
  "id": some-id, 
  "username":some-username,
  "password":password-hash
}

Authentication: not required

Response: 201 - some-jwt-token

Response: 403 - Wrong credentials

Response: 404 - User not found

```

- Logout User
```
POST localhost:8080/logout/{id}

Authentication: required

Response: 200

Response: 404 - User not found

```


### Contact

- Create Contact
```
POST localhost:8080/contact/userId 
{
  "firstName":some-firstName,
  "lastName":some-lastName,
  "phone":some-phone,
  "email":some-email
}

Authentication: required

Response: 201
{ 
  "id":some-id 
  "firstName":some-firstName,
  "lastName":some-lastName,
  "phone":some-phone,
  "email":some-email
}

Response 400 - Wrong id

Response 401 - No jwt token provided

Response: 404 - User not found

```

- Update Contact
```
PUT localhost:8080/contact 
{ 
  "id":some-id 
  "firstName":some-firstName,
  "lastName":some-lastName,
  "phone":some-phone,
  "email":some-email
}


Authentication: required

Response: 200 - Contact updated

Response 401 - No jwt token provided

Response: 404 - Contact not found

```

- Update and merge Contact
```
PUT localhost:8080/contact/merge?id={id}&id1={id1} 
{ 
  "id":some-id 
  "firstName":some-firstName,
  "lastName":some-lastName,
  "phone":some-phone,
  "email":some-email
}


Authentication: required

Response: 200 - Contact updated

Response 401 - No jwt token provided

Response: 404 - Contact not found

```


- Find Contact
```
GET localhost:8080/contact/{id} 

Authentication: required

Response: 302
{ 
  "id":some-id 
  "firstName":some-firstName,
  "lastName":some-lastName,
  "phone":some-phone,
  "email":some-email
}

Response 400 - Wrong id

Response 401 - No jwt token provided

Response: 404 - Contact not found
```

- Delete Contact
```
DELETE localhost:8080/contact/{id} 

Authentication: required

Response: 200 - Contact deleted 

Response 400 - Wrong id

Response 401 - No jwt token provided

Response: 404 - Contact not found
```
