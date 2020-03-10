
package persistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FilePersistence {

    public static void save (Object object_root, String file_path){
        if (object_root == null) return;
        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream (file_path));
            output.writeObject(object_root);
            output.close();
        } catch (Exception exception){
            System.out.println("Fail to save file: " + file_path + " --- " + exception);
        }
    }

    public static Object load (String file_path){
        try{
            ObjectInputStream input = new ObjectInputStream(new FileInputStream (file_path));
            Object object_root = input.readObject();
            return object_root;
        }catch (Exception exception){
            System.out.println("Fail to load file: " + file_path + " --- " + exception);
            return null;
        }
    }
    
}



