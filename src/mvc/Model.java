package mvc;

import java.io.Serializable;

public class Model extends Publisher implements Serializable{
    protected boolean unsavedChanges = false;
    protected String filename = null;
    public void changed(){
        unsavedChanges = true;
        notifySubscribers();
    }

    public boolean getUnsavedChanges(){
        return unsavedChanges;
    }

    public String getFileName(){
        return filename;
    }

    public void setUnsavedChanges(boolean status){
        unsavedChanges = status;
    }

    public void setFileName(String fname){
        filename = fname;
    }
}
