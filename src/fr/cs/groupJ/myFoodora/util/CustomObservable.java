package fr.cs.groupJ.myFoodora.util;


public interface CustomObservable {
    void addObserver(CustomObserver observer);
    void deleteObserver(CustomObserver observer);
    void setChanged();
    void notifyObservers(CustomObservable this, Object arg);
}