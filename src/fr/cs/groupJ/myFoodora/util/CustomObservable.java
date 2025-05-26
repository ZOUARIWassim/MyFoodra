package fr.cs.groupJ.myFoodora.util;


public interface CustomObservable {
    
    void addObserver(CustomObserver observer);
    void deleteObserver(CustomObserver observer);
    void setChanged();
    void notifyObserversCustomers(CustomObservable this, Object arg);
    void notifyObserversMeals(CustomObservable this, Object arg);
}