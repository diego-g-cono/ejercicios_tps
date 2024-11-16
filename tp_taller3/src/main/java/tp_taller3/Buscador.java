package tp_taller3;

import java.io.File;

public class Buscador implements Runnable{
    String nombreArchivo;
    ColaCarpetas colaCarpetas;
    boolean encontrado = false;

    public Buscador(String nombreArchivo, ColaCarpetas cola, boolean encontrado)
    {
        this.nombreArchivo = nombreArchivo;
        this.colaCarpetas = cola;
        this.encontrado = encontrado;
    }

    @Override
    public void run() {
        while (this.colaCarpetas.size() != 0 && !encontrado) {
            try {
                File carpeta = colaCarpetas.pop();
                System.out.println("Cantidad de elementos en cola: " + colaCarpetas.size());
                buscar(carpeta, nombreArchivo);
            } catch (Exception e) {
                System.out.println("Hilo interrumpido");
            }
        }
    }
    
    public void buscar(File fichero, String nombreArchivo)
    {
        File[] contenido = fichero.listFiles();
        if (contenido != null && contenido.length != 0) {
            for (File elemento : contenido) {
                if (elemento.isDirectory()) {
                    buscar(elemento, nombreArchivo);
                } else if(elemento.getName().equals(nombreArchivo)) {
                    System.out.println("Archivo " + elemento.getName() + " encontrado en la carpeta " + elemento.getParentFile().getAbsolutePath() + ".\n");
                    this.encontrado = true;
                    // return;
                }
            }
        }
    }
   
}
