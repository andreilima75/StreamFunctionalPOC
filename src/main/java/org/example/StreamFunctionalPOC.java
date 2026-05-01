package org.example;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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
    }
}
