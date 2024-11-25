package com.example.taskk11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@SpringBootApplication
public class Taskk11Application {

    public static void main(String[] args) {
        SpringApplication.run(Taskk11Application.class, args);

                // Task 1:
                List<String> names = Arrays.asList("Ivan", "Anna", "Peter", "Maria", "John");
                String oddNames = oddIndexNames(names);
                System.out.println("task 1: " + oddNames);

                // Task 2:
                List<String> upperAndSorted = toUpperAndSortDesc(names);
                System.out.println("task 2: " + upperAndSorted);

                // Task 3:
                String[] array = {"1, 2, 0", "4, 5"};
                String sortedNumbers = extractAndSortNumbers(array);
                System.out.println("task 3: " + sortedNumbers);

                // Task 4:
                long a = 25214903917L;
                long c = 11L;
                long m = 1L << 48;
                long seed = 0L;
                System.out.println("task 4: ");
                linearCongruentialGenerator(seed, a, c, m)
                        .limit(10)
                        .forEach(System.out::println);

                // Task 5:
                Stream<String> first = Stream.of("A", "B", "C");
                Stream<String> second = Stream.of("1", "2", "3", "4");
                System.out.println("Task 5: ");
                zip(first, second).forEach(System.out::println);
            }

            public static String oddIndexNames(List<String> names) {
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < names.size(); i++) {
                    if ((i + 1) % 2 != 0) {
                        result.append(i + 1).append(". ").append(names.get(i)).append(", ");
                    }
                }
                if (!result.isEmpty()) {
                    result.setLength(result.length() - 2);
                }
                return result.toString();
            }

            public static List<String> toUpperAndSortDesc(List<String> strings) {
                return strings.stream()
                        .map(String::toUpperCase)
                        .sorted(Comparator.reverseOrder())
                        .toList();
            }
            public static String extractAndSortNumbers(String[] array) {
                return Arrays.stream(array)
                        .flatMap(str -> Arrays.stream(str.split(", ")))
                        .map(Integer::parseInt)
                        .sorted()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", "));
            }
            public static Stream<Long> linearCongruentialGenerator(long seed, long a, long c, long m) {
                return Stream.iterate(seed, x -> (a * x + c) % m);
            }
            public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
                Iterator<T> iterator1 = first.iterator();
                Iterator<T> iterator2 = second.iterator();
                Iterable<T> iterable = () -> new Iterator<>() {
                    private boolean toggle = true;
                    @Override
                    public boolean hasNext() {
                        return iterator1.hasNext() && iterator2.hasNext();
                    }
                    @Override
                    public T next() {
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }
                        toggle = !toggle;
                        return toggle ? iterator1.next() : iterator2.next();
                    }
                };
                return StreamSupport.stream(iterable.spliterator(), false);
            }
        }

