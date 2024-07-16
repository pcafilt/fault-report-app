# Fault Reporting WebApp

### Reference Documentation

For further reference, please consider the following sections:

* [Introduction]()
* [Features]()
* [Technologies Used]()
* [Configuration]()
* [Usage]()
## Introduction
The Fault Reporting Web Application is the basis for a web application
that will allow individuals living within a city to report and track 
various types of faults they encounter randomly in their everyday life
(problems with dangerously exposed electricity equipment, 
water grid failures, damaged road infrastructure, 
and urban pollution) 
The user completes a minimal fault report and is able 
to view and manage all personal submissions. It is assumed that
the user has received authentication credentials by the app provider,
earlier and there exist users with administrator priviledges. 

## Features
- User authentication
- User authorization (2 roles, USER and ADMIN)
- Fault reporting with location, type, description, observation time (time of reporting), and optionally photo
- Dynamic background image change depending on the fault type
- User can view all faults reported personally
- Admin can filter fault reports by fault type and delete individual reports
- Responsive design with a mobile-friendly layout

## Technologies Used
- **Backend:** Spring Boot, Hibernate, JPA
- **Frontend:** Thymeleaf, HTML, CSS, JavaScript
- **Database:**  SQL
- **Security:** Spring Security with password encoding
- **Others:** Gradle, Hikari, Lombok 

## Configuration
Set up a database, listing your own connection credentials in the 
applications.properties. Create a table of users with fields
"username" and "password" and populate the users table to simulate the use of the app by different users.
Create a table of user_roles with a foreign "id" key and a "role" column with entries "USER" and "ADMIN" and join the two tables.
Please be aware that the passwords would need to be stored encrypted.


## Usage
1. **Register and login:**
    - Open your browser and navigate to `http://localhost:8080`.
    - Log in with the credentials created above.

2. **Report a fault:**
    - Fill in the required details and submit the form.

3. **View your reports:**
    - Navigate to "My Reports" by clicking the 
    hamburger button to view all faults reported by the
   specific user. In case you logged in as an admin, you can view all reports
   that have been submitted by the different users of the app.
