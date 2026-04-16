package ex2;

import java.util.concurrent.ArrayBlockingQueue;

public class CheckoutQueue {

private final ArrayBlockingQueue<Customer> queue;

public CheckoutQueue(int capacity) {
    queue = new ArrayBlockingQueue<>(capacity);
    }

    public void enterQueue(Customer customer) throws InterruptedException{
        queue.put(customer);   // waits if queue is full
        System.out.println("Customer " + customer.getCustomerID() + " entered the queue.");
    }

    public Customer serveCustomer() throws InterruptedException{
    return queue.take();
    }

    public int getQueueSize() {
    return queue.size();
    }

}
