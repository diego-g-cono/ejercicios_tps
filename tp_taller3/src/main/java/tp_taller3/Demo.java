package tp_taller3;

import java.io.File;

public class Demo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        System.out.print("Ingrese el nombre del archivo que desea buscar: \n");
        Scanner scanner = new Scanner(System.in);
        String nombreArchivo = scanner.nextLine();
        scanner.close();
        
        boolean encontrado = false;
        ColaCarpetas colaCarpetas = new ColaCarpetas();

        // Tomo carpeta raiz y creo una primera cola de carpetas con su contenido
        File ficheroRaiz = new File("/");
        File[] contenidoRaiz = ficheroRaiz.listFiles();
        for (File elemento : contenidoRaiz) {
            if (elemento.isDirectory()) {
                // Agrego las carpetas a la cola
                colaCarpetas.push(elemento);
            } else if(elemento.getName().equals(nombreArchivo)) {
                // Verifico si el archivo se encuentra en la raiz
                System.out.println("Archivo " + elemento.getName() + " encontrado en el directorio " + elemento.getParentFile().getAbsolutePath() + ".\n");
                return;
            }
        }

        System.out.println("Archivo a buscar: " + nombreArchivo);
        System.out.println("Directorio Raiz: " + ficheroRaiz.getName());
        System.out.println("Cola creada\n");
        System.out.println("Cantidad de carpetas: " + colaCarpetas.size());

        // Instancio un Buscador
        Buscador b = new Buscador(nombreArchivo, colaCarpetas, encontrado);

        // Instancio los hilos, les paso el buscador de argumento, y los inicio
        Thread[] hilos = new Thread[8];
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(b);
            hilos[i].start();
        }
        System.out.println("Cantidad de hilos: " + hilos.length);

        // Join threads (try catch)
        try {
            for (int i = 0; i < hilos.length; i++) {
                hilos[i].join();
            }
        } catch (Exception e) {
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        }

        if (!b.encontrado) {
            System.out.println("Archivo " + nombreArchivo + " no encontrado.\n");
        } else {
            System.out.println("Archivo " + nombreArchivo + " encontrado.\n");
        }
        /*
        */
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
