package ex2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class SimulationStats {
    private final AtomicInteger customersServed = new AtomicInteger();
    private final AtomicLong totalWaitTime = new AtomicLong();
    private final AtomicLong totalCheckoutTime = new AtomicLong();
    private final AtomicLong longestWaitTime = new AtomicLong();

    public void recordCustomer(Customer customer) {
        long waitTime = customer.getWaitTimeMillis();
        long checkoutTime = customer.getCheckoutDurationMillis();

        customersServed.incrementAndGet();
        totalWaitTime.addAndGet(waitTime);
        totalCheckoutTime.addAndGet(checkoutTime);
        longestWaitTime.accumulateAndGet(waitTime, Math::max);
    }

    public int getCustomersServed() {
        return customersServed.get();
    }

    public double getAverageWaitTime() {
        int served = customersServed.get();
        return served == 0 ? 0.0 : (double) totalWaitTime.get() / served;
    }

    public double getAverageCheckoutTime() {
        int served = customersServed.get();
        return served == 0 ? 0.0 : (double) totalCheckoutTime.get() / served;
    }

    public long getLongestWaitTime() {
        return longestWaitTime.get();
    }
}
