# Lab 18: Spring Security against User Input

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
* Users should be able to to log in to your app.
    * Upon logging in, users should be taken to a /myprofile route that displays their information.
    * Ensure that your homepage, login, and registration routes are accessible to non-logged in users. All other routes should be limited to logged-in users.
    * When a user is logged in, the app should display the user’s username on every page
    * Ensure that user registration also logs users into your app automatically.
* Add a Post entity to your app.
    * A Post has a body and a createdAt timestamp.
    * A logged-in user should be able to create a Post, and a post should belong to the user that created it.
    * A user’s posts should be visible on their profile page.
* Ensure there is some way (like a users index page) that a user can discover other users on the service.
* On a user profile page that does NOT belong to the currently logged-in user, display a “Follow” button. When a user clicks that follow button, the logged-in user is now following the viewed-profile-page user.
* A user can visit a url (like /feed) to view all of the posts from the users that they follow.
    * Each post should have a link to the user profile of the user who wrote the post.

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
