## CS 122B Project 2 Form example

This example shows how HTML <form> works to send user input to backend.

### To run this example: 
1. clone this repository using `git clone https://github.com/UCI-Chenli-teaching/project2-form-example.git`
2. open Eclipse -> File -> import -> under "Maven" -> "Existing Maven Projects" -> Click "Finish".
3. For "Root Directory", click "Browse" and select this repository's folder. Click "Finish".
4. In "Java Resources" folder, open `Form.java`. Change the mysql username and password and make sure you have the `moviedb` database.
5. You can run this project on Tomcat now.

### Brief Explanation
`Form.java` is a Java servlet that retrieves the data from HTML <form> and then talks to the database and generates the result HTML <table>

