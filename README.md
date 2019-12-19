A minimal, OAuth-protected gateway, using Google as it's auth provider and example.com as the sole
protected service to minimize dependencies.

## To run
(Not necessary but demonstrates that the gateway itself works)

The `client-id` and `client-secret` for the Google client registration will need to be replaced
for the gateway to work. If you do this, use `http://localhost:9999/login/oauth2/code/google` as 
the authorized redirect URI. Alternatively, set up a new auth provider and provide your own client
registration.

Then run
```bash
./gradlew bootRun
```

Browse to http://localhost:9999/example

## To test
Run
```
./gradlew test --info
```

Expected result:
* `testUnauthenticated` passes
* `testAuthenticated` fails