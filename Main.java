package Module11;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Main {

    public static void main(String[] args) {
        //1
        List<String> list = Arrays.asList("Ivan", "Alexander", "Peter", "Bryan", "Mike");
        for (int i = 0; i< list.size();i++){
            if (i % 2 == 0){
                System.out.print((i+1)+"."+list.get(i)+" ");
            }
        }
        //2
        List<String> sortedReverse = list.stream().map(a -> a.toUpperCase(Locale.ROOT)).sorted((o1, o2) -> -o1.compareTo(o2)).collect(Collectors.toList());
        System.out.println();
        System.out.println(sortedReverse);
        //3
        String[] array = new String[] {"1, 2, 0", "4, 5"};
        List<String> list1 = Arrays.asList("1, 2, 0", "4, 5");
        List<Integer> listOfNums = new ArrayList<>();
        for(int i = 0; i< array.length;i++){
            String[] arr = array[i].split(", ");
            for(int j = 0; j < arr.length;j++){
                String[] arr1 = arr[j].split(",");
                for(int k = 0; k < arr1.length; k++){
                    listOfNums.add(Integer.valueOf((arr1[k])));
                }
            }
        }
        List<Integer> sortedList = (List<Integer>) listOfNums.stream().sorted().collect(Collectors.toList());
        System.out.println(sortedList);
        //4
        System.out.println(generate(25214903917L, 11L, (Math.pow(2, 48))));
        Stream<String> s1 = Arrays.stream(new String[]{"A", "B", "C"});
        Stream<String> s2 = Arrays.stream(new String[]{"D", "E", "F"});
        System.out.println(zip(s1, s2).collect(Collectors.toList()));

    }

    static List<Long> generate(long seed, long c, double m){
        return Stream.iterate(seed, n -> seed * n + c).limit(100).collect(Collectors.toList());
    }
    //5
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second){
        List<T> firstList = first.collect(Collectors.toList());
        List<T> secondList = second.collect(Collectors.toList());

        int minSize;
        if (firstList.size() >= secondList.size()) {
            minSize = secondList.size();
            ;
        } else {
            minSize = firstList.size();
        }

        List<T> result = new ArrayList<>();

        for (int i = 0; i < minSize; i++) {
            result.add(firstList.get(i));
            result.add(secondList.get(i));
        }

        return result.stream();
    }
    }


