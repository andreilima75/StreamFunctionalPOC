package org.example;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class StreamFunctionalPOC {
    static void main() {


        Supplier<Integer> numeroAleatorio = () -> new Random().nextInt(100) + 1;
        Supplier<String> saudacao = () -> "Olá, mundo! " + LocalDateTime.now().getSecond() + "s";

        List<Integer> numeros = Stream.generate(numeroAleatorio)
                .limit(10)
                .toList();

        System.out.println("Números gerados: " + numeros);

        System.out.println(saudacao.get());

        Consumer<Integer> imprimirDobro = n -> System.out.println("Dobro de " + n + " = " + (n * 2));

        numeros.forEach(imprimirDobro);

        UnaryOperator<Integer> quadrado = x -> x * x;

        BinaryOperator<Integer> soma = Integer::sum;

        List<Integer> quadrados = numeros.stream()
                .map(quadrado)
                .toList();

        Optional<Integer> somaDosQuadrados = quadrados.stream()
                .reduce(soma);

        somaDosQuadrados.ifPresent(resultado ->
                System.out.println("Soma dos quadrados = " + resultado)
        );

        Consumer<Integer> imprimir = System.out::println;
        UnaryOperator<Integer> incrementar = x -> x + 1;

        int resultado = Stream.generate(numeroAleatorio)
                .limit(15)
                .map(incrementar)
                .map(quadrado)
                .filter(n -> n % 2 == 0)
                .peek(imprimir)
                .reduce(0, soma);

        System.out.println("Soma total: " + resultado);

        List<String> nomes = Arrays.asList("ana", "bruno", "carla", "daniel", "elisa");
        Consumer<String> imprimir2 = System.out::println;
        UnaryOperator<String> maiuscula = String::toUpperCase;
        String resultado2 = nomes.stream()
                .map(maiuscula)
                .peek(imprimir2)
                .reduce("", (a, b) -> a + b + " | ", (a, b) -> a + b);

        System.out.println("Resultado: " + resultado2);

        Supplier<Integer> random = () -> (int) (Math.random() * 50) + 1;
        UnaryOperator<Integer> aoCubo = x -> x * x * x;
        Consumer<Integer> mostrar = n -> System.out.printf("%d ", n);

        List<Integer> resultado3 = Stream.generate(random)
                .limit(8)
                .map(aoCubo)
                .peek(mostrar)
                .reduce(List.of(),
                        (list, num) -> {
                            List<Integer> nova = new ArrayList<>(list);
                            nova.add(num);
                            return nova;
                        },
                        (list1, list2) -> {
                            List<Integer> nova = new ArrayList<>(list1);
                            nova.addAll(list2);
                            return nova;
                        });

        System.out.println("Resultado final: " + resultado3);
    }
}
