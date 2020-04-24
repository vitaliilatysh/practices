package practice6;

import java.util.Objects;

public class Tree<E extends Comparable<E>> {

    private static final String INDENT = "   ";

    private Node<E> root = null;

    void add(E[] elements) {
        for (E element : elements) {
            add(element);
        }
    }

    boolean add(E e) {
        return add(new Node<>(e), this.root) != null;
    }

    private Node<E> add(Node<E> newNode, Node<E> root) {
        if (this.root == null) {
            this.root = newNode;
            return this.root;
        }
        if (root.compareTo(newNode) == 0) {
            return null;
        } else if (root.compareTo(newNode) > 0) {
            if (root.right != null) {
                return add(newNode, root.right);
            } else {
                root.right = newNode;
                return root.right;
            }
        } else {
            if (root.left != null) {
                return add(newNode, root.left);
            } else {
                root.left = newNode;
                return root.left;
            }
        }
    }

    boolean remove(E value) {
        if(containsNode(value)){
            remove(root, value);
            return true;
        } else{
            return false;
        }
//        return remove(root, value) != null;
    }

    private Node<E> remove(Node<E> current, E value) {
        if (current == null) {
            return null;
        }

        if (value == current.getValue()) {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }

            E smallestValue = findSmallestValue(current.left);
            current.value = smallestValue;
            current.left = remove(current.left, smallestValue);
            return current;
        } else if(value.compareTo(current.getValue()) > 0) {
            current.left = remove(current.left, value);
            return current;
        } else {
            current.right = remove(current.right, value);
            return current;
        }
    }

    private E findSmallestValue(Node<E> root) {
        return root.right == null ? root.getValue() : findSmallestValue(root.right);
    }

    private boolean containsNodeRecursive(Node<E> current, E value) {
        if (current == null) {
            return false;
        }
        if (value == current.getValue()) {
            return true;
        }
        return value.compareTo(current.getValue())>0
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }

    public boolean containsNode(E value) {
        return containsNodeRecursive(root, value);
    }

    void print() {
        print(root, -1);
    }

    private void print(Node<E> root, int counter) {
        if (root != null) {
            ++counter;
            print(root.right, counter);
            System.out.println(new String(new char[counter]).replace("\0", INDENT) + root.value);
            print(root.left, counter);
        }
    }

    private static class Node<E extends Comparable<E>> implements Comparable<Node<E>> {
        E value;
        Node<E> left, right;

        Node(E value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public int compareTo(Node<E> node) {
            return getValue().compareTo(node.getValue());
        }

        E getValue() {
            return value;
        }
    }
}
