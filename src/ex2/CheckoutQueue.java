package ex2;

import java.util.concurrent.ArrayBlockingQueue;

public class CheckoutQueue {
    private final ArrayBlockingQueue<Customer> queue;

    public CheckoutQueue(int capacity) {
        queue = new ArrayBlockingQueue<>(capacity);
    }

    public void enterQueue(Customer customer) throws InterruptedException {
        customer.markQueueEntered();
        queue.put(customer);
        System.out.println("Customer " + customer.getCustomerID()
                + " entered the queue. Queue size: " + queue.size());
    }

    public void addSentinel() throws InterruptedException {
        queue.put(Customer.poisonPill());
    }

    public Customer serveCustomer() throws InterruptedException {
        return queue.take();
    }

    public int getQueueSize() {
        return queue.size();
    }
}
