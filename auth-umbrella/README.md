# spring-oauth2-ldap-jwt

fork at https://github.com/wmfairuz/spring-oauth2-ldap-jwt



keytool -genkeypair -alias sbarnacle-auth-key \
                    -keyalg RSA \
                    -keypass sbarnacle-secret-auth@secret-key \
                    -keystore sbarnacle-secret-auth.jks \
                    -storepass sbarnacle-secret-auth-secretstorexxx

keytool -list -rfc --keystore sbarnacle-secret-auth.jks | openssl x509 -inform pem -pubkey  -noout > public.txt

Enter keystore password: sbarnacle-secret-auth-secretstorexxx




$ export myapp.ldap.url=ldap://192.168.100.141:30540
$ mvn clean package
$ mvn spring-boot:run




$ curl --user 'auth-umbrella:auth-umbrella-pswd' -d 'grant_type=password&username=admin&password=password' -X POST http://localhost:8081/sbarnacle/oauth2/oauth/token






