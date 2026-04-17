package ex2;

import java.util.concurrent.ThreadLocalRandom;

public class Customer implements Runnable {
    private final int customerID;
    private final CheckoutQueue checkoutQueue;
    private final boolean poisonPill;
    private long queueEnteredAt;
    private long checkoutStartedAt;
    private long checkoutFinishedAt;

    public Customer(int customerID, CheckoutQueue checkoutQueue) {
        this(customerID, checkoutQueue, false);
    }

    private Customer(int customerID, CheckoutQueue checkoutQueue, boolean poisonPill) {
        this.customerID = customerID;
        this.checkoutQueue = checkoutQueue;
        this.poisonPill = poisonPill;
    }

    public static Customer poisonPill() {
        return new Customer(-1, null, true);
    }

    public int getCustomerID() {
        return customerID;
    }

    public boolean isPoisonPill() {
        return poisonPill;
    }

    public void markQueueEntered() {
        queueEnteredAt = System.currentTimeMillis();
    }

    public void markCheckoutStarted() {
        checkoutStartedAt = System.currentTimeMillis();
    }

    public void markCheckoutFinished() {
        checkoutFinishedAt = System.currentTimeMillis();
    }

    public long getWaitTimeMillis() {
        return checkoutStartedAt - queueEnteredAt;
    }

    public long getCheckoutDurationMillis() {
        return checkoutFinishedAt - checkoutStartedAt;
    }

    public long getTotalQueueToExitMillis() {
        return checkoutFinishedAt - queueEnteredAt;
    }

    @Override
    public void run() {
        if (poisonPill) {
            return;
        }

        try {
            int shoppingTime = ThreadLocalRandom.current().nextInt(1000, 4001);
            System.out.println("Customer " + customerID + " is shopping for " + shoppingTime + "ms.");
            Thread.sleep(shoppingTime);

            System.out.println("Customer " + customerID + " is ready to checkout.");
            checkoutQueue.enterQueue(this);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
