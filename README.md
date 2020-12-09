Para levantar la aplicacion es necesario ejecutar los siguientes comandos (se requiere java y maven):
1) Generar compilado:
cd C:\Users\mtubio\Documents\Projects\cash (dirigirse a la ruta correspondiente al proyecto de git)
mvn clean install

2) Ejecutar compilado:
cd C:\Users\mtubio\Documents\Projects\cash\target (dirigirse a la ruta en la que fue generado el compilado)
java -jar cash-1.0.0.jar
