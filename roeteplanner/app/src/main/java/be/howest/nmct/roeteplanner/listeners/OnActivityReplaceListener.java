package be.howest.nmct.roeteplanner.listeners;

public interface OnActivityReplaceListener<TClass> {

    void newActivity(Class<TClass> activity);
}
