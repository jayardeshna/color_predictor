import java.util.ArrayList;
import java.util.List;

public class ColorPrediction {

    public static double calculateColorDistance(int[] rgb1, int[] rgb2) {
        return Math.sqrt(Math.pow(rgb1[0] - rgb2[0], 2) + Math.pow(rgb1[1] - rgb2[1], 2) + Math.pow(rgb1[2] - rgb2[2], 2));
    }

    public static List<Customer> predictCustomersForNewColor(int[] newColor, List<Preference> preferences) {
        // Define distance thresholds based on ratings
        double threshold = 0;
        List<Customer> matchingCustomers = new ArrayList<>();
        // Determine the threshold based on customer rating
        for (Preference pref : preferences) {
            if (pref.getRating() == 5) threshold = 75;
            else if (pref.getRating() == 3) threshold = 37.5;
            else if (pref.getRating() == 2) threshold = 18.75;
            else if (pref.getRating() == 1) threshold = 7.5;
            else continue;

            double distance = calculateColorDistance(newColor, pref.getRgb());

            if (distance <= threshold) {
                Customer customer = new Customer(pref.getCustomerId(), "Customer " + pref.getCustomerId());
                matchingCustomers.add(customer);
            }
        }

        return matchingCustomers;
    }

}
