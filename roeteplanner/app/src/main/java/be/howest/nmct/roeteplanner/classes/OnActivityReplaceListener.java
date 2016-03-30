package be.howest.nmct.roeteplanner.classes;

public interface OnActivityReplaceListener<TClass> {

    void newActivity(Class<TClass> activity);
}
