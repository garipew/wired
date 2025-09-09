# Wired
Wired is a url-shortener made with Spring Boot.

This is a **Minimum Viable Product** (MVP) meant to be a learning exercise, **not a production-ready product**.

## Run it locally!
Run wired locally with gradle:
```
gradle bootRun
```
This will start the app in "http://localhost:8080/".

Send a POST-request to this route with the url in the body, like in the example:
```
# POST-request to http://localhost:8080/
{
"url" : "http://test.com/this/is/a/test/url"
}
```
Wired will answer with
```
# Example answer from Wired
{
"url" : "http://test.com/this/is/a/test/url",
"shortUrl" : "Z2FyZ2l0",
"expirationTime" : "2025-12-31T00:00:00.00"
}
```
When acessing this newly created short url (in "http://localhost:8080/Z2FyZ2l0"), wired will redirect you to the original url.
