import Products.IsShipable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingService {
    public double[] shipItems(List<IsShipable> items){
        if(items.isEmpty()){
            return new double[] { 0.0 , 0.0 };
        }
        System.out.println("** Shipment notice **");
        Map<String, Integer> itemCounts = new HashMap<>();
        Map<String, Double> itemWeights = new HashMap<>();
        double totalWeight = 0.0 ;
        for (IsShipable item : items) {
            String name = item.getName(); // cheese
            itemCounts.put(name, itemCounts.getOrDefault(name, 0) + 1);
            itemWeights.put(name, item.getWeight());
            totalWeight += item.getWeight();
        }
        for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
            String name = entry.getKey();
            int quantity = entry.getValue();
            double weight = itemWeights.get(name);
            System.out.println(quantity + "x " + name + " " + (int)(weight * 1000) + "g");
        }
        System.out.println("Total package weight " + totalWeight + "kg");
        // In the pdf I see that shipment is 30 dollars so i will assume it is 30 dollars
        double shipPrice = 30.0;
        return new double[] { totalWeight, shipPrice };
    }
}
