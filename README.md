# weatherapi
Steps to run the API
1. Download the project from git repo
2. cd to project dir
3.run command 'gradlew clean build'
4. run command 'gradlew bootRun / bR'
5. application should start without any errors in log.

Swagger-ui.html URL : http://localhost:8080/swagger-ui.html

h2 DB URL : http://localhost:8080/h2

Test the api using swagger ui by giving valid inputs. or use the below url in browser to test it.

http://localhost:8080/weather/v1/api/getWeatherInfo?city=testcity&state=testcode&apiKey=fakekey