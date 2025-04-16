package Checklist;


public class CheckList {
    public CheckList(){

    }
    public  void lst() {
        FileTaskPersistence ftp = new FileTaskPersistence();
        TaskManager tm = new TaskManager(ftp);
        TaskChecklistUI t = new TaskChecklistUI(tm);
    }
}