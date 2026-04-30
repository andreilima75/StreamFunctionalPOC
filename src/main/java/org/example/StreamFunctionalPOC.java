package org.example;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
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
    }
}
