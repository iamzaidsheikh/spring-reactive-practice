package DataStructures;

public class DoublyLinkedList<T> {
  private int size = 0;
  private Node<T> head = null;
  private Node<T> tail = null;

  private class Node<T> {
    private T data;
    private Node<T> prev;
    private Node<T> next;

    public Node(T data, Node<T> prev, Node<T> next) {
      this.data = data;
      this.prev = prev;
      this.next = next;
    }

    @Override
    public String toString() {
      return data.toString();
    }
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public void clear() {
    Node<T> trav = head;
    while (trav != null) {
      Node<T> next = trav.next;
      trav.prev = trav.next = null;
      trav.data = null;
      trav = next;
    }
    head = tail = trav = null;
    this.size = 0;
  }

  public void add(T elem) {
    addLast(elem);
  }

  public void addFirst(T elem) {
    Node<T> node = new Node<T>(elem, null, null);
    if (isEmpty()) {
      head = tail = node;
    } else {
      head.prev = node;
      node.next = head;
      head = node;
    }
    size++;
  }

  public T peekFirst() {
    return head.data;
  }

  public T peekLast() {
    return tail.data;
  }

  public T removeAt(int index) {
    if (index < 0 || index >= size)
      throw new IllegalArgumentException();

    int i;
    Node<T> trav;

    if (index < size / 2) {
      for (i = 0, trav = head; i != index; i++) {
        trav = trav.next;
      }
    } else {
      for (i = size - 1, trav = tail; i != index; i--) {
        trav = trav.prev;
      }
    }
    return remove(trav);
  }

  public int indexOf(Object obj) {
    Node<T> trav = head;
    if(obj == null) {
      for(int index=0;trav != null;trav = trav.next, index++) {
        if(trav.data == null) 
          return index;
      }
    } else {
      for(int index=0;trav != null;trav = trav.next, index++) {
        if(obj.equals(trav.data)) {
          return index;
        }
      }
    }

    return -1;
  }

  public boolean contains(Object obj) {
    return indexOf(obj) != -1;
  }

  

  @Override
  public String toString() {
    if(isEmpty()) {
      return "[]";
    }else {
      String elems = "";
      var trav = head;
      while(trav!=null) {
        elems += trav.data + " ";
        trav=trav.next;
      }
      return elems;
    }
  }

  private void addLast(T elem) {
    if(isEmpty()) {
      head = tail = new Node<T>(elem, null, null);
    }else {
      tail.next = new Node<T>(elem, tail, null);
      tail = tail.next;  
    }
    size++;
  }

  private T remove(Node<T> node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
    
    T data = node.data;
    
    node.data = null;
    node = node.next = node.prev = null;

    size--;

    return data;
  }
}
