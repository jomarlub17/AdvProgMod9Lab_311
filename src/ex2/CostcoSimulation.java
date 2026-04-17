package ex2;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CostcoSimulation {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of customers: ");
        int customerCount = scanner.nextInt();

        System.out.print("Enter the number of cashiers: ");
        int cashierCount = scanner.nextInt();

        System.out.print("Enter the checkout queue capacity: ");
        int queueCapacity = scanner.nextInt();

        CheckoutQueue checkoutQueue = new CheckoutQueue(queueCapacity);
        SimulationStats stats = new SimulationStats();

        ExecutorService customerExecutor = Executors.newFixedThreadPool(customerCount);
        ExecutorService cashierExecutor = Executors.newFixedThreadPool(cashierCount);

        long startTime = System.currentTimeMillis();

        for (int cashierId = 1; cashierId <= cashierCount; cashierId++) {
            cashierExecutor.execute(new Cashier(cashierId, checkoutQueue, stats));
        }

        for (int customerId = 1; customerId <= customerCount; customerId++) {
            customerExecutor.execute(new Customer(customerId, checkoutQueue));
        }

        customerExecutor.shutdown();
        customerExecutor.awaitTermination(5, TimeUnit.MINUTES);

        for (int i = 0; i < cashierCount; i++) {
            checkoutQueue.addSentinel();
        }

        cashierExecutor.shutdown();
        cashierExecutor.awaitTermination(5, TimeUnit.MINUTES);

        long totalSimulationTime = System.currentTimeMillis() - startTime;

        System.out.println();
        System.out.println("Simulation complete.");
        System.out.println("Customers served: " + stats.getCustomersServed());
        System.out.printf("Average wait time: %.2fms%n", stats.getAverageWaitTime());
        System.out.printf("Average checkout time: %.2fms%n", stats.getAverageCheckoutTime());
        System.out.println("Longest wait time: " + stats.getLongestWaitTime() + "ms");
        System.out.println("Total simulation time: " + totalSimulationTime + "ms");
    }
}
