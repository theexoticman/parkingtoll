# Parking Toll Library

## Logging

Logging will be done using simple SLF4J. Using Simple Logging Facade has important benefit of not imposing a logging framework to the end-user. Actually, If no slf4j binding is found on the class path, then slf4j-api will default to a no operation discarding all logging request.