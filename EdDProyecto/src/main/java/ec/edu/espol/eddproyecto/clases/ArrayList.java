package ec.edu.espol.eddproyecto.clases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class ArrayList<E> implements java.util.List<E>, Serializable{

    private E[] array;
    int n,capacity;
    
    public ArrayList(){
        capacity = 10;
        array = (E[]) new Object[capacity];
        n = 0;
    }    
    
    public int totalCapacity(){
        return capacity;
    }
    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public boolean contains(Object o) {
        
    for (int i = 0; i < size(); i++) {
        if (o.equals(array[i])) {
            return true;
        }
    }
    return false;
}

    

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < n;
            }

            @Override
            public E next() {
                E e = array[cursor];
                cursor++;
                return e;
            }
        };

        return it;
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(E e) {
        if (n==capacity){ 
            growArray(); 
        }  
        array[n] = e;
        n++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E get(int index) {
        return array[index];
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index>n)
            throw new IndexOutOfBoundsException(index);
        if (n==capacity){
            growArray();
        }    
        for (int i = n; i>index; i--){
            array[i] = array[i-1];
        }
        array[index] = element;
        n++;
    }
    
    private void growArray(){
        E[] new_array = (E[]) new Object[capacity*2];
        for(int i=0; i<n; i++){
            new_array[i] = array[i];
        }
        array = new_array;
        capacity = capacity*2;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index>=n)
            throw new IndexOutOfBoundsException("Index fuera de los límites: "+ index);
        if (n == 0){
            throw new NoSuchElementException("Arreglo sin elementos. No se puede eliminar elemento.");
        }
        E deletedElement = array[index];
        for(int i=0; i<n; i++){
            if (index<i){
                array[i] = array[i+1];
            }
            if (n == i){
                array[i] = null;
            }
        }
        --n;
        return deletedElement;
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    @Override
    public String toString() {
        int f=0;
        if(this.isEmpty()){
            return null;
        }
        String str = "{ ";
        for(E e:this){
            str+= e.toString() + " ";
            f++;
        }
        str += "}";
        return str;
    }
    public int p() {
        int f=0;
        if(this.isEmpty()){
            return 0;
        }
        String str = "{ ";
        for(E e:this){
            str+= e.toString() + " ";
            f++;
        }
        str += "}";
        return f;
    }
    
        public void serialize(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
            System.out.println("ArrayList serializada correctamente.");
        } catch (IOException e) {
            System.out.println("No se pudo serializar la lista");
        }
    }
    
    public static Object deserialize(String fileName) {
        ArrayList<Contact> deserializedList = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            deserializedList = (ArrayList) ois.readObject();
            System.out.println("ArrayList deserializada correctamente.");
            if (deserializedList == null){
                deserializedList = new ArrayList<>();
                return deserializedList;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("vacio");
        }
        return deserializedList;
    }
}
