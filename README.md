#   En este ejercicio de Java, se emula un buscador de archivos a traves de un arbol de directorios con varios hilos de ejecucion.
Se utiliza para ello principalmente la clase File y Thread, asi como la interfaz Runnable, con la cual implementamos el Buscador.
Ademas, implementamos una Cola de Carpetas, a fin de constituir una lista enlazada de carpetas inicial, de la cual los hilos tomaran un punto de partida para realizar la busqueda. Cuando la cola se vacia, los hilos terminan su ejecucion.
