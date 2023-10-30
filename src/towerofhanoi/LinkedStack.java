package towerofhanoi;

import stack.StackInterface;
import java.util.EmptyStackException;

// -------------------------------------------------------------------------
/**
 * This class
 * represents the disk objects that we will be using to solve the tower of hanoi
 * recursion problem. The LinkedStack class offers an implementation of a
 * generic stack data structure using singly linked nodes. This structure is
 * efficient in terms of memory as nodes are created upon demand and no space is
 * wasted. Operations such as push, pop, and peek are implemented as per the
 * standard stack behavior. Moreover, auxiliary methods such as clear, size, and
 * isEmpty are provided to offer more functionality and ease of use. The
 * implementation guarantees constant time O(1) for its basic operations, making
 * it an optimal choice for stack-based algorithms.
 * 
 * @author cooln
 * @version Oct 7, 2023
 * @param <T>
 *            Generic to be stored with the parameter
 */
public class LinkedStack<T>
    implements StackInterface<T>
{

    private Node topNode;
    private int size;

    /**
     * Clears the entire stack, removing all entries. This method sets the top
     * node to null, effectively dereferencing the entire chain of nodes, making
     * them available for garbage collection. This operation ensures that the
     * stack is entirely empty post-execution.
     */
    public LinkedStack()
    {
        topNode = null;
    }

    // ~ private class ........................................................
    /**
     * class only meant to be used in the LinkedStack class. allows the
     * LinkedStack class to operate as a stack using a linked list structure
     * 
     * @author cooln
     * @version Oct 7, 2023
     */
    private class Node
    {
        private T data;
        private Node nextNode;

        /**
         * Node constructor that takes data only and sets next to null.
         * 
         * @param data
         *            to be implemented for the node
         */
        public Node(T data)
        {
            this(data, null);
        }


        /**
         * Node constructor that takes both data and the nextNode link.
         * 
         * @param data
         *            data for the next node
         * @param nextNode
         *            the next link for the node
         */
        public Node(T data, Node nextNode)
        {
            this.data = data;
            this.setNextNode(nextNode);
        }


        /**
         * sets the next node
         * 
         * @param nextNode
         *            the next link in the chain
         */
        public void setNextNode(Node nextNode)
        {
            this.nextNode = nextNode;
        }


        /**
         * getter method for the next link in the chain
         * 
         * @return the next node in the chain
         */
        public Node getNextNode()
        {
            return this.nextNode;
        }


        /**
         * getter method for the data in a particular node
         * 
         * @return the data in that node
         */
        public T getData()
        {
            return this.data;
        }
    }

    /**
     * Clears the entire stack, removing all entries. This method sets the top
     * node to null, effectively dereferencing the entire chain of nodes, making
     * them available for garbage collection. This operation ensures that the
     * stack is entirely empty post-execution.
     */
    @Override
    public void clear()
    {
        topNode = null;
        size = 0;
    }


    /**
     * Returns the current size of the stack, representing the number of
     * elements stored within. This provides a direct count of how many items
     * are currently pushed onto the stack.
     *
     * @return The total number of elements currently in the stack.
     */
    public int size()
    {
        return size;
    }


    /**
     * Determines if the stack is currently empty. An empty stack is one without
     * any elements, and this method offers a quick way to check the stack's
     * state.
     *
     * @return True if the stack has no elements; false otherwise.
     */
    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }


    /**
     * Retrieves the data stored in the top node of the stack without removing
     * it. This operation is useful when we want to inspect the top-most element
     * without modifying the stack.
     * 
     * @return The data held in the top node.
     * @throws EmptyStackException
     *             if the stack is currently empty.
     */
    @Override
    public T peek()
    {
        if (size == 0)
        {
            throw new EmptyStackException();
        }
        return topNode.getData();
    }


    /**
     * Removes the top-most node from the stack and returns its stored data.
     * This operation modifies the stack by decrementing its size and changing
     * the top node.
     * 
     * @return The data from the node that was on top.
     * @throws EmptyStackException
     *             if the stack is currently empty.
     */
    @Override
    public T pop()
    {
        if (size == 0)
        {
            throw new EmptyStackException();
        }

        Node currentNode = topNode;
        topNode = currentNode.getNextNode();
        size--;

        return currentNode.data;
    }


    /**
     * Adds a new entry to the top of the stack. A new node containing the
     * provided data is created and added to the beginning of the stack. This
     * operation modifies the stack by incrementing its size and changing the
     * top node.
     *
     * @param anEntry
     *            The data to be added to the new top node of the stack.
     */
    @Override
    public void push(T anEntry)
    {
        Node newNode = new Node(anEntry);
        newNode.setNextNode(topNode);
        topNode = newNode;
        size++;
    }


    /**
     * Provides a string representation of the current state of the stack. This
     * method can be especially useful for debugging or for logging purposes. It
     * starts with the most recently added element (left-most in the string) and
     * progresses to the earliest added element.
     *
     * @return A string representation of the stack's content.
     */
    @Override
    public String toString()
    {
        StringBuilder stackString = new StringBuilder();
        Node currentNode = topNode;

        stackString.append("[");
        while (currentNode != null)
        {
            stackString.append(currentNode.getData().toString());
            currentNode = currentNode.getNextNode();

            if (currentNode != null)
            {
                stackString.append(", ");
            }
        }
        stackString.append("]");

        return stackString.toString();
    }
}
