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
            ArrayList<Node<E>> sorted = new ArrayList<>();

            Node<E> current = head;
            while (current != null) {
                sorted.add(current);
                current = current.getNext();
            }

            // selection sort
            for (int i = 0; i < sorted.size() - 1; i++) {
                int smallest = i;

                for (int j = i + 1; j < sorted.size(); j++) {
                    if (sorted.get(j).getElement().compareTo(sorted.get(smallest).getElement()) < 0) {
                        smallest = j;
                    }
                }

                E temp = sorted.get(i).getElement();
                sorted.get(i).element = sorted.get(smallest).element;
                sorted.get(smallest).element = temp;
            }

            int left = 0;
            int right = sorted.size() - 1;

            while (left < right) {
                E temp = sorted.get(left).element;
                sorted.get(left).element = sorted.get(right).element;
                sorted.get(right).element = temp;

                left++;
                right--;
            }
        }
    }
}

