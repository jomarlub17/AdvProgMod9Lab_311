package ex2;

import java.util.concurrent.ThreadLocalRandom;

public class Cashier implements Runnable {
    private final int cashierId;
    private final CheckoutQueue checkoutQueue;
    private final SimulationStats stats;

    public Cashier(int cashierId, CheckoutQueue checkoutQueue, SimulationStats stats) {
        this.cashierId = cashierId;
        this.checkoutQueue = checkoutQueue;
        this.stats = stats;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Customer customer = checkoutQueue.serveCustomer();

                if (customer.isPoisonPill()) {
                    System.out.println("Cashier " + cashierId + " is closing.");
                    return;
                }

                customer.markCheckoutStarted();
                int serviceTime = ThreadLocalRandom.current().nextInt(500, 2001);
                System.out.println("Cashier " + cashierId + " is serving customer "
                        + customer.getCustomerID() + " for " + serviceTime + "ms.");
                Thread.sleep(serviceTime);
                customer.markCheckoutFinished();

                stats.recordCustomer(customer);
                System.out.println("Cashier " + cashierId + " finished customer "
                        + customer.getCustomerID() + ". Wait: " + customer.getWaitTimeMillis()
                        + "ms, checkout time: " + customer.getCheckoutDurationMillis()
                        + "ms.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
