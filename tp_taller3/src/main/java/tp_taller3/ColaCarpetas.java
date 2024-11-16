package tp_taller3;

import java.io.File;
import java.util.LinkedList;

public class ColaCarpetas {

    private LinkedList<File> cola;

    public ColaCarpetas()
    {
        this.cola = new LinkedList<File>();
    }
    
    public int size()
    {
        return cola.size();
    }

    public synchronized void push(File dir)
    {
        /*
        try {
            wait();
        } catch (InterruptedException e) {
            
        }
        notify();
        */
        cola.add(dir);
    }

    public synchronized File pop()
    {
        while (cola.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                
            }
        }
        notify();
        return cola.removeFirst();
    }
}