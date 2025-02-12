package IniFlex;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Collectors;

public class main {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        System.out.println("Funcionarios após a remoção de João:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }

        System.out.println("\nAplicando aumento de 10% no salário dos funcionários:");
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salarioAtual = funcionario.getSalario();
            BigDecimal aumento = salarioAtual.multiply(new BigDecimal("0.10"));
            funcionario.setSalario(salarioAtual.add(aumento));
        }

        System.out.println("\nFuncionarios após o aumento de 10%:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }

        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        System.out.println("\nFuncionários agrupados por função:");
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (Funcionario funcionario : entry.getValue()) {
                System.out.println("  " + funcionario);
            }
        }
        System.out.println("\nFuncionarios que fazem aniversario nos meses 10 e 12:");
        List<Funcionario> aniversariantesOutubroDezembro = funcionarios.stream()
                .filter(funcionario -> {
                    Month mesNacimento = funcionario.getDataNascimento().getMonth();
                    return mesNacimento == Month.OCTOBER || mesNacimento == Month.DECEMBER;
                })
                .collect(Collectors.toList());

        for (Funcionario funcionario : aniversariantesOutubroDezembro) {
            System.out.println(funcionario);
        }

        System.out.println(("\nFuncionario com a maior idade:"));
        Funcionario funcionarioMaisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElseThrow(() -> new RuntimeException("Lista de funcionarios vazia"));
        int idade = Period.between(funcionarioMaisVelho.getDataNascimento(), LocalDate.now()).getYears();

        System.out.println("Nome: " + funcionarioMaisVelho.getNome() + ", Idade: " + idade + " anos");

        System.out.println("\nFuncionarios ordenados por ordem alfabetica:");
        List<Funcionario> funcionariosOrdenados = funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .collect(Collectors.toList());

        for (Funcionario funcionario : funcionariosOrdenados) {
            System.out.println(funcionario);
        }

        System.out.println("\nTotal des salarios dos funcionarios:");
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        String totalSalariosFormatado = numberFormat.format(totalSalarios);

        System.out.println("Total: R$ " + totalSalariosFormatado);

        System.out.println("\nQunatos salarios mininos cada funcionario ganha (salario minimo de R$ 1212,00):");
        BigDecimal salarioMinino = new BigDecimal("1212.00");
         for (Funcionario funcionario : funcionarios) {
             BigDecimal salario = funcionario.getSalario();
             BigDecimal salariosMinimos = salario.divide(salarioMinino, 2, RoundingMode.HALF_UP);

             NumberFormat formatador = NumberFormat.getInstance(new Locale("pt", "BR"));
             formatador.setMinimumFractionDigits(2);
             formatador.setMaximumFractionDigits(2);
             String salariosMinimosFormatado = formatador.format(salariosMinimos);

             System.out.println(funcionario.getNome() + ": " + salariosMinimosFormatado + " salários mínimos");
         }
    }



}
