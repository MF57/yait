# yait

## Cloning and importing to IntelliJ
1. Clone it via console, not via IntelliJ
2. Open IntelliJ and click 'Open' and choose folder where repo have been cloned (it's name should be yait)
3. After project will open, click on the main module in 'Project' and click F4 to open Project Settings and go to Modules
4. Click '+' symbol and then Import Module and find and click build.gradle
5. In the popup make sure 'Use default gradle wrapper (recommended)' is selected.
6. Click Apply and then Ok and wait for a bit until gradle project will be imported
7. If everything is ok, .gradle and gradle folders should appear INSIDE webapp folder

## Running Web Application
1. Simply Run 'YaitApplication' class
2. If everything is ok, GET to the http://localhost:8080/api/hello should return "Hello World"

## LDAP SSL
```
openssl x509 -in <(openssl s_client -connect <host>:<port> -prexit 2>/dev/null) -out ~/example.crt
sudo keytool -importcert -file ~/example.crt -alias example -keystore $(/usr/libexec/java_home)/jre/lib/security/cacerts -storepass changeit

```