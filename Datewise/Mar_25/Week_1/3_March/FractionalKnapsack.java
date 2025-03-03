import java.util.Arrays;

// TC: O(nlogn)
// SC: O(1)

public class FractionalKnapsack {
    public static class Item {
        int weight;
        int value;
        double ratio;

        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
            this.ratio = (double) value / weight;
        }
    }

    public static void main(String[] args) {
        Item[] items = new Item[5];
        items[0] = new Item(10, 60);
        items[1] = new Item(20, 100);
        items[2] = new Item(50, 200);
        items[3] = new Item(40, 120);
        items[4] = new Item(30, 150);

        int capacity = 50;

        System.out.println(fractionalKnapsack(items, capacity));
    }

    public static double fractionalKnapsack(Item[] items, int capacity) {
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        int wsf = 0;
        double vsf = 0;

        for (int i = 0; i < items.length; i++) {
            if (wsf + items[i].weight <= capacity) {
                wsf += items[i].weight;
                vsf += items[i].value;
            } else {
                double remainingCapacity = capacity - wsf;
                vsf += remainingCapacity * items[i].ratio;
                break;
            }
        }

        return vsf;
    }
}
