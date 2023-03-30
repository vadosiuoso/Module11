package Module11;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Ivan", "Alexander", "Peter", "Bryan", "Mike");
        System.out.println("Task 1:");
        System.out.println(getNamesWithOddIndexes(list));
        System.out.println("Task 2:");
        System.out.println(getListOfNamesInUpperCaseReverseSorted(list));
        String[] arr = {"1, 2, 0", "4, 5"};
        System.out.println("Task 3:");
        System.out.println(getSortedStringArrayOfNumbers(arr));
        Stream<Long> randomStream = getGeneratedStreamOfRandomNumbers(11L, (long) Math.pow(2, 48), 25214903917L);
        System.out.println("Task 4:");
        System.out.println(randomStream.limit(10).toList());
        Stream<Integer> first = Stream.of(1,2,4,5,3);
        Stream<Integer> second = Stream.of(4,5,3,3);
        System.out.println("Task 5:");
        Stream<Integer> zipped = zip(first, second);
        zipped.forEach(s -> System.out.format("%s ", s));



    }

    //Method 1
    static String getNamesWithOddIndexes(List<String> namesList) {
        IntStream oddIndexes = IntStream
                .iterate(0, i -> i + 2)
                .limit(namesList.size() % 2 == 0 ? (namesList.size() / 2) : (namesList.size()) / 2 + 1);
        return oddIndexes
                .mapToObj(i -> i + 1 + ". " + namesList.get(i))
                .collect(Collectors.joining(", "));
    }

    //Method 2
    static List<String> getListOfNamesInUpperCaseReverseSorted(List<String> nameList) {
        return nameList.stream().map(s -> s.toUpperCase(Locale.ROOT)).sorted(Comparator.reverseOrder()).toList();
    }

    //Method 3
    static String getSortedStringArrayOfNumbers(String[] array) {
        return Arrays.stream(array)
                .flatMap(s -> Arrays.stream(s.split(", ")))
                .map(Integer::parseInt)
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    //Method 4
    static Stream<Long> getGeneratedStreamOfRandomNumbers(long c, long m, long seed) {
        return Stream.iterate(seed, x -> (seed * x + c) % m);
    }

    //Method 5
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Stream.Builder<T> builder = Stream.builder();

        var firstIterator = first.iterator();
        var secondIterator = second.iterator();

        while (firstIterator.hasNext() && secondIterator.hasNext()) {
            builder.accept(firstIterator.next());
            builder.accept(secondIterator.next());
        }
        return builder.build();
    }
}

