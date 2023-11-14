package Ex1;

// Fig. 23.9: Ex1.Buffer.java
// Ex1.Buffer interface specifies methods called by Ex1.Producer and Ex1.Consumer.
public interface Buffer {
    // place int value into Ex1.Buffer
    public void blockingPut(int value) throws InterruptedException;

    // return int value from Ex1.Buffer
    public int blockingGet() throws InterruptedException;
}


/**************************************************************************
 * (C) Copyright 1992-2015 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
