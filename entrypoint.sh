#!/bin/sh
# Obtenemos el ID de usuario pasado al comando docker run
USER_ID=${LOCAL_USER_ID:-9001}

# Cambiamos el propietario del directorio /app al de nuestro usuario
echo "Cambiando propietario de /app a $USER_ID"
chown -R $USER_ID:$USER_ID /app

# Ejecutamos el comando que se nos pase (en nuestro caso, será "mvn test")
exec gosu $USER_ID "$@"