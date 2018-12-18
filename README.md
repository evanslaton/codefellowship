# Lab 16: Spring Auth

## Challenge
* Build an app that allows users to create their profile on CodeFellowship.
* The site should have a splash page at the root route (/) that contains basic information about the site, as well as a link to the “sign up” page.
* An ApplicationUser should have a username, password (will be hashed using BCrypt), firstName, lastName, dateOfBirth, bio, and any other fields you think are useful.
* The site should allow users to create an ApplicationUser on the “sign up” page. (For now, there is no such thing as login.)
    * Your Controller should have an @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder; and use that to run bCryptPasswordEncoder.encode(password) before saving the password into the new user.
* The site should have a page which allows viewing the data about a single ApplicationUser, at a route like /users/{id}.
    * This should include a default profile picture, which is the same for every user, and their basic information.
* The site should be well-styled and attractive.
* The site should use reusable templates for its information. (At a minimum, it should have one Thymeleaf fragment that is used on multiple pages.)
* The site should have a non-whitelabel error handling page that lets the user know, at minimum, the error code and a brief message about what went wrong.
* The site should contain integration testing. At a minimum, there should be tests to ensure basic functionality for the splash page and the sign up page.

## Instructions
* Clone repo from github: https://github.com/evanslaton/codefellowship
* Create a database called codefellowship
* Create an application.properties file inside of the resources directory and add the following:
    * spring.datasource.platform=postgres
    * spring.datasource.url=jdbc:postgresql://localhost:<DATABASE>/codefellowship
    * spring.datasource.username=<USERNAME>
    * spring.datasource.password=<PASSWORD>
    * spring.jpa.generate-ddl=true // Add this line if it doesn't work the first time you run the app
    * spring.jpq.hibernate.ddl-auto=create
* Gradle command to run:  ./gradlew bootrun
