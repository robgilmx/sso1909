Ksquare SSO 
===========

For this project to work you will need a Postgres database, the default configuration is: 
* A localhost server running in port 5432.
* A database named "ksso".
* An postgres user named "ssomng" with the following password: "@qr3&wrNXNU!J7P("
* This database configurations can be changed in the "application.properties" file.

Running the Project:
====================

To start the project just enter de following command in your terminal/console. (You need to have Maven installed in the host) 

mvn spring-boot:run

mvn spring-boot:run -Dspring-boot.run.arguments="--spring.datasource.url=jdbc:postgresql://[database_url_port]/[database_name],[other_spring_boot_args]"

or 

java -jar target/sso1909-0.0.1-SNAPSHOT.war -Dspring-boot.run.arguments="--spring.datasource.url=jdbc:postgresql://[database_url_port]/[database_name],[other_spring_boot_args]"

example:
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.datasource.url=jdbc:postgresql://192.168.241.0/ksso"

database variables: 
--spring.datasource.url,
--spring.datasource.username,
--spring.datasource.password,
--spring.datasource.platform

Running the Project with docker:
====================
Download the repository: docker pull ksquarergil/ks-sso

Docker Run Example:  docker run -p 8080:8080 -it ksquarergil/ks-sso

Run's environment variables (with default values): 

* datasource_url=jdbc:postgresql://localhost:5432/ksso
* datasource_usr=ssomng
* datasource_psw=@qr3&wrNXNU!J7P(
* datasource_ptf=postgres
* server_port=8080

Using the app:
=============

* Test client id: defaultclient
* Test client secret: KsquareChangeThis
* Test system user: admin
* Test system user password: KsquareChangeThisPassword


To get an access token you have to need to call the "oauth/authorize" page in your browser but specifying an application client, 
requesting it to lead you to the clients dashboard and to return an authorization code.

You can use the following format:
http://[host_url]/oauth/authorize?client_id=[client_id]&response_type=code&redirect_uri=[host_url]/dashboard

In your browser you should see an basic login page, use your system user credentials an access the system.

This will guide you to a permission page, this will give it accesses to utilize your endpoint to the extent you want it, 
once you selected the client permissions click the "Authorize" button. 

If everything is alright a new page should load and with a generated authorization code, it will show in the navigation 
bar Url in your browser and inside the page with a text like: "Your code is: XXXXXX".
Copy this code as we will need it for the next step.

Once you got your code, open Postman or the HTTP request client of your preference.
To get our authorization token we need to make a POST request to call the "oauth/token" page in your browser.
For security we need to add the following to the request:
* Uri Params
* Authentication headers

Uri params
----------

We need to specify an application client, the code we obtained in the previous step, any of the user's valid redirect Uri's 
and that the grant type you are using is an authorization code.
Note: One of the user's valid redirect Uri should be [host_url]/dashboard.

You can use the following format:
POST
http://[host_url]/oauth/token?client_id=[client_id]&code=[code]&grant_type=authorization_code&redirect_uri=[host_url]/dashboard

Authentication header
---------------------

We need a basic authentication header.
For this we are gonna use our client id and secret joined by a single colon (:) and encoded
with the Base64 scheme. 
For example:  client_id:secret    --- gets encoded to --->     Y2xpZW50X2lkOnBhc3N3b3Jk

Once we got this code we will add a header to our Post requests with a key called "Authorization" and its value will be 
as following: 
"Basic [base64_credentials_code]". 

Using the previous example your request header should look like this: 
"Authorization: Basic Y2xpZW50X2lkOnBhc3N3b3Jk"

Getting the Token
-----------------

Now you only need to send the POST request and if everything is correct 
you should receive a JSON response with your authorization token.
