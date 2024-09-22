# Spring-Boot-Oauth2_and_JWT
# OAuth 2.0 Login Flow

This application supports OAuth 2.0 login using Google. This document provides the steps for logging in with Google and receiving a JWT token for authenticated access.

## Prerequisites

- Ensure you have the application running locally.
- You need to have a Google account for testing.

## Steps to Log in with Google

1. **Initiate Login**:
    - Navigate to the following URL to start the login process with Google:
      ```
      http://localhost:8080/oauth2/authorization/google
      ```

2. **Google Login Page**:
    - You will be redirected to Google’s login page. Log in with your Google credentials.

3. **Grant Permissions**:
    - Grant the necessary permissions for the application to access your Google account information.

4. **Redirection Back to the Application**:
    - After successful authentication and permission grant, you will be redirected back to the application.

5. **JWT Token Generation**:
    - The application will process the authorization code, generate a JWT token, and redirect you to the client application with the JWT token appended as a URL parameter.

6. **Use the JWT Token**:
    - The client application can extract the JWT token from the URL and use it for authenticated API requests.

## Example of Redirected URL

After successful login and redirection, the URL will look like this:
http://google.com/?token=your_jwt_token

 Note :: you can change http://google.com/ to the client's url

## Steps to Log in with LinkedIn

1. **Initiate Login**:
   - Navigate to the following URL to start the login process with LinkedIn:
     ```
     http://localhost:8080/oauth2/authorization/linkedin
     ```

2. **LinkedIn Login Page**:
   - You will be redirected to LinkedIn’s login page. Log in with your LinkedIn credentials.

3. **Grant Permissions**:
   - Grant the necessary permissions for the application to access your LinkedIn account information.

4. **Redirection Back to the Application**:
   - After successful authentication and permission grant, you will be redirected back to the application.

5. **JWT Token Generation**:
   - The application will process the authorization code, generate a JWT token, and redirect you to the client application with the JWT token appended as a URL parameter.

6. **Use the JWT Token**:
   - The client application can extract the JWT token from the URL and use it for authenticated API requests.

## Example of Redirected URL

After successful login and redirection, the URL will look like this:
{{client_url}}?token=your_jwt_token

Link to postman documentation https://documenter.getpostman.com/view/19693532/2sAXqtc2VN