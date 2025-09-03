Instalar

javafx.21.0.8

https://gluonhq.com/products/javafx/

Si no corre, para resolver, haga clic en Ejecutar -> Editar configuraciones... y añada estas opciones de VM:

Windows
--module-path "\path\to\javafx-sdk-21.0.8\lib" --add-modules javafx.controls,javafx.fxml

Linux / MAC
--module-path /path/to/javafx-sdk-21.0.8/lib --add-modules javafx.controls,javafx.fxml

para más detalles:

https://openjfx.io/openjfx-docs/#install-javafx
