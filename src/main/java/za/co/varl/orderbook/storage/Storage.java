package za.co.varl.orderbook.storage;

public interface Storage<T> {
    T add(T t);

    T edit(T t);

    T search(String fieldName);
}
