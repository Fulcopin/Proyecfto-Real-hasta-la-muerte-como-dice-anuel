/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.eddproyecto.clases;

import java.io.File;
import java.io.Serializable;


public class Photo implements Serializable{

    private String name;
    private File file;
   

    public Photo(String name, File file) {
        this.name = name;
        this.file = file;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

  

    @Override
    public String toString() {
        return "Photo{" +
                "name='" + name + '\'' +
                ", file=" + file  +
                '}';
    }
}
