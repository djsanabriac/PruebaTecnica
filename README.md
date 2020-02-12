# Prueba Tecnica AppGate - Cyxtera

En la carpeta executables se encuentran el respectivo ejecutable para cada una de las aplicaciones junto a su respectivo archivo de configuración (application.properties)

Basta con ejecutar cada uno de ellos

¡IMPORTANTE!

Para url-clasification-backend se debe crear ANTES DE EJECUTAR LA APLICACIÓN una base de datos MySQL, un usuario y asignarle permisos a este en dicha base de datos y luego colocar esa información en el archivo de configuración

Script para coincidir con archivo provisto(linux)
sudo mysql

mysql> create database url_clasification;
mysql> GRANT ALL PRIVILEGES ON url_clasification TO 'usertest'@'localhost' IDENTIFIED BY 'qwerty';

