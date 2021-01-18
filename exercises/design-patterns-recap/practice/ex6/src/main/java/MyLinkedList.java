import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T>{

    private Element<T> head;
    private Element<T> tail;

    public MyLinkedList(){
    }

    public void add(T data){
        Element<T> newElement = new Element<>(data);
        if(head == null){
            head = newElement;
        }
        else{
            tail.setNext(newElement);
        }
        tail = newElement;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator<T>(head,tail);
    }

    static class MyLinkedListIterator<T> implements Iterator<T>{
        private Element<T> head;
        private Element<T> tail;
        private Element<T> curr;

        public MyLinkedListIterator(Element<T> head,Element<T> tail) {
            this.head = head;
            this.tail = tail;
            this.curr = head;
        }

        @Override
        public boolean hasNext() {
            return !curr.equals(tail);
        }

        @Override
        public T next() {
            return curr.getNext().getData();
        }
    }
}
