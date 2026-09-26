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
    public void swap() {
        if (size <= 1) {
            return;
        }

        ArrayList<Node<E>> nodes = new ArrayList<>();

        Node<E> current = head;
        while (current != null) {
            nodes.add(current);
            current = current.getNext();
        }

        ArrayList<Node<E>> sorted = new ArrayList<>(nodes);

        mergeSort(sorted, 0, sorted.size() - 1);

        java.util.HashMap<Node<E>, Integer> rankMap = new java.util.HashMap<>();

        for (int i = 0; i < sorted.size(); i++) {
            rankMap.put(sorted.get(i), i);
        }

        ArrayList<Node<E>> newOrder = new ArrayList<>();

        for (Node<E> node : nodes) {
            int rank = rankMap.get(node);

            int oppositeRank = sorted.size() - 1 - rank;

            newOrder.add(sorted.get(oppositeRank));
        }

        for (int i = 0; i < newOrder.size() - 1; i++) {
            newOrder.get(i).setNext(newOrder.get(i + 1));
        }

        newOrder.get(newOrder.size() - 1).setNext(null);

        head = newOrder.get(0);
        tail = newOrder.get(newOrder.size() - 1);
    }

    private void mergeSort(ArrayList<Node<E>> list, int left, int right) {
        if (left >= right) {
            return;
        }

        int middle = (left + right) / 2;

        mergeSort(list, left, middle);
        mergeSort(list, middle + 1, right);

        merge(list, left, middle, right);
    }

    private void merge(ArrayList<Node<E>> list, int left, int middle, int right) {
        ArrayList<Node<E>> temp = new ArrayList<>();

        int i = left;
        int j = middle + 1;

        while (i <= middle && j <= right) {
            if (list.get(i).getElement()
                    .compareTo(list.get(j).getElement()) <= 0) {
                temp.add(list.get(i));
                i++;
            } else {
                temp.add(list.get(j));
                j++;
            }
        }

        while (i <= middle) {
            temp.add(list.get(i));
            i++;
        }

        while (j <= right) {
            temp.add(list.get(j));
            j++;
        }

        for (int k = 0; k < temp.size(); k++) {
            list.set(left + k, temp.get(k));
        }
    }
}

