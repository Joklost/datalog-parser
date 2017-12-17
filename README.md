# datalog-parser
Web Application to interface with the IRIS Datalog engine.

## Enabling HTTPS
The following enviroment variables are required to use HTTPS, as well as a generating a KeyStore file.

`export PISSWORD=<password>`
`export KEYSTORE=~/KeyStore.jks`

Use the password set in the enviroment variable when asked for a password by the keytool.

`keytool -genkey -alias localhost -keyalg RSA -keystore KeyStore.jks -validity 365 -keysize 2048`
