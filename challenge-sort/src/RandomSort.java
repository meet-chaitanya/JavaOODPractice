import java.util.*;

public class RandomSort implements SortingStrategy{
    private Random random;

    public RandomSort() {
        random = new Random();
    }
    @Override
    public void sort(List<String> list) {
        randomSort(list);
    }

    private void randomSort(List<String> list) {
        List<Map.Entry<String, Integer>> randomizedList = new ArrayList<>();

        for (String line: list) {
            randomizedList.add(new AbstractMap.SimpleEntry<>(line, random.nextInt()));
        }

        randomizedList.sort(Comparator.comparingInt(Map.Entry::getValue));

        list.clear();
        for (Map.Entry<String, Integer> entry: randomizedList) {
            list.add(entry.getKey());
        }
    }
}
