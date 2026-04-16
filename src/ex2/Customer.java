package ex2;

import java.util.Random;

// Customer implements Runnable so each customer can run as an independent thread,
// simulating concurrent shoppers joining the checkout queue.
public class Customer implements Runnable {
    private final int customerID;
    private final CheckoutQueue checkoutQueue;

    public Customer(int customerID, CheckoutQueue checkoutQueue) {
        this.customerID = customerID;
        this.checkoutQueue = checkoutQueue;
    }

    public int getCustomerID() {
        return customerID;
    }


    @Override
    public void run() {
        try {
            // simulate shopping time
            int shoppingTime = new Random().nextInt(3000) + 1000;
            System.out.println("Customer " + customerID + " is shopping for " + shoppingTime + "ms.");
            Thread.sleep(shoppingTime);

            // join the queue
            System.out.println("Customer " + customerID + " is ready to checkout.");
            checkoutQueue.enterQueue(this);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}