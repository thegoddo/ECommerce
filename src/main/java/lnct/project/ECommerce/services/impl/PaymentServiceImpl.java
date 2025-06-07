package lnct.project.ECommerce.services.impl;

import lnct.project.ECommerce.payload.PaymentDetails;
import lnct.project.ECommerce.services.PaymentService;
import org.springframework.stereotype.Service;

import java.util.UUID; // Used for generating fake order IDs
import java.util.Random; // Used to simulate success/failure

@Service("fakePaymentService") // Give it a specific name for injection if you have multiple implementations
public class PaymentServiceImpl implements PaymentService { // You might want to rename this to FakePaymentServiceImpl

    // No need for Razorpay keys or objects for fake payments
    // @Value("${razorpay.key_id}")
    // private String KEY;
    // @Value("${razorpay.key_secret}")
    // private String SECRET_KEY;

    private final String CURRENCY = "INR"; // Still relevant for the "fake" order details
    private final String FAKE_KEY_ID = "rzp_test_fakeId123"; // A placeholder for the key ID

    private final Random random = new Random(); // For simulating success/failure

    @Override
    public PaymentDetails CreateOrder(Double amount) {
        // In a real scenario, you'd integrate with a payment gateway here.
        // For a college project, we'll simulate it.

        try {
            // Simulate a slight delay to mimic network latency
            Thread.sleep(500); // 0.5 seconds delay

            // Simulate success or failure (e.g., 90% success rate)
            boolean paymentSuccessful = random.nextInt(100) < 90; // 90% chance of success

            if (paymentSuccessful) {
                // Generate fake order details
                String fakeOrderId = "order_" + UUID.randomUUID().toString().replace("-", "").substring(0, 14); // A pseudo-unique ID
                Integer fakeAmount = (int) (amount * 100); // Amount in smallest unit (e.g., paise)

                // Prepare and return fake payment details
                return prepareFakeTransaction(fakeOrderId, fakeAmount, CURRENCY, FAKE_KEY_ID);
            } else {
                System.out.println("Simulated payment failed for amount: " + amount);
                // In a real application, you might throw a custom exception here
                // or return a PaymentDetails object indicating failure.
                return null; // Or return a PaymentDetails object with a status field indicating failure
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.err.println("Fake payment simulation interrupted: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Error during fake payment simulation: " + e.getMessage());
            return null;
        }
    }

    // Helper method to prepare the PaymentDetails object
    private PaymentDetails prepareFakeTransaction(String orderId, Integer amount, String currency, String keyId) {
        // Assuming your PaymentDetails class has a constructor that matches these parameters
        return new PaymentDetails(orderId, amount, currency, keyId);
    }
}