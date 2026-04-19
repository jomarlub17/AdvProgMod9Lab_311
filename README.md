
# Costco Checkout Simulator
### Producer-Consumer Pattern Lab | Java Multithreading

**Team Members:**
- [Alex] — Cashier
- [Jake] — CheckoutQueue
- [Jomar] — Customer

---

## Overview
This project transforms a basic Producer-Consumer example into a Costco checkout simulator. Customers (producers) shop and join a queue; cashiers (consumers) serve them. The simulation demonstrates thread synchronization, race condition prevention, and data-driven staffing decisions using `ArrayBlockingQueue`.

## Experiment Results
### 1. Understaffed Scenario (1 Cashier, 20 Customers, Queue Size 5)
- **Average Wait Time:** 9667.65ms
- **Observation:** The system experienced significant delays and long queues. A single cashier was unable to keep up with the incoming customers
### 2. Optimal Staffing (3 Cashiers, 20 Customers, Queue Size 5)
- **Average Wait Time:** 2522.65ms
- **Observation:** Increasing the number of cashiers significantly reduced wait times. The system handled customer flow much more efficiently.
### 3. Overstaffed Scenario (10 Cashiers, 20 Customers, Queue Size 5)
- **Average Wait Time:** 16.5ms
- **Observation:** Customers were served almost immediately, with virtually no waiting time. However, this setup uses significantly more resources.
### 4. Small Queue Capacity (3, Cashiers, 20 Customers, Queue Size 1)
- **Average Wait Time:** 2179.5ms
- **Observation:** Despite the small queue size causing more frequent blocking, the overall wait time remained similar to the scenario where queue size was 5
### 5. Large Queue Capacity (3 Cashiers, 20 Customers, Queue Size 10)
- **Average Wait Time:** 2236.2ms
- **Observation:** Increasing the queue size had minimal impact on perforcmace, suggesting that queue capacity is not a major limiting factor in this system.
### Analysis
The results show that increasing the number of cashiers greatly reduces customer wait time, especially when moving from 1 to 3 cashiers. However, further increasing to 10 cashiers results in only a small improvement relative to the additional cost.

Additionally, varying the queue size had little effect on overall performance. Both small and large queue capacities produced similar average wait times, indicating that the number of cashiers is a much more signifigant factor than queue length.
### Recommendation
Based on the results, **3 cashiers** provide the best balance between efficiency and cost. While 10 cashiers minimize wait time, the improvement is not substantial enough to justify the increased staffing cost of $18 per cashier per hour.

Therefore, for handling approximately 20 customers efficiently, a staffing level of 3 cashiers is recommended.

## Pull Requests
Merging directly to `main` is disabled — all changes must go through a pull
request. When opening one, you may see an **"Update branch"** button if the
base branch has new changes. Click it before requesting a review to keep your
branch up to date and avoid merge conflicts.


##  Submission Disclaimer — Read Before Submitting

Each team member must submit their **own fork** of this repository, not the shared repo.
