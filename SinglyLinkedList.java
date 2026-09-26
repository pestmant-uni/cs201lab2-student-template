import java.util.ArrayList;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap(){
        if (size > 1) {
            ArrayList<Node<E>> original = new ArrayList<>();

            Node<E> current = head;
            while (current != null) {
                original.add(current);
                current = current.getNext();
            }

            ArrayList<Node<E>> sorted = new ArrayList<>(original);

            // selection sort
            for (int i = 0; i < sorted.size() - 1; i++) {
                int smallest = i;

                for (int j = i + 1; j < sorted.size(); j++) {
                    if (sorted.get(j).getElement().compareTo(sorted.get(smallest).getElement()) < 0) {
                        smallest = j;
                    }
                }

                Node<E> temp = sorted.get(i);
                sorted.set(i, sorted.get(smallest));
                sorted.set(smallest, temp);
            }

            ArrayList<Node<E>> newOrder = new ArrayList<>();
            
            for (Node<E> node : original) {
                int index = 0;

                while (sorted.get(index) != node) {
                    index++;
                }
                int oppositeRank = sorted.size() - 1 - index;

                newOrder.add(sorted.get(oppositeRank));
            } 

            for (int i = 0; i < newOrder.size() - 1; i++) {
                newOrder.get(i).setNext(newOrder.get(i + 1));
            }
            newOrder.get(newOrder.size() - 1).setNext(null);

            head = newOrder.get(0);
            tail = newOrder.get(newOrder.size() - 1);
        }
    }
}

